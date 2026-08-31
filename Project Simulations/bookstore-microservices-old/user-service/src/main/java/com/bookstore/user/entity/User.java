package com.bookstore.user.entity;

import jakarta.persistence.*;


@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String fullName;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false)
    private String password;

    private String phone;
    private boolean verified;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    public Long getId(){ return id; }
    public String getFullName(){ return fullName; }
    public void setFullName(String v){fullName=v;}
    public String getEmail(){return email;}
    public void setEmail(String v){email=v;}
    public String getPassword(){return password;}
    public void setPassword(String v){password=v;}
    public String getPhone(){return phone;}
    public void setPhone(String v){phone=v;}
    public boolean isVerified(){return verified;}
    public void setVerified(boolean v){verified=v;}
    public Role getRole(){return role;}
    public void setRole(Role v){role=v;}
}
