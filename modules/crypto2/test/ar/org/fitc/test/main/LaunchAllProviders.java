package ar.org.fitc.test.main;

import org.suiterunner.Runner;

import ar.org.fitc.test.util.Configuration;
import ar.org.fitc.test.util.SuiteRunner;

public class LaunchAllProviders {
    public static void main(String[] args) {

        Configuration conf = Configuration.getInstance();
        conf.setResultXML("AllProviders.xml");
        SuiteRunner sr = new SuiteRunner("ar.org.fitc.test.integration.cipher.TestCipherAllProviders",true,conf);
        Runner.main(sr.getParameters());
    }
}
