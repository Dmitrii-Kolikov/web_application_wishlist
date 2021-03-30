package com.project.wishlist.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "wishlist")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Your wish should not be empty")
    @Size(min = 2, max = 80, message = "Your wish should be between 2 and 30 characters")
    @Column(name = "wish")
    private String wish;

    @Column(name = "happened")
    private String happened;

    @ManyToOne
    private User user;

    public Wishlist() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWish() {
        return wish;
    }

    public void setWish(String wish) {
        this.wish = wish;
    }

    public String getHappened() {
        return happened;
    }

    public void setHappened(String happened) {
        this.happened = happened;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wishlist wishlist = (Wishlist) o;
        return Objects.equals(id, wishlist.id) && Objects.equals(wish, wishlist.wish) && Objects.equals(happened, wishlist.happened) && Objects.equals(user, wishlist.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, wish, happened, user);
    }
}
