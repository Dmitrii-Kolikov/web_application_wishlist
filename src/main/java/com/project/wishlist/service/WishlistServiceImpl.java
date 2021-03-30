package com.project.wishlist.service;

import com.project.wishlist.model.Wishlist;
import com.project.wishlist.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;

    @Autowired
    public WishlistServiceImpl(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public void saveWishlist(Wishlist wishlist) {
        this.wishlistRepository.save(wishlist);
    }

    @Override
    public List<Wishlist> findAll() {
        return this.wishlistRepository.findAll();
    }

    @Override
    public Wishlist getWishlistByID(Long id) {
        Optional<Wishlist> optional = wishlistRepository.findById(id);
        Wishlist wishlist = null;

        if(optional.isPresent()) {
            wishlist = optional.get();
        } else {
            throw new RuntimeException("Wish not found for id: " + id);
        }

        return wishlist;
    }

    @Override
    public void deleteWishlistById(Long id) {
        this.wishlistRepository.deleteById(id);

    }

    @Override
    public List<Wishlist> findByUserUsername(String username) {
       return this.wishlistRepository.findByUserUsername(username);
    }

    @Override
    public Page<Wishlist> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.wishlistRepository.findAll(pageable);
    }
}
