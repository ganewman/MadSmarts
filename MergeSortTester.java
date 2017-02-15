/* Team MadSmarts -- Jeffrey Lin, Leo Liu, Gabi Newman, Allard Peng
 * APCS2 period 03
 * LAB00 -- What Does the Data Say?
 * 2017-02-14
 */

/** MergeSortTester.
 *
 * ALGORITHM:
 * Take the array and split it into two arrays that are equivalent in size.
 * Continue splitting each array until they have two items each, keeping track
 * of the parent array.  Sort each individual two-item array.  Merge two decks
 * together to form the parent pile.  Continue to merge until there is one
 * sorted deck.
 *
 * BIG-OH CLASSIFICATION OF ALGORITHM:
 * O(n)
 *
 * Mean execution times for dataset of size n:
 *  Batch size:
 *  n=    1     time:     361 nanoseconds
 *  n=   10     time:    3253 nanoseconds
 *  n=  100     time:   32765 nanoseconds
 *  n=10000     time: 4002642 nanoseconds
 *
 * ANALYSIS:
 * We can run various regressions to see which line of best fit is most
 * applicable to the data. If we run a Linear Regression (ax+b) on the data
 * above, we get the line $y = 400.5276654x - 2678.556315$ with an r value of
 * 0.9999973273. This suggests that the data creates a linear trend.
 */

public class MergeSortTester {
    private static final long ROUNDS = 10000;
    private static final int    SIZE = 1000 + (int)( Math.random() * 1000 );

    /** Execution time analysis.
     * Create an array of size SIZE and populate it with arbitary values and
     * run merge sort on the array. Repeat ROUNDS times, then divide the total
     * amount of time spent by ROUNDS to obtain the average.
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

