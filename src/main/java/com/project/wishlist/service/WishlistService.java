package com.project.wishlist.service;

import com.project.wishlist.model.Wishlist;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WishlistService {
        void saveWishlist(Wishlist wishlist);
        List<Wishlist> findAll();
        Wishlist getWishlistByID(Long id);
        void deleteWishlistById(Long id);
        List<Wishlist> findByUserUsername(String username);

        Page<Wishlist> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
