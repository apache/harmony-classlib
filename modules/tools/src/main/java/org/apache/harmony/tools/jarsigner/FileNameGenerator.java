/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.apache.harmony.tools.jarsigner;

import java.util.Arrays;

/**
 * File to build the base file names for .SF and .DSA files.
 */
public class FileNameGenerator {
    private static final int fileNameLength = 8;
    
    /**
     * Generates the file name for .SF and .DSA files using
     * param.getSigFileName() or alias given on the command line.
     * If the alias 
     * 
     * @param param
     * @return
     */
    static String generateFileName(JSParameters param){
        if (param.getSigFileName() != null){
            return convertString(param.getSigFileName().toUpperCase());
        }
        String alias = param.getAlias(); 
        if (alias == null){
            throw new NullPointerException("Alias is null.");
        }
        int length = alias.length();
        if (length > fileNameLength){
            alias = alias.substring(0, 7);
            length = alias.length();
        } 
        
        alias = convertString(alias);
        if (length == fileNameLength){
            return alias.toUpperCase();
        } else {
            char[] remainder = new char[fileNameLength - length];
            Arrays.fill(remainder, '_');
            return alias + new String(remainder);
        }
    }
    
    // Finds disallowed letters in input String and converts
    // them to underscores ("_"). Allowed characters are letters, digits,
    // hyphens and underscores. If no changes are made the input string is
    // returned.
    private static String convertString(String input){
        char [] chars = input.toCharArray();
        boolean isChanged = false; 
        for (int i = 0; i < chars.length; i++){
            char current = chars[i];
            if ((current >= 'A' && current<= 'Z') || 
                    (current >= 'a' && current <= 'z') ||
                    (current >= '0' && current <= '9') ||
                    current == '-' || current == '_'){
                continue;
            }
            
            isChanged = true;
            chars[i] = '_';
        }
        if (isChanged){
            return new String(chars);
        } else {
            return input;
        }
    }
}

