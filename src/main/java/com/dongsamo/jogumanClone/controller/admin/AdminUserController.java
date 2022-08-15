package com.dongsamo.jogumanClone.controller.admin;

import com.dongsamo.jogumanClone.component.ValidateHandler;
import com.dongsamo.jogumanClone.domain.product.ProductRepository;
import com.dongsamo.jogumanClone.domain.productImage.ProductImageRepository;
import com.dongsamo.jogumanClone.domain.user.User;
import com.dongsamo.jogumanClone.domain.user.UserRepository;
import com.dongsamo.jogumanClone.dto.ProductSimpleDto;
import com.dongsamo.jogumanClone.dto.UserDto;
import com.dongsamo.jogumanClone.service.ProductService;
import com.dongsamo.jogumanClone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/admin/user/update")
    public UserDto update(@RequestParam(value = "id", required = true) Long id) {

        UserDto userDto = new UserDto();

        userDto = userService.findById(id);

        return userDto;
    }

    @PostMapping("/admin/user/update")
    public ModelAndView update(@Valid UserDto userDto,
                          BindingResult errors) throws Exception {

        ModelAndView mv = new ModelAndView();

        if (errors.hasErrors()) {
            // 유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = validateHandler.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                //model.addAttribute(key, validatorResult.get(key));
                mv.addObject(key, validatorResult.get(key));
            }

            mv.setView(new RedirectView("/admin/user"));
            //mv.setViewName("admin");

            return mv;
        }

        UserDto userDtoRet = userService.updateById(userDto);

        mv.addObject("userDto", userDtoRet);
        mv.setView(new RedirectView("/admin/user"));

        return mv;
    }

    @PostMapping("admin/user/delete")
    public ModelAndView delete(@RequestParam(value = "id", required = true) Long id) {
        ModelAndView mv = new ModelAndView();

        userService.deleteById(id);

        return mv;
    }
}
