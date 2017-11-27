package tr.edu.boun.cmpe.mas.akin.cpgen.util;

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
    
    public static void validateNotNullAndNotEmpty(String stringToValidate, String nameOfValidatedArgument) {
        validateNotNull(stringToValidate, nameOfValidatedArgument);
        validateNotEmptyString(stringToValidate, nameOfValidatedArgument);
    }
    
    public static void validateLargerThan(int threshold, int argument, String nameOfValidatedArgument) {
        if (argument <= threshold) {
            throw new IllegalArgumentException("Argument \"" + nameOfValidatedArgument + "\" must be larger than " + threshold + ". Given value is " + argument + ".");
        }
    }
    
    private ArgumentValidator() {}
}
