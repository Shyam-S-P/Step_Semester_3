public class Problem5_RaceNightSettlementEngine {

    static class RaceEntry {
        private static int bibCounter = 0;

        private final String entryCode;
        protected final String bibNumber;
        protected final double entryFee;
        protected double amountPaid;
        protected String paymentMode = "Not specified";

        public RaceEntry(String bibNumber, double entryFee) {
            if (bibNumber == null || bibNumber.trim().isEmpty()
                    || bibNumber.length() < 4) {
                throw new IllegalArgumentException("Invalid bib number");
            }
            if (entryFee <= 0) {
                throw new IllegalArgumentException("Entry fee must be positive");
            }

            bibCounter++;
            entryCode = "ENTRY" + bibCounter;
            this.bibNumber = bibNumber;
            this.entryFee = entryFee;
        }

        public void pay(double amount) {
            if (amount < 0) {
                throw new IllegalArgumentException("Payment cannot be negative");
            }
            amountPaid += amount;
        }

        public void pay(double amount, String mode) {
            pay(amount);
            paymentMode = mode;
            System.out.println("Paying via " + mode);
        }

        public double getBalanceDue() {
            return entryFee - amountPaid;
        }

        public String getEntryCode() {
            return entryCode;
        }

        public static boolean isValidDiscountCode(String code) {
            if (code == null || code.length() != 5) {
                return false;
            }

            return code.charAt(0) == 'M'
                    && Character.isDigit(code.charAt(1))
                    && Character.isDigit(code.charAt(2))
                    && Character.isDigit(code.charAt(3))
                    && Character.isUpperCase(code.charAt(4));
        }

        public static int getBibCounter() {
            return bibCounter;
        }
    }

    static class RunnerEntry extends RaceEntry {
        private final String category;

        public RunnerEntry(String bibNumber, double entryFee, String category) {
            super(bibNumber, entryFee);
            this.category = category;
        }
    }

    static class EliteRunnerEntry extends RunnerEntry {
        private final double sponsorBonus;

        public EliteRunnerEntry(String bibNumber, double entryFee,
                                String category, double sponsorBonus) {
            super(bibNumber, entryFee, category);
            this.sponsorBonus = sponsorBonus;
        }
    }

    static class RelayTeamEntry extends RaceEntry {
        private final int teamSize;

        public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
            super(bibNumber, entryFee);
            if (teamSize <= 0) {
                throw new IllegalArgumentException("Team size must be positive");
            }
            this.teamSize = teamSize;
        }

        public int getTeamSize() {
            return teamSize;
        }
    }

    static String settleNight(RaceEntry[] entries) {
        int processed = 0;
        int nullSkipped = 0;
        int relayCount = 0;
        int individualCount = 0;

        for (RaceEntry entry : entries) {
            if (entry == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (entry instanceof RelayTeamEntry) {
                relayCount++;
            } else {
                individualCount++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + relayCount + " relay | "
                + individualCount + " individual";
    }

    public static void main(String[] args) {
        System.out.println(RaceEntry.isValidDiscountCode("M123A"));
        System.out.println(RaceEntry.isValidDiscountCode("M12A"));
        System.out.println(RaceEntry.isValidDiscountCode("X123A"));

        RaceEntry r = new RaceEntry("BIB1001", 80);
        r.pay(10, "UPI");

        EliteRunnerEntry elite =
                new EliteRunnerEntry("BIB2001", 150, "Elite Full Marathon", 500);
        RelayTeamEntry relay =
                new RelayTeamEntry("BIB3001", 300, 4);

        System.out.println(settleNight(
                new RaceEntry[]{elite, null, relay}
        ));

        System.out.println("Counter: " + RaceEntry.getBibCounter());
    }
}
