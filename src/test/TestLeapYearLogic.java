package test;

import main.TimeSource;
import org.junit.Test;

import java.util.List;

import org.joda.time.DateTime;


public class TestLeapYearLogic {

  @Test
  public void foo() {
    StubTimeSource timeSource = new StubTimeSource();
    System.out.println(timeSource.currentTime());

    DateTime dt = new DateTime();
    System.out.println(dt);
  }

  @Test
  public void bar() {
    RealTimeSource timeSource = new RealTimeSource();
    System.out.println(timeSource.currentTime());
    }
}
