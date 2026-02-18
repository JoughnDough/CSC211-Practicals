import java.util.Arrays;

public class Main {

    // O(n^3):
    private static int mcs_ON3(int[] X) {
        int n = X.length;
        int maxsofar = Integer.MIN_VALUE;
        int count = 0;

        for (int low = 0; low < n; low++) {
            for (int high = low; high < n; high++) {
                int sum = 0;
                //sum of subjector X[low..high]
                for (int i = low; i <= high; i++) {
                    sum += X[i];
                    count++;
                    if (sum > maxsofar)
                        maxsofar = sum;
                }
            }
        }

        return count;
    }


    // O(n^2) A
    private static int mcs_ON2A(int[] X) {
        int n = X.length;
        int maxsofar = Integer.MIN_VALUE;
        int count = 0;

        for (int low = 0; low < n; low++) {
            int sum = 0;

            for (int r = low; r < n; r++) {
                // the sum of subvector X[low...r]
                sum += X[r];
                count++;

                if (sum > maxsofar)
                    maxsofar = sum;
            }
        }

        return count;
    }

    // O(n^2) B
    private static int mcs_ON2B(int[] X) {
        int n = X.length;
        int count = 0;
        int[] sumTo = new int[n + 1];

        for (int i = 0; i < n; i++) {
            if (i == 0)
                sumTo[i] = X[i];
            else
                sumTo[i] = sumTo[i - 1] + X[i];
            count++;
        }

        int maxsofar = Integer.MIN_VALUE;

        for (int low = 0; low < n; low++) {
            for (int high = low; high < n; high++) {
                int sum;

                if (low == 0)
                    sum = sumTo[high];
                else
                    //sum of subjector sumTo[high..low]
                    sum = sumTo[high] - sumTo[low - 1];

                if (sum > maxsofar)
                    maxsofar = sum;
                count++;
            }
        }


        return count;
    }

    //O(n)
    private static int mcs_ON(int[] X) {
        int N = X.length;
        int maxSoFar = Integer.MIN_VALUE;
        int maxToHere = 0;
        int count = 0;

        for (int i = 0; i < N; i++) {
            maxToHere = Math.max(maxToHere + X[i], X[i]);
            maxSoFar = Math.max(maxSoFar, maxToHere);
            count++;
        }

        return count;
    }
}
