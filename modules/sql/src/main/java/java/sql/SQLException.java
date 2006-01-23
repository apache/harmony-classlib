package java.sql;

/**
 *   Stub to keep J9 happy for now
 *
 */
public class SQLException extends Exception {

    SQLException() { 
        super();
    }
    SQLException(String s) {
        super(s);
    }
    
    SQLException(String s, String t) {
        super(s);
    }

    SQLException(String s, String t, int i) {
        super(s);
    }
}
