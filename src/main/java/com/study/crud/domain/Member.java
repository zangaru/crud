package com.study.crud.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;


@Getter
@Entity
@Builder
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)//같은 패키지의 클래스와 상속받은 클래스에서 생성자에 접근 가능
public class Member {

    @Id
    @Column(name = "member_id", length = 20, nullable = false)
    private String id;

    @Column(name = "member_password", nullable = false)
    private String password;

    @Column(name = "member_name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)//enum의 값을 String으로 저장
    @Column(name = "member_role", nullable = false)
    private MemberRole role;
}
