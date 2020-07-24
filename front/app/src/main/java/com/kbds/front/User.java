package com.kbds.front;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User {
    int userno;
    String id;
    String pw;
    String nickname;
    String phone;
}
