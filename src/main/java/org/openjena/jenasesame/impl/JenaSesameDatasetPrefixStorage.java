/*
 * (c) Copyright 2009 Talis Information Ltd.
 * (c) Copyright 2010, 2011, 2012 Epimorphics Ltd.
 * All rights reserved.
 * [See end of file]
 */

package org.openjena.jenasesame.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.jena.shared.JenaException;
import org.apache.jena.shared.PrefixMapping;
import org.apache.jena.sparql.core.DatasetPrefixStorage;
import org.apache.jena.sparql.graph.GraphPrefixesProjection;
import org.openrdf.model.Namespace;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;

public class JenaSesameDatasetPrefixStorage implements DatasetPrefixStorage {
    // Only one prefix mapping for the whole repository.
    private PrefixMapping        prefixMapping;
    private RepositoryConnection connection;

    public JenaSesameDatasetPrefixStorage(RepositoryConnection connection) {
        this.connection = connection;
        prefixMapping = new GraphPrefixesProjection(null, this);
    }

    private Set<String> getURIs() {
        try {
            RepositoryResult<Namespace> namespaces = connection.getNamespaces();
            Set<String> x = new HashSet<String>();
            for ( ; namespaces.hasNext() ; ) {
                Namespace ns = namespaces.next();
                x.add(ns.getName());
            }
            namespaces.close();
            return x;
        }
        catch (RepositoryException ex) {
            throw new JenaException(ex);
        }
    }

    private Set<Namespace> getNamespaces() {
        try {
            RepositoryResult<Namespace> namespaces = connection.getNamespaces();
            Set<Namespace> x = new HashSet<Namespace>();
            for ( ; namespaces.hasNext() ; ) {
                Namespace ns = namespaces.next();
                x.add(ns);
            }
            namespaces.close();
            return x;
        }
        catch (RepositoryException ex) {
            throw new JenaException(ex);
        }
    }

    @Override
    public PrefixMapping getPrefixMapping() {
        return prefixMapping;
    }

    @Override
    public PrefixMapping getPrefixMapping(String graphName) {
        return prefixMapping;
    }

    @Override
    public Set<String> graphNames() {

        return getURIs();
    }

    @Override
    public void insertPrefix(String graphName, String prefix, String uri) {
        try {
            connection.setNamespace(prefix, uri);
        }
        catch (RepositoryException ex) {
            throw new JenaException(ex);
        }
    }

    @Override
    public void loadPrefixMapping(String graphName, PrefixMapping pmap) {
        pmap.getNsPrefixMap().forEach((p,u)->{
            insertPrefix(graphName, p, u);
        }) ;
    }

    @Override
    public String readByURI(String graphName, String uriStr) {
        // Crude.
        Set<Namespace> x = getNamespaces();
        for ( Namespace ns : x ) {
            if ( ns.getName().equals(uriStr) )
                return ns.getPrefix();
        }
        return null;
    }

    @Override
    public String readPrefix(String graphName, String prefix) {
        try {
            return connection.getNamespace(prefix);
        }
        catch (RepositoryException ex) {
            throw new JenaException(ex);
        }
    }

    @Override
    public Map<String, String> readPrefixMap(String graphName) {
        Set<Namespace> x = getNamespaces();
        // prefix, uri.
        Map<String, String> z = new HashMap<String, String>();
        for ( Namespace ns : x )
            z.put(ns.getPrefix(), ns.getName());
        return z;
    }

    @Override
    public void removeFromPrefixMap(String graphName, String prefix) {
        try {
            connection.removeNamespace(prefix);
        }
        catch (RepositoryException ex) {
            throw new JenaException(ex);
        }
    }

    @Override
    public void removeAllFromPrefixMap(String graphName) {
        try {
            connection.clearNamespaces();
        }
        catch (RepositoryException ex) {
            throw new JenaException(ex);
        }
    }

    @Override
    public void close() {}

    @Override
    public void sync() {}

}

/*
 * (c) Copyright 2009 Talis Information Ltd.
 * (c) Copyright 2010, 2011, 2012 Epimorphics Ltd.
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