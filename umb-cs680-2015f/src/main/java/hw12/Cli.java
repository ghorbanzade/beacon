//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
* This class defines command line interface instances to interact with
* the file system. The class has been designed in a way to support
* multiple cli instances working with the same file system at the same
* time.
*
* @author Pejman Ghorbanzade
* @see CliConfig
*/
public final class Cli {
  /**
  * Each cli instance has a name, a history, a set of commands and a set of
  * methods for comparing file system elements. Each instance keeps track
  * of the active directory and the user interacting with it.
  */
  private final String name;
  private final User user;
  private final String hostname;
  private final History history;
  private Directory currentDirectory;
  private FileSystemElementComparator sortMethod;
  private final HashMap<String, Command> commands = new HashMap<String, Command>();
  private final HashMap<String, FileSystemElementComparator> sortMethods =
        new HashMap<String, FileSystemElementComparator>();

  /**
  * This constructor creates a CLI object of specified name.
  * Initially, the instance belongs to the default user and
  * works on the default hostname. It has an empty History
  * object. The constructor loads all known commands and all
  * known sort methods.
  *
  * @param name an identifier for each cli object
  */
  public Cli(String name) {
    CliConfig config = CliConfig.getInstance();
    this.name = name;
    this.user = new User(config.get("cli.user"));
    this.hostname = config.get("cli.hostname");
    String res = CliConfig.getInstance().get("cli.history.size");
    this.history = new History(Integer.parseInt(res));
    String defaultDirStr = config.get("cli.homedir");
    Directory defaultDir = (Directory) FileSystem.getInstance()
        .getElementByFullPath(this.getFullPath(defaultDirStr));
    this.setCurrentDirectory(defaultDir);
    this.initCommands();
    this.initSortMethods();
    this.setSortMethod(config.get("cli.list.sort"));
  }

  /**
  * This helper method introduces known commands and the command
  * class they associate with. This method is used by constructor
  * to initialize known commands for the CLI object.
  */
  private void initCommands() {
    this.commands.put("ls", new ListCommand());
    this.commands.put("cli", new CliCommand());
    this.commands.put("set", new SetCommand());
    this.commands.put("exit", new ExitCommand());
    this.commands.put("ln", new MakeLinkCommand());
    this.commands.put("touch", new MakeFileCommand());
    this.commands.put("history", new HistoryCommand());
    this.commands.put("cd", new ChangeDirectoryCommand());
    this.commands.put("mkdir", new MakeDirectoryCommand());
    this.commands.put("pwd", new CurrentDirectoryCommand());
    this.commands.put("rmdir", new RemoveDirectoryCommand());
  }

  /**
  * This helper method introduces known sort methods for sorting
  * file system elements. This method is used by constructor
  * to initialize known sort methods for the CLI object.
  */
  private void initSortMethods() {
    this.sortMethods.put("name", new NameComparator());
    this.sortMethods.put("time", new TimeComparator());
    this.sortMethods.put("size", new SizeComparator());
    this.sortMethods.put("default", new DefaultComparator());
  }

  /**
  * Once an instruction instance is created upon one-liner user
  * inputs, the name of the instruction is passed to the parse
  * method to retrieve the coorresponding command to that name.
  * If the command requested by the user is not recognized by the
  * cli instance, a proper exception would be thrown.
  *
  * @param instruction the instruction instance corresponding to
  *        the one-liner user input
  * @return a Command instance associated with command to be executed
  * @throws InvalidCommandException in case the requested command
  *         is not recognized by CLI object.
  */
  private Command parse(Instruction instruction)
      throws InvalidCommandException {
    String name = instruction.getName();
    if (this.commands.containsKey(name)) {
      return this.commands.get(name);
    } else {
      String message = String.format("%s: command not found", name);
      throw new InvalidCommandException(message);
    }
  }

