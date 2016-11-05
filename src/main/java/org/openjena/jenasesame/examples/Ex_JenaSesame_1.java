/*
 * (c) Copyright 2009 Talis Information Ltd.
 * All rights reserved.
 * [See end of file]
 */

package org.openjena.jenasesame.examples;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.apache.jena.atlas.lib.StrUtils;
import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.openjena.jenasesame.JenaSesame;
import org.openrdf.OpenRDFException;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.rio.RDFFormat;
import org.openrdf.sail.memory.MemoryStore;

/** Example: Create an in-memory Sesame repository, load some RDF, access via Jena API. */
public class Ex_JenaSesame_1 {
    public static void main(String... argv) throws OpenRDFException, IOException {
        System.out.println("** Example: Create an in-memory Sesame repository, load some RDF, access via Jena API.");
        new Ex_JenaSesame_1().run();
    }

    public void run() throws OpenRDFException, IOException {
        Repository repo = new SailRepository(new MemoryStore());
        repo.initialize();
        try {
            loadSomeData(repo);
            runExampleAction(repo);
        } finally {
            repo.shutDown();
        }
    }
    
    protected void runExampleAction(Repository repo) throws OpenRDFException {
        RepositoryConnection conn = repo.getConnection();
        try {
            // Access via Jena API
            Resource subject = ResourceFactory.createResource("http://example/s1") ;
            Model model = JenaSesame.createModel(conn) ;
            StmtIterator sIter = model.listStatements(subject, null, (RDFNode)null);
            sIter.forEachRemaining(System.out::println);
            System.out.println("----");
            RDFDataMgr.write(System.out, model, Lang.TTL);
            System.out.println("----");
        } finally {
            conn.close();
        }
    }

    private static String DATA = StrUtils.strjoinNL
        ("@prefix :        <http://example/> ."
         ,":s2 :p 123 ."
         ,":s1 :p '123' ."
         );

    
    private static void loadSomeData(Repository repo) throws OpenRDFException, IOException {
        // Load some data using Sesame.
        RepositoryConnection c = repo.getConnection() ;
        try {
            Reader in = new StringReader(DATA); 
            c.add(in, "http://base/", RDFFormat.TURTLE) ;
        } finally { 
            c.close() ;
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