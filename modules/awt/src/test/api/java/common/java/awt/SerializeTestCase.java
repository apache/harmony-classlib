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
 * @author Denis M. Kishenko
 * @version $Revision$
 */
package java.awt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import junit.framework.TestCase;


public class SerializeTestCase extends TestCase {

    static final String POSTFIX = ".actual";
    static final String FOLDER = "serialization";

    public String serializePath = null;

    public SerializeTestCase(String name) {
        super(name);
    }

    public static String getSerializePath(Class serializeClass) {
        String path = System.getProperty("TEST_SRC_DIR");
        assertNotNull("Variable TEST_SRC_DIR not defined", path);
        if (!path.endsWith(File.separator)) {
            path += File.separator;
        }
        String name = serializeClass.getName();
        name = name.substring(0, name.lastIndexOf('.'));
        path += name.replace('.', File.separator.charAt(0)) + File.separator + FOLDER + File.separator;
        new File(path).mkdirs();
        return path;
    }

    public String objToStr(Object obj) {
        return obj.toString();
    }

    public boolean checkRead(Object obj) {
        assertNotNull("Serialize path not defined");
        try {
            Object template = loadSerialized(serializePath + objToStr(obj));
            return objToStr(template).equals(objToStr(obj));
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
        return false;
    }

    public boolean checkWrite(Object obj) {
        assertNotNull("Serialize path not defined");
        String file = serializePath + objToStr(obj);
        saveSerialized(obj, file + POSTFIX);
        return compare(file, file + POSTFIX);
    }

    public void saveSerialized(Object obj) {
        assertNotNull("Serialize path not defined");
        saveSerialized(obj, serializePath + objToStr(obj));
    }

    public void saveSerialized(Object obj, String file) {
        try {
            FileOutputStream fs = new FileOutputStream(file);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(obj);
            os.close();
            fs.close();
            System.out.println("Write " + file);
        } catch (Exception e) {
            System.out.println("Can''t write object to file " + file);
        }
    }

    public Object loadSerialized(String file) throws IOException, ClassNotFoundException {
        FileInputStream fs = new FileInputStream(file);
        ObjectInputStream os = new ObjectInputStream(fs);
        Object obj = os.readObject();
        os.close();
        fs.close();
        System.out.println("Read " + file);
        return obj;
    }

    boolean compare(String file1, String file2) {
        boolean cmp = false;
        try {
            FileInputStream fs1 = new FileInputStream(file1);
            FileInputStream fs2 = new FileInputStream(file2);

            byte[] buf1 = new byte[256];
            byte[] buf2 = new byte[256];
            int count1, count2;

        OUTER:
            while(true) {
                count1 = fs1.read(buf1);
                count2 = fs2.read(buf2);

                if (count1 != count2) {
                    break OUTER;
                }
                if (count1 == -1) {
                    cmp = true;
                    break;
                }
                for(int i = 0; i < count1; i++) {
                    if (buf1[i] != buf2[i]) {
                        break OUTER;
                    }
                }
            }

            fs1.close();
            fs2.close();
        } catch (Exception e) {
            System.out.println("Can''t compare files " + file1 + " and " + file2);
        }
        return cmp;
    }

}