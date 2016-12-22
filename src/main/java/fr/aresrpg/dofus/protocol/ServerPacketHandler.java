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

import fr.aresrpg.dofus.protocol.account.server.*;
import fr.aresrpg.dofus.protocol.aks.Aks0MessagePacket;
import fr.aresrpg.dofus.protocol.basic.server.BasicConfirmPacket;
import fr.aresrpg.dofus.protocol.chat.server.ChatMessageOkPacket;
import fr.aresrpg.dofus.protocol.dialog.server.*;
import fr.aresrpg.dofus.protocol.exchange.server.*;
import fr.aresrpg.dofus.protocol.fight.server.*;
import fr.aresrpg.dofus.protocol.friend.server.FriendListPacket;
import fr.aresrpg.dofus.protocol.game.server.*;
import fr.aresrpg.dofus.protocol.guild.server.GuildStatPacket;
import fr.aresrpg.dofus.protocol.hello.server.HelloConnectionPacket;
import fr.aresrpg.dofus.protocol.hello.server.HelloGamePacket;
import fr.aresrpg.dofus.protocol.info.server.*;
import fr.aresrpg.dofus.protocol.item.server.*;
import fr.aresrpg.dofus.protocol.job.server.*;
import fr.aresrpg.dofus.protocol.mount.server.MountXpPacket;
import fr.aresrpg.dofus.protocol.party.server.*;
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

	void handle(GamePositionsPacket gamePositionsPacket);

	void handle(GamePositionStartPacket gamePositionStartPacket);

	void handle(GameServerActionPacket gameServerActionPacket);

	void handle(GameServerReadyPacket gameServerReadyPacket);

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

	void handle(ExchangeReadyPacket exchangeReadyPacket);

	void handle(ItemAddOkPacket itemAddOkPacket);

	void handle(ItemAddErrorPacket itemAddErrorPacket);

	void handle(ItemDropErrorPacket itemDropErrorPacket);

	void handle(ItemRemovePacket itemRemovePacket);

	void handle(ItemQuantityUpdatePacket itemQuantityUpdatePacket);

	void handle(ItemMovementConfirmPacket itemMovementConfirmPacket);

	void handle(ItemToolPacket itemToolPacket);

	void handle(ItemWeightPacket itemWeightPacket);

	void handle(AccountStatsPacket accountStatsPacket);

	void handle(AccountNewLevelPacket accountNewLevelPacket);

	void handle(AccountServerQueuePacket accountServerQueuePacket);

	void handle(HelloGamePacket helloGamePacket);

	void handle(ExchangeCraftPacket exchangeCraftOkPacket);

	void handle(ExchangeLocalMovePacket exchangeLocalMovePacket);

	void handle(ExchangeDistantMovePacket exchangeDistantMovementPacket);

	void handle(ExchangeCoopMovePacket exchangeCoopMovePacket);

	void handle(ExchangeStorageMovePacket exchangeStorageMovePacket);

	void handle(ExchangeShopMovePacket exchangeShopMovePacket);

	void handle(ExchangeCraftPublicPacket exchangeCraftPublicPacket);

	void handle(ExchangeSellToNpcResultPacket exchangeSellToNpcResultPacket);

	void handle(ExchangeBuyToNpcResultPacket exchangeBuyToNpcResultPacket);

	void handle(ExchangeCraftLoopPacket exchangeCraftLoopPacket);

	void handle(ExchangeCraftLoopEndPacket exchangeCraftLoopEndPacket);

	void handle(ExchangeLeaveResultPacket exchangeLeaveResultPacket);

	void handle(PartyInviteRequestOkPacket partyInviteRequestPacket);

	void handle(PartyInviteRequestErrorPacket partyInviteRequestErrorPacket);

	void handle(PartyLeaderPacket partyLeaderPacket);

	void handle(PartyCreateOkPacket partyCreateOkPacket);

	void handle(PartyCreateErrorPacket partyCreateErrorPacket);

	void handle(PartyPlayerLeavePacket partyPlayerLeavePacket);

	void handle(PartyFollowReceivePacket partyFollowReceivePacket);

	void handle(PartyMovementPacket partyMovementPacket);

	void handle(GameTeamPacket gameTeamPacket);

	void handle(JobSkillsPacket jobSkillsPacket);

	void handle(JobXpPacket jobXpPacket);

	void handle(JobLevelPacket jobLevelPacket);

	void handle(GameSpawnPacket gameSpawnPacket);

	void handle(FightCountPacket fightCountPacket);

	void handle(FightListPacket fightListPacket);

	void handle(FightDetailsPacket fightDetailsPacket);

	void handle(InfoCompassPacket infosCompassPacket);

	void handle(InfoCoordinatePacket infoCoordinatePacket);

	void handle(Aks0MessagePacket aksMessagePacket);

	void handle(FriendListPacket friendListPacket);

	void handle(ChatMessageOkPacket chatMessageOkPacket);

}
