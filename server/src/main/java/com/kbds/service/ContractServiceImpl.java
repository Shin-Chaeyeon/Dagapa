package com.kbds.service;

import com.kbds.dao.ContractDAO;
import com.kbds.vo.Contract;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ContractServiceImpl implements ContractService{
    private final ContractDAO contractDAO;

    @Override
    public List<Contract> findMyContracts(int userno) {
        log.info("[SERVICE] :: findMyContracts(user)");
        return contractDAO.findMyContracts(userno);
    }

    @Override
    public Contract findContractByNo(int contractno) {
        log.info("[SERVICE] :: findContractByNo(contractno)");
        return contractDAO.findContractByNo(contractno);
    }

    @Override
    public int addContract(Contract contract) {
        return contractDAO.addContract(contract);
    }

    @Override
    public int terminateContract(int contractno) {
        return contractDAO.terminateContract(contractno);
    }

    @Override
    public int acceptContract(int contractno) {
        return contractDAO.acceptContract(contractno);
    }

    @Override
    public int rejectContract(int contractno) {
        return contractDAO.rejectContract(contractno);
    }
}