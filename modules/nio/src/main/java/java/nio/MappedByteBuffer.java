/* Copyright 2004 The Apache Software Foundation or its licensors, as applicable
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

package java.nio;

import com.ibm.io.nio.DirectBuffer;
import com.ibm.platform.IMemorySystem;
import com.ibm.platform.struct.PlatformAddress;

/**
 * <code>MappedByteBuffer</code> is a special kind of direct byte buffer,
 * which maps a region of file to memory.
 * <p>
 * <code>MappedByteBuffer</code> can be created by calling
 * {@link java.nio.channels.FileChannel#map(java.nio.channels.FileChannel.MapMode, long, long) FileChannel.map}.
 * Once created, the mapping between the byte buffer and the file region remains
 * valid until the byte buffer is garbage collected.
 * </p>
 * <p>
 * All or part of a <code>MappedByteBuffer</code>'s content may change or
 * become inaccessible at any time, since the mapped file region can be modified
 * by another thread or process at any time. If this happens, the behavior of
 * the <code>MappedByteBuffer</code> is undefined.
 * </p>
 * 
 */
public abstract class MappedByteBuffer extends ByteBuffer {

    final DirectByteBuffer wrapped;

    private int mapMode;

    MappedByteBuffer(ByteBuffer directBuffer) {
        super(directBuffer.capacity);
        if (!directBuffer.isDirect()) {
            throw new IllegalArgumentException();
        }
        this.wrapped = (DirectByteBuffer) directBuffer;

    }

    MappedByteBuffer(PlatformAddress addr, int capa, int offset, int mode) {
        super(capa);
        mapMode = mode;
        switch (mapMode) {
        case IMemorySystem.MMAP_READ_ONLY:
            wrapped = new ReadOnlyDirectByteBuffer(addr, capa, offset);
            break;
        case IMemorySystem.MMAP_READ_WRITE:
        case IMemorySystem.MMAP_WRITE_COPY:
            wrapped = new ReadWriteDirectByteBuffer(addr, capa, offset);
            break;
        default:
            throw new IllegalArgumentException();
        }
        addr.autoFree();
    }

	/**
	 * Returns true if this buffer's content is loaded.
	 * 
	 * @return True if this buffer's content is loaded.
	 */
	public final boolean isLoaded() {
		return ((DirectBuffer) wrapped).getEffectiveAddress().mmapIsLoaded(
                wrapped.capacity());
	}

	/**
	 * Loads this buffer's content into memory.
	 * 
	 * @return This buffer
	 */
	public final MappedByteBuffer load() {
		((DirectBuffer) wrapped).getEffectiveAddress().mmapLoad(
                wrapped.capacity());
		return this;
	}

    /**
     * TODO: JavaDoc
     * @return
     */
    public final MappedByteBuffer force() {
        if (mapMode == IMemorySystem.MMAP_READ_WRITE) {
            ((DirectBuffer) wrapped).getEffectiveAddress().mmapFlush(
                    wrapped.capacity());
        }
        return this;
    }
}
