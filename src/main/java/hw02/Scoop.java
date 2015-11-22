//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw02;

import java.util.ArrayList;

/**
* This class defines a scoop. A scoop object can have one of flavors supported
* in the Flavor enumeration class. It has a price depending on the flavor.
* Scoops can sit on top of each other provided that the number of layers of
* the ice cream does not exceed the maximum number of layers supported for
* the container of the specified size. Client can order a list of toppings for
* each scoop of his ice cream with no extra charge.
*
* @author Pejman Ghorbanzade
* @see    Container
* @see    Flavor
*/
public class Scoop {
  /**
  * The scoop has a flavor and a list of toppings.
  */
  private float price;
  private int layerNum;
  private Flavor flavor;
  private Scoop upper = null;
  private ArrayList<Topping> toppings = new ArrayList<Topping>();

  /**
  * This constructor is used to add new scoops that could sit on the container.
  * Although public, this constructor is only called through addScoop method of
  * Container object.
  *
  * @param flavor the flavor of the new scoop
  */
  public Scoop(Flavor flavor) {
    this.flavor = flavor;
    this.layerNum = 1;
    setPrice(flavor);
  }

  /**
  * This private constructor is invoked when client tries to add a scoop on
  * top of a another scoop, in which case the layer number of the new scoop
  * is set to layer number of parent scoop, incremented by one.
  *
  * @param flavor the flavor of the the new scoop
  * @param layerNum the layer number of the new scoop
  */
  private Scoop(Flavor flavor, int layerNum) {
    this.flavor = flavor;
    this.layerNum = layerNum;
    setPrice(flavor);
  }

  /**
  * Everytime a new scoop is added to the ice cream, the price of the scoop is
  * obtained based on its flavor according to the configuration file.
  *
  * @param flavor the flavor of the new scoop
  */
  private void setPrice(Flavor flavor) {
    IceCreamConfig config = IceCreamConfig.getInstance();
    String priceStr = config.get("scoop.price.%s", flavor.getName());
    this.price = priceStr.isEmpty() ? 0 : Float.parseFloat(priceStr);
  }

  /**
  * The client is able to order multiple toppings for each of its scoops.
  * Toppings are of no charge and can be added to a scoop as many times as
  * the client wants.
  *
  * @param topping the topping of the scoop
  */
  public void addTopping(Topping topping) {
    this.toppings.add(topping);
  }

  /**
  * This method adds a new scoop on top of current scoop while making sure
  * the total number of layers in our ice cream will not exceed the maximum
  * number of layers for the container of the specified size.
  *
  * @param flavor the flavor for the new scoop
  * @throws IceCreamException in case the number of layers exceeds the maximum
  *         number of layers for container of the specified size.
  */
  public void addScoop(Flavor flavor) throws IceCreamException {
    IceCreamConfig config = IceCreamConfig.getInstance();
    String str = config.get("scoop.maxlayers");
    int max = str.isEmpty() ? 0 : Integer.parseInt(str);
    if (layerNum == max) {
      throw new IceCreamException("Ice cream exceeds maximum number of layers.");
    }
    this.upper = new Scoop(flavor, layerNum + 1);
  }

  /**
  * This method gives access to the scoop sitting on top of the current scoop.
  *
  * @return the scoop sitting on top of the current scoop
  */
  public Scoop getUpperLayer() {
    return this.upper;
  }

  /**
  * This method gives acess to the layer number the scoop is in.
  *
  * @return the number of layer the scoop is in
  */
  public int getLayerNum() {
    return this.layerNum;
  }

  /**
  * This getter method gives access to the toppings on the current scoop.
  *
  * @return a list of toppings set for the current scoop.
  */
  public ArrayList<Topping> getToppings() {
    return this.toppings;
  }

  /**
  * This method gives access to the flavor of the current scoop.
  *
  * @return the flavor of the current scoop
  */
  public Flavor getFlavor() {
    return this.flavor;
  }

  /**
  * This method gives access to the price of the current scoop. The price is
  * precalculated during instantiation and based on the flavor requested by
  * the user, according to application configuration file.
  *
  * @return price of the current scoop.
  */
  public float getPrice() {
    return this.price;
  }

  /**
  * This method makes it easy to generate a receipt for the client by preparing
  * a string representation of the scoops used in the ice cream.
  *
  * @return a string representation of the scoop containing information about
  *         its flavor and its toppings.
  */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("\t$%4.2f %s scoop", this.getPrice(), this.flavor.getName()));
    if (this.toppings.size() != 0) {
      sb.append(" with ");
      for (Topping topping: this.toppings) {
        sb.append(String.format("%s ", topping.getName()));
      }
      sb.append("topping");
    }
    sb.append(String.format(".%n"));
    if (this.getUpperLayer() != null) {
      sb.append(this.getUpperLayer().toString());
    }
    return sb.toString();
  }
}
