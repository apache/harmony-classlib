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

package ar.org.fitc.test.util.xmlcompare;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.concurrent.SynchronousQueue;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class Result {
	
	public Result(String url) {
		super();
		this.url = url;
	}
		
	public String name;
	public String url;
	public int pluss = 0;
	private static double porciento = 0.10;
	
	public String getName() {
		return name;
	}
	
	public static void setPrecition(double d) {
		if (d > 1)
			throw new ArithmeticException(d + " is gread than 1");
		porciento = d;
	}
	
	public static double getPrecition() {
		return porciento;
	}
	
	public long calls;
	
	public long totaltime;

	public void set(String tag, String data) {
		if (tag.endsWith("calls ")) {
			calls = Long.valueOf(data);
		} else if (tag.endsWith("totaltime")) {
			totaltime = Long.valueOf(data);
		} else if (tag.endsWith("name")) {
			name = data;
		}
	}
	
	public boolean newClass() {
		// si no comienza con espacio, es un metodo de la misma clase
		// en caso contrario, paso para sincronizar 
		return !name.startsWith(" ");
	}

	public boolean compare(Result other) {
		if (other == null) {
//			System.err.print("..............................................");
//			System.err.println("..............................................");
//			System.err.println("test1 : " + name);
//			System.err.println("test2 : " + other.name);
			return false;
		}
		double myTime = (calls > 0 ? totaltime / (double) calls: totaltime);
		double otherTime = (other.calls > 0 ? other.totaltime / (double) other.calls: other.totaltime);
		if (compare(myTime, otherTime)) {
			System.out.print("..............................................");
			System.out.println("..............................................");
			System.out.println("Archivos1 : " + url);
			System.out.println("Archivos2 : " + other.url);
			System.out.println("test : " + name);
			System.out.println("tiempos : ");
			System.out.println("promedio1 : \t" + myTime);
			System.out.println("promedio2 : \t" + otherTime);
			if (pluss != other.pluss) { 
				System.out.println("Pluss 1: " + pluss + "\t 2: " +other.pluss);
			}
		} else {
			System.out.print("..............................................");
			System.out.println("..............................................");
			System.out.println("test OK: " + name);
		}
		return true;
	}

	
	public static boolean compare(double a, double b) {
		if (a > b) {
			return ((a - b) / a) > porciento;
		} else {
			return ((b - a) / b) > porciento;
		}
	}

	public static boolean compare(long a, long b) {
		if (a > b) {
			return ((a - b) / (double) a) > porciento;
		} else {
			return ((b - a) / (double) b) > porciento;
		}
	}
	

	public void pluss(Result other) {
		if (other != null) {
			calls += other.calls;
			totaltime += other.totaltime;
			pluss = pluss + other.pluss + 1;
		}
	}
}

public class ComparerHandler extends DefaultHandler {
	
	public static void main(String[] argv) throws Exception {
		System.out.println("Start");
		run("profile.class.Crypto.ITC.html");
		run("profile.class.Crypto.SUN.html");
	}
	
	public static Thread run(final String url)
	throws ParserConfigurationException, SAXException {
		final SAXParser parser1 = SAXParserFactory.newInstance().newSAXParser();
		final ComparerHandler compare1 = new ComparerHandler(url);
		Thread t = new Thread() {
			public void run() {
				try {
					parser1.parse(new Html2XmlInputStream(new File(url)), compare1);
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		t.start();
		return t;
	}
	
	static private ComparerHandler productor = null;
	private SynchronousQueue<Hashtable<String, Result>> queue;
	
	
	private String data;
	private String url;
	private String tag;
	private Result result;
	private Hashtable<String, Result> totalClassResult;
	
	public ComparerHandler(String url) {
		super();
		this.url = url;
		synchronized (ComparerHandler.class) {
			if (productor == null) {
				productor = this;
				queue = new SynchronousQueue<Hashtable<String, Result>>();
			} else {
				queue = productor.queue;
			}
		}
	}
	
	public void startElement(String uri, String localName, String qName,
			Attributes attrs) throws SAXException {
		if (qName.equals("test")) {
			result = new Result(url);
		}
		tag = qName;
		data = "";
//		System.out.println("<" + qName + ">");
	}
	
	public void endElement(String uri, String localName, String qName)
	throws SAXException {
//		System.out.println("</" + qName + ">");
		if (qName.equals("test")) {
			if (productor == this) {
				if (result.newClass()) {
					if (totalClassResult != null) {
						try {
							queue.put(totalClassResult);
						} catch (InterruptedException e) {
							throw new SAXException(e);
						}
					}
					totalClassResult = new Hashtable<String, Result>();
				}
				if (totalClassResult.put(result.getName(), result) != null) {
					throw new SAXException("Obtein result of " + result.getName() + " fine that have just had result");
				}
			} else {
				if (result.newClass()) {
					try {
						totalClassResult = queue.take();
					} catch (InterruptedException e) {
						throw new SAXException(e);
					}
					System.out.println("........New Class " + result.getName() + " ..............");
				}
				result.compare(totalClassResult.get(result.getName()));			
			}
			result = null;
		} else if(result != null) {
			result.set(tag, data);
		} else if (qName.equals("PerformaceTest")) {
			if (productor == this) {
				try {
						queue.put(totalClassResult);
				} catch (InterruptedException e) {
						throw new SAXException(e);
				}
			}
		}
		tag = "";
	}
	
	public void characters(char[] ch, int start, int length)
	throws SAXException {
		data += String.copyValueOf(ch, start, length);
//		System.out.println(String.copyValueOf(ch, start, length));
	}
	
}
