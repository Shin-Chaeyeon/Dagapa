package com.kbds.controller;

import com.kbds.service.ContractService;
import com.kbds.vo.Contract;
import com.kbds.vo.User;
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
    @GetMapping("/my_contracts/{userno}")
    public List<Contract> findMyContracts(@PathVariable int userno){
        return contractService.findMyContracts(userno);
    }

    @ApiOperation(value = "계약서 상세보기", notes = "")
    @GetMapping("/contract/{contractno}")
    public Contract findContractByNo(@PathVariable int contractno){
        return contractService.findContractByNo(contractno);
    }
}