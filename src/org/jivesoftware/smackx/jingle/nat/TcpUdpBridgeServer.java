package org.jivesoftware.smackx.jingle.nat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.jivesoftware.smackx.jingle.SmackLogger;

/**
 * A Simple and Experimental Bridge. It Creates a TCP Socket Listeners for
 * Connections and forwards every packets received to an UDP Listener. And
 * forwards every packets received in UDP Socket, to the TCP Client
 */
public class TcpUdpBridgeServer {

	private static final SmackLogger LOGGER = SmackLogger
			.getLogger(TcpUdpBridgeServer.class);

	private String remoteUdpHost = null;
	private int remoteUdpPort = -1;
	private DatagramSocket localUdpSocket;
	private Socket localTcpSocket;
	private ServerSocket serverTcpSocket;

	public TcpUdpBridgeServer(String remoteTcpHost, String remoteUdpHost,
			int remoteTcpPort, int remoteUdpPort) {
		this.remoteUdpHost = remoteUdpHost;
		this.remoteUdpPort = remoteUdpPort;

		try {
			serverTcpSocket = new ServerSocket(remoteTcpPort);
			localUdpSocket = new DatagramSocket(0);
			localUdpSocket.getLocalPort();
			LOGGER.debug("UDP: " + localUdpSocket.getLocalPort());
		} catch (final IOException e) {
			e.printStackTrace();
		}
		startBridge();
	}

	public Socket getLocalTcpSocket() {
		return localTcpSocket;
	}

	public DatagramSocket getLocalUdpSocket() {
		return localUdpSocket;
	}

	public void startBridge() {

		final Thread process = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					final OutputStream out = localTcpSocket.getOutputStream();

					while (true) {

						final byte b[] = new byte[500];
						final DatagramPacket p = new DatagramPacket(b, 500);

						localUdpSocket.receive(p);
						if (p.getLength() == 0) {
							continue;
						}

						LOGGER.debug("UDP Server Received and Sending to TCP Client:"
								+ new String(p.getData(), 0, p.getLength(),
										"UTF-8"));

						out.write(p.getData(), 0, p.getLength());
						out.flush();
						LOGGER.debug("Server Flush");
					}

				} catch (final IOException e) {
					e.printStackTrace();
				}
			}

		});

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					localTcpSocket = serverTcpSocket.accept();
					process.start();
					final InputStream in = localTcpSocket.getInputStream();
					final InetAddress remoteHost = InetAddress
							.getByName(remoteUdpHost);

					while (true) {
						final byte b[] = new byte[500];

						final int s = in.read(b);
						// if (s == -1) continue;

						LOGGER.debug("TCP Server:"
								+ new String(b, 0, s, "UTF-8"));

						final DatagramPacket udpPacket = new DatagramPacket(b,
								s);

						udpPacket.setAddress(remoteHost);
						udpPacket.setPort(remoteUdpPort);

						localUdpSocket.send(udpPacket);

					}

				} catch (final IOException e) {
					e.printStackTrace();
				}
			}

		}).start();
	}
}
