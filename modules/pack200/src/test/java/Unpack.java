/*
+ *  Licensed to the Apache Software Foundation (ASF) under one or more
+ *  contributor license agreements.  See the NOTICE file distributed with
+ *  this work for additional information regarding copyright ownership.
+ *  The ASF licenses this file to You under the Apache License, Version 2.0
+ *  (the "License"); you may not use this file except in compliance with
+ *  the License.  You may obtain a copy of the License at
+ *
+ *     http://www.apache.org/licenses/LICENSE-2.0
+ *
+ *  Unless required by applicable law or agreed to in writing, software
+ *  distributed under the License is distributed on an "AS IS" BASIS,
+ *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
+ *  See the License for the specific language governing permissions and
+ *  limitations under the License.
+ */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.jar.JarOutputStream;

import org.apache.harmony.pack200.Pack200Exception;
import org.apache.harmony.pack200.Segment;

public class Unpack {
	public static void main(String[] args) throws IOException, Pack200Exception {
		BufferedInputStream in = new BufferedInputStream(
				args.length > 0 ? new FileInputStream(args[0]) : System.in);
		JarOutputStream out = new JarOutputStream(
				args.length > 1 ? (OutputStream) new FileOutputStream(args[1])
						: (OutputStream) new BufferedOutputStream(System.out));
		Segment.parse(in).writeJar(out);
	}
}
