package fr.aresrpg.dofus.protocol;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Arrays;
import java.util.Iterator;

public class DofusConnection<T extends SelectableChannel & ByteChannel> {
	public static final char DELIMITER = '\0';
	public static final char SEPARATOR = '|';

	private final T  channel;
	private final Selector selector;
	private final PacketHandler handler;
	private final ByteBuffer buffer = ByteBuffer.allocate(512);
	private StringBuilder currentPacket = new StringBuilder();

	public DofusConnection(T channel, PacketHandler handler) throws IOException {
		this.selector = Selector.open();
		this.channel = channel;
		this.handler = handler;
		this.channel.configureBlocking(false);
		this.channel.register(selector , SelectionKey.OP_READ);
	}

	public void read() throws IOException {
		this.selector.select();
		Iterator<SelectionKey> iter = this.selector.selectedKeys().iterator();
		while(iter.hasNext()) {
			SelectionKey key = iter.next();
			iter.remove();

			if(key.isReadable()) {
				ByteChannel channel = (ByteChannel) key.channel();
				int read;
				boolean decode = false;
				while( (read = channel.read(buffer)) > 0 ) {
					buffer.flip();
					byte[] bytes = new byte[read];
					buffer.get(bytes);
					int i = 0;
					for(; i < bytes.length ; i++){
						if(bytes[i] == DELIMITER){
							decode = true;
							break;
						}
					}

					buffer.clear();
					buffer.put(bytes , i , bytes.length - i);
					currentPacket.append(new String(bytes, 0 , i));
				}
				if(decode) {
					String packet = currentPacket.toString();
					currentPacket = new StringBuilder();
					String[] parts = packet.split("\\" + SEPARATOR);
					ProtocolRegistry registry;
					switch (parts[0].length()) {
						case 3:
						case 2:
							registry = ProtocolRegistry.getPacket(parts[0]);
							parts = Arrays.copyOfRange(parts , 1 , parts.length-1);
							break;
						default:
							registry = ProtocolRegistry.getPacket(parts[0].substring(0 , 3));
							parts[0] = parts[0].substring(registry.getId().length());
							break;
					}
					try {
						Packet p = registry.getPacket().newInstance();
						p.read(new StringDofusStream(parts));
						p.handle(handler);
					} catch (ReflectiveOperationException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
