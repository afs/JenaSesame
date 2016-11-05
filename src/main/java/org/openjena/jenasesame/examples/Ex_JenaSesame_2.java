/*
 * (c) Copyright 2009 Talis Information Ltd.
 * All rights reserved.
 * [See end of file]
 */

package org.openjena.jenasesame.examples;

import java.io.IOException;

import org.apache.jena.query.*;
import org.apache.jena.sparql.util.QueryExecUtils;
import org.openjena.jenasesame.JenaSesame;
import org.openrdf.OpenRDFException;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;

/** Example: Run a SPARQL query, passing the whole query to Sesame and handle results in Jena. */
public class Ex_JenaSesame_2 extends Ex_JenaSesame_1 {
    public static void main(String... argv) throws OpenRDFException, IOException {
        System.out.println("** Example: Run a SPARQL query, passing the whole query to Sesame and handle results in Jena.");
        new Ex_JenaSesame_2().run();
    }
    
    @Override
    protected void runExampleAction(Repository repo) throws OpenRDFException {
        RepositoryConnection conn = repo.getConnection();
        try {
            // Query dataset : pass SPARQL through to Sesame.
            Dataset ds = JenaSesame.createDataset(conn);
            {
                try {
                    String queryString = "prefix : <http://example/> SELECT * { ?s ?p 123 }";
                    Query query = QueryFactory.create(queryString);
                    try (QueryExecution qExec = QueryExecutionFactory.create(query, ds)) {
                        QueryExecUtils.executeQuery(query, qExec);
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace(System.err);
                }
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