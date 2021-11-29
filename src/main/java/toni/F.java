package toni;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class F {
    static ThreadLocalRandom rnd = ThreadLocalRandom.current();

    public static void izmjesaj(int[] polje) {
        int index;
        for (int i = polje.length - 1; i > 0; i--) {
            index = rnd.nextInt(i + 1);
            if (index != i) {
                polje[index] ^= polje[i];
                polje[i] ^= polje[index];
                polje[index] ^= polje[i];
            }
        }
    }

    static boolean imaDuplih(final int[] arr)
    {
        final int MAXZIP = arr.length* arr.length;
        boolean[] bitmap = new boolean[MAXZIP+1];  // Java guarantees init to false
        for (int item : arr)
            if (!(bitmap[item] ^= true)) return true;
        return false;
    }

    static void makniDuple(int[] arr) {
        while (imaDuplih(arr)) {
            for (int i=0; i<arr.length; i++) {
                for (int j=i+1; j< arr.length; j++) {
                    if (arr[i]==arr[j]) {
                        arr[j] = rnd.nextInt(arr.length);
                    }
                }
            }
        }
    }

    static int izbrojiDuple(int[] arr) {
        if (imaDuplih(arr)) {
            int[] koliko=new int[arr.length];
            for (int i=0; i< arr.length; i++) {
                for (int j=0; j< arr.length; j++) {
                    if (arr[j]==i) koliko[i]++;
                }
            }
            int cnt=0;
            for (int i=0; i< arr.length; i++) {
                if (koliko[i]>1) cnt+=(koliko[i]-1);
            }
            return cnt;
        }
        else return 0;
    }

    static int intMin (int a, int b) {
        return a < b ? a : b;
    }

    static int intMax (int a, int b) {
        return a < b ? b : a;
    }

    static double stdev (ArrayList<Integer> brojevi) {
        double mean = brojevi.stream().mapToDouble(s -> s).average().getAsDouble();
        double temp = 0;

        for (int i = 0; i < brojevi.size(); i++)
        {
            int val = brojevi.get(i);

            // Step 2:
            double squrDiffToMean = Math.pow(val - mean, 2);

            // Step 3:
            temp += squrDiffToMean;
        }

        // Step 4:
        double meanOfDiffs = (double) temp / (double) (brojevi.size());

        // Step 5:
        return Math.sqrt(meanOfDiffs);
    }
}
