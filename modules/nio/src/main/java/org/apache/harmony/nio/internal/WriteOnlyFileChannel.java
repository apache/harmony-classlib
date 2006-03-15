/* Copyright 2005, 2006 The Apache Software Foundation or its licensors, as applicable
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
package org.apache.harmony.nio.internal;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileLock;
import java.nio.channels.NonReadableChannelException;
import java.nio.channels.WritableByteChannel;
import java.nio.channels.FileChannel.MapMode;

public final class WriteOnlyFileChannel extends FileChannelImpl {

	private boolean append = false;

	public WriteOnlyFileChannel(Object stream, long handle) {
		super(stream, handle);
	}

	public WriteOnlyFileChannel(Object stream, long handle, boolean isAppend) {
		super(stream, handle);
		append = isAppend;
	}

	public long transferTo(long position, long count, WritableByteChannel target)
			throws IOException {
		openCheck();
		if (null == target) {
			throw new NullPointerException();
		}
		throw new NonReadableChannelException();
	}

	public long read(ByteBuffer[] buffers, int offset, int length)
			throws IOException {
		openCheck();
		throw new NonReadableChannelException();
	}

	public int read(ByteBuffer buffer) throws IOException {
		openCheck();
		throw new NonReadableChannelException();
	}

	public int read(ByteBuffer buffer, long position) throws IOException {
		openCheck();
		throw new NonReadableChannelException();
	}

	public MappedByteBuffer map(MapMode mode, long position, long size)
			throws IOException {
		openCheck();
		if (mode == null) {
			throw new NullPointerException();
		}
		if (position < 0 || size < 0 || size > Integer.MAX_VALUE) {
			throw new IllegalArgumentException();
		}
		throw new NonReadableChannelException();
	}

	public int write(ByteBuffer buffer) throws IOException {
		if (append) {
			position(size());
		}
		return super.write(buffer);
	}

	protected final FileLock basicLock(long position, long size,
			boolean shared, boolean wait) throws IOException {
		if (shared) {
			throw new NonReadableChannelException();
		}
		return super.basicLock(position, size, shared, true);
	}

}
