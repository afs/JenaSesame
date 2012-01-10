/*
 * (c) Copyright 2009 Talis Information Ltd.
 * All rights reserved.
 * [See end of file]
 */

package org.openjena.sesamejena.impl;

import org.openrdf.model.Literal ;
import org.openrdf.model.impl.LiteralImpl ;

import com.hp.hpl.jena.graph.Node_Literal ;

public class LiteralJena extends LiteralImpl implements Literal
{
    //private Node_Literal nodeLiteral ;

    /**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -5733579375071044561L;

	public LiteralJena(Node_Literal nodeLiteral)
    {
        //this.nodeLiteral = nodeLiteral ;
        super.setDatatype(getDatatype()) ;
        super.setLabel(getLabel()) ;
        super.setLanguage(getLanguage()) ;
    }
    

//    @Override
//    public URI getDatatype()
//    {
//        return null ;
//    }
//
//    @Override
//    public String getLabel()
//    {
//        return null ;
//    }
//
//    @Override
//    public String getLanguage()
//    {
//        return null ;
//    }
//
//    @Override
//    public String stringValue()
//    {
//        return null ;
//    }
//
//    // 
//    
//    public boolean booleanValue() {
//        return XMLDatatypeUtil.parseBoolean(getLabel());
//    }
//
//    public byte byteValue() {
//        return XMLDatatypeUtil.parseByte(getLabel());
//    }
//
//    public short shortValue() {
//        return XMLDatatypeUtil.parseShort(getLabel());
//    }
//
//    public int intValue() {
//        return XMLDatatypeUtil.parseInt(getLabel());
//    }
//
//    public long longValue() {
//        return XMLDatatypeUtil.parseLong(getLabel());
//    }
//
//    public float floatValue() {
//        return XMLDatatypeUtil.parseFloat(getLabel());
//    }
//
//    public double doubleValue() {
//        return XMLDatatypeUtil.parseDouble(getLabel());
//    }
//
//    public BigInteger integerValue() {
//        return XMLDatatypeUtil.parseInteger(getLabel());
//    }
//
//    public BigDecimal decimalValue() {
//        return XMLDatatypeUtil.parseDecimal(getLabel());
//    }
//
//    public XMLGregorianCalendar calendarValue() {
//        return XMLDatatypeUtil.parseCalendar(getLabel());
//    }

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