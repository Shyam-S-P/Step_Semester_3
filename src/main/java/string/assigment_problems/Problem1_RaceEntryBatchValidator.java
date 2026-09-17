public class Problem1_RaceEntryBatchValidator {

    static class RaceEntry {
        protected final String bibNumber;
        private final double entryFee;
        private double amountPaid;

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
            this.amountPaid = 0.0;
        }

        public void pay(double amount) {
            if (amount < 0) {
                throw new IllegalArgumentException("Payment cannot be negative");
            }
            amountPaid += amount;
        }

        public double getBalanceDue() {
            return entryFee - amountPaid;
        }

        public void announce() {
            System.out.println("Race Entry | Bib: " + bibNumber
                    + " | Balance: " + getBalanceDue());
        }

        public static String registerBatch(String[] bibNumbers, double entryFee) {
            int registered = 0;
            int rejected = 0;

            for (String bibNumber : bibNumbers) {
                try {
                    new RaceEntry(bibNumber, entryFee);
                    registered++;
                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }

            return "Registered: " + registered + " | Rejected: " + rejected;
        }
    }

    static class RunnerEntry extends RaceEntry {
        private final String category;

        public RunnerEntry(String bibNumber, double entryFee, String category) {
            super(bibNumber, entryFee);
            this.category = category;
        }

        @Override
        public void announce() {
            System.out.println("Runner Entry | Bib: " + bibNumber
                    + " | Category: " + category
                    + " | Balance: " + getBalanceDue());
        }

    }

    public static void main(String[] args) {
        RunnerEntry r = new RunnerEntry("BIB2001", 80, "Open 10K");
        r.pay(30);
        System.out.println(r.getBalanceDue());

        String[] bibs = {"BIB1", "B1", "BIB2"};
        System.out.println(RaceEntry.registerBatch(bibs, 80));
    }
}
