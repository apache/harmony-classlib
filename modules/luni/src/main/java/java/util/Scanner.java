/* Copyright 2006 The Apache Software Foundation or its licensors, as applicable
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
package java.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import org.apache.harmony.luni.util.NotYetImplementedException;

/**
 * A parser that parses a text string to primitive types with the help of
 * regular expression. It supports localized number and various radixes.
 * 
 * The input is broken into tokens by the delimiter pattern, which is whitespace
 * by default. The primitive types can be got via corresponding next methods. If
 * the token is not in valid format, an InputMissmatchException is thrown.
 * 
 * For example: Scanner s = new Scanner("1A true");
 * System.out.println(s.nextInt(16)); System.out.println(s.nextBoolean()); The
 * result: 26 true
 * 
 * A scanner can find or skip specific pattern with no regard to the delimiter.
 * All these methods and the various next and hasNext methods may block.
 * 
 * Scanner is not thread-safe without external synchronization
 */
public final class Scanner implements Iterator<String> {

    public Scanner(File src) throws FileNotFoundException {
        throw new NotYetImplementedException();
    }

    public Scanner(File src, String charsetName) throws FileNotFoundException {
        throw new NotYetImplementedException();
    }

    public Scanner(String src) {
        throw new NotYetImplementedException();
    }

    public Scanner(InputStream src) {
        throw new NotYetImplementedException();
    }

    public Scanner(InputStream src, String charsetName) {
        throw new NotYetImplementedException();
    }

    public Scanner(ReadableByteChannel src) {
        throw new NotYetImplementedException();
    }

    public Scanner(Readable src) {
        throw new NotYetImplementedException();
    }

    public Scanner(ReadableByteChannel src, String charsetName) {
        throw new NotYetImplementedException();
    }

    public void close() {
        throw new NotYetImplementedException();
    }

    public Pattern delimiter() {
        throw new NotYetImplementedException();
    }

    public String findInLine(Pattern pattern) {
        throw new NotYetImplementedException();
    }

    public String findInLine(String pattern) {
        throw new NotYetImplementedException();
    }

    public String findWithinHorizon(Pattern pattern, int horizon) {
        throw new NotYetImplementedException();
    }

    public String findWithinHorizon(String pattern, int horizon) {
        throw new NotYetImplementedException();
    }

    public boolean hasNext() {
        throw new NotYetImplementedException();
    }

    public boolean hasNext(Pattern pattern) {
        throw new NotYetImplementedException();
    }

    public boolean hasNext(String pattern) {
        throw new NotYetImplementedException();
    }

    public boolean hasNextBigDecimal() {
        throw new NotYetImplementedException();
    }

    public boolean hasNextBigInteger() {
        throw new NotYetImplementedException();
    }

    public boolean hasNextBigInteger(int radix) {
        throw new NotYetImplementedException();
    }

    public boolean hasNextBoolean() {
        throw new NotYetImplementedException();
    }

    public boolean hasNextByte() {
        throw new NotYetImplementedException();
    }

    public boolean hasNextByte(int radix) {
        throw new NotYetImplementedException();
    }

    public boolean hasNextDouble() {
        throw new NotYetImplementedException();
    }

    public boolean hasNextFloat() {
        throw new NotYetImplementedException();
    }

    public boolean hasNextInt() {
        throw new NotYetImplementedException();
    }

    public boolean hasNextInt(int radix) {
        throw new NotYetImplementedException();
    }

    public boolean hasNextLine() {
        throw new NotYetImplementedException();
    }

    public boolean hasNextLong() {
        throw new NotYetImplementedException();
    }

    public boolean hasNextLong(int radix) {
        throw new NotYetImplementedException();
    }

    public boolean hasNextShort() {
        throw new NotYetImplementedException();
    }

    public boolean hasNextShort(int radix) {
        throw new NotYetImplementedException();
    }

    public IOException ioException() {
        throw new NotYetImplementedException();
    }

    public Locale locale() {
        throw new NotYetImplementedException();
    }

    public MatchResult match() {
        throw new NotYetImplementedException();
    }

    public String next() {
        throw new NotYetImplementedException();
    }

    public String next(Pattern pattern) {
        throw new NotYetImplementedException();
    }

    public String next(String pattern) {
        throw new NotYetImplementedException();
    }

    public boolean nextBigDecimal() {
        throw new NotYetImplementedException();
    }

    public boolean nextBigInteger() {
        throw new NotYetImplementedException();
    }

    public boolean nextBigInteger(int radix) {
        throw new NotYetImplementedException();
    }

    public boolean nextBoolean() {
        throw new NotYetImplementedException();
    }

    public boolean nextByte() {
        throw new NotYetImplementedException();
    }

    public boolean nextByte(int radix) {
        throw new NotYetImplementedException();
    }

    public boolean nextDouble() {
        throw new NotYetImplementedException();
    }

    public boolean nextFloat() {
        throw new NotYetImplementedException();
    }

    public boolean nextInt() {
        throw new NotYetImplementedException();
    }

    public boolean nextInt(int radix) {
        throw new NotYetImplementedException();
    }

    public boolean nextLine() {
        throw new NotYetImplementedException();
    }

    public boolean nextLong() {
        throw new NotYetImplementedException();
    }

    public boolean nextLong(int radix) {
        throw new NotYetImplementedException();
    }

    public boolean nextShort() {
        throw new NotYetImplementedException();
    }

    public boolean nextShort(int radix) {
        throw new NotYetImplementedException();
    }

    public int radix() {
        throw new NotYetImplementedException();
    }

    public Scanner skip(Pattern pattern) {
        throw new NotYetImplementedException();
    }

    public Scanner skip(String pattern) {
        throw new NotYetImplementedException();
    }

    public String toString() {
        throw new NotYetImplementedException();
    }

    public Scanner useDelimiter(Pattern pattern) {
        throw new NotYetImplementedException();
    }

    public Scanner useDelimiter(String pattern) {
        throw new NotYetImplementedException();
    }

    public Scanner useLocale(Locale locale) {
        throw new NotYetImplementedException();
    }

    public Scanner useRadix(int radix) {
        throw new NotYetImplementedException();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
