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
    private String nickname;
    private String phone;
    private String lent;
    private String borrowed;
}
