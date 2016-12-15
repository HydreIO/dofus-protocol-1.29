package fr.aresrpg.dofus.protocol;

import fr.aresrpg.dofus.protocol.account.server.*;
import fr.aresrpg.dofus.protocol.basic.server.BasicConfirmPacket;
import fr.aresrpg.dofus.protocol.dialog.server.*;
import fr.aresrpg.dofus.protocol.exchange.client.ExchangeRequestPacket;
import fr.aresrpg.dofus.protocol.exchange.server.*;
import fr.aresrpg.dofus.protocol.game.server.*;
import fr.aresrpg.dofus.protocol.guild.server.GuildStatPacket;
import fr.aresrpg.dofus.protocol.hello.server.HelloConnectionPacket;
import fr.aresrpg.dofus.protocol.info.server.message.InfoMessagePacket;
import fr.aresrpg.dofus.protocol.mount.server.MountXpPacket;
import fr.aresrpg.dofus.protocol.specialization.server.SpecializationSetPacket;
import fr.aresrpg.dofus.protocol.spell.server.SpellChangeOptionPacket;
import fr.aresrpg.dofus.protocol.spell.server.SpellListPacket;
import fr.aresrpg.dofus.protocol.subarea.server.SubareaListPacket;
import fr.aresrpg.dofus.protocol.waypoint.server.ZaapCreatePacket;
import fr.aresrpg.dofus.protocol.waypoint.server.ZaapUseErrorPacket;

public interface ServerPacketHandler extends PacketHandler {
	void handle(AccountCharactersListPacket accountCharactersListPacket);

	void handle(AccountCommunityPacket accountCommunityPacket);

	void handle(AccountHostPacket accountHostPacket);

	void handle(AccountLoginErrPacket accountLoginErrPacket);

	void handle(AccountLoginOkPacket accountLoginOkPacket);

	void handle(AccountPseudoPacket accountPseudoPacket);

	void handle(AccountQuestionPacket accountQuestionPacket);

	void handle(AccountQueuePosition accountQueuePosition);

	void handle(AccountRestrictionsPacket accountRestrictionsPacket);

	void handle(AccountSelectCharacterOkPacket accountSelectCharacterOkPacket);

	void handle(AccountServerEncryptedHostPacket accountServerEncryptedHostPacket);

	void handle(AccountServerHostPacket accountServerHostPacket);

	void handle(AccountServerListPacket accountServerListPacket);

	void handle(AccountTicketOkPacket accountTicketOkPacket);

	void handle(AccountTicketPacket accountTicketPacket);

	void handle(ExchangeCreatePacket exchangeCreatePacket);

	void handle(ExchangeListPacket exchangeListPacket);

	void handle(ExchangeRequestPacket exchangeRequestPacket);

	void handle(BasicConfirmPacket basicConfirmPacket);

	void handle(GuildStatPacket guildStatPacket);

	void handle(HelloConnectionPacket helloConnectionPacket);

	void handle(InfoMessagePacket infoMessagePacket);

	void handle(MountXpPacket mountXpPacket);

	void handle(SpecializationSetPacket specializationSetPacket);

	void handle(SpellChangeOptionPacket spellChangeOptionPacket);

	void handle(SpellListPacket spellListPacket);

	void handle(SubareaListPacket subareaListPacket);

	void handle(ZaapCreatePacket zaapCreatePacket);

	void handle(ZaapUseErrorPacket zaapUseErrorPacket);

	void handle(GameActionFinishPacket gameActionFinishPacket);

	void handle(GameActionStartPacket gameActionStartPacket);

	void handle(GameEffectPacket gameEffectPacket);

	void handle(GameEndPacket gameEndPacket);

	void handle(GameFightChallengePacket gameFightChallengePacket);

	void handle(GameJoinPacket gameJoinPacket);

	void handle(GameMapDataPacket gameMapDataPacket);

	void handle(GameMapFramePacket gameMapFramePacket);

	void handle(GameMovementPacket gameMovementPacket);

	void handle(GameOnReadyPacket gameOnReadyPacket);

	void handle(GamePositionsPacket gamePositionsPacket);

	void handle(GamePositionStartPacket gamePositionStartPacket);

	void handle(GameServerActionPacket gameServerActionPacket);

	void handle(GameServerReadyPacket gameServerReadyPacket);

	void handle(GameStartPacket gameStartPacket);

	void handle(GameStartToPlayPacket gameStartToPlayPacket);

	void handle(GameTurnFinishPacket gameTurnFinishPacket);

	void handle(GameTurnListPacket gameTurnListPacket);

	void handle(GameTurnMiddlePacket gameTurnMiddlePacket);

	void handle(GameTurnReadyPacket gameTurnReadyPacket);

	void handle(GameTurnStartPacket gameTurnStartPacket);

	void handle(ExchangeRequestOkPacket exchangeRequestOkPacket);

	void handle(DialogCreateOkPacket dialogCreateOkPacket);

	void handle(DialogQuestionPacket dialogQuestionPacket);

	void handle(DialogPausePacket dialogPausePacket);
}