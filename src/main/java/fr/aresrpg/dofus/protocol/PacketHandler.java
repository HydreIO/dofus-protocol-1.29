/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol;

import fr.aresrpg.dofus.protocol.account.AccountKeyPacket;
import fr.aresrpg.dofus.protocol.account.AccountRegionalVersionPacket;
import fr.aresrpg.dofus.protocol.chat.ChatSubscribeChannelPacket;
import fr.aresrpg.dofus.protocol.dialog.DialogLeavePacket;
import fr.aresrpg.dofus.protocol.party.PartyRefusePacket;
import fr.aresrpg.dofus.protocol.subway.SubwayLeavePacket;
import fr.aresrpg.dofus.protocol.waypoint.ZaapLeavePacket;

public interface PacketHandler {

	default boolean parse(ProtocolRegistry registry, String packet) {
		throw new UnsupportedOperationException();
	}

	void register(DofusConnection<?> connection);

	void handle(AccountKeyPacket accountKeyPacket);

	void handle(AccountRegionalVersionPacket accountRegionalVersionPacket);

	void handle(ChatSubscribeChannelPacket chatSubscribeChannelPacket);

	void handle(ZaapLeavePacket zaapLeavePacket);

	void handle(PartyRefusePacket partyRefusePacket);

	void handle(DialogLeavePacket dialogLeavePacket);

	void handle(SubwayLeavePacket subwayLeavePacket);
}
