package br.com.zupacademy.breno.mercadolivre.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Void> createUser (@RequestBody @Valid UserRegisterRequest userRequest) {
        Optional<User> userObject = userRequest.convert();
        if(userObject.isPresent()) {
            repository.save(userObject.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
