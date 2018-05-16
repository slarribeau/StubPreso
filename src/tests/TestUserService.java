package tests;

import main.SimpleUserService;
import main.User;
import main.UserService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestUserService {
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
  public void testFindingUserByName() { //18.33
    userService.register("bob");
    User user = userService.find("bob");
    assertNotNull(user);
    assertEquals("bob", user.getUsername());
  }

  @Test
  public void testRegisterTwoUsers() {

    userService.register("bob");
    assertEquals(1, userService.users().size());

    userService.register("alice");
    assertEquals(2, userService.users().size());

    User user = userService.find("alice");
    assertEquals("alice", user.getUsername());
    assertEquals(stubTimeSource.currentTime(), user.getCreationTime());
  }
}
