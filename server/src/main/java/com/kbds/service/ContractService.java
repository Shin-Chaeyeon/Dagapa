package com.kbds.service;

import com.kbds.vo.Contract;

import java.util.List;

public interface ContractService {
    public List<Contract> findMyContracts(String id);
    public List<Contract> findMyLentContracts(String id);
    public List<Contract> findMyBorrowedContracts(String id);
    public Contract findContractByNo(int contractno);
    public int addContract(Contract contract) throws Exception;
    public int acceptContract(int contractno);
    public int rejectContract(int contractno);
    public String findDuedate(int contractno);
    public int terminateContract(int contractno);
}
