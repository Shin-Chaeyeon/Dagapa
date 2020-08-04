package com.kbds.dao;

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
    public List<Contract> findMyContracts(String id) {
        return sqlSession.selectList(ns + "findMyContracts", id);
    }

    @Override
    public List<Contract> findMyLentContracts(String id) {
        return sqlSession.selectList(ns + "findMyLentContracts", id);
    }

    @Override
    public List<Contract> findMyBorrowedContracts(String id) {
        return sqlSession.selectList(ns + "findMyBorrowedContracts", id);
    }

    @Override
    public Contract findContractByNo(int contractno) {
        return sqlSession.selectOne(ns + "findContractByNo", contractno);
    }

    @Override
    public int addContract(Contract contract) {
        return sqlSession.insert(ns + "addContract", contract);
    }

    @Override
    public int acceptContract(int contractno) {
        return sqlSession.update(ns + "acceptContract", contractno);
    }

    @Override
    public int rejectContract(int contractno) {
        return sqlSession.update(ns + "rejectContract", contractno);
    }

    @Override
    public String findDuedate(int contractno) {
        return sqlSession.selectOne(ns + "findDuedate", contractno);
    }

    @Override
    public int terminateContract(int contractno) {
        return sqlSession.update(ns + "terminateContract", contractno);
    }
}
