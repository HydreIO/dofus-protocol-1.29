package fr.aresrpg.dofus.protocol;

import fr.aresrpg.dofus.protocol.account.AccountKeyPacket;
import fr.aresrpg.dofus.protocol.account.AccountRegionalVersionPacket;
import fr.aresrpg.dofus.protocol.account.client.*;
import fr.aresrpg.dofus.protocol.account.server.*;
import fr.aresrpg.dofus.protocol.basic.server.BasicConfirmPacket;
import fr.aresrpg.dofus.protocol.chat.ChatSubscribeChannelPacket;
import fr.aresrpg.dofus.protocol.chat.client.ChatUseSmileyPacket;
import fr.aresrpg.dofus.protocol.emote.client.EmoteUsePacket;
import fr.aresrpg.dofus.protocol.exchange.server.ExchangeListPacket;
import fr.aresrpg.dofus.protocol.game.client.*;
import fr.aresrpg.dofus.protocol.game.server.*;
import fr.aresrpg.dofus.protocol.guild.server.GuildStatPacket;
import fr.aresrpg.dofus.protocol.hello.client.HelloGamePacket;
import fr.aresrpg.dofus.protocol.hello.server.HelloConnectionPacket;
import fr.aresrpg.dofus.protocol.info.client.InfoMapPacket;
import fr.aresrpg.dofus.protocol.info.server.message.InfoMessagePacket;
import fr.aresrpg.dofus.protocol.mount.client.PlayerMountPacket;
import fr.aresrpg.dofus.protocol.mount.server.MountXpPacket;
import fr.aresrpg.dofus.protocol.specialization.server.SpecializationSetPacket;
import fr.aresrpg.dofus.protocol.spell.server.SpellChangeOptionPacket;
import fr.aresrpg.dofus.protocol.spell.server.SpellListPacket;
import fr.aresrpg.dofus.protocol.subarea.server.SubareaListPacket;
import fr.aresrpg.dofus.protocol.waypoint.WaypointLeavePacket;
import fr.aresrpg.dofus.protocol.waypoint.client.WaypointUsePacket;
import fr.aresrpg.dofus.protocol.waypoint.server.WaypointCreatePacket;
import fr.aresrpg.dofus.protocol.waypoint.server.WaypointUseErrorPacket;

public interface PacketHandler {

	default boolean parse(ProtocolRegistry registry, String packet) {
		throw new UnsupportedOperationException();
	}

	void register(DofusConnection<?> connection);

	void handle(HelloGamePacket helloGamePacket);

	void handle(HelloConnectionPacket helloConnectionPacket);

	void handle(AccountAuthPacket accountAuthPacket);

	void handle(AccountLoginErrPacket accountLoginErrPacket);

	void handle(AccountLoginOkPacket accountLoginOkPacket);

	void handle(AccountPseudoPacket accountPseudoPacket);

	void handle(AccountCommunityPacket accountCommunityPacket);

	void handle(AccountHostPacket accountHostPacket);

	void handle(AccountQuestionPacket accountQuestionPacket);

	void handle(AccountListServersPacket accountListServersPacket);

	void handle(AccountServerListPacket accountServerListPacket);

	void handle(AccountAccessServerPacket accountAccessServerPacket);

	void handle(AccountServerEncryptedHostPacket accountServerEncryptedHostPacket);

	void handle(AccountServerHostPacket accountServerHostPacket);

	void handle(AccountTicketPacket accountTicketPacket);

	void handle(AccountTicketOkPacket accountTicketOkPacket);

	void handle(BasicConfirmPacket basicConfirmPacket);

	void handle(AccountKeyPacket accountKeyPacket);

	void handle(AccountRegionalVersionPacket accountRegionalVersionPacket);

	void handle(AccountGetGiftsPacket accountGetGiftsPacket);

	void handle(AccountIdentity accountIdentity);

	void handle(AccountGetCharactersPacket accountGetCharactersPacket);

	void handle(AccountCharactersListPacket accountCharactersListPacket);

	void handle(AccountSelectCharacterPacket accountSelectCharacterPacket);

	void handle(AccountGetQueuePosition accountGetQueuePosition);

	void handle(AccountQueuePosition accountQueuePosition);

	void handle(MountXpPacket mountXpPacket);

	void handle(GameExtraInformationPacket gameExtraInformationPacket);

	void handle(InfoMessagePacket infoMessagePacket);

	void handle(SpecializationSetPacket specializationSetPacket);

	void handle(InfoMapPacket infoMapPacket);

	void handle(GameCreatePacket gameCreatePacket);

	void handle(GameMapDataPacket gameMapDataPacket);

	void handle(PlayerMountPacket playerMountPacket);

	void handle(GameJoinPacket gameJoinPacket);

	void handle(GameEndTurnPacket gameEndTurnPacket);

	void handle(GameTurnOkPacket gameTurnOkPacket);

	void handle(FreeMySoulPacket freeMySoulPacket);

	void handle(LeaveGamePacket leaveGamePacket);

	void handle(GameSetPlayerPositionPacket gameSetPlayerPositionPacket);

	void handle(GamePositionStartPacket gamePositionStartPacket);

	void handle(GameOnReadyPacket gameOnReadyPacket);

	void handle(GameStartPacket gameStartPacket);

	void handle(GameEndPacket gameEndPacket);

	void handle(AccountSelectCharacterOkPacket accountSelectCharacterOkPacket);

	void handle(ChatSubscribeChannelPacket chatSubscribeChannelPacket);

	void handle(GameMovementPacket gameMovementPacket);

	void handle(GameMapFramePacket gameMapFramePacket);

	void handle(GameActionACKPacket gameActionACKPacket);

	void handle(GameClientActionPacket gameClientActionPacket);

	void handle(GameServerActionPacket gameServerActionPacket);

	void handle(GuildStatPacket guildStatPacket);

	void handle(SpellChangeOptionPacket spellChangeOptionPacket);

	void handle(SpellListPacket spellListPacket);

	void handle(SubareaListPacket subareaListPacket);

	void handle(AccountRestrictionsPacket accountRestrictionsPacket);

	void handle(GamePositionsPacket gamePositionPacket);

	void handle(GameClientReadyPacket gameReadyPacket);

	void handle(GameServerReadyPacket gameServerReadyPacket);

	void handle(GameStartToPlayPacket gameStartToPlayPacket);

	void handle(GameTurnListPacket gameTurnListPacket);

	void handle(GameTurnEndPacket gameTurnEndPacket);

	void handle(GameTurnMiddlePacket gameTurnMiddlePacket);

	void handle(GameTurnStartPacket gameTurnStartPacket);

	void handle(GameTurnFinishPacket gameTurnFinishPacket);

	void handle(GameTurnReadyPacket gameTurnReadyPacket);

	void handle(GameActionFinishPacket gameActionFinishPacket);

	void handle(GameEffectPacket gameEffectPacket);

	void handle(WaypointLeavePacket waypointLeavePacket);

	void handle(WaypointUseErrorPacket waypointUseErrorPacket);

	void handle(WaypointCreatePacket waypointCreatePacket);

	void handle(ChatUseSmileyPacket chatUseSmileyPacket);

	void handle(WaypointUsePacket waypointUsePacket);

	void handle(EmoteUsePacket emoteUsePacket);

	void handle(ExchangeListPacket exchangeListPacket);
}
