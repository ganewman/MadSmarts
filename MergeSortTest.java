/* LAB00 -- What Does the Data Say?
*/

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MergeSortTest implements Runnable {
  private static          int             size;
  private static          int             tests;
  private static          int             rounds;


  private static volatile double          time;
  private static volatile long            _rounds;
  private                 long            start;

  private static          ExecutorService service;
  private static          CountDownLatch  latch;


  @Override
  public void run() {
    int[] data = new int[size];

    for ( int i = 0; i < data.length; i++ ) {
      data[i] = (int) ( 1000 * Math.random() );
    }

    start = System.nanoTime();
    MergeSort.sort(data);
    time += (System.nanoTime() - start);
    latch.countDown();
  }


  /** Run a test. Takes the average time in rounds _rounds of testing with
   * a array of _size size. Prints results in csv format.
   */
  public static void test() throws InterruptedException {
    for ( int i = 0; i < rounds; i++ ) {
      service.execute(new MergeSortTest());
    }

    latch.await();

    double avg = time / rounds;
    System.out.format("%s,%s,%sns,%sns\n", rounds, size, time, avg);
    time = 0;
  }


  /** main()
   * @param args  Optional. Indexes:
   *              0- Initial size.
   *              1- tests per size.
   *              2- rounds per test.
   */
  public static void main( String[] args ) throws InterruptedException {
    try {
      size = Integer.parseInt(args[0]);
    } catch ( NumberFormatException | ArrayIndexOutOfBoundsException e ) {
      size = 100;
    }

    try {
      tests = Integer.parseInt(args[1]);
    } catch ( NumberFormatException | ArrayIndexOutOfBoundsException e ) {
      tests = 3;
    }

    try {
      rounds = Integer.parseInt(args[2]);
    } catch ( NumberFormatException | ArrayIndexOutOfBoundsException e ) {
      rounds = 100000;
    }

    int _threads = Runtime.getRuntime().availableProcessors() - 1;
    service = Executors.newFixedThreadPool(_threads);
    System.out.format("Running with %s parallel threads.\n", _threads);
    System.out.println("rounds,size,time,average");

    while ( true ) {
      for ( int i = 0; i < tests; i++ ) {
        latch = new CountDownLatch(rounds);
        test();
      }

      size *= 10;
    }
  }
}

