package com.inntri.support.component;

import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author nuwan
 */
public class ValidationMessageBundleHolder {

    private static ResourceBundleMessageSource validationMessageBundle = null;

    public static ResourceBundleMessageSource getValidationMessageBundle() {
        return validationMessageBundle;
    }

    public static void setValidationMessageBundle(ResourceBundleMessageSource validationMessageBundle) {
        ValidationMessageBundleHolder.validationMessageBundle = validationMessageBundle;
    }
}
