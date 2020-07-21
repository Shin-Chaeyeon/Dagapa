package com.kbds.dao;

import com.kbds.vo.Contract;
import com.kbds.vo.User;

import java.util.List;

public interface ContractDAO {
    public List<Contract> findMyContracts(int userno);
    public Contract findContractByNo(int contractno);
}
