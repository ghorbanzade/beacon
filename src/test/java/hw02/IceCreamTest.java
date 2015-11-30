package edu.umb.cs680.hw02;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
* Unit tests for ice cream question.
*
* @author   Pejman Ghorbanzade
*/
public class IceCreamTest {
  @Test
  public void getPriceTest() {
    Scoop scoop = new Scoop(Flavor.VANILLA);
    assertThat(scoop.getPrice(), is(500F));
  }
}
