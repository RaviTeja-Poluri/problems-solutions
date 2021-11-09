package problem_2;

import java.util.*;

/**
*** Class will convert camel case String to space seperated string.
*** Ex: "helloWorldTest" will be converted to "hello world test"
**/
public class CamelCaseStringToSpacedString{

    public static void main(String[] args) {
        System.out.println(camelCaseStringToSpacedString("helloWorldTest"));
    }

    public static String camelCaseStringToSpacedString(String name) {

        List<String> caps = new ArrayList<>();
        caps.add("A");
        caps.add("B");
        caps.add("C");
        caps.add("D");
        caps.add("E");
        caps.add("F");
        caps.add("G");
        caps.add("H");
        caps.add("I");
        caps.add("J");
        caps.add("K");
        caps.add("L");
        caps.add("M");
        caps.add("N");
        caps.add("O");
        caps.add("P");
        caps.add("Q");
        caps.add("R");
        caps.add("S");
        caps.add("T");
        caps.add("U");
        caps.add("V");
        caps.add("W");
        caps.add("X");
        caps.add("Y");
        caps.add("Z");

        StringBuilder finalString = new StringBuilder();
        String lastName = "";
        int i = 0, j = 0;
        while (i < name.length()) {
            String charAt = name.charAt(i) + "";
            if (caps.contains(charAt)) {
                finalString.append(name, j, i);
                finalString.append(" ");
                lastName = name.substring(i);
                j = i;
            }
            ++i;
        }
        finalString.append(lastName);
        return finalString.toString().toLowerCase();
    }

}
