package org.study.customerservicecenter.repository;

import org.springframework.stereotype.Repository;
import org.study.customerservicecenter.domain.User;

import java.util.List;
import java.util.Optional;

//저장소 변경으로 인해 잠시 비워둠
public interface UserRepository {
    User save(User user);
    Optional<User> findById(String id);
    Optional<User> findByName(String name);
    List<User> findAll();

}