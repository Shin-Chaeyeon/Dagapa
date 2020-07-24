package com.kbds.service;

import com.kbds.dao.ContractDAO;
import com.kbds.vo.Contract;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ContractServiceImpl implements ContractService, FileService<Contract> {
    private final ContractDAO contractDAO;

    @Override
    public List<Contract> findMyContracts(int userno) {
        return contractDAO.findMyContracts(userno);
    }

    @Override
    public List<Contract> findMyLentContracts(int userno) {
        return contractDAO.findMyLentContracts(userno);
    }

    @Override
    public List<Contract> findMyBorrowedContracts(int userno) {
        return contractDAO.findMyBorrowedContracts(userno);
    }

    @Override
    public Contract findContractByNo(int contractno) {
        return contractDAO.findContractByNo(contractno);
    }

    @Override
    public int addContract(Contract contract) throws IOException {
        fileSave(contract);
        return contractDAO.addContract(contract);
    }

    @Override
    public int acceptContract(int contractno) {
        return contractDAO.acceptContract(contractno);
    }

    @Override
    public int rejectContract(int contractno) {
        return contractDAO.rejectContract(contractno);
    }

    @Override
    public String findDuedate(int contractno) {
        return contractDAO.findDuedate(contractno);
    }

    @Override
    public int terminateContract(int contractno) {
        return contractDAO.terminateContract(contractno);
    }

    @Override
    public void fileSave(Contract contract) throws IOException{
        String base = "http://192.168.100.197:8080/";
        String resourcesPath = "dfdf";
        String basePath = "dfdf";

        MultipartFile[] multipartFiles = contract.getImage();
        StringBuffer stringBuffer = new StringBuffer();
        int count = 0;
        int size = multipartFiles.length;
        for(MultipartFile multipartFile : multipartFiles){
            String originFileName = multipartFile.getOriginalFilename();
            if(multipartFile != null){
                String saveFileName = String.format("%s_%s", contract.getLender(), originFileName);
                String savePath = String.format("%s/%s%s", base, resourcesPath, saveFileName);
                multipartFile.transferTo(new File(String.format("%s%s", basePath, resourcesPath), saveFileName));
                stringBuffer.append(savePath);
                if(size - 1 != count)stringBuffer.append(",");
            }
            count++;
        }
        contract.setImageurl(stringBuffer.toString());
    }
}