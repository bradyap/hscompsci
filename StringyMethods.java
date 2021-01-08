public class StringyMethods {
    
    public static void main(String[] args) {
        System.out.println("Expected: 4");
        System.out.println("  Actual: " + countOccurrences("Miss Mississippi is here!", "is"));
        System.out.println("Expected: 0");
        System.out.println("  Actual: " + countOccurrences("Miss Mississippi is here!", "sips"));
    
        System.out.println("Expected: rebraB .rM");
        System.out.println("  Actual: " + reverse("Mr. Barber"));
        System.out.println("Expected: suoicodilaipxecitsilegarfilacrepus");
        System.out.println("  Actual: " + reverse("supercalifragelisticexpialidocious"));

        System.out.println("Expected: abllte");
        System.out.println("  Actual: " + codeString("ballet"));
        System.out.println("Expected: dobdlal");
        System.out.println("  Actual: " + codeString("oddball"));
        System.out.println("Expected: beautiful");
        System.out.println("  Actual: " + codeString(codeString("beautiful")));
                
        System.out.println("Expected: 3");
        System.out.println("  Actual: " + countSubstringsStartingWith("blue", "bl"));
        System.out.println("Expected: 13");
        System.out.println("  Actual: " + countSubstringsStartingWith("bubble", "b"));
        System.out.println("Expected: 11");
        System.out.println("  Actual: " + countSubstringsStartingWith("Miss Mississippi", "si"));
    }

    public static int countOccurrences(String s, String target) {
        int count = 0;
        int index = 0;
        while (index != -1) {
            index = s.indexOf(target, index);
            if (index != -1) {
                count++;
                index += target.length();
            }
        }
        return count;
    }

    public static String reverse(String s) {
        //String output = new StringBuilder(s).reverse().toString();
        StringBuilder temp = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            temp.append(s.substring(i, i+1));
        }
        String output = temp.toString();
        return output;
    }
            
    public static String codeString(String s) {
        char[] chars = s.toCharArray();
        for (int i = 1; i < chars.length; i += 2) {
            char temp = chars[i];
            chars[i] = chars[i - 1];
            chars[i - 1] = temp;
        }
        String result = new String(chars);
        return result; 
    }

    public static int countSubstringsStartingWith(String s, String target) {
        int count = 0;
        int index = 0;
        while (index != -1) {
            index = s.indexOf(target, index);
            if (index != -1) {
                count = count + (s.length() - (index + (target.length() - 1)));
                index += target.length();
            }
        }
        return count;
    }
}
