//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw07;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
* Unit tests for student tuition question.
*
* @author Pejman Ghorbanzade
*/
public class StudentTuitionTest {
  @Test
  public void prank1Test() {
    Student student = StudentFactory.createInStateStudent("pejman");
    assertThat(student.getName(), is("pejman"));
    assertThat(student.getTuition(), is(1000F));
  }

  @Test
  public void prank2Test() {
    Student student = StudentFactory.createOutStateStudent("pejman");
    assertThat(student.getName(), is("pejman"));
    assertThat(student.getTuition(), is(2000F));
  }

  @Test
  public void prank3Test() {
    Student student = StudentFactory.createIntlStudent("pejman");
    assertThat(student.getName(), is("pejman"));
    assertThat(student.getTuition(), is(3000F));
  }
}
