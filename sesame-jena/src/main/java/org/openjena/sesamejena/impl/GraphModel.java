/**
 * 
 */
package org.openjena.sesamejena.impl;

import java.util.Collection;
import java.util.Iterator;

import org.openjena.jenasesame.util.Convert;
import org.openrdf.model.Graph;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.GraphImpl;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;

/**
 * @author Peter Ansell p_ansell@yahoo.com
 *
 */
public class GraphModel extends GraphImpl implements Graph
{
    
    /**
     * 
     */
    private static final long serialVersionUID = -1803550586980209750L;
    
    private Model jenaModel;

    public GraphModel(Model nextJenaModel)
    {
        this.jenaModel = nextJenaModel;
    }
    
    /* (non-Javadoc)
     * @see java.util.Collection#add(java.lang.Object)
     */
    @Override
    public boolean add(Statement stmt)
    {
        this.jenaModel.add((com.hp.hpl.jena.rdf.model.Resource)Convert.resourceToNode(stmt.getSubject()), (Property)Convert.uriToNode(stmt.getPredicate()), (RDFNode)Convert.valueToNode(stmt.getObject()));
        
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.util.Collection#addAll(java.util.Collection)
     */
    @Override
    public boolean addAll(Collection<? extends Statement> collection)
    {
        for(Statement nextStatement : collection)
        {
            this.add(nextStatement);
        }
        
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.util.Collection#clear()
     */
    @Override
    public void clear()
    {
        this.jenaModel = this.jenaModel.removeAll();
    }
    
    /* (non-Javadoc)
     * @see java.util.Collection#contains(java.lang.Object)
     */
    @Override
    public boolean contains(Object arg0)
    {
        if(!(arg0 instanceof Statement))
        {
            return false;
        }
        
        Statement stmt = (Statement)arg0;
        
        return this.jenaModel.contains(Convert.statementToJenaStatement(this.jenaModel, stmt));
    }
    
    /* (non-Javadoc)
     * @see java.util.Collection#containsAll(java.util.Collection)
     */
    @Override
    public boolean containsAll(Collection<? extends Object> arg0)
    {
        for(Object nextObj : arg0)
        {
            if(nextObj instanceof Statement)
            {
                if(!this.contains(nextObj))
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        
        return true;
    }
    
    /* (non-Javadoc)
     * @see java.util.Collection#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object arg0)
    {
        if(arg0 == null)
        {
            return false;
        }
        
        if(!(arg0 instanceof Graph))
        {
            return false;
        }
        
        Graph graph = (Graph)arg0;
        
        return this.containsAll(graph);
    }
    
    /* (non-Javadoc)
     * @see java.util.Collection#hashCode()
     */
    @Override
    public int hashCode()
    {
        return this.jenaModel.hashCode();
    }
    
    /* (non-Javadoc)
     * @see java.util.Collection#isEmpty()
     */
    @Override
    public boolean isEmpty()
    {
        return this.jenaModel.isEmpty();
    }
    
    /* (non-Javadoc)
     * @see java.util.Collection#iterator()
     */
    @Override
    public Iterator<Statement> iterator()
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /* (non-Javadoc)
     * @see java.util.Collection#remove(java.lang.Object)
     */
    @Override
    public boolean remove(Object arg0)
    {
        if(arg0 instanceof Statement)
        {
            Statement stmt = (Statement)arg0;
            
            if(this.contains(stmt))
            {
                this.jenaModel = this.jenaModel.remove(Convert.statementToJenaStatement(this.jenaModel, stmt));
                
                return true;
            }
        }
        
        return false;
    }
    
    /* (non-Javadoc)
     * @see java.util.Collection#removeAll(java.util.Collection)
     */
    @Override
    public boolean removeAll(Collection<?> arg0)
    {
        boolean change = false;
        
        for(Object nextStmt : arg0)
        {
            if(nextStmt instanceof Statement)
            {
                Statement stmt = (Statement)nextStmt;
                if(this.contains(stmt))
                {
                    if(!this.remove(stmt))
                    {
                        return false;
                    }
                    else
                    {
                        change = true;
                    }
                }
            }
        }
        
        return change;
    }
    
    /* (non-Javadoc)
     * @see java.util.Collection#retainAll(java.util.Collection)
     */
    @Override
    public boolean retainAll(Collection<?> arg0)
    {
        boolean change = false;
        
        // TODO: Implement me!
        
        return change;
    }
    
    /* (non-Javadoc)
     * @see java.util.Collection#size()
     */
    @Override
    public int size()
    {
        return (int)this.jenaModel.size();
    }
    
    /* (non-Javadoc)
     * @see java.util.Collection#toArray()
     */
    @Override
    public Object[] toArray()
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /* (non-Javadoc)
     * @see java.util.Collection#toArray(T[])
     */
    @Override
    public <T> T[] toArray(T[] arg0)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /* (non-Javadoc)
     * @see org.openrdf.model.Graph#getValueFactory()
     */
    @Override
    public ValueFactory getValueFactory()
    {
        return this.valueFactory;
    }
    
    /* (non-Javadoc)
     * @see org.openrdf.model.Graph#add(org.openrdf.model.Resource, org.openrdf.model.URI, org.openrdf.model.Value, org.openrdf.model.Resource[])
     */
    @Override
    public boolean add(Resource subj, URI pred, Value obj, Resource... contexts)
    {
        this.jenaModel = this.jenaModel.add((com.hp.hpl.jena.rdf.model.Resource)Convert.resourceToNode(subj), (Property)Convert.uriToNode(pred), (RDFNode)Convert.valueToNode(obj));
        
        return true;
    }
    
    /* (non-Javadoc)
     * @see org.openrdf.model.Graph#match(org.openrdf.model.Resource, org.openrdf.model.URI, org.openrdf.model.Value, org.openrdf.model.Resource[])
     */
    @Override
    public Iterator<Statement> match(Resource subj, URI pred, Value obj, Resource... contexts)
    {
        com.hp.hpl.jena.rdf.model.Resource subject = null;
        com.hp.hpl.jena.rdf.model.Property property = null;
        com.hp.hpl.jena.rdf.model.RDFNode object = null;
        
        if(subj != null)
        {
            subject = (com.hp.hpl.jena.rdf.model.Resource)Convert.resourceToNode(subj);
        }
        
        if(pred != null)
        {
            property = (com.hp.hpl.jena.rdf.model.Property)Convert.uriToNode(pred);
        }
        
        if(obj != null)
        {
            object = (com.hp.hpl.jena.rdf.model.RDFNode)Convert.valueToNode(obj);
        }
        
        this.jenaModel.contains(subject, property, object);
        
        // TODO: Implement me!
        
        return null;
    }
    
}
