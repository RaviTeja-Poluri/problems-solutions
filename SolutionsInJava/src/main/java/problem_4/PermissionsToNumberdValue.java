package problem_4;

/**
*** class will convert given permissions to numbered value.
*** provided each permission has value defined
******* w (2)
******* r (4)
******* x (1)
******* - (0)
*** Ex: "rwxr-x-w-" are provided permissions the resultant value produced will be 752.
*** so the above is splitted into three parts "rwx","r-x","-w-"
*** sum of each three is added together to get resultant value
*** sum of rwx=4+2+1=7
*** sum of r-x=4+0+1=5
*** sum of -w-=0+2+0=2
*** final result is 752
**/

public class PermissionsToNumberdValue{

      public static void main(String[] args) {
        String permString = "rwxr-x-w-";
        int len = permString.length();

        StringBuilder finalOctal = new StringBuilder();

        for (int i = 0; i < len; i += 3) {

            String subString = permString.substring(i, i + 3);

            String first = subString.charAt(0) + "";
            String second = subString.charAt(1) + "";
            String third = subString.charAt(2) + "";

            int sum = getNumber(first) + getNumber(second) + getNumber(third);

            finalOctal.append(sum);
        }
        System.out.println(finalOctal);
    }

    private static int getNumber(String val) {
        return switch (val) {
            case "w" -> 2;
            case "r" -> 4;
            case "x" -> 1;
            default -> 0;
        };
    }


}
