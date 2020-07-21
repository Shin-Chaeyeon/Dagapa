package com.kbds.dao;

import com.kbds.vo.User;
import com.kbds.vo.Contract;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContractDAOImpl implements ContractDAO{
    private String ns = "ContractMapper.";

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<Contract> findMyContracts(int userno) {
        return sqlSession.selectList(ns + "findMyContracts", userno);
    }

    @Override
    public Contract findContractByNo(int contractno) {
        return sqlSession.selectOne(ns + "findContractByNo", contractno);
    }
}
