package tests;

import main.SimpleUserService;
import main.User;
import main.UserService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestClock {
  private UserService userService;
  private FakeUserStore fakeUserStore;
  private StubTimeSource stubTimeSource;

  @Before
  public void createUserService() {
    fakeUserStore = new FakeUserStore();
    stubTimeSource = new StubTimeSource();
    userService = new SimpleUserService(fakeUserStore, stubTimeSource);
  }

  @Test
  public void testValidateTimestamp() { //18.33
    userService.register("bob");
    User user = userService.find("bob");
    assertNotNull(user);
    assertEquals("bob", user.getUsername());
    assertEquals(stubTimeSource.currentTime(), user.getCreationTime());
  }
}
