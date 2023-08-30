package com.example.fullmenusystem.Repository;

import com.example.fullmenusystem.Model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthRepositoryTest {

    @Autowired
    AuthRepository authRepository;

    User user;

    @BeforeEach
    void setup(){
        user = new User(null,"abdullah","Aa132455","USER",null);
    }


    // 5 RepositoryTest
    @Test
    void findUserByUsernameTest(){
        authRepository.save(user);
        User user1 = authRepository.findUserByUsername(user.getUsername());
        Assertions.assertThat(user1.getUsername()).isEqualTo(user.getUsername());
    }
}
