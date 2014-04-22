package pl.edu.agh.dsm.sensor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.dsm.sensor.monitored.MonitoredFactory;
import pl.edu.agh.dsm.sensor.monitored.MonitoringException;
import pl.edu.agh.dsm.sensor.udp.SensorSocket;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sensor {

    private static final Logger log = LoggerFactory.getLogger(Sensor.class);
    private static final ScheduledExecutorService es = Executors.newScheduledThreadPool(4); // TODO


    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: sensor <monitor address> <resource> <metric> [<metric> [ ... ]]");
            log.error("Not enough arguments supplied.");
            System.exit(1);
        }
        String ipPort = args[0];
        String resourceName = args[1];
        Pattern pattern = Pattern.compile("(\\d+\\.\\d+\\.\\d+\\.\\d+):(\\d+)");
        Matcher matcher = pattern.matcher(ipPort);
        if (!matcher.matches()) {
            System.out.println("Malformed monitor address.");
            log.error("Monitor address did not match basic ip:port regex.");
            System.exit(1);
        }
        String ip = matcher.group(1);
        String port = matcher.group(2);

        SensorConfiguration sensorConfiguration = new SensorConfiguration();
        sensorConfiguration.setResource(resourceName);

        try {
            startSensors(args, sensorConfiguration, new SensorSocket(ip, Integer.parseInt(port)));
        } catch (IOException ex) {
            log.error("IOException caught while creating SensorSocket: ", ex);

        }
        log.info("Startup completed.");
    }

    private static void startSensors(String[] args, SensorConfiguration sensorConfiguration, SensorSocket sensorSocket) {
        log.trace("Starting sensors.");
        MonitoredFactory factory = new MonitoredFactory();
        for (int i = 2; i < args.length; ++i) {
            log.info("Starting sensor for metric " + args[i]);
            try {
                SensorThread thread = new SensorThread(factory.createMonitoredResource(args[i]), sensorConfiguration, sensorSocket);
                es.scheduleAtFixedRate(thread, 0, 2500, TimeUnit.MILLISECONDS);
            } catch (MonitoringException ex) {
                log.error("MonitoringException caught while starting sensor for metric " + args[i] + ": ", ex);
            }
        }
    }

}
