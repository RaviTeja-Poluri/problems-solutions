package problem_6;

import java.util.HashMap;
import java.util.Map;

/**
 * Given three inputs calculate how much each player scored runs in a over and extras bowled.
 * Assuming no wickets fallen in the over.
 * Input 1: comma separated values with runs secured per ball. It will be either number or WD or NB
 *          Wd-wide, NB-nobe
 *          valid numbers=>1 2 3 4 6 0
 * Input 2: comma separated striker,non-striker
 *
 * SAMPLE INPUT
 * 1,2,4,4,wd,1,2
 * p2,p1
 * =================================
 * SAMPLE OUTPUT
 * p1 11
 * p2 3
 * extras 1
 * =================================
 *
 */
public class ScoresMadeInAOver {
    int extras = 0;

    public static void main(String[] args) {
        // must be >= 6
        String overStats = args[0];
        String playerOnStrikeAfterLastBall = args[1];
        ScoresMadeInAOver scoresMadeInAOver = new ScoresMadeInAOver();
        scoresMadeInAOver.calculateRuns(scoresMadeInAOver.parseOverStats(overStats),
                playerOnStrikeAfterLastBall.split(","));

    }

    private void calculateRuns(String[] runsPerBall,String[] playerOnStrikeAfterLastBall){
        String playerOnStrike = playerOnStrikeAfterLastBall[0];
        String nonStriker = playerOnStrikeAfterLastBall[1];
        Map<String ,Integer> playerScore = new HashMap<>(2);
        playerScore.put(playerOnStrike, 0);
        playerScore.put(nonStriker,0);
        for (int run = runsPerBall.length - 1; run >= 0; run--) {
            String runsScored = runsPerBall[run];
            if (runsScored.equalsIgnoreCase("WD") || runsScored.equalsIgnoreCase("NB")){
                extras +=1;
            } else {
               if (changeStrike(Integer.parseInt(runsScored))){
                   String temp = playerOnStrike;
                   playerOnStrike = nonStriker;
                   nonStriker = temp;
               }
                Integer runs = playerScore.get(playerOnStrike);
                runs += Integer.parseInt(runsScored);
                System.out.println(playerOnStrike + " == "+ runs);
                playerScore.put(playerOnStrike, runs);
            }
        }
        System.out.println("scores = "+playerScore + " and extras = "+extras);
    }

    private boolean changeStrike(int runs){
        return (runs == 1 || runs == 3);
    }

    private String[] parseOverStats(String overStats){
        String[] runsPerBallInOver = overStats.split(",");
        if (runsPerBallInOver.length < 6){
            throw new RuntimeException("Invalid over");
        }
        return runsPerBallInOver;
    }
}
