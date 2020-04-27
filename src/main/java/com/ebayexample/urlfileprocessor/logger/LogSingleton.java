package com.ebayexample.urlfileprocessor.logger;

public final class LogSingleton {

    // May need to be a BigInt at some point
    private static long successCounter = 0L;
    private static long failureCounter = 0L;

    private LogSingleton() {
    }

    synchronized public static void addToSuccessCounter() {
        successCounter++;
    }

    synchronized public static void addToFailureCounter() {
        failureCounter++;
    }

    synchronized public static long getSuccessCounter() {
        return successCounter;
    }

    synchronized public static long getFailureCounter() {
        return failureCounter;
    }
}
