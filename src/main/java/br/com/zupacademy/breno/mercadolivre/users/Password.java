package br.com.zupacademy.breno.mercadolivre.users;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotNull;

public class Password {

    private String password;

    public Password(@NotNull String rawPassword) {
        this.password =  rawPassword;
    }

    public String hash() {
        return new BCryptPasswordEncoder().encode(password);
    }
}
