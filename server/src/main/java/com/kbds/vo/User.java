package com.kbds.vo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User {
    private String id;
    private String pw;
    private String name;
    private String phone;
    private int lent;
    private int borrowed;
}
