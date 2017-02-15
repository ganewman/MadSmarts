# MadSmarts

To find the execution time of the algorithm, we created a small program to run
the algorithm many times, and found the average by dividing the total time
spent by the number of times the algorithm was run. The table below contains
times returned from one run of the program for each dataset size.

| Dataset size | Time (nanoseconds) |
| -----------: | -----------------: |
|     1        |              361   |
|    10        |             3253   |
|   100        |            32765   |
| 10000        |          4002642   |

Running a linear regression on the data above gives us a linear equation with
an r value of almost 1 (0.9999973273), so it is safe to assume that the Big-Oh
classification of merge sort is **O(n)**.

