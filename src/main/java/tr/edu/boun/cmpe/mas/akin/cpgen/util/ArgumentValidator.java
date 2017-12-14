package tr.edu.boun.cmpe.mas.akin.cpgen.util;

import java.util.Collection;

/**
 * @author Akin Gunay
 */
public class ArgumentValidator {

    public static void validateNotNull(Object object, String nameOfValidatedArgument) {
        if (object == null) {
            throw new NullPointerException("Argument \"" + nameOfValidatedArgument + "\" must not be null.");
        }
    }
    
    public static void validateNotEmptyString(String stringToValidate, String nameOfValidatedArgument) {
        if (stringToValidate.isEmpty()) {
            throw new IllegalArgumentException("Argument \"" + nameOfValidatedArgument + "\" must not be empty.");
        }
    }

    public static <T> void validateNotEmptyCollection(Collection<T> collectionToValidate, String nameOfValidatedArgument) {
        if (collectionToValidate.isEmpty()) {
            throw new IllegalArgumentException("Argument \"" + nameOfValidatedArgument + "\" must not be empty.");
        }
    }
    
    public static void validateNotNullAndNotEmptyString(String stringToValidate, String nameOfValidatedArgument) {
        validateNotNull(stringToValidate, nameOfValidatedArgument);
        validateNotEmptyString(stringToValidate, nameOfValidatedArgument);
    }
    
    public static <T> void validateNotNullAndNotEmptyCollection(Collection<T> collectionToValidate, String nameOfValidatedArgument) {
        validateNotNull(collectionToValidate, nameOfValidatedArgument);
        validateNotEmptyCollection(collectionToValidate, nameOfValidatedArgument);
    }
    
    
    public static void validateLargerThan(int threshold, int argument, String nameOfValidatedArgument) {
        if (argument <= threshold) {
            throw new IllegalArgumentException("Argument \"" + nameOfValidatedArgument + "\" must be larger than " + threshold + ". Given value is " + argument + ".");
        }
    }
    
    private ArgumentValidator() {}
}
