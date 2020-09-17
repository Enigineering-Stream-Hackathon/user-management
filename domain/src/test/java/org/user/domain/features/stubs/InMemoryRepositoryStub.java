package org.user.domain.features.stubs;

import lombok.AllArgsConstructor;
import org.user.domain.UserRepository;
import org.user.domain.entities.User;

@AllArgsConstructor
public class InMemoryRepositoryStub implements UserRepository {
    private TestContext testContext;

    @Override
    public void save(User user) {
        testContext.addUser(user);
    }

    @Override
    public User getByUserName(String userName) {
        return testContext.getUsers().stream()
            .filter(it -> it.getId().equals(userName))
            .findFirst()
            .orElse(null);
    }
}
