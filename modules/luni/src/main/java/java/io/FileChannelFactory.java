/* Copyright 2005,2006 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.io;


import java.nio.channels.FileChannel;

import com.ibm.io.nio.ReadOnlyFileChannel;
import com.ibm.io.nio.ReadWriteFileChannel;
import com.ibm.io.nio.WriteOnlyFileChannel;
import com.ibm.platform.IFileSystem;

/**
 * A simple factory to provide a generic way to create FileChannel
 * implementation from within the java.io package.
 */
class FileChannelFactory {
	static FileChannel getFileChannel(Object stream, long fd, int mode) {
        switch(mode){
        case IFileSystem.O_RDONLY:
            return new ReadOnlyFileChannel(stream, fd);
        case IFileSystem.O_WRONLY:
            return new WriteOnlyFileChannel(stream, fd);
        case IFileSystem.O_RDWR:
            return new ReadWriteFileChannel(stream, fd);
        case IFileSystem.O_APPEND:
            return new WriteOnlyFileChannel(stream, fd, true);
        default:
            throw new RuntimeException("Unknown file channel type: "+mode); //$NON-NLS-1$
        }
	}
}
