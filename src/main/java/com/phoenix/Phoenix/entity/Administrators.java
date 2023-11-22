package com.phoenix.Phoenix.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Administrators")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Administrators {
    @Id
    @Column(name = "Username")
    private String username;

    @Column(name = "Password")
    private String password;

    @Column(name = "JobTitle")
    private String jobTitle;
}
