package pl.edu.agh.dsm.sensor.udp;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.*;

public class SensorSocket {

	private static final Logger log = LoggerFactory
			.getLogger(SensorSocket.class);

	private DatagramSocket socket;
	private InetAddress address;
	private Integer monitorPort;

	public SensorSocket(String monitorIp, Integer monitorPort)
			throws IOException {
		this.monitorPort = monitorPort;
		address = InetAddress.getByName(monitorIp);
		socket = new DatagramSocket();
	}

	public void sendData(UdpValueUpdate data) {
		try {
			ObjectWriter ow = new ObjectMapper().writer();
			String json = ow.writeValueAsString(data);
			log.trace("Sending JSON: " + json);
			byte[] sendData = json.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData,
					sendData.length, address, monitorPort);
			socket.send(sendPacket);
		} catch (IOException ex) {
			log.error("IOException caught while announcing new value: " + ex);
		}
	}

}
