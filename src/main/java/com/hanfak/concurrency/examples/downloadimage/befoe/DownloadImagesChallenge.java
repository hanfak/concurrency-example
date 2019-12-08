package com.hanfak.concurrency.examples.downloadimage.befoe; /**
 * Challenge: Download a collection of images
 */

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.stream.IntStream;

/* sequential implementation of multiple image downloader */
class SequentialImageDownloader {

    private int[] imageNumbers;

    public SequentialImageDownloader(int[] imageNumbers) {
        this.imageNumbers = imageNumbers;
    }

    /* returns total bytes from downloading all images in imageNumbers array */
    public int downloadAll() {
        int totalBytes = 0;
        for (int num : imageNumbers)
            totalBytes += downloadImage(num);
        return totalBytes;
    }

    /* returns number of bytes from downloading image */
    private int downloadImage(int imageNumber) {
        try {
            imageNumber = (Math.abs(imageNumber) % 50) + 1; // force number between 1 and 50
            URL photoURL = new URL(String.format("http://699340.youcanlearnit.net/image%03d.jpg", imageNumber));
            BufferedInputStream in = new BufferedInputStream(photoURL.openStream());
            int bytesRead, totalBytes = 0;
            byte buffer[] = new byte[1024];
            while((bytesRead = in.read(buffer, 0, 1024)) != -1)
                totalBytes += bytesRead;
            return totalBytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

/* parallel implementation of multiple image downloader */
class ParallelImageDownloader {

    private int[] imageNumbers;

    public ParallelImageDownloader(int[] imageNumbers) {
        this.imageNumbers = imageNumbers;
    }

    /* returns total bytes from downloading all images in imageNumbers array */
    public int downloadAll() {
        // YOUR CODE GOES HERE //
        return 0;
    }
}

public class DownloadImagesChallenge {

    /* evaluate performance of sequential and parallel implementations */
    public static void main(String[] args) {
        final int NUM_EVAL_RUNS = 3;
        final int[] IMAGE_NUMS = IntStream.rangeClosed(1,200).toArray(); // images to download

        System.out.println("Evaluating Sequential Implementation...");
        SequentialImageDownloader sid = new SequentialImageDownloader(IMAGE_NUMS);
        int sequentialResult = sid.downloadAll();
        double sequentialTime = 0;
        for(int i=0; i<NUM_EVAL_RUNS; i++) {
            long start = System.currentTimeMillis();
            sid.downloadAll();
            sequentialTime += System.currentTimeMillis() - start;
        }
        sequentialTime /= NUM_EVAL_RUNS;

        System.out.println("Evaluating Parallel Implementation...");
        ParallelImageDownloader pid = new ParallelImageDownloader(IMAGE_NUMS);
        int parallelResult = pid.downloadAll();
        double parallelTime = 0;
        for(int i=0; i<NUM_EVAL_RUNS; i++) {
            long start = System.currentTimeMillis();
            pid.downloadAll();
            parallelTime += System.currentTimeMillis() - start;
        }
        parallelTime /= NUM_EVAL_RUNS;

        // display sequential and parallel results for comparison
        if (sequentialResult != parallelResult)
            throw new Error("ERROR: sequentialResult and parallelResult do not match!");
        System.out.format("Downloaded %d images totaling %.1f MB\n", IMAGE_NUMS.length, sequentialResult/1e6);
        System.out.format("Average Sequential Time: %.1f ms\n", sequentialTime);
        System.out.format("Average Parallel Time: %.1f ms\n", parallelTime);
        System.out.format("Speedup: %.2f \n", sequentialTime/parallelTime);
        System.out.format("Efficiency: %.2f%%\n", 100*(sequentialTime/parallelTime)/Runtime.getRuntime().availableProcessors());
    }
}