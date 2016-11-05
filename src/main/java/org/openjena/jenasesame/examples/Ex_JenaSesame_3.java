/*
 * (c) Copyright 2009 Talis Information Ltd.
 * All rights reserved.
 * [See end of file]
 */

package org.openjena.jenasesame.examples;

import java.io.IOException;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.sparql.util.QueryExecUtils;
import org.openjena.jenasesame.JenaSesame;
import org.openrdf.OpenRDFException;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;

/** Example: Run a SPARQL query on a Jena model backed by Sesame. Jena SPARQL execution. */
public class Ex_JenaSesame_3 extends Ex_JenaSesame_1 {
    
    public static void main(String... argv) throws OpenRDFException, IOException {
        System.out.println("**** Example: Run a SPARQL query on a Jena model backed by Sesame. Jena SPARQL execution.");
        new Ex_JenaSesame_3().run();
    }

    @Override
    protected void runExampleAction(Repository repo) throws OpenRDFException {
        RepositoryConnection conn = repo.getConnection();
        Model model = JenaSesame.createModel(conn);
        try {
            // Query model : execution is Jena, data is in Sesame.
            String queryString = "prefix : <http://example/> SELECT * { ?s ?p 123 }";
            Query query = QueryFactory.create(queryString);
            try (QueryExecution qExec = QueryExecutionFactory.create(query, model)) {
                QueryExecUtils.executeQuery(query, qExec);
            }
        }
        finally {
            conn.close();
        }
    }
}

/* (c) Copyright 2009 Talis Information Ltd. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met: 1. Redistributions of source
 * code must retain the above copyright notice, this list of conditions and the following
 * disclaimer. 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution. 3. The name of the author may
 * not be used to endorse or promote products derived from this software without specific
 * prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
 * AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */