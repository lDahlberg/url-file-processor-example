package com.ebayexample.urlfileprocessor.logger;

import com.ebayexample.urlfileprocessor.services.CounterService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Logger {
    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

    public void setLogger() {
        final Runnable scheduledLogger =
            () -> System.out.println(
                String.format("Total processed: %d, Total successes: %d, Total failures: %d",
                        CounterService.getTotal(),
                        CounterService.getSuccessCounter(),
                        CounterService.getFailureCounter()));

        int interval = 15;
        final ScheduledFuture<?> scheduledFuture  =
                scheduler.scheduleAtFixedRate(scheduledLogger, interval, interval, SECONDS);
        scheduler.schedule(() -> { scheduledFuture.cancel(true); }, 60 * 60, SECONDS);
    }
}
