package org.study.customerservicecenter.repository;

import org.springframework.stereotype.Repository;
import org.study.customerservicecenter.domain.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {
        User save(User user);
        Optional<User> findById(String id);
        Optional<User> findByName(String name);
        List<User> findAll();
        boolean matches(String id, String password);
        void clearUserList();
    }
