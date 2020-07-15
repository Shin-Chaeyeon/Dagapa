package com.kbds.controller;

import com.kbds.service.UserService;
import com.kbds.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"***User***"})
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "로그인", notes = "로그인을 할 경우[...]")
    @PostMapping("/sign_in")
    public User login(@RequestBody User user){
        User search = userService.findUser(user);
        if(user.getPw().equals(search.getPw())) {
            System.out.println("마자아아아아아아아용");
            return search;
        }
        else return null;
    }
}
