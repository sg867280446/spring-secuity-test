package com.hjl.security.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyConstraintValidator implements ConstraintValidator<MyConstraint,Object> {

    /**
     * 可以使用@Resource、@Autowired注解，且MyConstraintValidator不需要加@Service这些，因为
     * 只要实现了ConstraintValidator，就会被Spirng管理。
     */

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println(value);
        return false;
    }
}
