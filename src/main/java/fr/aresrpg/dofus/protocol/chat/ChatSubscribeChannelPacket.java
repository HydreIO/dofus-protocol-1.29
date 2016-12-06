package fr.aresrpg.dofus.protocol.chat;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;
import fr.aresrpg.dofus.structures.Chat;

import java.io.IOException;
import java.util.Arrays;

public class ChatSubscribeChannelPacket implements Packet {
	private boolean add;
	private Chat[] channels;

	@Override
	public void read(DofusStream stream) throws IOException {
		String data = stream.read();
		add = data.charAt(0) == '+';
		channels = new Chat[data.length() -1];
		for(int i = 0 ; i < channels.length ; i++){
			channels[i] = Chat.valueOf(data.charAt(i + 1));
		}
	}

	@Override
	public void write(DofusStream stream) throws IOException {
		StringBuilder builder = new StringBuilder().append(add ? '+' : '-');
		for(Chat channel : channels)
			builder.append(channel.getCode());
		stream.allocate(1).write(builder.toString());
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	public boolean isAdd() {
		return add;
	}

	public ChatSubscribeChannelPacket setAdd(boolean add) {
		this.add = add;
		return this;
	}

	public Chat[] getChannels() {
		return channels;
	}

	public ChatSubscribeChannelPacket setChannels(Chat[] channels) {
		this.channels = channels;
		return this;
	}

	@Override
	public String toString() {
		return "ChatSubscribeChannelPacket{(add=" + add +
				", channels=" + Arrays.toString(channels) + ")[" + getId() + ']';
	}
}
