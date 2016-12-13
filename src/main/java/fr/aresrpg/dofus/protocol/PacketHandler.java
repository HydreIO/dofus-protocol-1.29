package fr.aresrpg.dofus.protocol;

import fr.aresrpg.dofus.protocol.account.AccountKeyPacket;
import fr.aresrpg.dofus.protocol.account.AccountRegionalVersionPacket;
import fr.aresrpg.dofus.protocol.chat.ChatSubscribeChannelPacket;
import fr.aresrpg.dofus.protocol.exchange.ExchangeLeavePacket;
import fr.aresrpg.dofus.protocol.waypoint.ZaapLeavePacket;

public interface PacketHandler {

	default boolean parse(ProtocolRegistry registry, String packet) {
		throw new UnsupportedOperationException();
	}

	void register(DofusConnection<?> connection);

	void handle(AccountKeyPacket accountKeyPacket);

	void handle(AccountRegionalVersionPacket accountRegionalVersionPacket);

	void handle(ChatSubscribeChannelPacket chatSubscribeChannelPacket);

	void handle(ExchangeLeavePacket exchangeLeavePacket);

	void handle(ZaapLeavePacket zaapLeavePacket);
}
