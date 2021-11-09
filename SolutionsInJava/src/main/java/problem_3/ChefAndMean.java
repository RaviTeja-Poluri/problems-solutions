package problem_3;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ChefAndMean {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCases = scanner.nextInt();
        scanner.nextLine();

        while (testCases != 0) {
            int numberOfCoins = scanner.nextInt();
            scanner.nextLine();
            String coinValues = scanner.nextLine();
            String[] individualValues = coinValues.split(" ");
            double initialMean = getMeanOfCoins(individualValues,numberOfCoins);
            long numberOfCoinsRemoved = getNumberOfCoinsCanBeRemoved(individualValues, initialMean);
            if (numberOfCoinsRemoved > 0) {
                System.out.println(1);
            } else {
                System.out.println("Impossible");
            }
            testCases--;
        }
    }

    private static long getNumberOfCoinsCanBeRemoved(String[] originalCoins, double originalMean) {
        long removedCoins = 0;
        for (int i = 0; i < originalCoins.length; i++) {
            String[] newCopy = Arrays.copyOf(originalCoins, originalCoins.length);
            newCopy[i] = "0";
            double newMean = getMeanOfCoins(newCopy,originalCoins.length - 1);
            System.out.println(newMean + " :: "+ originalMean);
            if (newMean == originalMean) {
                removedCoins++;
            }
        }
        return removedCoins;
    }


    private static double getMeanOfCoins(String[] coins,long totalCoins) {
        long totalSum = 0;
        for (String coin : coins) {
            int val = Integer.parseInt(coin);
            totalSum += val;
        }
        return totalSum / (double) totalCoins;
    }
}
