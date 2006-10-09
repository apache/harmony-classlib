/*
 *  Copyright 2005 - 2006 The Apache Software Foundation or its licensors, as applicable.
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
 * @author Elena V. Sayapina 
 * @version $Revision: 1.5 $ 
 */ 

package javax.print.attribute.standard;

import javax.print.attribute.EnumSyntax;
import javax.print.attribute.PrintJobAttribute;
import javax.print.attribute.PrintRequestAttribute;

/*
 * Table values are obtained from RFC2911: Internet Printing Protocol/1.1: 
 * Model and Semantics, section 4.2.4, http://ietf.org/rfc/rfc2911.txt?number=2911
 */

public class MultipleDocumentHandling extends EnumSyntax 
    implements PrintJobAttribute, PrintRequestAttribute {


    public static final MultipleDocumentHandling
        SINGLE_DOCUMENT = new MultipleDocumentHandling (0);

    public static final MultipleDocumentHandling 
        SEPARATE_DOCUMENTS_UNCOLLATED_COPIES = new MultipleDocumentHandling (1);

    public static final MultipleDocumentHandling 
        SEPARATE_DOCUMENTS_COLLATED_COPIES = new MultipleDocumentHandling (2);

    public static final MultipleDocumentHandling
        SINGLE_DOCUMENT_NEW_SHEET = new MultipleDocumentHandling (3);

    
    private static final MultipleDocumentHandling[] enumValueTable = { 

        SINGLE_DOCUMENT,
        SEPARATE_DOCUMENTS_UNCOLLATED_COPIES,
        SEPARATE_DOCUMENTS_COLLATED_COPIES,
        SINGLE_DOCUMENT_NEW_SHEET
    };
    
    private static final String[] stringTable = {

        "single-document",
        "separate-documents-uncollated-copies",
        "separate-documents-collated-copies",
        "single-document-new-sheet"
    };
    
    
    protected MultipleDocumentHandling(int value) {
        super(value);
    }
    
    
    public final Class getCategory() {
    /* 1.5 support requires the following changes 
       Class<? extends Attribute> getCategory() { */
        return MultipleDocumentHandling.class;
    }
    
    protected EnumSyntax[] getEnumValueTable() {
        return (EnumSyntax[]) enumValueTable.clone();
    }
    
    public final String getName() {
        return "multiple-document-handling";
    }
    
    protected String[] getStringTable() {
        return (String[]) stringTable.clone();
    }
    

}
