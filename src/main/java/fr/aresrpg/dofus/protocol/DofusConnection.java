package fr.aresrpg.dofus.protocol;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
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
		this.handler.register(this);
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
				boolean decode = false;
				while(true) {
					while(buffer.position() > 0 || channel.read(buffer) > 0 ) {
						int read = buffer.position();
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
						if(i != bytes.length -1)
							buffer.put(bytes , i , bytes.length - i);
						currentPacket.append(new String(bytes, 0 , i));
						if(decode)
							break;
					}

					if(decode) {
						currentPacket.deleteCharAt(currentPacket.length()-1); // Remove \0
						String packet = currentPacket.toString();
						currentPacket = new StringBuilder();
						String[] parts = packet.split("\\" + SEPARATOR);
						ProtocolRegistry registry;
						switch (parts[0].length()) {
							case 2:
								registry = ProtocolRegistry.getRegistry(parts[0].substring(0 , 2));
								break;
							default:
								registry = ProtocolRegistry.getRegistry(parts[0].substring(0 , 3));
								break;
						}
						if(registry != null) {
							parts[0] = parts[0].substring(registry.getId().length());//Remove id
							try {
								Packet p = registry.getPacket().newInstance();
								p.read(new StringDofusStream(parts));
								p.handle(handler);
							} catch (ReflectiveOperationException e) {
								e.printStackTrace();
							}
						}
					}
					if(buffer.position() == 0)
						break;

				}
			}
		}
	}

	public void send(Packet packet) throws IOException {
		StringDofusStream stream = new StringDofusStream();
		packet.write(stream);
		StringBuilder sb = new StringBuilder();
		boolean indexAtEnd = ProtocolRegistry.getRegistry(packet.getClass()).isIndexAtEnd();
		if(!indexAtEnd)
			sb.append(packet.getId());
		String[] out = stream.getOut();
		if(out != null){
			if(out.length == 1)
				sb.append(out[0]);
			else {
				if(!indexAtEnd)
					sb.append(SEPARATOR);
				for(int i = 0 ; i < out.length ; i++) {
					sb.append(out[i]);
					if(i != out.length -1)
						sb.append(SEPARATOR);
				}
			}
		}
		if(indexAtEnd)
			sb.append(packet.getId());
		sb.append(DELIMITER);
		System.out.println(sb.toString());
		channel.write(ByteBuffer.wrap(sb.toString().getBytes()));
	}
}
