package pl.edu.agh.dsm.sensor.main;

import pl.edu.agh.dsm.sensor.iface.MonitoredResource;
import pl.edu.agh.dsm.sensor.iface.MonitoringException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

import dsm.common.dto.UdpSensorUpdate;

public class SensorThread implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(SensorThread.class);

    private MonitoredResource resource;

    private final String id;
    private final String nodeId;
    private final String monitorIp;
    private final int monitorPort;
    private final long interval;

    public SensorThread(String id, int interval, MonitoredResource resource) {
        this.id = id;
        this.interval = interval;
        this.resource = resource;

        this.nodeId = SensorConfiguration.getInstance().getSensorId();
        this.monitorIp = SensorConfiguration.getInstance().getMonitorIp();
        this.monitorPort = SensorConfiguration.getInstance().getMonitorPort();

    }

    public long getInterval() {
        return interval;
    }


    private void announceNewValue(Double value) throws IOException {
        log.trace("Sending update to monitor server (resource id: " + id + ")");
        UdpSensorUpdate val = new UdpSensorUpdate();
        val.setTimestamp(new Date());
        val.setValue(value);
        val.setId(id);
        val.setNodeId(nodeId);
        //XXX: val.setMetric(resource.getClass().getName());
        val.setMetric(resource.getClass().getSimpleName());

        ObjectWriter ow = new ObjectMapper().writer();
        String json = ow.writeValueAsString(val);

        log.trace("JSON generated for resource " + id + ": " + json);
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress address = InetAddress.getByName(monitorIp);
        byte[] sendData = json.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, monitorPort);
        clientSocket.send(sendPacket);
    }

    @Override
    public void run() {
        log.trace("Executing resource value update for sensor " + id + "");
        Double result;
        try {
            result = resource.checkResource();
            announceNewValue(result);
            log.trace("Executed resource value update for sensor " + id + ", got result: " + result);
        } catch (MonitoringException ex) {
            if (ex.isFatal()) {
                log.error("Resource monitor failure, most likely due to malformed configuration,"
                        + " see MonitoringException.message for cause: ", ex);
                throw new RuntimeException("Killing thread.");
            } else {
                log.error("MonitoringException caught while executing resource "
                        + "value update for sensor " + id + ": ", ex);
            }
        } catch (Throwable th) {
            log.error("Throwable caught while executing resource value update for sensor " + id + ": ", th);
        }
    }
}
