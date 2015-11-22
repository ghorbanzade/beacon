//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw02;

import java.util.ArrayList;

/**
* This abstract class defines the container that holds the scoops.
* Each container has a price and a size and can hold a number of scoops.
* The structure of scoops is important.
* <ul>
* <li>Container cannot hold more than MAX_NUM_SCOOP scoops in total.</li>
* <li>Container cannot have more than MAX_NUM_LAYERS layers.</li>
* <li>Container cannot have more than MAX_NUM_SCOOPS_PER_LAYER scoops in
* each layer.</li>
* </ul>
*
* @author   Pejman Ghorbanzade
* @see      Size
* @see      Cone
* @see      Cup
*/
public abstract class Container {
  private ArrayList<Scoop> scoops = new ArrayList<Scoop>();
  private String type;
  private float price;
  private Size size;
  private int capacity;

  /**
  * This constructor initializes price and size of the container.
  * Once called by its subclasses that pass the size this constructor will
  * lookup the price of the container of the specified size from the
  * ice cream configuration file.
  *
  * @param type name of the subclass calling this constructor
  * @param size size of the container
  */
  public Container(String type, Size size) {
    this.type = type;
    this.size = size;
    IceCreamConfig config = IceCreamConfig.getInstance();
    String priceStr = config.get("price.%s.%s", this.type, size.getName());
    this.price = priceStr.isEmpty() ? 0 : Float.parseFloat(priceStr);
    String capacityStr = config.get("capacity.%s.%s", this.type, this.size.getName());
    this.capacity = capacityStr.isEmpty() ? 0 : Integer.parseInt(capacityStr);
  }

  /**
  * This method allows client to add scoops to the container.
  * The number of scoops that can sit on the container cannot be more than
  * the capacity of the container (according to its size) as specified in
  * the application configuration file.
  *
  * @param scoop new scoop the be added to the container
  * @throws IceCreamException in case the container's capacity is full
  */
  public void addScoop(Scoop scoop) throws IceCreamException {
    if (this.scoops.size() >= this.capacity) {
      throw new IceCreamException("Number of scoops exceeds size of container.");
    }
    this.scoops.add(scoop);
  }

  /**
  * This method gives access to the scoops the container is holding.
  *
  * @return an array list of scoops the container is holding
  */
  public ArrayList<Scoop> getScoops() {
    return this.scoops;
  }

  /**
  * This method gives access to price of the ice cream which is defined by
  * price of the container in addition to price of the scoops it holds.
  *
  * @return price of the container along with price of all the scoops it holds
  */
  public float getPrice() {
    float price = this.price;
    for (Scoop scoop: this.scoops) {
      do {
        price += scoop.getPrice();
        scoop = scoop.getUpperLayer();
      } while (scoop != null);
    }
    return price;
  }

  /**
  * Although not needed for this particular problem, bearing extensibility in
  * mind, it makes sense to let client access the Size of the container.
  *
  * @return the size of the container.
  */
  public Size getSize() {
    return this.size;
  }

  /**
  * This method gives access to the total number of scoops the container is holding.
  *
  * @return the number of scoops the container is holding
  */
  public int getNumScoops() {
    int sum = 0;
    for (Scoop scoop: this.scoops) {
      while (scoop != null) {
        scoop = scoop.getUpperLayer();
        sum++;
      }
    }
    return sum;
  }

  /**
  * This method allows easy output of the information about the scoop.
  *
  * @return a string literal containing the information about the scoop
  */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("$%4.2f %s %s with %d scoops:%n",
            this.getPrice(), this.size.getName(), this.type, this.getNumScoops()
            ));
    for (Scoop scoop: this.scoops) {
      sb.append(scoop.toString());
    }
    return sb.toString();
  }
}
