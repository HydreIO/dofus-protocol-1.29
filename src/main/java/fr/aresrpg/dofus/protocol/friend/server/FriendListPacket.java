package fr.aresrpg.dofus.protocol.friend.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.Friend;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @since
 */
public class FriendListPacket implements ServerPacket { // FL|CallOfDutyDu34|El-Jawod|Sceat;1;Jawad;166;0;4;0;40

	List<String> offlineFriends = new ArrayList<>();
	List<Friend> onlineFriends = new ArrayList<>();

	@Override
	public void read(DofusStream stream) {
		stream.read();
		while (stream.available() > 0) {
			String data = stream.read();
			Friend f = Friend.parse(data);
			if (f == null) offlineFriends.add(data);
			else onlineFriends.add(f);
		}
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(offlineFriends.size() + onlineFriends.size()+1).write("");
		offlineFriends.forEach(stream::write);
		onlineFriends.stream().map(Friend::serialize).forEach(stream::write);
	}

	/**
	 * @return the offlineFriends
	 */
	public List<String> getOfflineFriends() {
		return offlineFriends;
	}

	/**
	 * @param offlineFriends
	 *            the offlineFriends to set
	 */
	public void setOfflineFriends(List<String> offlineFriends) {
		this.offlineFriends = offlineFriends;
	}

	/**
	 * @return the onlineFriends
	 */
	public List<Friend> getOnlineFriends() {
		return onlineFriends;
	}

	/**
	 * @param onlineFriends
	 *            the onlineFriends to set
	 */
	public void setOnlineFriends(List<Friend> onlineFriends) {
		this.onlineFriends = onlineFriends;
	}

	@Override
	public String toString() {
		return "FriendListPacket [offlineFriends=" + offlineFriends + ", onlineFriends=" + onlineFriends + "]";
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

}
