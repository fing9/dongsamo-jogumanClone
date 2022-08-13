package com.dongsamo.jogumanClone.controller.admin;

import com.dongsamo.jogumanClone.component.ValidateHandler;
import com.dongsamo.jogumanClone.domain.product.ProductRepository;
import com.dongsamo.jogumanClone.domain.productImage.ProductImageRepository;
import com.dongsamo.jogumanClone.domain.user.UserRepository;
import com.dongsamo.jogumanClone.dto.UserDto;
import com.dongsamo.jogumanClone.service.ProductService;
import com.dongsamo.jogumanClone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class AdminUserController {

    private final UserService userService;
    private final UserRepository userRepository;

    private final ValidateHandler validateHandler;

    @GetMapping("/admin/user")
    public Model findAll(Model model) {
        List<UserDto> userDtoList = userService.findAll();

        model.addAttribute("userList", userDtoList);

        return model;
    }

}
