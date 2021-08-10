package br.com.zupacademy.breno.mercadolivre.categories;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryRepository repository;

    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?> createNewCategory (@Valid @RequestBody CategoryRegisterRequest categoryRequest) {
        Optional<Category>  categoryObject =  categoryRequest.convert(repository);
        if(categoryObject.isPresent()) {
            repository.save(categoryObject.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
