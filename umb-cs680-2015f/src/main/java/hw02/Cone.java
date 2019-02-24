//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw02;

/**
* The Cone class defines a Container type that can hold the ice cream scoops.
* A cone has a size and depending on its size, has a price that is specified
* in application configuration file. As a bonus, the user can get free topping
* on his cone.
*
* @author     Pejman Ghorbanzade
* @see        Container
* @see        Cup
*/
public final class Cone extends Container {
  private Topping topping = null;

  /**
  * This constructor allows client to specify the size of the cone and the
  * price of the cone will be set based on the information given in
  * configuration file.
  *
  * @param size the size of the cone
  */
  public Cone(Size size) {
    super("cone", size);
  }

  /**
  * According to the problem statement, client may have topping on the cone.
  * Once the topping is set, it is not possible to change the topping.
  *
  * @param topping The topping on the cone
  * @throws IceCreamException in case topping is already set
  */
  public void setTopping(Topping topping) throws IceCreamException {
    if (this.topping == null) {
      if (this.getScoops().isEmpty()) {
        this.topping = topping;
      } else {
        throw new IceCreamException("Cone is not empty.");
      }
    } else {
      throw new IceCreamException("Topping is already set.");
    }
  }

  /**
  * This method gives access to the topping on the cone.
  *
  * @return the topping on the cone
  */
  public Topping getTopping() {
    return this.topping;
  }

}
