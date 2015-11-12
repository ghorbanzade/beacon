package edu.umb.cs680.hw01;

import static org.junit.Assert.*;
import org.junit.Test;

public class HelloWorldTest {
	@Test
	public void testHelloWorld1(){
		HelloWorld hello = new HelloWorld();
		assertEquals("Hello World!", hello.sayHello());
	}
	@Test
	public void testHelloWorld2(){
		HelloWorld hello = new HelloWorld();
		assertEquals("Hello World!", hello.sayHello());
	}
	@Test
	public void testHelloWorld3(){
		HelloWorld hello = new HelloWorld();
		assertEquals("Good Bye!", hello.sayGoodBye());
	}
}
