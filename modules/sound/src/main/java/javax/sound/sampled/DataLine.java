/*
 *  Copyright 2006 The Apache Software Foundation or its licensors, as applicable.
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

package javax.sound.sampled;

public interface DataLine extends Line {

    class Info extends Line.Info {
        private AudioFormat[] formats;
        private int minBufferSize;
        private int maxBufferSize;
        
        public Info(Class <?> lineClass, AudioFormat format) {
            super(lineClass);

            this.formats = new AudioFormat[] { format };
            this.minBufferSize = AudioSystem.NOT_SPECIFIED;
            this.maxBufferSize = AudioSystem.NOT_SPECIFIED;
        }

        public Info(Class <?> lineClass, AudioFormat[] formats, int minBufferSize, int maxBufferSize) {
            super(lineClass);

            this.formats = formats;
            this.minBufferSize = minBufferSize;
            this.maxBufferSize = maxBufferSize;
        }

        public Info(Class <?> lineClass, AudioFormat format, int bufferSize) {
            super(lineClass);

            this.formats = new AudioFormat[] { format };
            this.minBufferSize = bufferSize;
            this.maxBufferSize = bufferSize;
        }

        public AudioFormat[] getFormats(){
            return formats;
        }
        
        public boolean isFormatSupported(AudioFormat format) {
            throw new Error("not yet implemented");
        }

        public int getMinBufferSize() {
            return minBufferSize;
        }

        public int getMaxBufferSize() {
            return maxBufferSize;
        }

        public boolean matches(Line.Info info) {
            throw new Error("not yet implemented");
        }
        
        public String toString() {
            throw new Error("not yet implemented");
        }
    }

    int available();

    void drain();

    void flush();
    
    int getBufferSize();
    
    AudioFormat getFormat();
    
    int getFramePosition();
    
    float getLevel();
    
    long getLongFramePosition();
    
    long getMicroSecondPosition();
    
    boolean isActive();
    
    boolean isRunning();
    
    void start();
    
    void stop();
}