  /**
  * This method takes a user instruction, parses it to take its corresponding
  * command and tries to execute that command.
  *
  * @param instruction the instruction instance cooresponding to the user input
  * @throws InvalidCommandException in case no command is found for
  *         the user-specified input
  * @throws GracefulTerminationException in case the user-specified command
  *         requires a controlled program termination
  * @throws UnsupportedOperationException in case user input is not
  *         in proper form to be executed
  */
  public void execute(Instruction instruction)
      throws UnsupportedOperationException,
             InvalidCommandException,
             GracefulTerminationException {
    Command command = this.parse(instruction);
    command.execute(this, instruction);
  }

  /**
  * The method takes the path given by the user and converts it
  * to a full path ready to be given to getFullPath method.
  * This method is used in many different file system commands.
  *
  * @param arg a relative or full path
  * @return the full path starting from the root directory
  */
  public String toFullPath(String arg) {
    String currentDir  = this.currentDirectory.getFullPath();
    if (!"/".equals(currentDir)) {
      currentDir += "/";
    }
    return arg.startsWith("/") ? arg : currentDir.concat(arg);
  }

  /**
  * This method takes a path to a file system element and breaks
  * it into a list of components, resolving '..' and '.' parts.
  * This method does not validate the path, therefore the list
  * given as full path to the element may not point to an existing
  * file system element.
  *
  * @param path a string literal representing the full path to
  *        an element within the file system. The string literal
  *        is special in that it is a mixture of full and
  *        relative paths as it contains '..' and '.' components.
  * @return a list of directories starting from root directory
  */
  public ArrayList<String> getFullPath(String path) {
    String newPath = (path.charAt(0) == '/') ? path.substring(1) : path;
    ArrayList<String> names = new ArrayList<String>();
    names.addAll(Arrays.asList(newPath.split("/")));
    if (path.charAt(0) != '/') {
      String currentDir = this.currentDirectory.getFullPath();
      if (!"/".equals(currentDir)) {
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
  * This method records the user instruction in the history of
  * the cli object. The instructions are recorded regardless of
  * their validity.
  *
  * @param instruction the new user instruction to be recorded
  */
  public void record(Instruction instruction) {
    this.history.add(instruction);
  }

  /**
  * This accessor method returns the cli prompt to be printed on the console.
  * The cli prompt shows the user, the hostname and the path of the active
  * directory of the cli.
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
    sb.append(String.format(
        "%s@%s:%s$ ", this.user.getUsername(), this.hostname, path
    ));
    return sb.toString();
  }

  /**
  * This accessor method returns the comparator currently in use for sorting
  * file system elements.
  *
  * @return current method in use for sorting file system elements.
  */
  public FileSystemElementComparator getSortMethod() {
    return this.sortMethod;
  }

  /**
  * This accessor method returns the name assigned to the active cli instance.
  *
  * @return the name of the current cli object
  */
  public String getName() {
    return this.name;
  }

  /**
  * This accessor method returns the user currently logged into the cli.
  *
  * @return the name of the user currently logged into the cli.
  */
  public User getUser() {
    return this.user;
  }

  /**
  * This accessor method returns the hostname of the file system.
  * At the moment, this method is only used in forming prompt of the cli.
  *
  * @return the hostname
  */
  public String getHostname() {
    return this.hostname;
  }

  /**
  * This accessor method returns the active directory of the cli instance.
  *
  * @return the directory command line interface is currently set on
  */
  public Directory getCurrentDirectory() {
    return this.currentDirectory;
  }

  /**
  * This accessor method returns the history of the cli instance.
  *
  * @return a History object holding last few instructions given by the user.
  */
  public History getHistory() {
    return this.history;
  }

  /**
  * The client is able to set any directory as the active directoryin its
  * cli instance. Having active directory in Cli class instead of FileSystem
  * class allows working on different directories of the same file system
  * using multiple cli instances.
  *
  * @param directory the directory to be set as the current directory.
  */
  public void setCurrentDirectory(Directory directory) {
    this.currentDirectory = directory;
  }

  /**
  * The client is able to set the method for sorting file system
  * elements as in listing elements in a specified directory.
  *
  * @param key the name of the comparator to be used for sorting
  *        file system elements.
  * @throws UnsupportedOperationException in case no sorting method
  *         is found for the given key.
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
