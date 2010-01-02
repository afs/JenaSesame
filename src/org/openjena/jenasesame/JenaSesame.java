/*
 * (c) Copyright 2009 Talis Information Ltd.
 * All rights reserved.
 * [See end of file]
 */

package org.openjena.jenasesame;

import org.openjena.jenasesame.impl.GraphRepository ;
import org.openjena.jenasesame.impl.JenaSesameDatasetGraph ;
import org.openjena.jenasesame.impl.JenaSesameQueryEngineFactory ;
import org.openrdf.model.Resource ;
import org.openrdf.repository.RepositoryConnection ;

import com.hp.hpl.jena.graph.Graph ;
import com.hp.hpl.jena.query.Dataset ;
import com.hp.hpl.jena.rdf.model.Model ;
import com.hp.hpl.jena.rdf.model.ModelFactory ;
import com.hp.hpl.jena.sparql.core.DatasetGraph ;
import com.hp.hpl.jena.sparql.core.DatasetImpl ;
import com.hp.hpl.jena.sparql.engine.QueryEngineFactory ;
import com.hp.hpl.jena.sparql.engine.QueryEngineRegistry ;

/** Jena API over Sesame repository */
public class JenaSesame
{
    private static boolean initialized = false ;
    static QueryEngineFactory factory = new JenaSesameQueryEngineFactory() ;
    static { init () ; }
    
    public static void init()
    {
        if (initialized == true) return ;
        initialized = true ;
        QueryEngineRegistry.addFactory(factory) ;
    }

    public QueryEngineFactory getQueryEngineFactory() { return factory ; }
    
    /** Create a Model that is backed by a repository.
     *  The model is the triples seen with no specification of the context.  
     */
    public static Model createModel(RepositoryConnection connection)
    { 
        Graph graph =  new GraphRepository(connection) ;
        return ModelFactory.createModelForGraph(graph) ;
    }
    
    /** Create a dataset that is backed by a repository */ 
    public static Dataset createDataset(RepositoryConnection connection)
    {
        DatasetGraph dsg = new JenaSesameDatasetGraph(connection) ;
        return new DatasetImpl(dsg) ;
    }
    
    /** Create a model that is backed by a repository.
     *  The model is the triples seen with specificied context.
     */ 
    public static Model createModel(RepositoryConnection connection, Resource context)
    { 
        Graph graph =  new GraphRepository(connection, context) ;
        return ModelFactory.createModelForGraph(graph) ;
    }
}

/*
 * (c) Copyright 2009 Talis Information Ltd.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */