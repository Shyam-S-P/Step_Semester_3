import java.util.Scanner;

class Canteen {
    private String canteenCode;
    private String canteenName;
    private int trustScore;

    public Canteen(String canteenCode,
                   String canteenName,
                   int trustScore) {
        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    public Canteen(String canteenCode, String canteenName) {
        this(canteenCode, canteenName, 3);
    }

    public int compareTo(Canteen other) {
        int result = Integer.compare(
                other.trustScore, this.trustScore);

        if (result != 0)
            return result;

        result = this.canteenCode.compareToIgnoreCase(
                other.canteenCode);

        if (result != 0)
            return result;

        return Integer.compare(
                this.canteenName.length(),
                other.canteenName.length());
    }

    public static Canteen[] rankCanteens(Canteen[] canteens) {
        Canteen[] result = canteens.clone();

        for (int i = 1; i < result.length; i++) {
            Canteen current = result[i];
            int j = i - 1;

            while (j >= 0 &&
                   result[j].compareTo(current) > 0) {
                result[j + 1] = result[j];
                j--;
            }

            result[j + 1] = current;
        }

        return result;
    }

    public String getCanteenCode() {
        return canteenCode;
    }
}

public class Week_4_CanteenTrustScoreRankingEngine {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of canteens:");
        int n = sc.nextInt();
        sc.nextLine();

        Canteen[] canteens = new Canteen[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter canteen code:");
            String code = sc.nextLine();

            System.out.println("Enter canteen name:");
            String name = sc.nextLine();

            System.out.println(
                    "Enter trust score, or -1 for default:");
            int score = sc.nextInt();
            sc.nextLine();

            if (score == -1)
                canteens[i] = new Canteen(code, name);
            else
                canteens[i] =
                    new Canteen(code, name, score);
        }

        Canteen[] ranked =
                Canteen.rankCanteens(canteens);

        System.out.println("Ranked canteens:");
        for (Canteen canteen : ranked)
            System.out.println(canteen.getCanteenCode());

        sc.close();
    }
}
