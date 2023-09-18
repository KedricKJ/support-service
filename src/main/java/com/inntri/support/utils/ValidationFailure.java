package com.inntri.support.utils;

import com.inntri.support.component.ValidationMessageBundleHolder;
import lombok.Data;

import java.util.Locale;

/**
 * @author Nuwan
 */
@Data
public class ValidationFailure {

    private String field;
    private String code;

    public ValidationFailure(String field, String code) {
        this.field = field;
        this.code = code;
    }

    public String getCode() {
        return ValidationMessageBundleHolder.getValidationMessageBundle().getMessage(code, null, Locale.US);
    }
}
