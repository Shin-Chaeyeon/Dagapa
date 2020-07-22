package com.kbds.service;

import com.kbds.vo.User;
import com.kbds.vo.Contract;

import java.util.List;

public interface ContractService {
    public List<Contract> findMyContracts(int userno);
    public Contract findContractByNo(int contractno);
    public int addContract(Contract contract);
    public int acceptContract(int contractno);
    public int rejectContract(int contractno);
    public String findDuedate(int contractno);
    public int terminateContract(int contractno);
}
