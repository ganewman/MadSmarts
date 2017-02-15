/** MergeSortTester.
 *
 * ALGORITHM:
 *
 *
 * BIG-OH CLASSIFICATION OF ALGORITHM:
 *
 *
 * Mean execution times for dataset of size n:
 *  Batch size:
 *  n=1         time:
 *  n=10        time:
 *  n=100       time:
 *  n=1000000   time:
 *
 *  ANALYSIS:
 *
 */

public class MergeSortTester {
    private static final long ROUNDS = 10000;
    private static final int  SIZE   = 1000 + (int)( Math.random() * 1000 );

    /** Execution time analysis.
     *
     */
    public static void main( String[] args ) {
        System.out.println("Running " + ROUNDS + " rounds of testing.\n");
        System.out.format("%s%30s%n", "Length", "Time (nanoseconds)");

        long time = 0;
        long s = 0;
        long e = 0;
        int[] data = new int[SIZE];

        for ( long rounds = ROUNDS; rounds > 0; rounds-- ) {
            for ( int i = 0; i < data.length; i++ ) {
                data[i] = (int) ( 1000 * Math.random() );
            }

            s = System.nanoTime();
            MergeSort.sort(data);
            e = System.nanoTime();

            time += (e - s);
        }

        System.out.format("%s%20s%n", data.length, time / ROUNDS);
    }
}

