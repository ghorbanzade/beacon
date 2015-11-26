package edu.umb.cs680.hw03;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
*
*
* @author Pejman Ghorbanzade
*/
public class StudentTuitionTest {
  @Test
  public void getInStateStudentTuition() {
    InStateStudent status = new InStateStudent();
    assertThat(status.getTuition(), is(1000F));
  }

  @Test
  public void getOutStateStudentTuition() {
    OutStateStudent status = new OutStateStudent();
    assertThat(status.getTuition(), is(2000F));
  }

  @Test
  public void getIntlStudentTution() {
    IntlStudent status = new IntlStudent();
    assertThat(status.getTuition(), is(3000F));
  }

  @Test
  public void getStudentName() {
    StudentStatus status = new InStateStudent();
    Student student = new Student("pejman", status);
    assertThat(student.getName(), is("pejman"));
    assertThat(student.getTuition(), is(status.getTuition()));
  }
}
