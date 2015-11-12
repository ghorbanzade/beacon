// package edu.umb.cs680;
import static org.junit.Assert.*;
import org.junit.Test;
public class HelloWorldTest {
	@Test
	public void testHellowWorld(){
		HelloWorld hello = new HelloWorld();
		assertEquals("Hello World!", hello.sayHello());
	}
}
