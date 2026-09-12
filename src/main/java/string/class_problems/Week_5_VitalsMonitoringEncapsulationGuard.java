import java.util.Arrays;
import java.util.Scanner;

class PatientVitals {
    private double[] readings;
    private int count;

    PatientVitals(double[] initialReadings) {
        readings = new double[500];
        count = 0;

        if (initialReadings != null) {
            for (double reading : initialReadings)
                recordReading(reading);
        }
    }

    void recordReading(double reading) {
        if (reading <= 0 || reading > 45 || count == readings.length)
            return;

        readings[count++] = reading;
    }

    double getAverage() {
        if (count == 0) return 0.0;

        double total = 0;
        for (int i = 0; i < count; i++)
            total += readings[i];

        return total / count;
    }

    double[] getAllReadings() {
        return Arrays.copyOf(readings, count);
    }
}

public class Week_5_VitalsMonitoringEncapsulationGuard {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of initial readings:");
        int n = sc.nextInt();

        double[] initialReadings = new double[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter reading " + (i + 1) + ":");
            initialReadings[i] = sc.nextDouble();
        }

        PatientVitals vitals = new PatientVitals(initialReadings);

        System.out.println("Valid readings: " +
                Arrays.toString(vitals.getAllReadings()));

        System.out.println("Average: " + vitals.getAverage());

        System.out.println("Enter a new reading:");
        double newReading = sc.nextDouble();

        vitals.recordReading(newReading);

        System.out.println("Updated readings: " +
                Arrays.toString(vitals.getAllReadings()));

        sc.close();
    }
}
