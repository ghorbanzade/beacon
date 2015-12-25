
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
*
*
* @author Pejman Ghorbanzade
*/
public final class StockFileHandler {
  /**
  *
  *
  *
  */
  public static ArrayList<StockEvent> loadEvents() {
    ArrayList<StockEvent> events = new ArrayList<StockEvent>();
    try {
      String filename = StockConfig.getInstance().get("file.stockquote");
      String filepath = filename;
      File file = new File(filepath);
      Scanner lineReader = new Scanner(file);
      while (lineReader.hasNext()) {
        String line = lineReader.nextLine();
        if (line.isEmpty() == false && line.trim().charAt(0) != '#') {
          Scanner tokenReader = new Scanner(line).useDelimiter("\\s*, \\s*");
          String name = tokenReader.next();
          String symbol = tokenReader.next();
          float quote = tokenReader.nextFloat();
          events.add(new StockEvent(new StockTrademark(symbol, name), quote));
        }
      }
    } catch (FileNotFoundException e) {
      // TODO: Should not leave exception unnoticed.
    }
    return events;
  }
}
