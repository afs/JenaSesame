/*
 * (c) Copyright 2009 Talis Information Ltd.
 * All rights reserved.
 * [See end of file]
 */

package examples;

import java.io.File ;

import org.openjena.jenasesame.JenaSesame ;

import org.openrdf.repository.Repository ;
import org.openrdf.repository.RepositoryConnection ;
import org.openrdf.repository.sail.SailRepository ;
import org.openrdf.rio.RDFFormat ;
import org.openrdf.sail.memory.MemoryStore ;

import com.hp.hpl.jena.query.Dataset ;
import com.hp.hpl.jena.query.Query ;
import com.hp.hpl.jena.query.QueryExecution ;
import com.hp.hpl.jena.query.QueryExecutionFactory ;
import com.hp.hpl.jena.query.QueryFactory ;
import com.hp.hpl.jena.rdf.model.Model ;
import com.hp.hpl.jena.rdf.model.Resource ;
import com.hp.hpl.jena.rdf.model.ResourceFactory ;
import com.hp.hpl.jena.sparql.util.QueryExecUtils ;

/** Example: Create an in-memory repository, load some RDF, and query it. */
public class Ex_JenaSesame_1
{
    public static void main(String...argv) throws Exception
    {
        Repository repo = new SailRepository(new MemoryStore());
        repo.initialize();
        
        // Load some data.
        RepositoryConnection c = repo.getConnection() ;
        c.add(new File("D.ttl"), "http://base/", RDFFormat.TURTLE) ;
        c.close() ;

        RepositoryConnection conn = repo.getConnection();
        
        Resource r = ResourceFactory.createResource("http://example/") ;
        
        //Model model = JenaSesame.createModel(conn) ;
        Dataset ds = JenaSesame.createDataset(conn) ;
        
        Model m = ds.getDefaultModel() ;
        System.out.println(m.getNsPrefixMap()) ;
        
        try {
            String queryString = "prefix : <http://example/> SELECT * { ?s ?p 123 }" ;
            Query query = QueryFactory.create(queryString) ;
            QueryExecution qExec = QueryExecutionFactory.create(query, ds) ;
            QueryExecUtils.executeQuery(query, qExec) ;
            qExec.close();
        } 
        catch (Exception ex)
        { ex.printStackTrace(System.err) ; }
        finally 
        {
            conn.close() ;
            repo.shutDown() ;
        }
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