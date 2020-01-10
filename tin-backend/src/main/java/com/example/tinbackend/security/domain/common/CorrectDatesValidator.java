package com.example.tinbackend.security.domain.common;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class CorrectDatesValidator implements ConstraintValidator<CorrectDateOrder, Object>
{
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(final CorrectDateOrder constraintAnnotation)
    {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context)
    {
        try
        {
            final String startString = BeanUtils.getProperty(value, firstFieldName);
            final String endString = BeanUtils.getProperty(value, secondFieldName);
            LocalDate fromDate = LocalDate.parse(startString);
            LocalDate toDate = LocalDate.parse(endString);
            return !toDate.isBefore(fromDate);
        }
        catch (final Exception ignore)
        {
            // ignore
        }
        return true;
    }
}
