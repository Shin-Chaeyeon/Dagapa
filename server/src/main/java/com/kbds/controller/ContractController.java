package com.kbds.controller;

import com.kbds.service.ContractService;
import com.kbds.vo.Contract;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"***Contract***"})
@RequiredArgsConstructor
@RestController
@Slf4j
public class ContractController {
    private final ContractService contractService;

    @ApiOperation(value = "나의 모든 계약서 보기", notes = "")
//    @GetMapping(value = "/my_contracts/{userno}", produces = "text/plain;charset=UTF8")
    @GetMapping(value = "/my_contracts/{userno}")
    public List<Contract> findMyContracts(@PathVariable int userno){
        return contractService.findMyContracts(userno);
    }

    @ApiOperation(value = "계약서 상세보기", notes = "")
    @GetMapping("/contract/{contractno}")
    public Contract findContractByNo(@PathVariable int contractno){
        return contractService.findContractByNo(contractno);
    }

    @ApiOperation(value = "계약서 추가하기", notes = "")
    @PostMapping("/add_contract")
    public int addContract(@RequestBody Contract contract){
        return contractService.addContract(contract);
    }

    @ApiOperation(value = "계약서 강제종료", notes = "")
    @GetMapping("/terminate_contract/{contractno}")
    public int terminateContract(@PathVariable int contractno){
        return contractService.terminateContract(contractno);
    }
}