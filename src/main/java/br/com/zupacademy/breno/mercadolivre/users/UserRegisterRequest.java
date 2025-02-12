package br.com.zupacademy.breno.mercadolivre.users;

import br.com.zupacademy.breno.mercadolivre.validations.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Optional;

public class UserRegisterRequest {

    @Email
    @NotBlank
    @UniqueValue(domainClass = User.class, fieldName = "email")
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;

    public UserRegisterRequest(String emailLogin, String password) {
        this.email = emailLogin;
        this.password = password;
    }

    public Optional<User> convert() {
        User user = new User(email, new Password(password));
        return Optional.of(user);
    }
}
