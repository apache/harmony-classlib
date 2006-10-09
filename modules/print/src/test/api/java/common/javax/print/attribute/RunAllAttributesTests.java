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
/** 
 * @author Elena V. Sayapina 
 * @version $Revision: 1.3 $ 
 */ 

package javax.print.attribute;

import javax.print.attribute.standard.FinishingsTest;
import javax.print.attribute.standard.JobStateReasonTest;
import javax.print.attribute.standard.JobStateReasonsTest;
import javax.print.attribute.standard.MediaNameTest;
import javax.print.attribute.standard.MediaPrintableAreaTest;
import javax.print.attribute.standard.MediaSizeNameTest;
import javax.print.attribute.standard.MediaSizeTest;
import javax.print.attribute.standard.MediaTest;
import javax.print.attribute.standard.PrinterStateReasonTest;
import javax.print.attribute.standard.PrinterStateReasonsTest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class RunAllAttributesTests extends TestCase {


    public static void main(String[] args) {
        junit.textui.TestRunner.run( suite() );
    }

    public static Test suite() {
        TestSuite suite = new TestSuite("Run all JUnit Attributes Tests");

        suite.addTest(new TestSuite(AttributeSetUtilitiesTest.class));
        suite.addTest(new TestSuite(DateTimeSyntaxTest.class));
        suite.addTest(new TestSuite(EnumSyntaxTest.class));
        suite.addTest(new TestSuite(HashAttributeTest.class));
        suite.addTest(new TestSuite(IntegerSyntaxTest.class));
        suite.addTest(new TestSuite(ResolutionSyntaxTest.class));
        suite.addTest(new TestSuite(SetOfIntegerSyntaxTest.class));
        suite.addTest(new TestSuite(Size2DSyntaxTest.class));
        suite.addTest(new TestSuite(TextSyntaxTest.class));
        suite.addTest(new TestSuite(URISyntaxTest.class));

        suite.addTest(new TestSuite(FinishingsTest.class));
        suite.addTest(new TestSuite(JobStateReasonsTest.class));
        suite.addTest(new TestSuite(JobStateReasonTest.class));
        suite.addTest(new TestSuite(MediaTest.class));
        suite.addTest(new TestSuite(MediaNameTest.class));
        suite.addTest(new TestSuite(MediaPrintableAreaTest.class));
        suite.addTest(new TestSuite(MediaSizeNameTest.class));
        suite.addTest(new TestSuite(MediaSizeTest.class));
        suite.addTest(new TestSuite(PrinterStateReasonsTest.class));
        suite.addTest(new TestSuite(PrinterStateReasonTest.class));

        return suite;
    }

}
