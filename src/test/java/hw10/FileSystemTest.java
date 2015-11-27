package edu.umb.cs680.hw10;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.util.Date;

/**
* Unit test suit for file system question.
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
    assertThat(link.getTarget(), is(file));
  }

  @Test
  public void targetOfLinkPointingToDirectory() {
    File file = new File("file", "owner", 100);
    Link link = new Link("link", "owner", file);
    assertThat(link.getTarget(), is(file));
  }

  @Test
  public void targetOfLinkPointingToLink() {
    File file = new File("file", "owner", 100);
    Link link1 = new Link("link1", "owner", file);
    Link link2 = new Link("link2", "owner", link1);
    assertThat(link2.getTarget(), is(file));
  }

  @Test
  public void nameOfFSElementType() {
    FSElementType type = FSElementType.FILE;
    assertThat(type.getName(), is("file"));
  }

}
