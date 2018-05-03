package test;

import main.TimeSource;
import org.joda.time.DateTime;

public class RealTimeSource implements TimeSource {
  @Override
  public DateTime currentTime() {
    return new DateTime();
  }
}
