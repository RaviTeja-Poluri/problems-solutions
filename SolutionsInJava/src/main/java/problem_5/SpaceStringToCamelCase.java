package problem_5;

/**
 * * This class will convert space seperated string to camel case
 * * Ex: "class name is camel case" will be converted to classNameInCamelCase
 **/
public class SpaceStringToCamelCase {
    public static void main(String[] args) {
        String name = "class name is camel case";
        System.out.println(spaceStringToCamelCaseString(name));
    }


    public static String spaceStringToCamelCaseString(String name) {
        String[] splittedString = name.split(" ");
        StringBuilder finalString = new StringBuilder(splittedString[0]);
        for (int i = 1; i < splittedString.length; i++) {
            String atPosition = splittedString[i];
            char c = atPosition.charAt(0);
            String remainingString =
                    String.valueOf(c).toUpperCase() + atPosition.substring(1);
            finalString.append(remainingString);
        }
        return finalString.toString();
    }
}
