/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.beans.tests.support.mock;

import java.io.Serializable;

public class MockJavaBean implements Serializable {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    private String beanName;

    private String propertyOne;

    private Integer propertyTwo;

    private String propertyThree;

    private String[] propertyFour;

    private String[] propertyFive;

    private Integer[] propertySix;

    private String protectedProp;

    public MockJavaBean() {
        this.beanName = getClass().getName();
    }

    public MockJavaBean(String beanName) {
        this.beanName = beanName;
    }

    /**
     * @return Returns the beanName.
     */
    public String getBeanName() {
        return beanName;
    }

    /**
     * @param beanName
     *            The beanName to set.
     */
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    /**
     * @return Returns the propertyOne.
     */
    public String getPropertyOne() {
        return propertyOne;
    }

    /**
     * @param propertyOne
     *            The propertyOne to set.
     */
    public void setPropertyOne(String propertyOne) {
        this.propertyOne = propertyOne;
    }

    /**
     * @return Returns the propertyTwo.
     */
    public Integer getPropertyTwo() {
        return propertyTwo;
    }

    /**
     * @param propertyTwo
     *            The propertyTwo to set.
     */
    public void setPropertyTwo(Integer propertyTwo) {
        this.propertyTwo = propertyTwo;
    }

    public String invalidGetMethod(String arg) {
        // for PropertyDescriptorTest: with args
        return arg;
    }

    public void invalidGetMethod() {
        // for PropertyDescriptorTest
        // return void
    }

    /**
     * @return Returns the propertyThree.
     */
    public String getPropertyThree() {
        return propertyThree;
    }

    /**
     * @param propertyThree
     *            The propertyThree to set.
     */
    public void setPropertyThree(String propertyThree) {
        this.propertyThree = propertyThree;
    }

    /**
     * @return Returns the propertyFour.
     */
    public String[] getPropertyFour() {
        return propertyFour;
    }

    /**
     * @param propertyFour
     *            The propertyFour to set.
     */
    public void setPropertyFour(String[] propertyFour) {
        this.propertyFour = propertyFour;
    }

    public String getPropertyFour(int i) {
        return getPropertyFour()[i];
    }

    public void getPropertyFourInvalid(int i) {

    }

    public void setPropertyFour(int i, String value) {
        propertyFour[i] = value;
    }

    public void setPropertyFour(int i, int value) {
        propertyFour[i] = "";
    }

    public int setPropertyFourInvalid(int i, String value) {
        return i;
    }

    public void setPropertyFourInvalid2(String i, String value) {
        // return i;
    }

    /**
     * @return Returns the propertyFive.
     */
    public String[] getPropertyFive() {
        return propertyFive;
    }

    /**
     * @param propertyFive
     *            The propertyFive to set.
     */
    public void setPropertyFive(String[] propertyFive) {
        this.propertyFive = propertyFive;
    }

    public String getPropertyFive(int i) {
        return getPropertyFive()[i];
    }

    public void setPropertyFive(int i, String value) {
        propertyFive[i] = value;
    }

    /**
     * @return Returns the protectedProp.
     */
    protected String getProtectedProp() {
        return protectedProp;
    }

    /**
     * @param protectedProp
     *            The protectedProp to set.
     */
    protected void setProtectedProp(String protectedProp) {
        this.protectedProp = protectedProp;
    }

    /**
     * @return Returns the propertySix.
     */
    public Integer[] getPropertySix() {
        return propertySix;
    }

    public Integer getPropertySix(int i) {
        return null;
    }

    /**
     * @param propertySix
     *            The propertySix to set.
     */
    public void setPropertySix(Integer[] propertySix) {
        this.propertySix = propertySix;
    }

    public void setPropertySix(int i, Integer value) {

    }

    public void addMockPropertyChangeListener(
            MockPropertyChangeListener listener) {

    }

    public void removeMockPropertyChangeListener(
            MockPropertyChangeListener listener) {

    }
}
