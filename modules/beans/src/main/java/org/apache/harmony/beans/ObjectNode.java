/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.1.2.1 $
 */
package org.apache.harmony.beans;

import java.beans.Expression;
import java.beans.Statement;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.1.2.1 $
 */

public class ObjectNode {

    private Expression initializer;
    private Object objectValue = null;
    private HashMap nodes;
    
    private Vector statements = new Vector();
    private Vector expressions = new Vector();
    private Vector referencedExpressions = new Vector();
    
    private int referencesNumber = 0;
    private String id = null;

    public ObjectNode(Expression initializer) {
        this.initializer = initializer;
        this.nodes = null;
    }
    
    public ObjectNode(Expression initializer, HashMap nodes) {
        this.initializer = initializer;
        this.nodes = nodes;
    }
    
    public Expression getInitializer() {
        return initializer;
    }
    
    public Object getObjectValue() throws Exception {
        if(objectValue != null) {
            return objectValue;
        }
        
        if(nodes != null) {
            Object[] oldArgs = initializer.getArguments();            
            Object[] newArgs = new Object[oldArgs.length];
            
            for(int i = 0; i < oldArgs.length; ++i) {
                if(oldArgs[i] != null) {
                    ObjectNode node = (ObjectNode) nodes.get(oldArgs[i]);
                    newArgs[i] = node.getObjectValue();
                } else {
                    newArgs[i] = null;
                }
            }
            
            objectValue = (new Expression(initializer.getTarget(),
                    initializer.getMethodName(), newArgs)).getValue();
        } else {
            objectValue = initializer.getValue();
        }
        
        return objectValue; 
    }
    
    public Class getObjectType() throws Exception {
        Object value = getObjectValue();
        return (value != null) ? value.getClass() : null;
    }
    
    public Object[] getObjectArguments() {
        return initializer.getArguments();
        //return (nodes == null) ? new Object[0] : initializer.getArguments();
    }
    
    public int getReferencesNumber() {
        return referencesNumber;
    }
    
    public int getReferencedExpressionsNumber() {
        return referencedExpressions.size();
    }
    
    public void addReference() {
        referencesNumber++;
    }
    
    public void addReferencedExpression(Expression expr) {
        referencedExpressions.add(expr);
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void addExpression(Expression expression) {
        expressions.add(expression);
    }
    
    public void addStatement(Statement statement) {
        boolean found = false;
        Iterator i = statements.iterator();
        while(i.hasNext()) {
            Statement s = (Statement) i.next();
            
            if(s.equals(statement)) {
                found = true;
                break;
            }
        }
        
        if(!found) {
            statements.add(statement);
        }
    }
    
    public Enumeration expressions() {
        return expressions.elements();
    }
    
    public Enumeration referencedExpressions() {
        return referencedExpressions.elements();
    }
    
    public Enumeration statements() {
        return statements.elements();
    }
}
