//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw13;

import java.util.ArrayList;
import java.util.Scanner;

/**
* This class is provided to separate program data (needed for
* demonstration of the software) from the source code. This class
* reads the file containing stock information (whose path is given
* in properties file of the program) and prepares those
* information for use in observable class.
*
* @author Pejman Ghorbanzade
* @see StockConfig
* @see StockEvent
*/
public final class StockFileHandler {
  /**
  * This static method will read the stock information file whose
  * path is given in stock properties file and will return the
  * information as a list of stock events to be used for loading
  * the StockObservable instance.
  *
  * <p>In case the file of specified path does not exist or does
  * not contain any stock event information, this method will
  * return an empty list.
  *
  * @return a list of stock events where each stock event
  *         corresponds to one line of the stock info file.
  */
  public static ArrayList<StockEvent> loadEvents() {
    ArrayList<StockEvent> events = new ArrayList<StockEvent>();
    String filename =
        StockConfig.getInstance().get("file.stockquote");
    Scanner lineReader = new Scanner(
        StockConfig.class.getResourceAsStream("/" + filename), "UTF-8"
    );
    while (lineReader.hasNext()) {
      String line = lineReader.nextLine();
      if (!line.isEmpty() && line.trim().charAt(0) != '#') {
        Scanner tokenReader = new Scanner(line).useDelimiter("\\s*, \\s*");
        String name = tokenReader.next();
        String symbol = tokenReader.next();
        float quote = tokenReader.nextFloat();
        events.add(new StockEvent(new StockTrademark(symbol, name), quote));
      }
    }
    return events;
  }
}
