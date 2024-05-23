package org.study.customerservicecenter.repository;

import org.study.customerservicecenter.domain.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class memoryuserRepository implements UserRepository {
    Map<String,User> userList = new HashMap<>();


    @Override
    public Optional<User> findById(String id){
        Optional.ofNullable(userList.get(id));

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
    public void clearStore() {
        userList.clear();
    }
}
