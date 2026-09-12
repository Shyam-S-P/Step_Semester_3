import java.util.Random;
import java.util.Scanner;

public class Week_1_RockPaperScissorsGame {

    static String playRound(String playerMove, String computerMove) {
        playerMove = playerMove.trim().toLowerCase();
        computerMove = computerMove.trim().toLowerCase();

        if (playerMove.equals(computerMove))
            return "Draw";

        if ((playerMove.equals("rock") && computerMove.equals("scissors")) ||
            (playerMove.equals("paper") && computerMove.equals("rock")) ||
            (playerMove.equals("scissors") && computerMove.equals("paper")))
            return "Player Wins";

        return "Computer Wins";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        String[] moves = {"Rock", "Paper", "Scissors"};

        System.out.println("Enter number of rounds:");
        int n = sc.nextInt();
        sc.nextLine();

        String[] players = new String[n];
        String[] computers = new String[n];
        String[] results = new String[n];

        int wins = 0;
        int losses = 0;
        int draws = 0;

        for (int i = 0; i < n; i++) {
            System.out.println("Round " + (i + 1) +
                    " - Enter Rock, Paper, or Scissors:");
            String player = sc.nextLine();

            String computer = moves[random.nextInt(3)];
            String result = playRound(player, computer);

            players[i] = player;
            computers[i] = computer;
            results[i] = result;

            if (result.equals("Player Wins"))
                wins++;
            else if (result.equals("Computer Wins"))
                losses++;
            else
                draws++;
        }

        System.out.println("\nRound | Player Move | Computer Move | Result");

        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + " | " +
                    players[i] + " | " +
                    computers[i] + " | " +
                    results[i]);
        }

        double winPercentage = n == 0 ? 0 : (wins * 100.0) / n;

        System.out.printf("Wins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%%n",
                wins, losses, draws, winPercentage);

        sc.close();
    }
}
