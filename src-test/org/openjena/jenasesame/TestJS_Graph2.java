/*
 * (c) Copyright 2009 Talis Information Ltd.
 * All rights reserved.
 * [See end of file]
 */

package org.openjena.jenasesame;

import org.openjena.jenasesame.impl.GraphRepository ;
import org.openrdf.repository.Repository ;
import org.openrdf.repository.RepositoryConnection ;
import org.openrdf.repository.sail.SailRepository ;
import org.openrdf.sail.memory.MemoryStore ;

import com.hp.hpl.jena.graph.Graph ;
import com.hp.hpl.jena.shared.JenaException ;
import com.hp.hpl.jena.tdb.junit.AbstractTestGraph2 ;

public class TestJS_Graph2 extends AbstractTestGraph2
{
//@Override public void testBulkAddWithReification() {}
    
    //@Override public void testIsomorphismFile() {}
    
    // Meaning less - graphs (and hence models) are not indpenedent of their datasets. 
    //@Override public void testCloseSetsIsClosed() {}

    @Override
    protected Graph emptyGraph()
    {
        return newGraph() ;
    }

    static Graph newGraph()
    {
        try {
            Repository repo = new SailRepository(new MemoryStore());
            repo.initialize();
            RepositoryConnection connection = repo.getConnection() ;
            return new GraphRepository(connection) ;
        } catch (Exception ex)
        {
            throw new JenaException(ex) ; 
        }
    }
    
    @Override
    protected void returnGraph(Graph g)
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