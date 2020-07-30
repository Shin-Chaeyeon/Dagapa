package com.kbds.vo;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Contract {
    private MultipartFile[] image;
    private int contractno;
    private String lender;
    private String borrower;
    private int type;
    private String goods;
    private String startdate;
    private String duedate;
    private String imageurl;
    private String description;
    private int status;
    private String creator;
}