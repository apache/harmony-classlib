/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
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
 * @author Oleg V. Khaschansky
 * @version $Revision$
 */
package org.apache.harmony.awt.gl.opengl;

public interface OGLContextManager {
    public static class OffscreenBufferObject {
        public final long id;
        public final int width;
        public final int height;

        public OffscreenBufferObject(long id, int width, int height) {
            this.id = id;
            this.width = width;
            this.height = height;
        }
    }

    public long getOGLContext(); // Creates OpenGL context based on GraphicsConfiguration
    public void destroyOGLContext(long oglContext); // Destroys existing OpenGL context
    public boolean makeCurrent(long oglContext, long drawable); // Makes OpenGL context current
    public boolean makeContextCurrent(long oglContext, long draw, long read);
    public void swapBuffers(long drawable);
    public OffscreenBufferObject createOffscreenBuffer(int w, int h);
    public void freeOffscreenBuffer(OffscreenBufferObject handle);
}
