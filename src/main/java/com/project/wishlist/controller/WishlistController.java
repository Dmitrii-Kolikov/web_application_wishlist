package com.project.wishlist.controller;

import com.project.wishlist.model.User;
import com.project.wishlist.model.Wishlist;
import com.project.wishlist.repository.UserRepository;
import com.project.wishlist.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class WishlistController {

    private final WishlistService wishlistService;
    private final UserRepository userRepository;

    @Autowired
    public WishlistController(WishlistService wishlistService, UserRepository userRepository) {
        this.wishlistService = wishlistService;
        this.userRepository = userRepository;
    }


    @GetMapping("/")
    public String indexPage(Model model, Principal principal) {
//        model.addAttribute("wishlist", wishlistService.findAll());
//        return "wishlist/index";


        return findPaginated(1,"happened", "asc", model, principal);

    }

    @GetMapping("/showNewFormYourWish")
    public String showNewFormWishlist(Model model) {

        //create model attribute to bind form data
        Wishlist wish = new Wishlist();
        model.addAttribute("yourWish", wish);

        return "wishlist/new_wish";
    }

    @PostMapping("/saveWish")
    public String saveWishlist(@ModelAttribute("yourWish") @Valid Wishlist wish, Principal principal, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "wishlist/new_wish";
        }
        User user = userRepository.findByUsername(principal.getName()).get();
        wish.setUser(user);

        //save wish to database
        wishlistService.saveWishlist(wish);

        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(Model model, @PathVariable(value = "id") Long id) {

        //set wish as a model attribute to pre-populate the form
        model.addAttribute("yourWish", wishlistService.getWishlistByID(id));  //get employee from the service
        return "wishlist/update_wish";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("yourWish") @Valid Wishlist wishlist, BindingResult bindingResult,
             @PathVariable("id") Long id) {

        if(bindingResult.hasErrors()) {
            return "wishlist/update_wish";
        }

        wishlistService.saveWishlist(wishlist);

        return "redirect:/";
    }

    @GetMapping("/deleteWishlist/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Long id) {

        //call delete wishlist method
        this.wishlistService.deleteWishlistById(id);
        return "redirect:/";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pagNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model, Principal principal) {
        int pageSize = 5;

        Page<Wishlist> page = wishlistService.findPaginated(pagNo, pageSize, sortField, sortDir);
        List<Wishlist> wishlist = page.getContent();

        model.addAttribute("currentPage", pagNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("wish", wishlistService.findByUserUsername(principal.getName()));

        return "wishlist/index";
    }
}

