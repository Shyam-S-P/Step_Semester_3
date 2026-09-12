import java.util.Arrays;
import java.util.Scanner;

class DischargeSummary {
    private final String patientId;
    private final String[] medicationCodes;

    static {
        System.setProperty("meditrack.ledger.ready", "true");
    }

    public DischargeSummary(String patientId, String[] medicationCodes) {
        if (medicationCodes == null)
            throw new IllegalArgumentException("Invalid medication codes");

        for (String code : medicationCodes) {
            if (code == null || !code.matches("MED-[A-Z]"))
                throw new IllegalArgumentException("Invalid medication code");
        }

        this.patientId = patientId;
        this.medicationCodes = medicationCodes.clone();
    }

    public String getPatientId() {
        return patientId;
    }

    public String[] getMedicationCodes() {
        return medicationCodes.clone();
    }

    public DischargeSummary withCorrectedMedication(
            int index, String newCode) {

        if (index < 0 || index >= medicationCodes.length ||
            newCode == null || !newCode.matches("MED-[A-Z]"))
            throw new IllegalArgumentException("Invalid correction");

        String[] corrected = medicationCodes.clone();
        corrected[index] = newCode;

        return new DischargeSummary(patientId, corrected);
    }

    static String processNightlyBatch(DischargeSummary[] summaries) {
        int processed = 0, nullSkipped = 0;
        int critical = 0, routine = 0;

        if (summaries == null)
            return "0 processed | 0 null skipped | 0 critical-care | 0 routine";

        for (DischargeSummary summary : summaries) {
            if (summary == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (summary instanceof CriticalCareDischargeSummary)
                critical++;
            else
                routine++;
        }

        return processed + " processed | " + nullSkipped +
               " null skipped | " + critical +
               " critical-care | " + routine + " routine";
    }
}

class CriticalCareDischargeSummary extends DischargeSummary {
    private final int icuDays;

    public CriticalCareDischargeSummary(String patientId,
                                        String[] medicationCodes,
                                        int icuDays) {
        super(patientId, medicationCodes);

        if (icuDays < 0)
            throw new IllegalArgumentException("Invalid ICU days");

        this.icuDays = icuDays;
    }
}

public class Week_5_ImmutableDischargeSummaryNightlyLedger {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter patient ID:");
        String patientId = sc.nextLine();

        System.out.println("Enter number of medication codes:");
        int n = Integer.parseInt(sc.nextLine());

        String[] codes = new String[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter medication code " + (i + 1) +
                    " (format MED-X):");
            codes[i] = sc.nextLine();
        }

        try {
            DischargeSummary summary =
                    new DischargeSummary(patientId, codes);

            System.out.println("Medication codes: " +
                    Arrays.toString(summary.getMedicationCodes()));

            System.out.println("Enter index to correct:");
            int index = Integer.parseInt(sc.nextLine());

            System.out.println("Enter new medication code:");
            String newCode = sc.nextLine();

            DischargeSummary corrected =
                    summary.withCorrectedMedication(index, newCode);

            System.out.println("Corrected codes: " +
                    Arrays.toString(corrected.getMedicationCodes()));

            System.out.println("Enter ICU days for critical-care record:");
            int icuDays = Integer.parseInt(sc.nextLine());

            DischargeSummary[] batch = {
                new CriticalCareDischargeSummary(
                        patientId, codes, icuDays),
                null,
                summary
            };

            System.out.println(
                    DischargeSummary.processNightlyBatch(batch));

        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        sc.close();
    }
}
