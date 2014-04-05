package pl.edu.agh.dsm.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import pl.edu.agh.dsm.common.dto.UdpSensorUpdate;
import pl.edu.agh.dsm.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

@SuppressWarnings("InfiniteLoopStatement")
public class SensorListener implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(SensorListener.class);
    private static final int BUFFER_SIZE = 1024;

    private int port;
    private DataService dataService;

    public SensorListener(int port, DataService dataService) {
        this.port = port;
        this.dataService = dataService;
    }

    @Override
    public void run() {
        log.trace("SensorListener.run called.");
        DatagramSocket serverSocket = null;
        byte[] receiveBuffer = new byte[BUFFER_SIZE]; // FIXME: jeśli rozmiar update'u przekroczy 1024 to jest źle...
        while (true) {
            try {
                log.trace("jasbfjsabdjasvhd");
                serverSocket = new DatagramSocket(port);
                while (true) {
                    DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                    serverSocket.receive(receivePacket);
                    String receivedString = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    InetAddress address = receivePacket.getAddress();
                    int sourcePort = receivePacket.getPort();
                    log.trace("Received data from " + address.toString() + ":" + sourcePort + ": " + receivedString);

                    ObjectReader or = new ObjectMapper().reader(UdpSensorUpdate.class);
                    UdpSensorUpdate udpSensorUpdate = or.readValue(receivedString);
                    dataService.registerNewValue(udpSensorUpdate, address.toString());
                }
            } catch (Throwable th) {
                log.warn("Socket failed due to an exception, restarting: ", th);
            } finally {
                if (serverSocket != null) {
                    serverSocket.close();
                    serverSocket = null;
                }
            }
        }
    }
}
