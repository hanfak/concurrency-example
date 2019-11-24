package concurrency.readwritelock.after; /**
 * Several users reading a calendar, but only a few users updating it
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class CalendarUser1 extends Thread {

    private static final String[] WEEKDAYS = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    private static int today = 0;
    private static ReentrantReadWriteLock marker = new ReentrantReadWriteLock();
    private static Lock readMarker = marker.readLock();
    private static Lock writeMarker = marker.writeLock();

    public CalendarUser1(String name) {
        this.setName(name);
    }

    public void run() {
        while (today < WEEKDAYS.length-1){
            if (this.getName().contains("Writer")) { // update the shared calendar
                writeMarker.lock();
                try {
                    today = (today+1) % 7;
                    System.out.println(this.getName() + " updated date to " + WEEKDAYS[today]);
                } catch (Exception e)
                    {e.printStackTrace(); }
                {
                    writeMarker.unlock();
                }
            } else { // Reader - check to see what today is
                readMarker.lock();
                try {
                    System.out.println(this.getName() + " sees that today is " + WEEKDAYS[today] + "; total readers: " + marker.getReadLockCount());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    readMarker.unlock();
                }
            }
        }
    }
}

public class ReadWriteLockDemo1 {
    public static void main(String[] args) {
        // create ten reader threads
        for (int i=0; i<10; i++)
            new CalendarUser1("Reader-"+i).start();

        // ...but only two writer threads
        for (int i=0; i<2; i++)
            new CalendarUser1("Writer-"+i).start();
    }
}