package com.cybersoft.cozastore_java21.repository;

import com.cybersoft.cozastore_java21.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

//    @Query("FROM users WHERE email = ?1")
//    List<UserEntity> getUserByEmail (String email);

    UserEntity findByEmail(String email);
}
