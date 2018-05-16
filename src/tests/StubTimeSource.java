package tests;

import main.TimeSource;
import org.joda.time.DateTime;


public class StubTimeSource implements TimeSource {
  @Override
  public DateTime currentTime() {
    return new DateTime(2020, 2, 29, 0, 0,01);
  }
}
