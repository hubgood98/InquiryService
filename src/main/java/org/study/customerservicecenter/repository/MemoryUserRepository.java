package org.study.customerservicecenter.repository;

import org.springframework.stereotype.Repository;
import org.study.customerservicecenter.domain.User;

import java.util.*;

@Repository
public class MemoryUserRepository implements UserRepository {
    Map<String,User> userMap = new HashMap<>();

    MemoryUserRepository()
    {
        userMap.put("admin",new User("admin","password","관리자",true));
        userMap.put("user1",new User("user1","1111"));
        userMap.put("user2",new User("user2","2222"));
    }

    @Override
    public User save(User user) {
        userMap.put(user.getId(),user);
        return user;
    }

    @Override
    public Optional<User> findById(String id){
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public Optional<User> findByName(String name) {
        return userMap.values().stream()
                .filter(m -> m.getName().equals(name)).findAny();
    }

    //아직 작성안함
    @Override
    public List<User> findAll() {
        return new ArrayList<>(userMap.values().stream().toList());
    }

    @Override
    public boolean matches(String id, String password) {
        Optional<User> optionalUser = findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getPassword().equals(password);
        } else {
            // Optional이 비어 있다면 사용자 ID가 없음을 의미하여 false 반환
            return false;
        }
    }

    @Override
    public void clearUserList() {
        userMap.clear();
    }
}
