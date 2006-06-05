package ar.org.fitc.test.main;

import org.suiterunner.Runner;

import ar.org.fitc.test.util.Configuration;
import ar.org.fitc.test.util.SuiteRunner;

public class LaunchBouncyCastleProviderTest {
  
    public static void main(String[] args) {
        synchronized (Runner.class) { 
            Configuration conf = Configuration.getInstance();
            conf.setResultXML("BouncyCastleProvider.xml");
            SuiteRunner sr = new SuiteRunner("ar.org.fitc.test.integration.cipher.TestCipherAllBouncyCastle",true,conf);
            Runner.main(sr.getParameters());
        }
    }
}
