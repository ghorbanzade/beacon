//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw04;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
* Unit tests for student tuition question.
*
* @author Pejman Ghorbanzade
*/
public class StudentTuitionTest {
  @Test
  public void tuitionOfStudentWithInStateStatus() {
    StudentStatus status = StudentStatus.INSTATE;
    assertThat(status.getTuition(), is(1000F));
  }

  @Test
  public void tuitionOfStudentWithNonExistantStatus() {
    StudentStatus status = StudentStatus.INTL;
    assertThat(status.getTuition(), is(0F));
  }

  @Test
  public void valuesOfStudentStatus() {
    assertThat(StudentStatus.valueOf("INSTATE"), is(StudentStatus.INSTATE));
  }

  @Test
  public void studentName() {
    Student student = new Student("name", StudentStatus.OUTSTATE);
    assertThat(student.getName(), is("name"));
  }

  @Test
  public void studentTuition() {
    Student student = new Student("name", StudentStatus.OUTSTATE);
    assertThat(student.getTuition(), is(2000F));
  }

  @Test
  public void configInstance() {
    assertThat(StudentConfig.getInstance(), is(instanceOf(StudentConfig.class)));
  }

  @Test
  public void getProperty() {
    StudentConfig sc = StudentConfig.getInstance();
    assertThat(sc.get("tuition.instate"), is("1000"));
  }
}
