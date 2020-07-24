package com.kbds.front;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Contract {
    int contractno;
    String lender;
    String borrower;
    int type;
    String goods;
    String duedate;
    String imageurl;
    String audiourl;
    String description;
    int agreed;
}