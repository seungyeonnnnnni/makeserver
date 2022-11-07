package com.ssumc.crud.controller;

import com.ssumc.crud.domain.User;
import com.ssumc.crud.service.UserService;
import com.ssumc.crud.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userService = userServiceImpl;
    }

    @GetMapping(value = "/users/new")
    public String createForm() {
        return "users/createUserForm";
    }

    @PostMapping(value = "users/new")
    public String create(UserForm form) {
        User user = new User();
        user.setUserName(form.getUserName());

        userService.join(user);
        return "redirect:/";
    }

    @GetMapping(value = "users")
    public String list(Model model) {
        List<User> users = userService.findUsers();
        model.addAttribute("users", users);
        return "users/userList";
    }


    @PostMapping("/join")
    public Object userJoin() {
        log.debug("/userJoin start");
        List<User> userList = userService.findUsers();
        log.debug(userList.toString());

        User user = new User();
        user.setUserEmail("aaa");
        user.setUserPhone("3234");
        user.setUserName("seungo");
        user.setPassword("hello");

        user.setUserStatus('1');
        user.setUserGrade('1');
        userService.join(user);

        return userList;

    }
}
