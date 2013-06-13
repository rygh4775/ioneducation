package net.ion.bleujin.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import junit.framework.TestCase;
import net.ion.bleujin.CustomClassLoader;
import net.ion.definition.Greeting;


// http://www.slideshare.net/CiaranMcHale/java-reflection-explained-simply

public class TestClassLoader extends TestCase {


	private CustomClassLoader cloader;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.cloader = new CustomClassLoader(TestClassLoader.class.getClassLoader());
	}
	
	public void testLoad() throws Exception {
		Class clz = cloader.loadClass("net.ion.bleujin.SayHello");
		Object ins = clz.newInstance();
		
		Method m = clz.getMethod("hello");
		Object result = m.invoke(ins) ;
		
		assertEquals("hello", result) ;
	}
	
	
	public void testSayHi() throws Exception {
		Class clz = cloader.loadClass("net.ion.bleujin.SayHi") ;
		Constructor cons = clz.getDeclaredConstructor(String.class);
		cons.setAccessible(true) ;
		
		Object ins = cons.newInstance("bleujin");
		Method m = clz.getMethod("hi") ;
		
		Object result = m.invoke(ins) ;
		assertEquals("hi bleujin", result) ;
	}


	public void testGreeting() throws Exception {
		Class clz = cloader.loadClass("net.ion.bleujin.SayGreeting") ;
		Greeting ins = (Greeting)clz.newInstance();
		assertEquals("gombangwa", ins.greeting()) ;
	}

	
	
	

}
