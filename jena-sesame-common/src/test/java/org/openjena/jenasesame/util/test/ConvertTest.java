/**
 * 
 */
package org.openjena.jenasesame.util.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openjena.jenasesame.util.Convert;
import org.openrdf.model.BNode;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.ValueFactoryImpl;

import com.hp.hpl.jena.datatypes.TypeMapper;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.vocabulary.XSD;

/**
 * @author Peter Ansell p_ansell@yahoo.com
 * 
 */
public class ConvertTest
{
    
    private ValueFactory testValueFactory;
    private Model testModelEmpty;
    private Model testModelSingleURIURITypedLiteral;
    private Model testModelSingleURIURIPlainLiteral;
    private Model testModelSingleURIURILanguageLiteral;
    private Model testModelSingleURIURIBlankNode;
    private Model testModelSingleURIURIURI;
    private Resource testSubjectUri1;
    private Property testPredicateUri1;
    private Literal testTypedLiteral1;
    private Literal testPlainLiteral1;
    private Literal testLanguageLiteral1;
    private Resource testBlankNode1;
    private Resource testObjectUri1;
    private Object testStatementURIURIBlankNode1;
    private URI testSesameSubjectUri1;
    private URI testSesamePredicateUri1;
    private URI testSesameObjectUri1;
    private BNode testSesameObjectBlankNode1;
    private Statement testStatementURIURIURI1;
    private Value testSesameObjectPlainLiteral1;
    private org.openrdf.model.Literal testSesameObjectTypedLiteral1;
    private Statement testStatementURIURIPlainLiteral1;
    private Statement testStatementURIURITypedLiteral1;
    private Object testStatementURIURILanguageLiteral1;
    private org.openrdf.model.Literal testSesameObjectLanguageLiteral1;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        this.testValueFactory = new ValueFactoryImpl();
        
        this.testSubjectUri1 = ResourceFactory.createResource("http://test.example.org/test/subject/uri/1");
        // NOTE: The following URI must be strictly compatible with the XML specification or Jena
        // will reject it, even though it could be a perfectly reasonable URI otherwise
        this.testPredicateUri1 =
                ResourceFactory.createProperty("http://test.example.org/test/property/uri/aValidJenaXMLUri1");
        this.testObjectUri1 = ResourceFactory.createResource("http://test.example.org/test/object/uri/1");
        this.testPlainLiteral1 = ResourceFactory.createPlainLiteral("My Test Plain Literal!");
        this.testTypedLiteral1 =
                ResourceFactory.createTypedLiteral("123678",
                        TypeMapper.getInstance().getSafeTypeByName(XSD.integer.getURI()));
        this.testBlankNode1 = ResourceFactory.createResource();
        
        this.testModelEmpty = ModelFactory.createDefaultModel();
        
        this.testModelSingleURIURITypedLiteral = ModelFactory.createDefaultModel();
        this.testModelSingleURIURITypedLiteral
                .add(this.testSubjectUri1, this.testPredicateUri1, this.testTypedLiteral1);
        
        this.testModelSingleURIURIPlainLiteral = ModelFactory.createDefaultModel();
        this.testModelSingleURIURIPlainLiteral
                .add(this.testSubjectUri1, this.testPredicateUri1, this.testPlainLiteral1);
        
        this.testModelSingleURIURILanguageLiteral = ModelFactory.createDefaultModel();
        // For some reason the Jena ResourceFactory doesn't have this method so we need to use the
        // model to create it!
        this.testLanguageLiteral1 =
                this.testModelSingleURIURILanguageLiteral.createLiteral(
                        "Just a test language string in some random language", "en_AU");
        this.testModelSingleURIURILanguageLiteral.add(this.testSubjectUri1, this.testPredicateUri1,
                this.testLanguageLiteral1);
        
        this.testModelSingleURIURIBlankNode = ModelFactory.createDefaultModel();
        this.testModelSingleURIURIBlankNode.add(this.testSubjectUri1, this.testPredicateUri1, this.testBlankNode1);
        
        this.testModelSingleURIURIURI = ModelFactory.createDefaultModel();
        this.testModelSingleURIURIURI.add(this.testSubjectUri1, this.testPredicateUri1, this.testObjectUri1);
        
        this.testSesameSubjectUri1 = this.testValueFactory.createURI("http://test.example.org/test/subject/uri/1");
        this.testSesamePredicateUri1 =
                this.testValueFactory.createURI("http://test.example.org/test/property/uri/aValidJenaXMLUri1");
        this.testSesameObjectUri1 = this.testValueFactory.createURI("http://test.example.org/test/object/uri/1");
        this.testSesameObjectBlankNode1 = this.testValueFactory.createBNode();
        this.testSesameObjectPlainLiteral1 = this.testValueFactory.createLiteral("My Test Plain Literal!");
        this.testSesameObjectTypedLiteral1 =
                this.testValueFactory.createLiteral("123678", this.testValueFactory.createURI(XSD.integer.getURI()));
        this.testSesameObjectLanguageLiteral1 =
                this.testValueFactory.createLiteral("Just a test language string in some random language", "en_AU");
        
