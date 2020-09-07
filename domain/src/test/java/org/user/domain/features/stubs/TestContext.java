package org.user.domain.features.stubs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.user.domain.entities.User;

public class TestContext {
    private List<User> users;

    public void init(){
        users = new ArrayList<>();
    }

    public void addUser(User user){
        users.add(user);
    }

    public List<User> getUsers(){
        return Collections.unmodifiableList(users);
    }

    public void clearContext(){
        users.clear();
    }

}
