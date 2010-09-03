/*
 * (c) Copyright 2009 Talis Information Ltd.
 * (c) Copyright 2010 Epimorphics Ltd.
 * All rights reserved.
 * [See end of file]
 */

package org.openjena.jenasesame.impl;

import java.util.Iterator ;

import org.openrdf.model.Resource ;
import org.openrdf.repository.RepositoryConnection ;
import org.openrdf.repository.RepositoryException ;
import org.openrdf.repository.RepositoryResult ;

import com.hp.hpl.jena.graph.Graph ;
import com.hp.hpl.jena.graph.Node ;
import com.hp.hpl.jena.shared.Lock ;
import com.hp.hpl.jena.shared.LockMRSW ;
import com.hp.hpl.jena.sparql.ARQException ;
import com.hp.hpl.jena.sparql.core.DatasetGraph ;
import com.hp.hpl.jena.sparql.core.Quad ;
import com.hp.hpl.jena.sparql.util.Context ;

public class JenaSesameDatasetGraph implements DatasetGraph
{

    private Lock lock = new LockMRSW() ;
    private RepositoryConnection connection ;

    public JenaSesameDatasetGraph(RepositoryConnection connection)
    {
        this.connection = connection ;
    }
    
    public RepositoryConnection getConnection() { return connection ; } 
    
    @Override
    public void close()
    {
        try { connection.close() ; }
        catch (RepositoryException ex) { throw new ARQException(ex) ; }
    }

    @Override
    public boolean containsGraph(Node graphNode)
    {
        return false ;
    }

    @Override
    public Graph getDefaultGraph()
    {
        return new GraphRepository(connection) ; 
    }

    @Override
    public Graph getGraph(Node graphNode)
    {
        return new GraphRepository(connection, Convert.nodeToValueResource(connection.getValueFactory(), graphNode)) ;
    }

    @Override
    public Lock getLock()
    {
        return lock ;
    }

    @Override
    public Iterator<Node> listGraphNodes()
    {
        try
        {
            RepositoryResult<Resource> named = connection.getContextIDs() ;
            // Mask bnodes.
            // Map to Jena terms 
            
            
        } catch (RepositoryException ex)
        {
            ex.printStackTrace();
        }
        return null; // connection.getContextIDs() ;
    }

    @Override
    public long size()
    {
        return -1 ;
    }

    @Override
    public void add(Quad quad)
    {}

    @Override
    public void addGraph(Node graphName, Graph graph)
    {}

    @Override
    public boolean contains(Quad quad)
    {
        return false ;
    }

    @Override
    public boolean contains(Node g, Node s, Node p, Node o)
    {
        return false ;
    }

    @Override
    public void delete(Quad quad)
    {}

    @Override
    public void deleteAny(Node g, Node s, Node p, Node o)
    {}

    @Override
    public Iterator<Quad> find(Quad quad)
    {
        return null ;
    }

    @Override
    public Iterator<Quad> find(Node g, Node s, Node p, Node o)
    {
        return null ;
    }

    @Override
    public Context getContext()
    {
        return null ;
    }

    @Override
    public boolean isEmpty()
    {
        return false ;
    }

    @Override
    public void removeGraph(Node graphName)
    {}

    @Override
    public void setDefaultGraph(Graph g)
    {}
}

/*
 * (c) Copyright 2009 Talis Information Ltd.
 * (c) Copyright 2010 Epimorphics Ltd.
 * 
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