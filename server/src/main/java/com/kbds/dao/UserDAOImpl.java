package com.kbds.dao;

import com.kbds.vo.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO{
    private String ns = "UserMapper.";

    @Autowired
    private SqlSession sqlSession;

    @Override
    public User findUser(User user) {
        User res = sqlSession.selectOne(ns + "findUser", user);
        System.out.println(res.toString());
        return res;
        //return sqlSession.selectList(ns + "findUser", user);
    }
}
