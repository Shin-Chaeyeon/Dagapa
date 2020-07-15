package com.kbds.service;

import com.kbds.dao.UserDAO;
import com.kbds.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserDAO userDAO;

    @Override
    public User findUser(User user) {
        System.out.println("service에용");
        return userDAO.findUser(user);
    }
}
