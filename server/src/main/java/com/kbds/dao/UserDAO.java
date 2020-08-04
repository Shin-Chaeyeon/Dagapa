package com.kbds.dao;

import com.kbds.vo.User;

public interface UserDAO {
    public User findUser(User user);
    public int updateMyCounts(User user);
}
