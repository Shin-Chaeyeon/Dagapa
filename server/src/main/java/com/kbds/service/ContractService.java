package com.kbds.service;

import com.kbds.vo.User;
import com.kbds.vo.Contract;

import java.util.List;

public interface ContractService {
    public List<Contract> findMyContracts(int userno);
    public Contract findContractByNo(int contractno);
    public int addContract(Contract contract);
    public int terminateContract(int contractno);
}
