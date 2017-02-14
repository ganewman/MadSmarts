import java.time.Duration;
import java.time.Instant;

public class Test8 {
    private static final long ROUNDS = 10000000;

    public static Duration[][] test() {
        Duration[][] ret = new Duration[6][2];

        for ( int i = 0; i < ret.length; i++ ) {
            for ( int j = 0; j < 2; j++ ) {
                ret[i][j] = Duration.ZERO;
            }
        }

        // declare variables
        Instant start = Instant.now();
        Instant end = Instant.now();
        for ( long rounds = ROUNDS; rounds > 0; rounds-- ) {
            // simple multiplication
            start = Instant.now();
            int i = 3 * 2;
            end = Instant.now();
            ret[0][0] = ret[0][0].plus(Duration.between(start, end));

            // harder multiplication
            start = Instant.now();
            int j = 3628800 * 11;
            end = Instant.now();
            ret[1][0] = ret[1][0].plus(Duration.between(start, end));

            // generate new array of booleans
            start = Instant.now();
            boolean[] k = new boolean[1000];
            end = Instant.now();
            ret[2][0] = ret[2][0].plus(Duration.between(start, end));

            // populate the array
            start = Instant.now();
            for ( boolean z : k ) { z = true; }
            end = Instant.now();
            ret[3][0] = ret[3][0].plus(Duration.between(start, end));

            // get a value
            start = Instant.now();
            boolean l = k[567];
            end = Instant.now();
            ret[4][0] = ret[4][0].plus(Duration.between(start, end));

            // compare two values
            start = Instant.now();
            boolean n = (k[193] == k[592]);
            end = Instant.now();
            ret[5][0] = ret[5][0].plus(Duration.between(start, end));
        }

        return ret;
    }


    public static void main(String[] args) {
        System.out.println("Running " + ROUNDS + " rounds of testing.\n");
        Duration[][] c = test();

        // average and total
        System.out.format("%21s%20s%n", "Total", "Average");
        Duration t = Duration.ZERO;

        for ( int i = 0; i < c.length; i++ ) {
            c[i][1] = c[i][0].dividedBy(ROUNDS);
            t = t.plus(c[i][0]);

            System.out.print(String.format("%16s%-18s%s", " ",
                        c[i][0], c[i][1]));

            System.out.println();
        }

        System.out.println("\n" + String.format("%s%12s%24s",
                    "Grand Total:", t, t.dividedBy(ROUNDS)));
    }
}

