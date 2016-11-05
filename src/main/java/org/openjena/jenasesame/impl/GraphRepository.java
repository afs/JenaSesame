/*
 * (c) Copyright 2009 Talis Information Ltd.
 * All rights reserved.
 * [See end of file]
 */

package org.openjena.jenasesame.impl;

import org.apache.jena.graph.Capabilities;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.Triple;
import org.apache.jena.graph.impl.AllCapabilities;
import org.apache.jena.graph.impl.GraphBase;
import org.apache.jena.shared.JenaException;
import org.apache.jena.shared.PrefixMapping;
import org.apache.jena.sparql.core.DatasetPrefixStorage;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.util.iterator.NiceIterator;
import org.openrdf.model.*;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;

public class GraphRepository extends GraphBase {
    private RepositoryConnection connection;
    private ValueFactory         valueFactory;
    private Resource[]           contexts;

    public GraphRepository(RepositoryConnection connection, Resource context) {
        init(connection, context);
    }

    public GraphRepository(RepositoryConnection connection) {
        init(connection);
    }

    private void init(RepositoryConnection connection, Resource... contexts) {
        this.contexts = contexts;
        this.connection = connection;
        this.valueFactory = connection.getValueFactory();
    }

    @Override
    public void performAdd(Triple t) {
        Node s = t.getSubject();
        Node p = t.getPredicate();
        Node o = t.getObject();

        Resource subj = Convert.nodeToValueResource(valueFactory, s);
        URI pred = Convert.nodeURIToValue(valueFactory, p);
        Value obj = Convert.nodeToValue(valueFactory, o);

        try {
            Statement stmt = valueFactory.createStatement(subj, pred, obj);
            connection.add(stmt, contexts);
        }
        catch (RepositoryException ex) {
            ex.printStackTrace(System.err);
            throw new JenaException(ex);
        }
    }

    @Override
    public void performDelete(Triple t) {
        Node s = t.getSubject();
        Node p = t.getPredicate();
        Node o = t.getObject();

        Resource subj = Convert.nodeToValueResource(valueFactory, s);
        URI pred = Convert.nodeURIToValue(valueFactory, p);
        Value obj = Convert.nodeToValue(valueFactory, o);

        try {
            Statement stmt = valueFactory.createStatement(subj, pred, obj);
            connection.remove(stmt, contexts);
        }
        catch (RepositoryException ex) {
            ex.printStackTrace(System.err);
            throw new JenaException(ex);
        }
    }

    @Override
    protected ExtendedIterator<Triple> graphBaseFind(Triple m) {
        Node s = m.getMatchSubject();
        Node p = m.getMatchPredicate();
        Node o = m.getMatchObject();

        Resource subj = (s == null ? null : Convert.nodeToValueResource(valueFactory, s));
        URI pred = (p == null ? null : Convert.nodeURIToValue(valueFactory, p));
        Value obj = (o == null ? null : Convert.nodeToValue(valueFactory, o));

        try {
            RepositoryResult<Statement> iter1 = connection.getStatements(subj, pred, obj, true, contexts);
            return new RepositoryResultIterator(iter1);
        }
        catch (RepositoryException ex) {
            ex.printStackTrace(System.err);
            throw new JenaException(ex);
        }
    }

    static class RepositoryResultIterator extends NiceIterator<Triple> {
        RepositoryResult<Statement> iter;

        public RepositoryResultIterator(RepositoryResult<Statement> iter1) {
            iter = iter1;
        }

        @Override
        public void close() {
            try {
                iter.close();
            }
            catch (RepositoryException ex) {
                throw new JenaException(ex);
            }
        }

        @Override
        public boolean hasNext() {
            try {
                return iter.hasNext();
            }
            catch (RepositoryException ex) {
                throw new JenaException(ex);
            }
        }

        @Override
        public Triple next() {
            try {
                Statement stmt = iter.next();
                return Convert.statementToTriple(stmt);
            }
            catch (RepositoryException ex) {
                throw new JenaException(ex);
            }
        }

        @Override
        public void remove() {
            try {
                iter.remove();
            }
            catch (RepositoryException ex) {
                throw new JenaException(ex);
            }
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        }
        catch (RepositoryException ex) {
            ex.printStackTrace(System.err);
            throw new JenaException(ex);
        }
        super.close();
    }

    @Override
    protected PrefixMapping createPrefixMapping() {
        DatasetPrefixStorage dps = new JenaSesameDatasetPrefixStorage(connection);
        return dps.getPrefixMapping();
    }
    
    @Override
    public Capabilities getCapabilities() {
        if ( capabilities == null )
            capabilities = new AllCapabilities() {
            @Override public boolean handlesLiteralTyping() { return false; }
            @Override public boolean iteratorRemoveAllowed() { return false; }
        } ;
        return capabilities;
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