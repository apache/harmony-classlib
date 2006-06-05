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
 * @author Hugo Beilis
 * @author Osvaldo Demo
 * @author Jorge Rafael
 * @version 1.0
 */

package ar.org.fitc.test.util;


public class Configuration {

    private static Configuration conf;
    
    private String resultXML = "C:\\TestMath\\ResultXML\\AllTestsMath.xml";

    private String unitTestingProject = "D:\\Mis documentos\\testing-workspace\\MathJunitTesting\\";

    private String utilitiesProject = "D:\\Mis documentos\\testing-workspace\\TestingUtilities";

    private String resultFailed = "C:\\TestMath\\Failed\\FailedAllTestsMath.rtf";
    
    private String javaBin = "C:\\Program Files\\Java\\jdk1.5.0_05\\bin";

    private String classFilesPath = "/home/odemo/classes"; 
    
    public String getJavaBin() {
        return javaBin;
    }
        
    public void setJavaBin(String javaBin) {
        this.javaBin = javaBin;
    }

    public String getResultFailed() {
        return resultFailed;
    }

    public void setResultFailed(String resultFailed) {
        this.resultFailed = resultFailed;
    }

    public String getResultXML() {
        return resultXML;
    }

    public void setResultXML(String resultXML) {
        this.resultXML = resultXML;
    }

    public String getUnitTestingProject() {
        return unitTestingProject;
    }

    public void setUnitTestingProject(String unitTestingProject) {
        this.unitTestingProject = unitTestingProject;
    }

    public String getUtilitiesProject() {
        return utilitiesProject;
    }

    public void setUtilitiesProject(String utilitiesProject) {
        this.utilitiesProject = utilitiesProject;
    }

    public static Configuration getInstance() {
        if (conf == null) {
            conf = new Configuration();
        }
        return conf;    
    }
    
    private Configuration() {
        
    }

    public String getClassFilesPath() {
        return classFilesPath;
    }

    public void setClassFilesPath(String classFiles) {
        this.classFilesPath = classFiles;
    }
}
