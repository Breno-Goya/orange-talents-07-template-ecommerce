package br.com.zupacademy.breno.mercadolivre.authentications;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginUserRequest {

    private String email;

    private String password;

    public LoginUserRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UsernamePasswordAuthenticationToken convert() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
