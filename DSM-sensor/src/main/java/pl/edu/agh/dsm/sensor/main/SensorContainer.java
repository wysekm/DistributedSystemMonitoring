package pl.edu.agh.dsm.sensor.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SensorContainer {

    private static final Logger log = LoggerFactory.getLogger(SensorContainer.class);

    private static final String CONFIGURATION_FILE = "DSM-sensor/src/main/config/monitored_objects.xml"; // TODO
    private List<ScheduledFuture<?>> futures;

    public static void main(String[] args) {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        SensorContainer sensorContainer = new SensorContainer();
        sensorContainer.run();
    }

    private void run() {
        SensorConfiguration.getInstance().readConfigurationFile(CONFIGURATION_FILE);

        final ScheduledExecutorService resourceMonitorExecutorService = Executors.newScheduledThreadPool(
                SensorConfiguration.getInstance().getThreadCount());

        futures = new ArrayList<>();
        for (SensorThread m : SensorConfiguration.getInstance().getMonitoredObjects()) {
            futures.add(resourceMonitorExecutorService.scheduleAtFixedRate(m, 0L, m.getInterval(), TimeUnit.SECONDS));
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                log.info("SensorContainer shutting down.");
                try {
                    for (ScheduledFuture<?> f : futures) {
                        f.cancel(true);
                    }
                    resourceMonitorExecutorService.shutdownNow();
                    resourceMonitorExecutorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
                } catch (InterruptedException ex) {
                    log.error("Clean shutdown interrupted, killing process now:", ex);
                    System.exit(1);
                }
                log.info("Shutdown finished successfully.");
            }
        });
    }
}
