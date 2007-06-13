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

package org.apache.harmony.niochar.charset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

import org.apache.harmony.nio.AddressUtil;


public class ISO_8859_1 extends Charset {

        public ISO_8859_1(String csName, String[] aliases) {
            super(csName, aliases);
        }

        public boolean contains(Charset cs) {
            return cs instanceof ISO_8859_1 || cs instanceof US_ASCII;
        }

        public CharsetDecoder newDecoder() {
            return new Decoder(this);
        }

        public CharsetEncoder newEncoder() {
            return new Encoder(this);
        }

	private final class Decoder extends CharsetDecoder{
		private Decoder(Charset cs){
			super(cs, 1, 1);

		}

		public native int nDecode(char[] array, int arrPosition, int remaining, long outAddr, int absolutePos);


		protected CoderResult decodeLoop(ByteBuffer bb, CharBuffer cb){
                        int cbRemaining = cb.remaining();

		        if(bb.isDirect() && bb.hasRemaining() && cb.hasArray()){
		            int toProceed = bb.remaining();
		            boolean throwOverflow = false; 
		            int cbPos = cb.position();
		            int bbPos = bb.position();
		            if( cbRemaining < toProceed ) { 
		                toProceed = cbRemaining;
                                throwOverflow = true;
                            }
                            int res = nDecode(cb.array(), cb.arrayOffset()+cbPos, toProceed, AddressUtil.getDirectBufferAddress(bb), bbPos);
                            cb.position(cbPos+res);  
                            bb.position(bbPos+res);
                            if(throwOverflow) return CoderResult.OVERFLOW;
                        }else{
                        	if(bb.hasArray() && cb.hasArray()) {
                                int rem = bb.remaining();
                                rem = cbRemaining >= rem ? rem : cbRemaining;
                                byte[] arr = bb.array();
                                char[] cArr = cb.array();
                                int bStart = bb.position();
                                int cStart = cb.position();
                                int i;
                                for(i=bStart; i<bStart+rem; i++) {
                                    cArr[cStart++] = (char)((int)arr[i] & 0xFF);
                                }
                                bb.position(i);
                                cb.position(cStart);
                                if(rem == cbRemaining && bb.hasRemaining()) return CoderResult.OVERFLOW;
                        	} else {
                                int rem = bb.remaining();
                                rem = cbRemaining >= rem ? rem : cbRemaining;
                                byte[] arr = new byte[rem];
                                bb.get(arr);
                                char[] cArr = new char[rem];
                                for(int i=0; i<rem; i++) {
                                    cArr[i] = (char)((int)arr[i] & 0xFF);
                                }
                                cb.put(cArr); if(cb.remaining() == 0) return CoderResult.OVERFLOW;
                        	}
			} 
                        return CoderResult.UNDERFLOW;
		}
        }

	private final class Encoder extends CharsetEncoder{
		private Encoder(Charset cs){
			super(cs, 1, 1);      
		}
                   
		private native void nEncode(long outAddr, int absolutePos, char[] array, int arrPosition, int[] res);
                                                                                                                          
		protected CoderResult encodeLoop(CharBuffer cb, ByteBuffer bb){                                             
                        int bbRemaining = bb.remaining();                                                                     
                        if(bb.isDirect() && cb.hasRemaining() && cb.hasArray()){                                                
		            int toProceed = cb.remaining();
		            boolean throwOverflow = false; 
		            int cbPos = cb.position();
		            int bbPos = bb.position();
		            if( bbRemaining < toProceed ) { 
		                toProceed = bbRemaining;
                                throwOverflow = true;
                            }
                            int[] res = {toProceed, 0};
                            nEncode(AddressUtil.getDirectBufferAddress(bb), bbPos, cb.array(), cb.arrayOffset()+cbPos, res);
                            if( res[0] <= 0 ) {                                                                                                                
                                bb.position(bbPos-res[0]);                                                                                        
                                cb.position(cbPos-res[0]);
                                if(res[1]!=0) {
                                    if(res[1] < 0)
                                        return CoderResult.malformedForLength(-res[1]);
                                    else 
                                        return CoderResult.unmappableForLength(res[1]);
                                }
                            }else{                                          
                                bb.position(bbPos+res[0]);               
                                cb.position(cbPos+res[0]);                 
                                if(throwOverflow) return CoderResult.OVERFLOW;
                            }                                                     
                        }else{
                        	if(bb.hasArray() && cb.hasArray()) {
                            	byte[] byteArr = bb.array();
                            	char[] charArr = cb.array();
                                int rem = cb.remaining();
                                int byteArrStart = bb.position();
                                rem = bbRemaining <= rem ? bbRemaining : rem;
                                int x;
                                for(x=cb.position(); x < cb.position()+rem; x++) {                                 
                                    char c = charArr[x];              
                                    if(c > (char)0x00FF){   
                                        if (c >= 0xD800 && c <= 0xDFFF) {
                                            if(x+1 < cb.limit()) {
                                                char c1 = charArr[x+1];
                                                if(c1 >= 0xD800 && c1 <= 0xDFFF) {
                                                    bb.position(byteArrStart); cb.position(x);
                                                    return CoderResult.unmappableForLength(2);             
                                                } 
                                            } else {
                                                bb.position(byteArrStart); cb.position(x);
                                                return CoderResult.UNDERFLOW;             
                                            }
                                            bb.position(byteArrStart); cb.position(x);
                                            return CoderResult.malformedForLength(1);             
                                        }
                                        bb.position(byteArrStart); cb.position(x);
                                        return CoderResult.unmappableForLength(1);             
                                    }else{
                                        byteArr[byteArrStart++] = (byte)c;
                                    }                                                                  
                                } 
                                bb.position(byteArrStart);
                                cb.position(x);
                                if(rem == bbRemaining && cb.hasRemaining()) {
                                	return CoderResult.OVERFLOW;
                                }
                        	} else {
                                while(cb.hasRemaining()){                                 
                                    if( bbRemaining == 0 ) return CoderResult.OVERFLOW;    
                                    char c = cb.get();              
                                    if(c > (char)0x00FF){   
                                        if (c >= 0xD800 && c <= 0xDFFF) {
                                            if(cb.hasRemaining()) {
                                                char c1 = cb.get();
                                                if(c1 >= 0xD800 && c1 <= 0xDFFF) {
                                                    cb.position(cb.position()-2);
                                                    return CoderResult.unmappableForLength(2);             
                                                } else {
                                                	cb.position(cb.position()-1);
                                                }
                                            } else {
                                                cb.position(cb.position()-1);
                                                return CoderResult.UNDERFLOW;             
                                            }
                                            cb.position(cb.position()-1);
                                            return CoderResult.malformedForLength(1);             
                                        }
                                        cb.position(cb.position()-1);
                                        return CoderResult.unmappableForLength(1);             
                                    }else{                                                      
                                        bb.put((byte)c);                                      
                                        bbRemaining--;                                                
                                    }                                                                  
                                }                                                                       
                        		
                        	}
			}                                                                            
			return CoderResult.UNDERFLOW;                                                 
		}                                                                                     
                                                                                                      
	}         
}
