/*
 * (c) Copyright 2009 Talis Information Ltd.
 * All rights reserved.
 * [See end of file]
 */

package org.openjena.jenasesame.impl;

import org.openrdf.model.Value ;
import org.openrdf.query.BindingSet ;
import org.openrdf.query.QueryEvaluationException ;
import org.openrdf.query.TupleQueryResult ;

import com.hp.hpl.jena.graph.Node ;
import com.hp.hpl.jena.sparql.ARQException ;
import com.hp.hpl.jena.sparql.core.Var ;
import com.hp.hpl.jena.sparql.engine.binding.Binding ;
import com.hp.hpl.jena.sparql.engine.binding.BindingMap ;
import com.hp.hpl.jena.sparql.engine.iterator.QueryIteratorBase ;
import com.hp.hpl.jena.sparql.serializer.SerializationContext ;
import com.hp.hpl.jena.sparql.util.IndentedWriter ;

public class QueryIteratorSesame extends QueryIteratorBase
{
    TupleQueryResult result ;
    
    public QueryIteratorSesame(TupleQueryResult result)
    {
        this.result = result ;
    }
    
    @Override
    protected void closeIterator()
    {
        try
        {
            result.close();
        } catch (QueryEvaluationException ex)
        {
            throw new ARQException(ex) ;
        } 
    }
    
    @Override
    protected boolean hasNextBinding()
    {
        try
        {
            return result.hasNext() ;
        } catch (QueryEvaluationException ex)
        {
            throw new ARQException(ex) ;
        }
    }
    
    @Override
    protected Binding moveToNextBinding()
    {
        try
        {
            BindingSet bindingSet = result.next();
            BindingMap arqBinding = new BindingMap() ;
            
            for ( String vn : result.getBindingNames() )
            {
                Value v = bindingSet.getValue(vn) ;
                Node n = Convert.valueToNode(v) ;
                arqBinding.add(Var.alloc(vn), n) ;
            }
            return arqBinding ;
        } catch (QueryEvaluationException ex)
        {
            throw new ARQException(ex) ;
        }
    }
    
    @Override
    public void output(IndentedWriter out, SerializationContext sCxt)
    {}
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