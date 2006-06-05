package ar.org.fitc.test.main;

public class LaunchAllTests {

    public static void main(String[] args) {
        LaunchAllCryptoTests.main(null);
        System.err.println("termino??");
        LaunchCipherCertificateTests.main(null);
        System.err.println("no creo...");
        LaunchBouncyCastleProviderTest.main(null);
    }

}
