import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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

    //Generate a random integer from the range [start,end]
    private static int randint(int start, int end) {
        int range = start - end;

        if (range == 0)
            return start;
        else
            return start + (int) (Math.random() * range);
    }

    //generate a random array of length n
    private static int[] newArray(int n) {
        int[] X = new int[n];

        int countPositive = 0;
        int countNegative = 0;

        for (int i = 0; i < n; i++) {
            int x = randint(1, n) * (-1) ^ randint(2, 4);

            if (x < 0)
                countNegative += 1;
            else
                countPositive += 1;

            X[i] = x;
        }

        //System.out.println("Number of negative numbers in X: " + countNegative);
        //System.out.println("Number of positive numbers in X: " + countPositive + "\n");
        return X;
    }

    public static void main(String[] args) {
        //The application NEVER finishes termination at base = 10 because the datasets become so huge that we'd need to wait till the end of the universe to
        int base = 2;

        String[][] cellsData = {
                {base + "^2", "...", "...", "...", "..."},
                {base + "^3", "...", "...", "...", "..."},
                {base + "^4", "...", "...", "...", "..."},
                {base + "^5", "...", "...", "...", "..."},
                {base + "^6", "...", "...", "...", "..."}
        };


        for (int pow = 2; pow <= 6; pow++) {
            int N = (int) Math.pow(base, pow);
            int[] X = newArray(N);

            int i = pow - 2;
            cellsData[i][1] = Integer.toString(mcs_ON3(X));
            cellsData[i][2] = Integer.toString(mcs_ON2A(X));
            cellsData[i][3] = Integer.toString(mcs_ON2B(X));
            cellsData[i][4] = Integer.toString(mcs_ON(X));
        }

        String[] columnNames = {"N", "O(n^3)", "O(n^2) A", "O(n^2) B", "O(n)"};

        DefaultTableModel model = new DefaultTableModel(cellsData, columnNames);
        JTable table = new JTable(model);

        JFrame frame = new JFrame("");
        frame.add(new JScrollPane(table));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
