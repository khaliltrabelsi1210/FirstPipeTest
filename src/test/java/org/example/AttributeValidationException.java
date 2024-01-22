package org.example;

public class AttributeValidationException extends RuntimeException {

    public AttributeValidationException(String message) {
        super(message);
    }


    public static void validateNotNull(Object attribute, String attributeName) throws AttributeValidationException {
        if (attribute == null) {
            throw new AttributeValidationException(attributeName + " ne peut pas être null.");
        }

    }

}



