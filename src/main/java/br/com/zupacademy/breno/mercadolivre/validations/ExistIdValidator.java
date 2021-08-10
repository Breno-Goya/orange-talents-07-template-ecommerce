package br.com.zupacademy.breno.mercadolivre.validations;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistIdValidator implements ConstraintValidator<ExistId, Long> {

    private String attribute;
    private Class<?> klass;
    private boolean permiteNulo;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(ExistId constraintAnnotation) {
        attribute = constraintAnnotation.fieldName();
        klass = constraintAnnotation.domainClass();
        permiteNulo = constraintAnnotation.permiteNulo();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {

        if(permiteNulo && value == null) {
            return true;
        }

        Query query = entityManager.createQuery("SELECT 1 FROM " + klass.getName() + " WHERE " + attribute + " = :value");
        query.setParameter("value", value);

        List<?> result = query.getResultList();
        return !result.isEmpty();
    }
}
