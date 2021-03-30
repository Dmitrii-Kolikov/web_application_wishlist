package com.project.wishlist;


import com.project.wishlist.model.User;
import com.project.wishlist.model.Wishlist;
import com.project.wishlist.service.WishlistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class WishlistRepositoryTests {

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    void whenSaved_thenFindsByName() {
        User user = new User();
        user.setUsername("Sergey");

        assertThat(wishlistService.findByUserUsername(user.getUsername())).isNotNull();
    }

}
