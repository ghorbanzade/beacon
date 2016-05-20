# Object Oriented Software Development

[![Build Status](https://travis-ci.com/ghorbanzade/UMB-CS681-2016S.svg?token=qeVsJz4jt7qobqTJazMT&branch=master)](https://travis-ci.com/ghorbanzade/UMB-CS681-2016S)

## Introduction

This repository contains the source code developed for _CS681: Object-Oriented Software Development_ course at [University of Massachusetts Boston].
It includes +4.5K high quality source lines of Java code that provide solution to 14 problems using different software design patterns.

All solutions are unit tested with +95% coverage using JUnit, and source codes are extensively documented using Javadoc.

The software uses `ant` and `make` build automation tools to build source codes and documentations, respectively.
The automation tool is configured to build the source code, package programs in separate jar files, run all unit tests, measure code coverage and initiate a number of other software development tools such as _javadoc_, _checkstyle_, _pmd_ and _findbugs_.

## Build Instructions

### Building Java Codes

You can build java programs by issuing one of `make`, `make code` or `ant` in the top-level directory.

The package(s) needed for building java codes are listed below.

```
sudo apt-get intsall ant
sudo apt-get install oraclejdk8
```

All other dependencies will be handled using Apache Ivy tool that will be configured by Ant.

Once the build is complete, you can execute each program by adding the path to its jar file in classpath of `java` command and providing the name of its `*Main.java` class as its argument, as shown in the following example:

```
java -cp UMB-CS681-2016S-hw01.jar edu.umb.cs681.hw01.CarMain
```

## Contribution

Content of this repository is __not__ subject to change.
Bug reports however are immensely appreciated.

## License

All source codes are released under the terms of the [MIT License].

## Contact

For questions or further information please contact [Pejman Ghorbanzade].

[University of Massachusetts Boston]: http://www.umb.edu
[MIT License]: https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
[Creative Commons Attribution-ShareAlike 4.0 International License]: https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/src/doc/LICENSE
[Pejman Ghorbanzade]: http://www.ghorbanzade.com
