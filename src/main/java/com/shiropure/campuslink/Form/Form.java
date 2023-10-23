package com.shiropure.campuslink.Form;

import com.shiropure.campuslink.utils.InputCheck;

import java.util.Arrays;

public abstract class Form {

    public abstract boolean isFormValid();

    protected boolean isNull(String s) {
        return s == null || s.trim().isEmpty();
    }

    protected boolean areAnyFieldsNull(Object... fields) {
        return Arrays.stream(fields).anyMatch(field ->
                field == null || (field instanceof String && isNull((String) field))
        );
    }

    protected boolean isEmailValid(String email) {
        return !isNull(email) && InputCheck.isEmail(email);
    }
}
