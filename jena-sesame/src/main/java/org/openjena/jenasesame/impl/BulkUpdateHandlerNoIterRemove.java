/*
 * (c) Copyright 2009 Talis Information Ltd.
 * (c) Copyright 2010 Epimorphics Ltd.
 * All rights reserved.
 * [See end of file]
 */

package org.openjena.jenasesame.impl;

import java.util.List ;

import org.openjena.atlas.iterator.Iter ;

import com.hp.hpl.jena.graph.BulkUpdateHandler ;
import com.hp.hpl.jena.graph.GraphEvents ;
import com.hp.hpl.jena.graph.Node ;
import com.hp.hpl.jena.graph.Triple ;
import com.hp.hpl.jena.graph.impl.GraphWithPerform ;
import com.hp.hpl.jena.graph.impl.SimpleBulkUpdateHandler ;
import com.hp.hpl.jena.util.iterator.ExtendedIterator ;

public class BulkUpdateHandlerNoIterRemove extends SimpleBulkUpdateHandler implements BulkUpdateHandler
{

    public BulkUpdateHandlerNoIterRemove(GraphWithPerform graph)
    {
        super(graph) ;
    }
    
    @Override
    public void remove(Node s, Node p, Node o)
    {
        s = fix(s) ;
        p = fix(p) ;
        o = fix(o) ;
        removeWorker(s,p,o) ;
        manager.notifyEvent( graph, GraphEvents.remove( s, p, o ) );
    }

    private static Node fix(Node n) { return (n!=null)? n : Node.ANY ; }
    
    @Override
    public void removeAll()
    {
         removeWorker(null, null, null) ;
         notifyRemoveAll(); 
    }

    private void removeWorker(Node s, Node p, Node o)
    {
        ExtendedIterator<Triple> iter = super.graph.find(s, p, o) ;
        List<Triple> x = Iter.toList(iter) ; 
        iter.close() ;
        
        for ( Triple t : x )
            graph.performDelete(t) ;
    }
}

/*
 * (c) Copyright 2009 Talis Information Ltd.
 * (c) Copyright 2010 Epimorphics Ltd.
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