package fr.aresrpg.dofus.protocol;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Arrays;
import java.util.Iterator;

public class DofusConnection<T extends SelectableChannel & ByteChannel> {
	public static final char SEPARATOR = '|';

	private final T channel;
	private final Selector selector;
	private final PacketHandler handler;
	private final ByteBuffer buffer = ByteBuffer.allocate(512);
	private final ProtocolRegistry.Bound bound;
	private final String label;
	private StringBuilder currentPacket = new StringBuilder();

	public DofusConnection(String label, T channel, PacketHandler handler, ProtocolRegistry.Bound bound) throws IOException {
		this.selector = Selector.open();
		this.channel = channel;
		this.handler = handler;
		this.bound = bound;
		this.label = label;
		this.handler.register(this);
		this.channel.configureBlocking(false);
		this.channel.register(selector, SelectionKey.OP_READ);
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	public void close() throws IOException {
		buffer.clear();
		channel.close();
		selector.close();
	}

	public void read() throws IOException {
		this.selector.select();
		Iterator<SelectionKey> iter = this.selector.selectedKeys().iterator();
		while (iter.hasNext()) {
			SelectionKey key = iter.next();
			iter.remove();
			if (key.isReadable())
				readFrom((ReadableByteChannel) key.channel());
		}
	}

	private void readFrom(ReadableByteChannel channel) throws IOException {
		StringBuilder packet = new StringBuilder();
		loop: while (channel.isOpen()) {
			read: while (buffer.position() > 0 || channel.read(buffer) > 0) {
				int read = buffer.position();
				buffer.flip();
				byte[] bytes = new byte[read];
				buffer.get(bytes);
				buffer.clear();
				for (int i = 0; i < bytes.length; i++) {
					if (check(bytes, i, bound.getDelimiter().toCharArray())) {
						int dIndex = i + bound.getDelimiter().length();
						buffer.put(bytes, dIndex, bytes.length - dIndex);
						packet.append(new String(bytes, 0, i));
						decode(packet.toString());
						if (buffer.position() == 0)
							break loop;
						packet = new StringBuilder();
						break read;
					}
				}

				packet.append(new String(bytes));
			}
		}

	}

	private static boolean check(byte[] toCheck, int index, char[] expected) {
		if (index + expected.length > toCheck.length)
			return false;
		for (int i = 0; i < expected.length; i++) {
			if (toCheck[i + index] != expected[i])
				return false;
		}
		return true;
	}

	private void decode(String packet) throws IOException {
		if (packet.length() == 0)
			return;
		System.out.println("[RECEIVE from " + label + "] <- " + packet);
		String fullPacket = currentPacket.length() == 0 ? packet : currentPacket.toString() + bound.getDelimiter() + packet;
		ProtocolRegistry registry = getId(fullPacket);
		if (registry == null) // Try with only current packet
			registry = getId(packet);
		else
			packet = fullPacket;

		try {
			if (handler.parse(registry, packet + bound.getDelimiter()))
				currentPacket = new StringBuilder();
		} catch (UnsupportedOperationException ignored) {
			if (registry != null) {
				if (registry.isIndexAtEnd())
					packet = packet.substring(0, packet.length() - registry.getId().length());
				else
					packet = packet.substring(registry.getId().length());
				currentPacket = new StringBuilder();
				try {
					Packet p = registry.getPacket().newInstance();
					p.read(new StringDofusStream(packet.isEmpty() ? new String[0] : packet.split("\\" + SEPARATOR)));
					p.handle(handler);
				} catch (ReflectiveOperationException e) {
					e.printStackTrace();
				}
			} else {
				if (currentPacket.length() != 0)
					currentPacket.append(bound.getDelimiter());
				currentPacket.append(packet);
			}
		}
	}

	private ProtocolRegistry getId(String packet) {
		if (packet.length() < 2)
			return null;
		int size = packet.length() >= 3 ? 3 : 2;
		ProtocolRegistry registry = ProtocolRegistry.getRegistry(packet.substring(0, size), false, bound);
		if (registry == null)
			registry = ProtocolRegistry.getRegistry(packet.substring(packet.length() - size, packet.length()), true, bound);
		if (registry == null && size != 2)
			registry = ProtocolRegistry.getRegistry(packet.substring(packet.length() - 2, packet.length()), true, bound);
		return registry;
	}

	public void send(Packet packet) throws IOException {
		StringDofusStream stream = new StringDofusStream();
		packet.write(stream);
		StringBuilder sb = new StringBuilder();
		boolean indexAtEnd = ProtocolRegistry.getRegistry(packet.getClass()).isIndexAtEnd();
		String[][] outs = stream.getOut();
		if (!indexAtEnd) {
			sb.append(packet.getId());
		}
		for (int i = 0; i < outs.length; i++) {
			String[] out = outs[i];

			if (out != null) {
				for (int y = 0; y < out.length; y++) {
					sb.append(out[y]);
					if (y != out.length - 1)
						sb.append(SEPARATOR);
				}
			}
			if (i == outs.length - 1 && indexAtEnd)
				sb.append(packet.getId());
			sb.append(bound.getOther().getDelimiter());
		}
		System.out.println("[SEND to " + label + "] -> " + sb.toString() + " " + Arrays.toString(sb.toString().getBytes()));
		channel.write(ByteBuffer.wrap(sb.toString().getBytes()));
	}

	public T getChannel() {
		return channel;
	}
}
