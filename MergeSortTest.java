/* LAB00 -- What Does the Data Say?
*/

import java.math.BigInteger;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Queue;

public class MergeSortTest implements Runnable {
  private static final    Queue<Integer> JOBS  = new ConcurrentLinkedQueue<>();

  private static          int            runs;
  private static          int            tests;
  private static          int            size;

  private                 int            _size;
  private                 long           _start;
  private                 BigInteger     _time;


  @Override
  public void run() {
    while ( ! JOBS.isEmpty() ) {
      _time = BigInteger.ZERO;
      _size = JOBS.poll().intValue();

      for ( int i = 0; i < tests; i++ ) {
        test();
      }

      System.out.format("%s,%s\n", _size, _time.divide(
            BigInteger.valueOf(tests)));
    }
  }

  private void test() {
    int[] data = new int[_size];

    for ( int i = 0; i < _size; i++ ) {
      data[i] = ThreadLocalRandom.current().nextInt(0, 1000);
    }

    _start = System.nanoTime();
    MergeSort.sort(data);
    _time = _time.add(BigInteger.valueOf(System.nanoTime() - _start));
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

    // {{{ Generate jobs
    while ( size >= 0 ) {
      for ( int i = 0; i < runs; i++ ) {
        JOBS.add(new Integer(size));
      }
      size *= 2;
    }
    // }}}

    int _threads = Runtime.getRuntime().availableProcessors() - 1;

    System.out.format("Running %s runs with %s tests per run using %s parallel threads.\n",
        runs, tests, _threads);
    System.out.println("size,average (ns)");

    for ( int i = 0; i < _threads; i++ ) {
      new Thread(new MergeSortTest()).start();
    }
  }
}

// vim: foldmethod=marker
