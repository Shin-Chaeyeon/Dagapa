package com.kbds.controller;

import com.kbds.service.UserService;
import com.kbds.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags = {"***User***"})
@RequiredArgsConstructor
@RestController
@Slf4j
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "로그인", notes = "")
    @PostMapping("/sign_in")
    public User login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        User search = userService.findUser(user);
        if(user.getPw().equals(search.getPw())) {
            log.info("[CONTROLLER] :: login");
            log.info("[CONTROLLER] :: "+search.getId()+" "+search.getPw());
            return search;
        }
        else return null;
    }
}
