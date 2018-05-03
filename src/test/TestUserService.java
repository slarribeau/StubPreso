package test;

import main.SimpleUserService;
import main.User;
import main.UserService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TestUserService {
  private UserService userService;
  private tests.MockAuditLog mockAuditLog;
  private tests.FakeUserStore fakeUserStore;
  private StubTimeSource stubTimeSource;

  @Before
  public void createUserService() {
    mockAuditLog = new tests.MockAuditLog();
    fakeUserStore = new tests.FakeUserStore();
    stubTimeSource = new StubTimeSource();
    userService = new SimpleUserService(mockAuditLog, fakeUserStore, stubTimeSource);
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
    mockAuditLog.enable();

    mockAuditLog.expect("user", "register", "bob");
    userService.register("bob");
    mockAuditLog.verify();
    assertEquals(1, userService.users().size());

    mockAuditLog.expect("user", "register", "alice");
    userService.register("alice");
    mockAuditLog.verify();
    assertEquals(2, userService.users().size());

    User user = userService.find("alice");
    assertEquals("alice", user.getUsername());
    assertEquals(stubTimeSource.currentTime(), user.getCreationTime());
  }
}
