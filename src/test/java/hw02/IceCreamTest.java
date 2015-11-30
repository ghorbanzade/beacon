package edu.umb.cs680.hw02;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

/**
* Unit tests for ice cream question.
*
* @author   Pejman Ghorbanzade
*/
public class IceCreamTest {

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Test
  public void getFlavorName() {
    Flavor flavor = Flavor.VANILLA;
    assertThat(flavor.getName(), is("vanilla"));
  }

  @Test
  public void valuesOfFlavor() {
    assertThat(Flavor.valueOf("VANILLA"), is(Flavor.VANILLA));
  }

  @Test
  public void getSizeName() {
    Size size = Size.SMALL;
    assertThat(size.getName(), is("small"));
  }

  @Test
  public void valuesOfSize() {
    assertThat(Size.valueOf("SMALL"), is(Size.SMALL));
  }

  @Test
  public void getToppingName() {
    Topping topping = Topping.NUTS;
    assertThat(topping.getName(), is("nuts"));
  }

  @Test
  public void valuesOfTopping() {
    assertThat(Topping.valueOf("NUTS"), is(Topping.NUTS));
  }

  @Test
  public void getScoopPrice() {
    Scoop scoop = new Scoop(Flavor.VANILLA);
    assertThat(scoop.getPrice(), is(500F));
  }

  @Test
  public void getScoopPrice2() {
    Scoop scoop = new Scoop(Flavor.LIME);
    assertThat(scoop.getPrice(), is(0F));
  }

  @Test
  public void priceOfEmptyContainer() {
    Container cone = new Cone(Size.SMALL);
    assertThat(cone.getPrice(), is(70F));
  }

  @Test
  public void priceOfNonEmptyContainer() {
    try {
      Container cone = new Cone(Size.MEDIUM);
      cone.addScoop(new Scoop(Flavor.VANILLA));
      Scoop scoop = new Scoop(Flavor.VANILLA);
      scoop.addScoop(Flavor.VANILLA);
      cone.addScoop(scoop);
      assertThat(cone.getPrice(), is(1640F));
    } catch (IceCreamException e) {
      fail("operation should not throw exception");
    }
  }

  @Test
  public void priceOfContainer() throws IceCreamException {
    exception.expect(IceCreamException.class);
    exception.expectMessage("Number of scoops exceeds size of container.");
    Container cone = new Cone(Size.SMALL);
    cone.addScoop(new Scoop(Flavor.VANILLA));
    cone.addScoop(new Scoop(Flavor.VANILLA));
  }

  @Test
  public void getScoopFlavor() {
    Scoop scoop = new Scoop(Flavor.VANILLA);
    assertThat(scoop.getFlavor(), is(Flavor.VANILLA));
  }

  @Test
  public void sizeOfCup() {
    Cup cup = new Cup(Size.SMALL);
    assertThat(cup.getSize(), is(Size.SMALL));
  }

  @Test
  public void sizeOfCone() {
    Cone cone = new Cone(Size.SMALL);
    assertThat(cone.getSize(), is(Size.SMALL));
  }

  @Test
  public void getToppingOfConeWithoutTopping() {
    Cone cone = new Cone(Size.SMALL);
    assertThat(cone.getTopping(), is(nullValue()));
  }

  @Test
  public void setToppingOfConeWithoutTopping() {
    try {
      Cone cone = new Cone(Size.SMALL);
      cone.setTopping(Topping.NUTS);
      assertThat(cone.getTopping(), is(not(nullValue())));
      assertThat(cone.getTopping(), is(Topping.NUTS));
    } catch (IceCreamException e) {
      fail("operation should not throw exception.");
    }
  }

  @Test
  public void setConeToppingForConeWithTopping() throws IceCreamException {
    exception.expect(IceCreamException.class);
    exception.expectMessage("Topping is already set.");
    Cone cone = new Cone(Size.SMALL);
    cone.setTopping(Topping.NUTS);
    cone.setTopping(Topping.EXTRACHOCOLATE);
  }

  @Test
  public void setConeToppingForNonEmptyCone() throws IceCreamException {
    exception.expect(IceCreamException.class);
    exception.expectMessage("Cone is not empty.");
    Cone cone = new Cone(Size.SMALL);
    cone.addScoop(new Scoop(Flavor.VANILLA));
    cone.setTopping(Topping.NUTS);
  }

  @Test
  public void getScoopsOfEmptyContainer() {
    Container cone = new Cone(Size.SMALL);
    ArrayList<Scoop> scoops = cone.getScoops();
    assertThat(scoops.size(), is(0));
    assertThat(cone.getNumScoops(), is(0));
  }

