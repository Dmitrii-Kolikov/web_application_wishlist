package com.project.wishlist;


import com.project.wishlist.model.User;
import com.project.wishlist.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setUsername("Oleg");
        user.setPassword("user");

        User savedUser = userRepository.save(user);
        User existUser = entityManager.find(User.class, savedUser.getId());
        assertThat(existUser.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void testFindUserByEmail() {

        String username = "Oleg";
        Optional<User> user = userRepository.findByUsername(username);
        assertThat(user).isNotNull();
    }
}
