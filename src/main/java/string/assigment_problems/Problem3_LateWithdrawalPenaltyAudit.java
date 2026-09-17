import java.util.Arrays;

public class Problem3_LateWithdrawalPenaltyAudit {

    static class RaceEntry {
        protected final String bibNumber;
        protected final double entryFee;
        protected double amountPaid;

        private final double[] lateFeeHistory = new double[10];
        private int lateFeeCount = 0;

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

        protected void applyLateFee(double amount) {
            if (lateFeeCount >= lateFeeHistory.length) {
                throw new IllegalStateException("Late fee history is full");
            }

            amountPaid -= amount;
            lateFeeHistory[lateFeeCount++] = amount;
        }

        public double[] getLateFeeHistory() {
            return Arrays.copyOf(lateFeeHistory, lateFeeCount);
        }
    }

    static class RunnerEntry extends RaceEntry {
        private final String category;

        public RunnerEntry(String bibNumber, double entryFee, String category) {
            super(bibNumber, entryFee);
            this.category = category;
        }

        @Override
        protected void applyLateFee(double amount) {
            super.applyLateFee(amount * 2);
        }
    }

    public static void main(String[] args) {
        RunnerEntry r = new RunnerEntry("BIB2001", 80, "Open 10K");
        r.pay(30);

        r.applyLateFee(20);

        System.out.println(r.getBalanceDue());

        double[] history = r.getLateFeeHistory();
        System.out.println(Arrays.toString(history));

        history[0] = 999;
        System.out.println(Arrays.toString(r.getLateFeeHistory()));
    }
}
