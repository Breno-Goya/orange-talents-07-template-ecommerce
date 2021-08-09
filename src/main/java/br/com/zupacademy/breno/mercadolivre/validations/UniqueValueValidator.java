package br.com.zupacademy.breno.mercadolivre.validations;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    @PersistenceContext
    private EntityManager entityManager;

    private String attribute;
    private Class<?> klass;


    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        attribute = constraintAnnotation.fieldName();
        klass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = entityManager.createQuery("SELECT 1 FROM " + klass.getName() + " WHERE " + attribute + " = :value");
        query.setParameter("value", value);
        List<?> result = query.getResultList();
        return result.isEmpty();
    }

}
