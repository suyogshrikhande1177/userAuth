package com.practice.userAuth.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames="username")
//        @UniqueConstraint(columnNames = "email")
})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeqGen")
    @SequenceGenerator(name = "userSeqGen", sequenceName = "user_seq_gen", initialValue = 1)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "username")
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    @Column(name = "email")
    private String email;

    @NotBlank
    @Size(max = 120)
    @Column(name = "password")
    private String password;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

//    @Column(name = "role_id")
//    private String RoleId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "pin_code")
    private String pinCode;

    @Column(name = "mob_no")
    private String mobNo;

    @Column(name = "adhar_no")
    private String adharNo;

    @Column(name = "pan_no")
    private String panNo;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "company_key")
    private Long companyKey;

}
