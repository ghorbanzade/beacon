package edu.umb.cs680.hw04;

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
  public void tuitionOfInStateStatus() {
    StudentStatus status = StudentStatus.INSTATE;
    assertThat(status.getTuition(), is(1000F));
  }
}