  @Test
  public void getScoopsOfNonEmptyContainer() {
    try {
      Container cone = new Cone(Size.SMALL);
      cone.addScoop(new Scoop(Flavor.VANILLA));
      ArrayList<Scoop> scoops = cone.getScoops();
      assertThat(scoops.size(), is(1));
      assertThat(cone.getNumScoops(), is(1));
      assertThat(scoops.get(0).getFlavor(), is(Flavor.VANILLA));
    } catch (IceCreamException e) {
      fail("operation should not throw exception.");
    }
  }

  @Test
  public void numberOfScoopsForConeWithStackedScoops() {
    try {
      Container cone = new Cone(Size.SMALL);
      Scoop scoop = new Scoop(Flavor.VANILLA);
      scoop.addScoop(Flavor.VANILLA);
      cone.addScoop(scoop);
      assertThat(cone.getNumScoops(), is(2));
    } catch (IceCreamException e) {
      fail("operation should not throw exception.");
    }
  }

  @Test
  public void tooManyStackedScoops() throws IceCreamException {
    exception.expect(IceCreamException.class);
    exception.expectMessage("Ice cream exceeds maximum number of layers.");
    Scoop scoop = new Scoop(Flavor.VANILLA);
    scoop.addScoop(Flavor.VANILLA);
    scoop.getUpperLayer().addScoop(Flavor.VANILLA);
    scoop.getUpperLayer().getUpperLayer().addScoop(Flavor.VANILLA);
  }

  @Test
  public void upperLayerOfScoop() {
    try {
      Container cone = new Cone(Size.SMALL);
      Scoop scoop = new Scoop(Flavor.VANILLA);
      assertThat(scoop.getUpperLayer(), is(nullValue()));
      scoop.addScoop(Flavor.CHOCOLATE);
      assertThat(scoop.getUpperLayer(), is(not(nullValue())));
      assertThat(scoop.getUpperLayer().getFlavor(), is(Flavor.CHOCOLATE));
    } catch (IceCreamException e) {
      fail("operation should not throw exception.");
    }
  }

  @Test
  public void layerNumberOfScoop() {
    try {
      Container cone = new Cone(Size.SMALL);
      Scoop scoop = new Scoop(Flavor.VANILLA);
      assertThat(scoop.getLayerNum(), is(1));
      scoop.addScoop(Flavor.CHOCOLATE);
      assertThat(scoop.getUpperLayer().getLayerNum(), is(2));
    } catch (IceCreamException e) {
      fail("operation should not throw exception.");
    }
  }

  @Test
  public void toppingsOfScoop() {
    Scoop scoop = new Scoop(Flavor.VANILLA);
    assertThat(scoop.getToppings().size(), is(0));
    scoop.addTopping(Topping.NUTS);
    scoop.addTopping(Topping.EXTRACHOCOLATE);
    ArrayList<Topping> expectedArray = new ArrayList<Topping>();
    expectedArray.add(Topping.NUTS);
    expectedArray.add(Topping.EXTRACHOCOLATE);
    assertThat(scoop.getToppings().size(), is(2));
    assertThat(scoop.getToppings(), is(expectedArray));
  }

  @Test
  public void stringOfScoopWithNoTopping() {
    Scoop scoop = new Scoop(Flavor.VANILLA);
    assertThat(scoop.toString(), containsString(scoop.getFlavor().getName()));
  }

  @Test
  public void stringOfScoopWithTopping() {
    Scoop scoop = new Scoop(Flavor.VANILLA);
    scoop.addTopping(Topping.NUTS);
    assertThat(scoop.toString(), containsString("with"));
    assertThat(scoop.toString(), containsString(scoop.getToppings().get(0).getName()));
  }

  @Test
  public void stringOfScoopWithUpperLayer() throws IceCreamException {
    try {
      Scoop scoop = new Scoop(Flavor.VANILLA);
      scoop.addScoop(Flavor.CHOCOLATE);
      assertThat(scoop.toString(), containsString(scoop.getUpperLayer().getFlavor().getName()));
    } catch (IceCreamException e) {
      fail("operation should not throw exception.");
    }
  }

  @Test
  public void stringOfContainer() {
    try {
      Container cone = new Cone(Size.SMALL);
      Scoop scoop = new Scoop(Flavor.VANILLA);
      cone.addScoop(scoop);
      assertThat(cone.toString(), containsString(cone.getSize().getName()));
      assertThat(cone.toString(), containsString(" scoops"));
    } catch (IceCreamException e) {
      fail("operation should not throw exception.");
    }
  }

}
