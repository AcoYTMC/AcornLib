package net.acoyt.acornlib.api.util;

@SuppressWarnings("unused")
public class MathUtils {
    public static boolean lessThan(int input, int compare) {
        return input < compare;
    }
    
    public static boolean lessThanOrEqualTo(int input, int compare) {
        return input <= compare;
    }
    
    public static boolean moreThan(int input, int compare) {
        return input > compare;
    }
    
    public static boolean moreThanOrEqualTo(int input, int compare) {
        return input >= compare;
    }
    
    public static boolean equalTo(int input, int compare) {
        return input == compare;
    }
}
