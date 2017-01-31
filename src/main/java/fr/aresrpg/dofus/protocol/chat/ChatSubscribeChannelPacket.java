/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.chat;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.Chat;

import java.util.Arrays;

public class ChatSubscribeChannelPacket implements Packet {
	private boolean add;
	private Chat[] channels;

	@Override
	public void read(DofusStream stream) {
		String data = stream.read();
		add = data.charAt(0) == '+';
		channels = new Chat[data.length() - 1];
		for (int i = 0; i < channels.length; i++) {
			channels[i] = Chat.valueOf(data.charAt(i + 1));
		}
	}

	@Override
	public void write(DofusStream stream) {
		StringBuilder builder = new StringBuilder().append(add ? '+' : '-');
		for (Chat channel : channels)
			builder.append(channel == null ? "" : channel.getCode());
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
