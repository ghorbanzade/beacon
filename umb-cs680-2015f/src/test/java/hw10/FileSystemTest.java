//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw10;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
* Unit tests for file system question.
*
* @author Pejman Ghorbanzade
*/
public class FileSystemTest {
  @Test
  public void singletonInstance() {
    FileSystem fs1 = FileSystem.getFileSystem();
    FileSystem fs2 = FileSystem.getFileSystem();
    assertThat(fs1, is(fs2));
  }

  @Test
  public void nullCheckRootDir() {
    FileSystem fs = FileSystem.getFileSystem();
    assertThat(fs.getRoot(), is(not(nullValue())));
    assertThat(fs.getRoot(), is(instanceOf(Directory.class)));
  }

  @Test
  public void showAllElementsOfFileSystem() {
    try {
      ByteArrayOutputStream sink1 = new ByteArrayOutputStream();
      FileSystem fs = FileSystem.getFileSystem();
      System.setOut(new PrintStream(sink1, true, "UTF-8"));
      fs.showAllElements();
      String result1 = new String(sink1.toByteArray(), "UTF-8");
      ByteArrayOutputStream sink2 = new ByteArrayOutputStream();
      System.setOut(new PrintStream(sink2, true, "UTF-8"));
      fs.getRoot().showAllElements();
      String result2 = new String(sink2.toByteArray(), "UTF-8");
      assertThat(result1, is(result2));
      System.setOut(null);
    } catch (UnsupportedEncodingException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void sizeOfFile() {
    FSElement file = new File("file", "owner", 100);
    assertThat(file.getSize(), is(100));
  }

  @Test
  public void sizeOfEmptyDirectory() {
    FSElement directory = new Directory("directory", "owner");
    assertThat(directory.getSize(), is(0));
  }

  @Test
  public void sizeOfNonEmptyDirectory() {
    Directory directory = new Directory("directory", "owner");
    FSElement file = new File("file", "owner", 100);
    directory.appendChild(file);
    assertThat(directory.getSize(), is(100));
  }

  @Test
  public void sizeOfNestedNonEmptyDirectory() {
    Directory directory1 = new Directory("directory1", "owner");
    Directory directory2 = new Directory("directory2", "owner");
    FSElement file = new File("file", "owner", 100);
    directory2.appendChild(file);
    directory1.appendChild(directory2);
    assertThat(directory1.getSize(), is(100));
  }

  @Test
  public void sizeOfNonEmptyDirectoryContainingLink() {
    Directory directory = new Directory("directory", "owner");
    FSElement file = new File("file", "owner", 100);
    FSElement link = new Link("link", "owner", file);
    directory.appendChild(link);
    assertThat(directory.getSize(), is(0));
  }

  @Test
  public void sizeOfLinkPointingToFile() {
    FSElement target = new File("file", "owner", 100);
    FSElement link = new Link("link", "owner", target);
    assertThat(link.getSize(), is(100));
  }

  @Test
  public void sizeOfLinkPointingToDirectory() {
    FSElement target = new Directory("directory", "owner");
    FSElement link = new Link("link", "owner", target);
    assertThat(link.getSize(), is(0));
  }

  @Test
  public void childrenOfEmptyDirectory() {
    Directory directory = new Directory("directory", "owner");
    assertThat(directory.getChildren().size(), is(0));
  }

  @Test
  public void childrenOfNonEmptyDirectory1() {
    Directory directory = new Directory("directory", "owner");
    FSElement file = new File("file", "owner", 100);
    directory.appendChild(file);
    assertThat(directory.getChildren().size(), is(1));
  }

  @Test
  public void childrenOfNonEmptyDirectory2() {
    Directory directory1 = new Directory("directory1", "owner");
    FSElement directory2 = new Directory("directory2", "owner");
    FSElement file = new File("file", "owner", 100);
    directory1.appendChild(file);
    directory1.appendChild(directory2);
    assertThat(directory1.getChildren().size(), is(2));
  }

  @Test
  public void childrenOfNestedNonEmptyDirectory() {
    Directory directory1 = new Directory("directory1", "owner");
    Directory directory2 = new Directory("directory2", "owner");
    FSElement file = new File("file", "owner", 100);
    directory2.appendChild(file);
    directory1.appendChild(directory2);
    assertThat(directory1.getChildren().size(), is(1));
  }

  @Test
  public void isLeafEmptyDirectory() {
    Directory directory = new Directory("directory", "owner");
    assertThat(directory.isLeaf(), is(true));
  }

  @Test
  public void isLeafNonEmptyDirectory() {
    Directory directory1 = new Directory("directory1", "owner");
    Directory directory2 = new Directory("directory2", "owner");
    directory2.appendChild(directory1);
    assertThat(directory2.isLeaf(), is(false));
  }

  @Test
  public void isLeafFile() {
    File file = new File("file", "owner", 100);
    assertThat(file.isLeaf(), is(true));
  }

  @Test
  public void isLeafLink() {
    File file = new File("file", "owner", 100);
    Link link = new Link("link", "owner", file);
    assertThat(link.isLeaf(), is(true));
  }

  @Test
  public void ownerOfFile() {
    FSElement file = new File("file", "owner", 100);
    assertThat(file.getOwner(), is("owner"));
  }

  @Test
  public void ownerOfDirectory() {
    FSElement directory = new Directory("directory", "owner");
    assertThat(directory.getOwner(), is("owner"));
  }

  @Test
  public void ownerOfLink() {
    FSElement file = new File("file", "owner", 100);
    FSElement link = new Link("link", "owner", file);
    assertThat(link.getOwner(), is("owner"));
  }

  @Test
  public void nameOfFile() {
    FSElement file = new File("file", "owner", 100);
    assertThat(file.getName(), is("file"));
  }

  @Test
  public void nameOfDirectory() {
    FSElement directory = new Directory("directory", "owner");
    assertThat(directory.getName(), is("directory"));
  }

  @Test
  public void nameOfLink() {
    FSElement file = new File("file", "owner", 100);
    FSElement link = new Link("link", "owner", file);
    assertThat(link.getName(), is("link"));
  }

  @Test
  public void createdFile() {
    FSElement file = new File("file", "owner", 100);
    Date date = new Date();
    long diff = Math.abs(file.getCreated().getTime() - date.getTime());
    assertThat(diff, is(lessThan(1000L)));
  }

  @Test
  public void createdDirectory() {
    FSElement directory = new Directory("directory", "owner");
    Date date = new Date();
    long diff = Math.abs(directory.getCreated().getTime() - date.getTime());
    assertThat(diff, is(lessThan(1000L)));
  }

  @Test
  public void createdLink() {
    FSElement file = new File("file", "owner", 100);
    FSElement link = new Link("link", "owner", file);
    Date date = new Date();
    long diff = Math.abs(link.getCreated().getTime() - date.getTime());
    assertThat(diff, is(lessThan(1000L)));
  }

  @Test
  public void targetOfLinkPointingToFile() {
    File file = new File("file", "owner", 100);
    Link link = new Link("link", "owner", file);
    assertThat(link.getTarget(), is(instanceOf(File.class)));
    assertThat((File) link.getTarget(), is(file));
  }

  @Test
  public void targetOfLinkPointingToDirectory() {
    File file = new File("file", "owner", 100);
    Link link = new Link("link", "owner", file);
    assertThat(link.getTarget(), is(instanceOf(File.class)));
    assertThat((File) link.getTarget(), is(file));
  }

  @Test
  public void targetOfLinkPointingToLink() {
    File file = new File("file", "owner", 100);
    Link link1 = new Link("link1", "owner", file);
    Link link2 = new Link("link2", "owner", link1);
    assertThat(link2.getTarget(), is(instanceOf(File.class)));
    assertThat((File) link2.getTarget(), is(file));
  }

  @Test
  public void nameOfFSElementType() {
    FSElementType type = FSElementType.FILE;
    assertThat(type.getName(), is("file"));
  }

  @Test
  public void valuesOfFSElementType() {
    assertThat(FSElementType.valueOf("FILE"), is(FSElementType.FILE));
  }

  @Test
  public void changeOwnerOfFile() {
    File file = new File("file", "owner", 100);
    file.setOwner("newOwner");
    assertThat(file.getOwner(), is("newOwner"));
  }

  @Test
  public void changeOwnerOfDirectory() {
    Directory dir = new Directory("directory", "owner");
    dir.setOwner("newOwner");
    assertThat(dir.getOwner(), is("newOwner"));
  }

  @Test
  public void changeOwnerOfLink() {
    File file = new File("file", "owner", 100);
    Link link = new Link("link", "owner", file);
    link.setOwner("newOwner");
    assertThat(link.getOwner(), is("newOwner"));
  }

  @Test
  public void changeNameOfFile() {
    File file = new File("file", "owner", 100);
    file.setName("newName");
    assertThat(file.getName(), is("newName"));
  }

  @Test
  public void changeNameOfDirectory() {
    Directory dir = new Directory("directory", "owner");
    dir.setName("newName");
    assertThat(dir.getName(), is("newName"));
  }

  @Test
  public void changeNameOfLink() {
    File file = new File("file", "owner", 100);
    Link link = new Link("link", "owner", file);
    link.setName("newName");
    assertThat(link.getName(), is("newName"));
  }

  @Test
  public void fullPathOfIsolatedFile() {
    File file = new File("file", "owner", 100);
    assertThat(file.getFullPath(), is("file"));
  }

  @Test
  public void fullPathOfIsolatedDirectory() {
    Directory dir = new Directory("directory", "owner");
    assertThat(dir.getFullPath(), is("directory"));
  }

  @Test
  public void fullPathOfDirectory() {
    Directory dir1 = new Directory("directory1", "owner");
    Directory dir2 = new Directory("directory2", "owner");
    File file = new File("file", "owner", 100);
    Link link = new Link("link", "owner", file);
    dir1.appendChild(file);
    dir1.appendChild(dir2);
    dir2.appendChild(link);
    assertThat(dir1.getFullPath(), is("directory1"));
    assertThat(file.getFullPath(), is("directory1/file"));
    assertThat(dir2.getFullPath(), is("directory1/directory2"));
    assertThat(link.getFullPath(), is("directory1/directory2/link"));
  }

  @Test
  public void lastModifiedFile() {
    FSElement file = new File("file", "owner", 100);
    Date date = new Date();
    long diff = Math.abs(file.getLastModified().getTime() - date.getTime());
    assertThat(diff, is(lessThan(1000L)));
  }

  @Test
  public void lastModifiedDirectory() {
    FSElement directory = new Directory("directory", "owner");
    Date date = new Date();
    long diff = Math.abs(directory.getLastModified().getTime() - date.getTime());
    assertThat(diff, is(lessThan(1000L)));
  }

  @Test
  public void lastModifiedLink() {
    FSElement file = new File("file", "owner", 100);
    FSElement link = new Link("link", "owner", file);
    Date date = new Date();
    long diff = Math.abs(link.getLastModified().getTime() - date.getTime());
    assertThat(diff, is(lessThan(1000L)));
  }

  @Test
  public void displayInfoForFile() {
    FSElement file = new File("file", "owner", 100);
    assertThat(file.toString(), containsString(FSElementType.FILE.getName()));
    assertThat(file.toString(), containsString(file.getOwner()));
    assertThat(file.toString(), containsString(Integer.toString(file.getSize())));
    assertThat(file.toString(), containsString(file.getName()));
  }

  @Test
  public void displayInfoForLink() {
    File file = new File("file", "owner", 100);
    Link link = new Link("link", "owner", file);
    assertThat(link.toString(), containsString("-->"));
    assertThat(link.toString(), containsString(link.getTarget().getFullPath()));
  }

  @Test
  public void showAllElementsOfEmptyDirectory() {
    try {
      ByteArrayOutputStream sink = new ByteArrayOutputStream();
      System.setOut(new PrintStream(sink, true, "UTF-8"));
      Directory dir = new Directory("directory", "owner");
      dir.showAllElements();
      String result = new String(sink.toByteArray(), "UTF-8");
      assertThat(result, containsString(dir.getFullPath()));
      assertThat(result, containsString(Integer.toString(dir.getSize())));
      System.setOut(null);
    } catch (UnsupportedEncodingException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void showAllElementsOfNonEmptyDirectory() {
    try {
      ByteArrayOutputStream sink = new ByteArrayOutputStream();
      System.setOut(new PrintStream(sink, true, "UTF-8"));
      Directory dir1 = new Directory("directory1", "owner1");
      Directory dir2 = new Directory("directory2", "owner2");
      File file = new File("file", "owner3", 100);
      dir2.appendChild(file);
      dir1.appendChild(dir2);
      dir1.showAllElements();
      String result = new String(sink.toByteArray(), "UTF-8");
      assertThat(result, containsString(dir1.getFullPath()));
      assertThat(result, containsString(Integer.toString(dir1.getSize())));
      assertThat(result, containsString(dir2.getName()));
      assertThat(result, containsString(file.getName()));
      System.setOut(null);
    } catch (UnsupportedEncodingException e) {
      fail(e.getMessage());
    }
  }
}
