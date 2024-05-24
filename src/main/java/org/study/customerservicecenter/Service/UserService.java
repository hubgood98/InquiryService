package org.study.customerservicecenter.Service;

import org.springframework.stereotype.Service;
import org.study.customerservicecenter.domain.User;
import org.study.customerservicecenter.repository.UserRepository;

import java.util.List;
import java.util.Optional;


public class UserService implements UserRepository {

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public boolean matches(String id, String password) {
        return false;
    }

    @Override
    public void clearUserList() {

    }
}
