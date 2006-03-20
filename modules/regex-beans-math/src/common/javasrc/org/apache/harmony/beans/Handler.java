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

import java.util.HashMap;
import java.util.Stack;
import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.beans.XMLDecoder;

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.1.2.1 $
 */

public class Handler extends DefaultHandler {
    
    private Vector result;
    private Vector commands;
    private XMLDecoder decoder;
    private HashMap references;
    private Stack stack;
    private int tabCount;

    public Handler(XMLDecoder decoder, Vector result) {
        this.decoder = decoder;
        this.result = result;
        this.commands = new Vector();
        this.references = new HashMap();
        this.stack = new Stack();
    }
    
    // clear collections to prepare parsing document
    public void startDocument() {
        references.clear();
        tabCount = 0;
    }
    
    // create new command and put it on stack
    public void startElement(String namespaceURI, String localeName,
        String tagName, Attributes attrs) throws SAXException
    {
        Command.printAttrs(tabCount, tagName, attrs);
        Command cmd = tagName.equals("java") ? new Command(decoder, tagName,
                Command.parseAttrs(tagName, attrs)) :
            new Command(tagName, Command.parseAttrs(tagName, attrs));
        stack.push(cmd);
        ++tabCount;
    }
    
    // add data to command
    public void characters(char[] text, int start, int length)
            throws SAXException {
        if(length > 0) {
            String data = String.valueOf(text, start, length).replace('\n', ' ')
                    .replace('\t', ' ').trim();
            if(data.length() > 0) {
                Command.prn(tabCount, tabCount + ">setting data=" + data
                        + "<EOL>");
                Command cmd = (Command) stack.peek();
                cmd.setData(data);
            }
        }
    }
    
    // pop command from stack and put it to one of collections
    public void endElement(String namespaceURI, String localeName,
        String tagName) throws SAXException
    {
        Command cmd = (Command) stack.pop();
        cmd.setTabCount(tabCount);
            
        // find if command works in context
        if(!stack.isEmpty()) {
            Command ctx = (Command) stack.peek();
            ctx.addChild(cmd);
        }
    
        // upper level commands
        if(stack.size() == 1 && cmd.isExecutable()){
            commands.add(cmd);
        }
        
        // store reference to command
        if(cmd.hasAttr("id")) {
            references.put(cmd.getAttr("id"), cmd);
        }
            
        try {
            cmd.exec(references);
        } catch (Exception e) {
            throw new SAXException(e);
        }
            
        if(--tabCount < 0) {
            tabCount = 0;
        }
        
        Command.prn(tabCount, tabCount + ">...<" + tagName + "> end");
    }
    
    // iterate over deferred commands and execute them again
    public void endDocument() throws SAXException {
        for(int i = 0; i < commands.size(); ++i) {
            Command cmd = (Command) commands.elementAt(i);
            boolean backtracked = true;
               try {
                backtracked = cmd.backtrack(references);
               } catch (Exception e) {
                   throw new SAXException("Exception in command excution");
               }
               /*
               if(!backtracked)
                   throw new SAXException("Command " + cmd.getTagName() +
                       " is unresolved on second run() call.");
                   */
        }

        for(int i = 0; i < commands.size(); ++i) {
            Command cmd = (Command) commands.elementAt(i);
            result.add(cmd.getResultValue());
        }
    }
}
