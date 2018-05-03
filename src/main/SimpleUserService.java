package main;

import java.util.List;

public class SimpleUserService implements UserService {
  private UserStore userStore;
  private TimeSource timeSource;

  public SimpleUserService(UserStore userStore, TimeSource timeSource) {
    this.userStore = userStore;
    this.timeSource = timeSource;
  }
  @Override
  public List<User> users() {
    return userStore.findAll();
  }

  @Override
  public void register(String username) {
    if (hasUser(username)) {
    } else {
      userStore.store(new User(username, timeSource.currentTime()));
    }
  }

  @Override
  public User find(String username ) {
    List<User> allUsers = userStore.findAll();
    for (User user : allUsers ) {
      if (username.equals(user.getUsername()))
        return user;
    }
    return null;
  }

  private boolean hasUser(String username) {
    User user = find(username);
    return user != null;
  }
}

