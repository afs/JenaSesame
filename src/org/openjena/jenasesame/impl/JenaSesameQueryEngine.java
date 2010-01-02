/*
 * (c) Copyright 2009 Talis Information Ltd.
 * All rights reserved.
 * [See end of file]
 */

package org.openjena.jenasesame.impl;

import org.openrdf.query.MalformedQueryException ;
import org.openrdf.query.QueryEvaluationException ;
import org.openrdf.query.QueryLanguage ;
import org.openrdf.query.TupleQuery ;
import org.openrdf.query.TupleQueryResult ;
import org.openrdf.repository.RepositoryException ;

import com.hp.hpl.jena.query.Query ;
import com.hp.hpl.jena.sparql.ARQException ;
import com.hp.hpl.jena.sparql.algebra.Op ;
import com.hp.hpl.jena.sparql.core.Closeable ;
import com.hp.hpl.jena.sparql.core.DatasetGraph ;
import com.hp.hpl.jena.sparql.engine.Plan ;
import com.hp.hpl.jena.sparql.engine.PlanBase ;
import com.hp.hpl.jena.sparql.engine.QueryEngineBase ;
import com.hp.hpl.jena.sparql.engine.QueryIterator ;
import com.hp.hpl.jena.sparql.engine.binding.Binding ;
import com.hp.hpl.jena.sparql.util.Context ;

public class JenaSesameQueryEngine extends QueryEngineBase
{
    JenaSesameDatasetGraph jsdg ;
    Query query = null ;
    
    public JenaSesameQueryEngine(Query query, JenaSesameDatasetGraph dataset, Binding initial, Context context)
    {
        // Hide the dataset for now.
        super(query, dataset, initial, context) ;
        this.query = query ;
        this.jsdg = dataset ;
    }

    @Override
    public QueryIterator eval(Op op, DatasetGraph dsg, Binding initial, Context context)
    {
        // Ignore op!
        return null ;
    }
    
    @Override
    protected Op modifyOp(Op op)
    {
        // Ignore op!
        return op ;
    }
    
    // This is the one that matters! 
    @Override
    public Plan getPlan()
    {
        // Create query execution.
        
        try
        {
            TupleQuery tupleQuery = jsdg.getConnection().prepareTupleQuery(QueryLanguage.SPARQL, query.toString()) ;
            final TupleQueryResult result = tupleQuery.evaluate() ;
            final QueryIterator qIter = new QueryIteratorSesame(result) ;
            Closeable closeable = new Closeable() {
                @Override
                public void close()
                {
                    try { result.close() ; }
                    catch (QueryEvaluationException ex) { throw new ARQException(ex) ; }
                }
            } ;
            
            return new PlanBase(null, closeable){
                @Override
                protected QueryIterator iteratorOnce()
                {
                    return qIter ;
                }} ;
        } 
        catch (RepositoryException ex)      { throw new ARQException(ex) ; }
        catch (MalformedQueryException ex)  { throw new ARQException(ex) ; }
        catch (QueryEvaluationException ex) { throw new ARQException(ex) ; }
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