package br.com.zupacademy.breno.mercadolivre.users;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotEmpty
    @Column(unique = true)
    private String emailLogin;

    @Size(min = 6)
    private String password;

    @PastOrPresent
    private LocalDate registrationTime;

    @Deprecated
    public User() {
    }

    public User(String emailLogin, Password rawPassword) {
        this.emailLogin = emailLogin;
        this.password = rawPassword.hash();
        registrationTime = LocalDate.now();
    }
}
