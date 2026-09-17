public class Problem4_RaceDayAnnouncer {

    static class RaceEntry {
        protected final String bibNumber;
        protected final double entryFee;
        protected double amountPaid;

        public RaceEntry(String bibNumber, double entryFee) {
            if (bibNumber == null || bibNumber.trim().isEmpty()
                    || bibNumber.length() < 4) {
                throw new IllegalArgumentException("Invalid bib number");
            }
            if (entryFee <= 0) {
                throw new IllegalArgumentException("Entry fee must be positive");
            }
            this.bibNumber = bibNumber;
            this.entryFee = entryFee;
        }

        public void pay(double amount) {
            amountPaid += amount;
        }

        public double getBalanceDue() {
            return entryFee - amountPaid;
        }

        public String announce() {
            return "Race Entry | Bib: " + bibNumber
                    + " | Balance: " + getBalanceDue();
        }
    }

    static class RunnerEntry extends RaceEntry {
        private final String category;

        public RunnerEntry(String bibNumber, double entryFee, String category) {
            super(bibNumber, entryFee);
            this.category = category;
        }

        @Override
        public String announce() {
            return "Runner Entry | Bib: " + bibNumber
                    + " | Category: " + category
                    + " | Balance: " + getBalanceDue();
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

        @Override
        public String announce() {
            return "Relay Team | Bib: " + bibNumber
                    + " | Team Size: " + teamSize
                    + " | Balance: " + getBalanceDue();
        }

        public int getTeamSize() {
            return teamSize;
        }
    }

    static String announceAll(RaceEntry[] entries) {
        StringBuilder report = new StringBuilder();

        for (RaceEntry entry : entries) {
            report.append(entry.announce()).append(" ");

            if (entry instanceof RelayTeamEntry) {
                RelayTeamEntry relay = (RelayTeamEntry) entry;
                report.append("[Team size via downcast: ")
                      .append(relay.getTeamSize())
                      .append("] ");
            }
        }

        return report.toString();
    }

    public static void main(String[] args) {
        RunnerEntry runner = new RunnerEntry("BIB2001", 80, "Open 10K");
        RelayTeamEntry relay = new RelayTeamEntry("BIB4001", 300, 4);
        runner.pay(30);

        RaceEntry[] fleet = {runner, relay};
        System.out.println(announceAll(fleet));
    }
}
