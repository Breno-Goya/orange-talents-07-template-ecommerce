package br.com.zupacademy.breno.mercadolivre.categories;

import br.com.zupacademy.breno.mercadolivre.validations.ExistId;
import br.com.zupacademy.breno.mercadolivre.validations.UniqueValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Optional;

public class CategoryRegisterRequest {

    @NotBlank
    @UniqueValue(domainClass = Category.class, fieldName = "name")
    private String name;

    @JsonProperty(value = "superCategory")
    @Positive
//    @ExistId(domainClass = Category.class, fieldName = "id")
    private Long superCategory;

    public CategoryRegisterRequest(String name, Long superCategory) {
        this.name = name;
        this.superCategory = superCategory;
    }

    public Optional<Category> convert(CategoryRepository repository) {
        Category category = new Category(name);

        if(superCategory != null) {
            Optional<Category> superCategoryObject = repository.findById(superCategory);

            category.setSuperCategory(superCategoryObject.get());
        }
        return Optional.of(category);
    }
}
