/*
 * (c) Copyright 2009 Talis Information Ltd.
 * All rights reserved.
 * [See end of file]
 */

package org.openjena.sesamejena.impl;

import org.openrdf.model.BNode ;
import org.openrdf.model.Literal ;
import org.openrdf.model.Resource ;
import org.openrdf.model.Statement ;
import org.openrdf.model.URI ;
import org.openrdf.model.Value ;
import org.openrdf.model.ValueFactory ;
import org.openrdf.model.impl.ValueFactoryBase ;

public class ValueFactoryJena extends ValueFactoryBase implements ValueFactory
{

    @Override
    public BNode createBNode(String nodeID)
    {
        return null ;
    }

    @Override
    public Literal createLiteral(String label)
    {
        return null ;
    }

    @Override
    public Literal createLiteral(String label, String language)
    {
        return null ;
    }

    @Override
    public Literal createLiteral(String label, URI datatype)
    {
        return null ;
    }

    @Override
    public Statement createStatement(Resource subject, URI predicate, Value object)
    {
        return null ;
    }

    @Override
    public Statement createStatement(Resource subject, URI predicate, Value object, Resource context)
    {
        return null ;
    }

    @Override
    public URI createURI(String uri)
    {
        return null ;
    }

    @Override
    public URI createURI(String namespace, String localName)
    {
        return null ;
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