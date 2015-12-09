//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw12;

import java.lang.NumberFormatException;
import java.lang.UnsupportedOperationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
*
* @author Pejman Ghorbanzade
*/
public final class Cli {
  /**
  *
  */
  private String name;
  private String user;
  private String group;
  private History history;
  private Directory currentDirectory;
  private FileSystemElementComparator comparator;
  private HashMap<String, Command> commands = new HashMap<String, Command>();

  /**
  *
  *
  * @param name
  * @throws NumberFormatException
  */
  public Cli(String name) throws NumberFormatException {
    CliConfig config = CliConfig.getInstance();
    this.name = name;
    this.user = config.get("cli.user");
    this.group = config.get("cli.group");
    this.history = new History();
    String defaultDirStr = config.get("cli.homedir");
    Directory defaultDir = (Directory) FileSystem.getInstance()
        .getElementByFullPath(this.getFullPath(defaultDirStr));
    this.setCurrentDirectory(defaultDir);
    this.commands.put("exit", new ExitCommand());
    this.commands.put("ls", new ListCommand());
    this.commands.put("cd", new ChangeDirectoryCommand());
    this.commands.put("pwd", new CurrentDirectoryCommand());
    this.commands.put("cli", new CliCommand());
    this.commands.put("history", new HistoryCommand());
  }

  /**
  *
  *
  * @param name
  * @return command to be executed
  * @throws InvalidCommandException
  */
  public Command parse(String name) throws InvalidCommandException {
    if (this.commands.containsKey(name)) {
      return this.commands.get(name);
    } else {
      String message = String.format("%s: command not found", name);
      throw new InvalidCommandException(message);
    }
  }

  /**
  *
  *
  * @param command
  * @param instruction
  * @throws InvalidCommandException
  */
  public void execute(Command command, Instruction instruction)
        throws UnsupportedOperationException, GracefulTerminationException {
    command.execute(this, instruction);
  }

  /**
  *
  *
  * @param instruction
  */
  public void record(Instruction instruction) {
    this.history.add(instruction);
  }

  /**
  *
  *
  * @param path
  * @return
  */
  public ArrayList<String> getFullPath(String path) {
    String newPath = (path.charAt(0) == '/') ? path.substring(1) : path;
    ArrayList<String> names =
        new ArrayList<String>(Arrays.asList(newPath.split("/")));
    if (path.charAt(0) != '/') {
      names.addAll(0, new ArrayList<String>(Arrays.asList(
          this.currentDirectory.getFullPath().substring(1).split("/")
      )));
    }
    return names;
  }

  /**
  *
  *
  * @return prompt of the command line interface
  */
  public String getPrompt() {
    StringBuilder sb = new StringBuilder();
    String homedir = CliConfig.getInstance().get("cli.homedir");
    String path = this.currentDirectory.getFullPath();
    if (path.startsWith(homedir)) {
      path = path.substring(homedir.length());
    }
    sb.append(String.format("%s@%s:%s$ ", this.user, this.group, path));
    return sb.toString();
  }

  /**
  *
  *
  * @return the name of the current cli object
  */
  public String getName() {
    return this.name;
  }

  /**
  *
  *
  * @return the name of the current user
  */
  public String getUser() {
    return this.user;
  }

  /**
  *
  *
  * @return the name of the group current user belongs to
  */
  public String getGroup() {
    return this.group;
  }

  /**
  *
  *
  * @return the directory command line interface is currently set on
  */
  public Directory getCurrentDirectory() {
    return this.currentDirectory;
  }

  /**
  *
  *
  * @return
  */
  public History getHistory() {
    return this.history;
  }

  /**
  *
  *
  * @param directory
  */
  public void setCurrentDirectory(Directory directory) {
    this.currentDirectory = directory;
  }
}
