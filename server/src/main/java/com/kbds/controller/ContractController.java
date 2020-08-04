package com.kbds.controller;

import com.kbds.service.ContractService;
import com.kbds.service.UserService;
import com.kbds.vo.Contract;
import com.kbds.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Api(tags = {"***Contract***"})
@RequiredArgsConstructor
@RestController
@Slf4j
public class ContractController {
    private final ContractService contractService;
    private final UserService userService;

    @ApiOperation(value = "나의 모든 계약서 보기", notes = "")
    @GetMapping(value = "/my_contracts/{id}")
    public List<Contract> findMyContracts(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception{
        log.info("[CONTROLLER] :: findMyContracts");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        //마감일이 지난 계약서가 있는지 찾고, 있으면 종료시키기
        List<Contract> temp = contractService.findMyContracts(id);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat d_format = new SimpleDateFormat("yyMMdd");
        String today = d_format.format(cal.getTime());
        for(Contract c : temp){
            if(c.getStatus()==1 || c.getStatus()==3) continue;  //대기중이거나 이미 종료된 계약은 건너뛰기
            String duedate = contractService.findDuedate(c.getContractno());
            duedate = duedate.replace("-","");
            if(Integer.parseInt(duedate) < Integer.parseInt(today))
                contractService.terminateContract(c.getContractno());
        }

        //user 테이블 내 본인의 lent, borrowed 값 갱신
        int myLentCount = contractService.findMyLentContracts(id).size();
        int myBorrowedCount = contractService.findMyBorrowedContracts(id).size();
        User userToUpdate = new User();
        userToUpdate.setId(id);
        userToUpdate.setLent(myLentCount);
        userToUpdate.setBorrowed(myBorrowedCount);
        userService.updateMyCounts(userToUpdate);

        return contractService.findMyContracts(id);
    }

    @ApiOperation(value = "나의 모든 임차 계약서 보기", notes = "")
    @GetMapping(value = "/my_lent_contracts/{id}")
    public List<Contract> findMyLentContracts(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception{
        log.info("[CONTROLLER] :: findMyLentContracts");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        return contractService.findMyLentContracts(id);
    }

    @ApiOperation(value = "나의 모든 임대 계약서 보기", notes = "")
    @GetMapping(value = "/my_borrowed_contracts/{id}")
    public List<Contract> findMyBorrowedContracts(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception{
        log.info("[CONTROLLER] :: findMyBorrowedContracts");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        return contractService.findMyBorrowedContracts(id);
    }

    @ApiOperation(value = "계약서 상세보기", notes = "")
    @GetMapping("/contract/{contractno}")
    public Contract findContractByNo(@PathVariable int contractno, HttpServletRequest request, HttpServletResponse response) throws Exception{
        log.info("[CONTROLLER] :: findContractByNo");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        return contractService.findContractByNo(contractno);
    }

    @ApiOperation(value = "계약서 추가하기", notes = "")
    @PostMapping("/add_contract")
    public int addContract(Contract contract, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("[CONTROLLER] :: addContract");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        return contractService.addContract(contract);
    }

    @ApiOperation(value = "계약서 수락", notes = "")
    @GetMapping("/accept_contract/{contractno}")
    public int acceptContract(@PathVariable int contractno, HttpServletRequest request, HttpServletResponse response) throws Exception{
        log.info("[CONTROLLER] :: acceptContract");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        return contractService.acceptContract(contractno);
    }

    @ApiOperation(value = "계약서 거절", notes = "")
    @GetMapping("/reject_contract/{contractno}")
    public int rejectContract(@PathVariable int contractno, HttpServletRequest request, HttpServletResponse response) throws Exception{
        log.info("[CONTROLLER] :: rejectContract");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        return contractService.rejectContract(contractno);
    }

    @ApiOperation(value = "계약서 강제종료", notes = "")
    @GetMapping("/terminate_contract/{contractno}")
    public int terminateContract(@PathVariable int contractno, HttpServletRequest request, HttpServletResponse response) throws Exception{
        log.info("[CONTROLLER] :: terminateContract");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        return contractService.terminateContract(contractno);
    }
}