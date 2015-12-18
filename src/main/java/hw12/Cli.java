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
  private FileSystemElementComparator sortMethod;
  private HashMap<String, Command> commands = new HashMap<String, Command>();
  private HashMap<String, FileSystemElementComparator> sortMethods =
        new HashMap<String, FileSystemElementComparator>();

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
    this.commands.put("ls", new ListCommand());
    this.commands.put("cli", new CliCommand());
    this.commands.put("set", new SetCommand());
    this.commands.put("exit", new ExitCommand());
    this.commands.put("history", new HistoryCommand());
    this.commands.put("cd", new ChangeDirectoryCommand());
    this.commands.put("mkdir", new MakeDirectoryCommand());
    this.commands.put("pwd", new CurrentDirectoryCommand());
    this.sortMethods.put("name", new NameComparator());
    this.sortMethods.put("time", new TimeComparator());
    this.sortMethods.put("size", new SizeComparator());
    this.sortMethods.put("default", new DefaultComparator());
    this.setSortMethod(config.get("cli.list.sort"));
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
  * @return a list of directories starting from root directory
  */
  public ArrayList<String> getFullPath(String path) {
    String newPath = (path.charAt(0) == '/') ? path.substring(1) : path;
    ArrayList<String> names = new ArrayList<String>();
    names.addAll(Arrays.asList(newPath.split("/")));
    if (path.charAt(0) != '/') {
      String currentDir = this.currentDirectory.getFullPath();
      if (currentDir.equals("/") == false) {
        names.addAll(0, new ArrayList<String>(Arrays.asList(
            currentDir.substring(1).split("/")
        )));
      }
    }
    while (names.contains(".")) {
      names.remove(names.indexOf("."));
    }
    while (names.contains("..")) {
      int index = names.indexOf("..");
      names.remove(index);
      if (index > 0) {
        names.remove(index - 1);
      }
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
      path = "~" + path.substring(homedir.length());
    }
    sb.append(String.format("%s@%s:%s$ ", this.user, this.group, path));
    return sb.toString();
  }

  /**
  *
  *
  * @return
  */
  public FileSystemElementComparator getSortMethod() {
    return this.sortMethods.containsKey(this.sortMethod)
          ? this.sortMethods.get(this.sortMethod)
          : this.sortMethods.get("default");
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

  /**
  *
  *
  * @param key
  */
  public void setSortMethod(String key)
              throws UnsupportedOperationException {
    FileSystemElementComparator value = this.sortMethods.get(key);
    if (value == null) {
      throw new UnsupportedOperationException("comparator key not found");
    } else {
      this.sortMethod = value;
    }
  }
}
