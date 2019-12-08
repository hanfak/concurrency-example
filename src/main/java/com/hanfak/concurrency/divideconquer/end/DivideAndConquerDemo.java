package com.hanfak.concurrency.divideconquer.end; /**
 * Recursively sum a range of numbers
 */

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class RecursiveSum extends RecursiveTask<Long> {

    private long lo, hi;

    public RecursiveSum(long lo, long hi) {
        this.lo = lo;
        this.hi = hi;
    }

    protected Long compute() {
        if (hi-lo <= 100_000) { // base case threshold
            long total = 0;
            for (long i = lo; i <= hi; i++)
                total += i;
            return total;
        } else {
            long mid = (hi+lo)/2; // middle index for split
            RecursiveSum left = new RecursiveSum(lo, mid);
            RecursiveSum right = new RecursiveSum(mid+1, hi);
            left.fork(); // forked thread computes left half
            return right.compute() + left.join(); // current thread computes right half
        }
    }
}

public class DivideAndConquerDemo {
    public static void main(String args[]) {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        Long total = pool.invoke(new RecursiveSum(0, 1_000_000_000));
        pool.shutdown();
        System.out.println("Total sum is " + total);
    }
}