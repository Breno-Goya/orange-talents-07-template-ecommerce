package br.com.zupacademy.breno.mercadolivre.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExistIdValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface ExistId {

    public String message() default "Validation Constraint Error";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default{};

    String fieldName();

    Class<?> domainClass();
}
