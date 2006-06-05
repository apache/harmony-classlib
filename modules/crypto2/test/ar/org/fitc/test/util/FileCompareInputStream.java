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

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileCompareInputStream extends FileInputStream{

	public FileCompareInputStream(File file) throws FileNotFoundException {
		super(file);		
	}
	
	public FileCompareInputStream(FileDescriptor fdObj){
		super(fdObj);
	}
	
	public FileCompareInputStream(String name) throws FileNotFoundException{
		super(name);
	}
	
	/*Compara los archivos
	 */
	public boolean isEqualTo(FileInputStream file){
		boolean result = true;
		int b1 = 0, b2 = 0;
		
		if (this != file){			
			try{			
				do{
					b1 = this.read();
					b2 = file.read();
					
					if (b1 != b2){
						result = false;
						break;
					}
				}while(b1 != -1 && b2 != -1);
			}catch(Throwable e){
				result = false;
			}
		}
		
		return result;
	}
}
