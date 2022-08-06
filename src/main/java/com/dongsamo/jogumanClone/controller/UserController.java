package com.dongsamo.jogumanClone.controller;

import com.dongsamo.jogumanClone.component.ValidateHandler;
import com.dongsamo.jogumanClone.domain.user.UserRepository;
import com.dongsamo.jogumanClone.dto.ProductSimpleDto;
import com.dongsamo.jogumanClone.dto.UserDto;
import com.dongsamo.jogumanClone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final ValidateHandler validateHandler;

    // "/user"로 POST요청이 오면 userService의 save()를 호출한 후에 "/Login"페이지로 이동하게 함
    @PostMapping("/user")
    public String signup(@Valid UserDto userDto,
                         BindingResult errors,
                         Model model) throws Exception { //회원 추가

        if (errors.hasErrors()) {
            // 유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = validateHandler.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            // 데이터 갱신을 위한 임시 코드
            model.addAttribute(userDto);

            return "/signup";
        }

        userService.save(userDto);
        return "redirect:/login";
    }

    // Spring Security 설정에서 로그아웃 관련 설정을 했지만, 이 로그아웃은 POST 요청에 csrf를 보내야 하기 때문에 직접 패스를 치면 404에러가 뜬다.
    // 따라서 GET 요청으로 로그아웃을 해도 로그아웃이 가능하게 구현
    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        //기본으로 제공해주는 SecurityContextLogoutHandler()의 logout()을 사용해서 로그아웃 처리 구현

        return "redirect:/login";
    }
}
