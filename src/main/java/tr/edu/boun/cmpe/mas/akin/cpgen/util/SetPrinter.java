package tr.edu.boun.cmpe.mas.akin.cpgen.util;

import java.util.Set;

/**
 * @author Akin Gunay
 */
public class SetPrinter {

    private SetPrinter() {}
    
    public static <T> String setPrinter(Set<T> set) {
        StringBuilder str = new StringBuilder("{");
        for(T element : set) {
            str.append(element).append(", ");
        }
        if (!set.isEmpty()) {
            str.replace(str.lastIndexOf(", "), str.length(), "");
        }
        return str.append("}").toString();
    }
}