        this.testStatementURIURIBlankNode1 =
                this.testValueFactory.createStatement(this.testSesameSubjectUri1, this.testSesamePredicateUri1,
                        this.testSesameObjectBlankNode1);
        this.testStatementURIURIURI1 =
                this.testValueFactory.createStatement(this.testSesameSubjectUri1, this.testSesamePredicateUri1,
                        this.testSesameObjectUri1);
        this.testStatementURIURIPlainLiteral1 =
                this.testValueFactory.createStatement(this.testSesameSubjectUri1, this.testSesamePredicateUri1,
                        this.testSesameObjectPlainLiteral1);
        this.testStatementURIURITypedLiteral1 =
                this.testValueFactory.createStatement(this.testSesameSubjectUri1, this.testSesamePredicateUri1,
                        this.testSesameObjectTypedLiteral1);
        this.testStatementURIURILanguageLiteral1 =
                this.testValueFactory.createStatement(this.testSesameSubjectUri1, this.testSesamePredicateUri1,
                        this.testSesameObjectLanguageLiteral1);
    }
    
    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception
    {
        this.testValueFactory = null;
        
        this.testSubjectUri1 = null;
        this.testPredicateUri1 = null;
        this.testObjectUri1 = null;
        this.testPlainLiteral1 = null;
        this.testTypedLiteral1 = null;
        this.testLanguageLiteral1 = null;
        this.testBlankNode1 = null;
        
        this.testModelEmpty = null;
        this.testModelSingleURIURITypedLiteral = null;
        this.testModelSingleURIURIPlainLiteral = null;
        this.testModelSingleURIURILanguageLiteral = null;
        this.testModelSingleURIURIBlankNode = null;
        this.testModelSingleURIURIURI = null;
        
        this.testStatementURIURIBlankNode1 = null;
        this.testStatementURIURIURI1 = null;
        this.testStatementURIURIPlainLiteral1 = null;
        this.testStatementURIURITypedLiteral1 = null;
        this.testStatementURIURILanguageLiteral1 = null;
        
        this.testSesameSubjectUri1 = null;
        this.testSesamePredicateUri1 = null;
        this.testSesameObjectUri1 = null;
        this.testSesameObjectBlankNode1 = null;
        this.testSesameObjectPlainLiteral1 = null;
        this.testSesameObjectTypedLiteral1 = null;
        this.testSesameObjectLanguageLiteral1 = null;
    }
    
    /**
     * Test method for
     * {@link org.openjena.jenasesame.util.Convert#bnodeToNode(org.openrdf.model.BNode)}.
     */
    @Ignore
    @Test
    public void testBnodeToNode()
    {
        Assert.fail("Not yet implemented"); // TODO
    }
    
    /**
     * Test method for
     * {@link org.openjena.jenasesame.util.Convert#literalToNode(org.openrdf.model.Literal)}.
     */
    @Ignore
    @Test
    public void testLiteralToNode()
    {
        Assert.fail("Not yet implemented"); // TODO
    }
    
    /**
     * Test method for
     * {@link org.openjena.jenasesame.util.Convert#literalToRDFNode(com.hp.hpl.jena.rdf.model.Model, org.openrdf.model.Literal)}
     * .
     */
    @Ignore
    @Test
    public void testLiteralToRDFNode()
    {
        Assert.fail("Not yet implemented"); // TODO
    }
    
    /**
     * Test method for
     * {@link org.openjena.jenasesame.util.Convert#nodeBlankToValue(org.openrdf.model.ValueFactory, com.hp.hpl.jena.graph.Node)}
     * .
     */
    @Ignore
    @Test
    public void testNodeBlankToValue()
    {
        Assert.fail("Not yet implemented"); // TODO
    }
    
    /**
     * Test method for
     * {@link org.openjena.jenasesame.util.Convert#nodeLiteralToValue(org.openrdf.model.ValueFactory, com.hp.hpl.jena.graph.Node)}
     * .
     */
    @Ignore
    @Test
    public void testNodeLiteralToValue()
    {
        Assert.fail("Not yet implemented"); // TODO
    }
    
    /**
     * Test method for
     * {@link org.openjena.jenasesame.util.Convert#nodeToValue(org.openrdf.model.ValueFactory, com.hp.hpl.jena.graph.Node)}
     * .
     */
    @Ignore
    @Test
    public void testNodeToValue()
    {
        Assert.fail("Not yet implemented"); // TODO
    }
    
    /**
     * Test method for
     * {@link org.openjena.jenasesame.util.Convert#nodeToValueResource(org.openrdf.model.ValueFactory, com.hp.hpl.jena.graph.Node)}
     * .
     */
    @Ignore
    @Test
    public void testNodeToValueResource()
    {
        Assert.fail("Not yet implemented"); // TODO
    }
    
    /**
     * Test method for
     * {@link org.openjena.jenasesame.util.Convert#nodeURIToValue(org.openrdf.model.ValueFactory, com.hp.hpl.jena.graph.Node)}
     * .
     */
    @Ignore
    @Test
    public void testNodeURIToValue()
    {
        Assert.fail("Not yet implemented"); // TODO
    }
    
    /**
     * Test method for
     * {@link org.openjena.jenasesame.util.Convert#resourceToNode(org.openrdf.model.Resource)}.
     */
    @Ignore
    @Test
    public void testResourceToNode()
    {
        Assert.fail("Not yet implemented"); // TODO
    }
    
    /**
     * Test method for
     * {@link org.openjena.jenasesame.util.Convert#resourceToResource(com.hp.hpl.jena.rdf.model.Model, org.openrdf.model.Resource)}
     * .
     */
    @Test
    public void testResourceToResourceBNode()
    {
        final Resource resourceToResource =
                Convert.resourceToResource(this.testModelSingleURIURIBlankNode, this.testSesameObjectBlankNode1);
        
        Assert.assertNotNull(resourceToResource);
        Assert.assertEquals(this.testSesameObjectBlankNode1.getID(), resourceToResource.getId().getLabelString());
    }
    
    /**
     * Test method for
     * {@link org.openjena.jenasesame.util.Convert#resourceToResource(com.hp.hpl.jena.rdf.model.Model, org.openrdf.model.Resource)}
     * .
     */
    @Test
    public void testResourceToResourceURI()
    {
        final Resource resourceToResource =
                Convert.resourceToResource(this.testModelSingleURIURIURI, this.testSesameObjectUri1);
        
        Assert.assertNotNull(resourceToResource);
        Assert.assertEquals(this.testSesameObjectUri1.stringValue(), resourceToResource.getURI());
    }
    
    /**
     * Test method for
     * {@link org.openjena.jenasesame.util.Convert#statementToJenaStatement(com.hp.hpl.jena.rdf.model.Model, org.openrdf.model.Statement)}
     * .
     */
    @Ignore
    @Test
    public void testStatementToJenaStatement()
    {
        Assert.fail("Not yet implemented"); // TODO
    }
    
    /**
     * Test method for
     * {@link org.openjena.jenasesame.util.Convert#statementToTriple(org.openrdf.model.Statement)}.
     */
    @Ignore
    @Test
    public void testStatementToTriple()
    {
        Assert.fail("Not yet implemented"); // TODO
    }
    
    /**
     * Test method for
     * {@link org.openjena.jenasesame.util.Convert#tripleToStatement(org.openrdf.model.ValueFactory, com.hp.hpl.jena.graph.Triple)}
     * .
     */
    @Ignore
    @Test
    public void testTripleToStatement()
    {
        Assert.fail("Not yet implemented"); // TODO
    }
    
    /**
     * Test method for {@link org.openjena.jenasesame.util.Convert#uriToNode(org.openrdf.model.URI)}
     * .
     */
    @Ignore
    @Test
    public void testUriToNode()
    {
        Assert.fail("Not yet implemented"); // TODO
    }
    
    /**
     * Tests whether the Convert.uriToProperty method restricts the URIs that can be used based on
     * the arbitrary XML and hence, RDF/XML restrictions on how URI endings appear.
     * 
     * If Convert was using ResourceFactory.createProperty() this test would not succeed, however,
     * it is using Model.createProperty which does not appear to suffer from the ResourceFactory
     * weakness so it succeeds.
     * 
     * Test method for
     * {@link org.openjena.jenasesame.util.Convert#uriToProperty(com.hp.hpl.jena.rdf.model.Model, org.openrdf.model.URI)}
     * .
     */
    @Test
    public void testUriToProperty()
    {
        Property validXmlProperty = Convert.uriToProperty(this.testModelSingleURIURIURI, this.testSesamePredicateUri1);
        Assert.assertNotNull(validXmlProperty);
        Assert.assertEquals(this.testSesamePredicateUri1.stringValue(), validXmlProperty.getURI());
        
        Property invalidXmlProperty = Convert.uriToProperty(this.testModelSingleURIURIURI, this.testSesameSubjectUri1);
        Assert.assertNotNull(invalidXmlProperty);
        Assert.assertEquals(this.testSesameSubjectUri1.stringValue(), invalidXmlProperty.getURI());
    }
    
    /**
     * Test method for
     * {@link org.openjena.jenasesame.util.Convert#valueToNode(org.openrdf.model.Value)}.
     */
    @Ignore
    @Test
    public void testValueToNode()
    {
        Assert.fail("Not yet implemented"); // TODO
    }
    
    /**
     * Test method for
     * {@link org.openjena.jenasesame.util.Convert#valueToRDFNode(com.hp.hpl.jena.rdf.model.Model, org.openrdf.model.Value)}
     * .
     */
    @Ignore
    @Test
    public void testValueToRDFNode()
    {
        Assert.fail("Not yet implemented"); // TODO
    }
    
}
