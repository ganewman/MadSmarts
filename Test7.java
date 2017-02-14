public class Test7 {
    private static final long ROUNDS = 10000000;
    private static final int  TESTS  =        6;


    public static long[][] test() {
        long[][] ret = new long[TESTS][2];

        // declare variables
        long start = System.nanoTime();
        long end   = System.nanoTime();
        for ( long rounds = ROUNDS; rounds > 0; rounds-- ) {
            int tmp = 1000 + (int)( Math.random() * 1000 );
            int[] data = new int[tmp];
            int[] data2 = new int[tmp];
            int[] data3 = new int[tmp];

            for ( int i = 0; i < data.length; i++ ) {
                tmp = (int) ( 1000 * Math.random() );
                data[i] = tmp;
                data2[i] = tmp;
                data3[i] = tmp;
            }

            start = System.nanoTime();
            InsertionSort.sort(data);
            end = System.nanoTime();
            ret[0][0] += end - start;

            // harder multiplication
            start = System.nanoTime();
            SelectionSort.sort(data2);
            end = System.nanoTime();
            ret[1][0] += end - start;

            // generate new array of booleans
            start = System.nanoTime();
            MergeSort.sort(data3);
            end = System.nanoTime();
            ret[2][0] += end - start;

            // populate the array
            start = System.nanoTime();
            end = System.nanoTime();
            ret[3][0] += end - start;

            // get a value
            start = System.nanoTime();
            end = System.nanoTime();
            ret[4][0] += end - start;

            // compare two values
            start = System.nanoTime();
            end = System.nanoTime();
            ret[5][0] += end - start;
        }

        return ret;
    }


    public static void main(String[] args) {
        System.out.println("Running " + ROUNDS + " rounds of testing.\n");
        long[][] c = test();

        // average and total
        System.out.format("%21s%20s%n", "Total", "Average");
        long t = 0;

        for ( int i = 0; i < c.length; i++ ) {
            c[i][1] = c[i][0] / ROUNDS;
            t += c[i][0];

            System.out.print(String.format("%16s%-18s%s", " ",
                        c[i][0], c[i][1]));

            System.out.println();
        }

        System.out.println("\n" + String.format("%s%14s%11s",
                    "Grand Total:", t, t / ROUNDS));
    }
}

