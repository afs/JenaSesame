/*
 * (c) Copyright 2009 Talis Information Ltd. All rights reserved. [See end of file]
 */

package org.openjena.sesamejena.impl;

import org.openrdf.model.BNode;
import org.openrdf.model.Literal;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.ValueFactoryBase;
import org.openrdf.model.impl.ValueFactoryImpl;

import com.hp.hpl.jena.rdf.model.AnonId;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

public class ValueFactoryJena
{
    private ValueFactory valueFactory = new ValueFactoryImpl();
    
    public BNode createBNode(Resource anonResource)
    {
        if(!anonResource.isAnon())
        {
            throw new IllegalArgumentException(
                    "Cannot create an OpenRDF Blank Node from this Jena Resource as it is not anonymous");
        }
        
        return valueFactory.createBNode(anonResource.getId().getLabelString());
    }
    
    public Literal createLiteral(Resource literalResource)
    {
        if(!literalResource.isLiteral())
        {
            throw new IllegalArgumentException(
                    "Cannot create an OpenRDF Literal from this Jena Resource as it is not a literal");
        }
        
        com.hp.hpl.jena.rdf.model.Literal asLiteral = literalResource.asLiteral();
        
        if(asLiteral.getLanguage() != null && !"".equals(asLiteral.getLanguage()))
        {
            return valueFactory.createLiteral(asLiteral.getString(), asLiteral.getLanguage());
        }
        else if(asLiteral.getDatatypeURI() == null)
        {
            return valueFactory.createLiteral(asLiteral.getString());
        }

	return null;    
    }
    
    public Statement createStatement(Resource subject, URI predicate, Value object)
    {
        return null;
    }
    
    public Statement createStatement(Resource subject, URI predicate, Value object, Resource context)
    {
        return null;
    }
    
    public URI createURI(String uri)
    {
        return null;
    }
    
    public URI createURI(String namespace, String localName)
    {
        return null;
    }
}

/*
 * (c) Copyright 2009 Talis Information Ltd. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met: 1. Redistributions of source code must retain the
 * above copyright notice, this list of conditions and the following disclaimer. 2. Redistributions
 * in binary form must reproduce the above copyright notice, this list of conditions and the
 * following disclaimer in the documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products derived from this
 * software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
