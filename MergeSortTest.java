/* LAB00 -- What Does the Data Say?
*/

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

public class MergeSortTest implements Runnable {
  private static          int        runs;
  private static          int        tests;

  private static volatile int        size;
  private static volatile int        _runs;

  private                 int        _size;
  private                 long       start;
  private                 BigInteger time;


  @Override
  public void run() {
    while ( size >= 0 ) {
      if ( _runs-- <= 1 ) {
        size *= 10;
        _runs = runs;
      }

      time = BigInteger.ZERO;
      _size = size;

      for ( int i = 0; i < tests; i++ ) {
        test();
      }

      System.out.format("%s,%s\n", _size, time.divide(
            BigInteger.valueOf(tests)));
    }
  }

  private void test() {
    int[] data = new int[_size];

    for ( int i = 0; i < _size; i++ ) {
      data[i] = ThreadLocalRandom.current().nextInt(0, 1000);
    }

    start = System.nanoTime();
    MergeSort.sort(data);
    time = time.add(BigInteger.valueOf(System.nanoTime() - start));
  }


  /** main()
   * @param args  Optional. Indexes:
   *              0- Initial size.
   *              1- runs per size.
   *              2- tests per run.
   */
  public static void main( String[] args ) {
    // {{{ Set variables
    try {
      size = Integer.parseInt(args[0]);
    } catch ( NumberFormatException | ArrayIndexOutOfBoundsException e ) {
      size = 100;
    }

    try {
      runs = Integer.parseInt(args[1]);
    } catch ( NumberFormatException | ArrayIndexOutOfBoundsException e ) {
      runs = 3;
    }

    try {
      tests = Integer.parseInt(args[2]);
    } catch ( NumberFormatException | ArrayIndexOutOfBoundsException e ) {
      tests = 100000;
    }
    // }}}

    int _threads = Runtime.getRuntime().availableProcessors() - 1;

    System.out.format("Running %s runs with %s tests per run using %s parallel threads.\n",
        runs, tests, _threads);
    System.out.println("size,average (ns)");

    _runs = runs + 1;
    for ( int i = 0; i < _threads; i++ ) {
      new Thread(new MergeSortTest()).start();
    }
  }
}

// vim: foldmethod=marker foldlevel=0
