/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.harmony.beans;

import java.beans.Expression;
import java.beans.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class ObjectNode {

    private Expression initializer;

    private Object objectValue;

    private Map<Object, ObjectNode> nodes;

    private Set<Statement> statements = new LinkedHashSet<Statement>();

    private Vector<Expression> expressions = new Vector<Expression>();

    // XXX referencedExpressions is not used indeed
    // private Vector<Expression> referencedExpressions = new
    // Vector<Expression>();

    private int referencesNumber;

    private String id;

    public ObjectNode(Expression initializer) {
        this.initializer = initializer;
        this.nodes = null;
    }

    public ObjectNode(Expression initializer, HashMap<Object, ObjectNode> nodes) {
        this.initializer = initializer;
        this.nodes = nodes;
    }

    public Expression getInitializer() {
        return initializer;
    }

    public Object getObjectValue() throws Exception {
        if (objectValue != null) {
            return objectValue;
        }

        if (nodes != null) {
            Object[] oldArgs = initializer.getArguments();
            Object[] newArgs = new Object[oldArgs.length];

            for (int i = 0; i < oldArgs.length; ++i) {
                if (oldArgs[i] != null) {
                    ObjectNode node = nodes.get(oldArgs[i]);

                    newArgs[i] = node.getObjectValue();
                } else {
                    newArgs[i] = null;
                }
            }

            objectValue = (new Expression(initializer.getTarget(), initializer
                    .getMethodName(), newArgs)).getValue();
        } else {
            objectValue = initializer.getValue();
        }

        return objectValue;
    }

    public Class<?> getObjectType() throws Exception {
        Object value = getObjectValue();

        return (value != null) ? value.getClass() : null;
    }

    public Object[] getObjectArguments() {
        return initializer.getArguments();
        // return (nodes == null) ? new Object[0] : initializer.getArguments();
    }

    public int getReferencesNumber() {
        return referencesNumber;
    }

    // XXX never called by other classes
    // public int getReferencedExpressionsNumber() {
    // return referencedExpressions.size();
    // }

    public void addReference() {
        referencesNumber++;
    }

    // public void addReferencedExpression(Expression expr) {
    // referencedExpressions.add(expr);
    // }

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
        statements.add(statement);
    }

    public Iterator<Expression> expressions() {
        return expressions.iterator();
    }

    // XXX never called by other classes
    // public Iterator<Expression> referencedExpressions() {
    // return referencedExpressions.iterator();
    // }

    public Iterator<Statement> statements() {
        return statements.iterator();
    }
}
