/*
 * (c) Copyright 2009 Talis Information Ltd.
 * All rights reserved.
 * [See end of file]
 */

package org.openjena.jenasesame.impl;

import org.openrdf.model.BNode ;
import org.openrdf.model.Literal ;
import org.openrdf.model.Resource ;
import org.openrdf.model.Statement ;
import org.openrdf.model.URI ;
import org.openrdf.model.Value ;
import org.openrdf.model.ValueFactory ;

import com.hp.hpl.jena.graph.Node ;
import com.hp.hpl.jena.graph.Triple ;
import com.hp.hpl.jena.rdf.model.AnonId ;

class Convert
{
    public static Node valueToNode(Value value)
    {
        if ( value instanceof Literal )
            return literalToNode((Literal)value) ; 
        if ( value instanceof URI )
            return uriToNode((URI)value) ; 
        if ( value instanceof BNode )
            return bnodeToNode((BNode)value) ; 
        throw new IllegalArgumentException("Not a concrete value") ;
    }
    
    public static Node bnodeToNode(BNode value)
    {
        return Node.createAnon(new AnonId(value.getID())) ;
    }

    public static Node uriToNode(URI value)
    {
        return Node.createURI(value.stringValue()) ;
    }

    public static Node literalToNode(Literal value)
    {
        if ( value.getLanguage() != null )
            return Node.createLiteral(value.getLabel(), value.getLanguage(), false) ;
        if ( value.getDatatype() != null )
            return Node.createLiteral(value.getLabel(),null, Node.getType(value.getDatatype().stringValue())) ;
        // Plain literal
        return Node.createLiteral(value.getLabel()) ;
    }

    public static Triple statementToTriple(Statement stmt)
    {
        Node s = Convert.valueToNode(stmt.getSubject()) ;
        Node p = Convert.uriToNode(stmt.getPredicate()) ;
        Node o = Convert.valueToNode(stmt.getObject()) ;            
        return new Triple(s,p,o) ;
    }
    
    // ----
    // Problems with the ValueFactory
    
    public static Value nodeToValue(ValueFactory factory, Node node)
    {
        if ( node.isLiteral() )
            return nodeLiteralToValue(factory, node) ;
        if ( node.isURI() )
            return nodeURIToValue(factory, node) ;
        if ( node.isBlank() )
            return nodeBlankToValue(factory, node) ;
        throw new IllegalArgumentException("Not a concrete node") ;
    }
    
    public static Resource nodeToValueResource(ValueFactory factory, Node node)
    {
        if ( node.isURI() )
            return nodeURIToValue(factory, node) ;
        if ( node.isBlank() )
            return nodeBlankToValue(factory, node) ;
        throw new IllegalArgumentException("Not a URI nor a blank node") ;
    }

    public static BNode nodeBlankToValue(ValueFactory factory, Node node)
    {
        return factory.createBNode(node.getBlankNodeLabel()) ;
    }

    public static URI nodeURIToValue(ValueFactory factory, Node node)
    {
        return factory.createURI(node.getURI()) ;
    }

    public static Value nodeLiteralToValue(ValueFactory factory, Node node)
    {
        if ( node.getLiteralDatatype() != null )
        {
            URI x = factory.createURI(node.getLiteralDatatypeURI()) ;
            return factory.createLiteral(node.getLiteralLexicalForm(), x) ;
        }
        if ( ! node.getLiteralLanguage().equals("") )
        {
            return factory.createLiteral(node.getLiteralLexicalForm(), node.getLiteralLanguage()) ;
        }
        
        return factory.createLiteral(node.getLiteralLexicalForm()) ;
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