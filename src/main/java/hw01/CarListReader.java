//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * This class allows generation of a list of cars according to configurations
 * given in a file.
 *
 * @author Pejman Ghorbanzade
 * @see Car
 */
public final class CarListReader {

  /**
   * A CarListReader object has only one attribute that is the list of car
   * objects.
   */
  private final ArrayList<Car> cars = new ArrayList<Car>();

  /**
   * Upon constructing an object of this class, the file corresponding to
   * a given path is read line by line and new car objects are added to
   * the arraylist.
   *
   * @param path the path to the text file containing the configurations
   */
  public CarListReader(String path) {
    try (InputStream fis = CarMain.class.getResourceAsStream(path)) {
      BufferedReader bf = new BufferedReader(
          new InputStreamReader(fis, "UTF-8")
      );
      String line;
      while ((line = bf.readLine()) != null) {
        if (line.trim().charAt(0) == '#') {
          continue;
        }
        String[] params = line.split(",");
        this.cars.add(new Car(
            Integer.parseInt(params[0]),
            Integer.parseInt(params[1]),
            Integer.parseInt(params[2])
        ));
      }
      bf.close();
      fis.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * This method is called by the main method to return the list of cars
   * with configurations as specified in the given file list.
   *
   * @return an arraylist of car objects
   */
  public ArrayList<Car> load() {
    return this.cars;
  }
}
