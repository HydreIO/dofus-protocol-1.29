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

import fr.aresrpg.dofus.protocol.account.client.*;
import fr.aresrpg.dofus.protocol.basic.client.BasicChatMessageSendPacket;
import fr.aresrpg.dofus.protocol.basic.client.BasicUseSmileyPacket;
import fr.aresrpg.dofus.protocol.conquest.client.WorldInfosJoinPacket;
import fr.aresrpg.dofus.protocol.conquest.client.WorldInfosLeavePacket;
import fr.aresrpg.dofus.protocol.dialog.client.*;
import fr.aresrpg.dofus.protocol.emote.client.EmoteUsePacket;
import fr.aresrpg.dofus.protocol.exchange.ExchangeLeavePacket;
import fr.aresrpg.dofus.protocol.exchange.client.*;
import fr.aresrpg.dofus.protocol.fight.client.*;
import fr.aresrpg.dofus.protocol.friend.client.*;
import fr.aresrpg.dofus.protocol.game.client.*;
import fr.aresrpg.dofus.protocol.guild.client.GuildRefuseInvitPacket;
import fr.aresrpg.dofus.protocol.info.client.InfoMapPacket;
import fr.aresrpg.dofus.protocol.item.client.*;
import fr.aresrpg.dofus.protocol.job.client.JobChangeStatsPacket;
import fr.aresrpg.dofus.protocol.mount.client.MountPlayerPacket;
import fr.aresrpg.dofus.protocol.party.client.*;
import fr.aresrpg.dofus.protocol.spell.client.SpellBoostPacket;
import fr.aresrpg.dofus.protocol.spell.client.SpellMoveToUsedPacket;
import fr.aresrpg.dofus.protocol.subway.client.SubwayUsePacket;
import fr.aresrpg.dofus.protocol.waypoint.client.ZaapUsePacket;

public interface ClientPacketHandler extends PacketHandler {
	void handle(BasicUseSmileyPacket chatUseSmileyPacket);

	void handle(AccountAuthPacket accountAuthPacket);

	void handle(AccountAccessServerPacket accountAccessServerPacket);

	void handle(AccountGetCharactersPacket accountGetCharactersPacket);

	void handle(AccountGetGiftsPacket accountGetGiftsPacket);

	void handle(AccountGetQueuePosition accountGetQueuePosition);

	void handle(AccountIdentityPacket accountIdentityPacket);

	void handle(AccountListServersPacket accountListServersPacket);

	void handle(AccountSelectCharacterPacket accountSelectCharacterPacket);

	void handle(AccountSetCharacterPacket accountSetCharacterPacket);

	void handle(GameActionACKPacket gameActionACKPacket);

	void handle(GameClientActionPacket gameClientActionPacket);

	void handle(GameClientReadyPacket gameClientReadyPacket);

	void handle(GameCreatePacket gameCreatePacket);

	void handle(GameEndTurnPacket gameEndTurnPacket);

	void handle(GameExtraInformationPacket gameExtraInformationPacket);

	void handle(GameFreeMySoulPacket gameFreeMySoulPacket);

	void handle(GameLeavePacket gameLeavePacket);

	void handle(GameSetPlayerPositionPacket gameSetPlayerPositionPacket);

	void handle(GameTurnEndPacket gameTurnEndPacket);

	void handle(GameTurnOkPacket gameTurnOkPacket);

	void handle(InfoMapPacket infoMapPacket);

	void handle(MountPlayerPacket playerMountPacket);

	void handle(ExchangeAcceptPacket exchangeAcceptPacket);

	void handle(ZaapUsePacket accountAccessServerPacket);

	void handle(EmoteUsePacket emoteUsePacket);

	void handle(ExchangeRequestPacket exchangeRequestPacket);

	void handle(ExchangeMoveItemsPacket exchangeMovePacket);

	void handle(ExchangeSendReadyPacket exchangeReadyPacket);

	void handle(ExchangeShopPacket exchangeShopPacket);

	void handle(ExchangeMoveKamasPacket exchangeMoveKamasPacket);

	void handle(ExchangeSellToNpcPacket exchangeSellToNpcPacket);

	void handle(ExchangeBuyToNpcPacket exchangeBuyToNpcPacket);

	void handle(ExchangeDisconnectAsMerchantPacket exchangeDisconnectAsMerchandPacker);

	void handle(ExchangeAskToDisconnectAsMerchantPacket exchangeAskToDisconnectAsMerchantPacket);

	void handle(ExchangeHdvPacket exchangeAskHdvTypePacket);

	void handle(ExchangeGetCrafterForJobPacket exchangeGetCrafterForJobPacket);

	void handle(ExchangeMountPacket exchangeMountPacket);

	void handle(ExchangeParkMountPacket exchangeParkMountPacket);

	void handle(ExchangeReplayCraftPacket exchangeReplayCraftPacket);

	void handle(ExchangeRepeatCraftPacket exchangeRepeatCraftPacket);

	void handle(DialogBeginPacket dialogBeginPacket);

	void handle(DialogCreatePacket dialogCreatePacket);

	void handle(DialogResponsePacket dialogResponsePacket);

	void handle(FightBlockSpectatePacket fightBlockSpectatePacket);

	void handle(FightRestrictGroupPacket fightRestrictGroupPacket);

	void handle(FightBlockAllPacket fightBlockAllPacket);

	void handle(FightNeedHelpPacket fightNeedHelpPacket);

	void handle(GameActionCancelPacket gameActionCancel);

	void handle(ItemMovementPacket itemMovementFastInvPacket);

	void handle(ItemDropPacket itemDropPacket);

	void handle(ItemDestroyPacket itemDestroyPacket);

	void handle(ItemUsePacket itemUsePacket);

	void handle(ExchangeLeavePacket exchangeLeavePacket);

	void handle(PartyInvitePacket partyInvitePacket);

	void handle(PartyLeavePacket partyLeavePacket);

	void handle(PartyFollowPacket partyFollowPacket);

	void handle(PartyWherePacket partyWherePacket);

	void handle(PartyFollowAllPacket partyFollowAllPacket);

	void handle(JobChangeStatsPacket jobChangeStatsPacket);

	void handle(WorldInfosJoinPacket worldInfosJoinPacket);

	void handle(WorldInfosLeavePacket worldInfosLeavePacket);

	void handle(ItemSkinPacket itemSkinPacket);

	void handle(FriendGetListPacket friendGetListPacket);

	void handle(FriendAddPacket friendAddPacket);

	void handle(FriendRemovePacket friendRemovePacket);

	void handle(BasicChatMessageSendPacket basicChatMessageSendPacket);

	void handle(PartyAcceptPacket partyAcceptPacket);

	void handle(SpellMoveToUsedPacket spellMoveToUsedPacket);

	void handle(SpellBoostPacket spellBoostPacket);

	void handle(SubwayUsePacket subwayUsePacket);

	void handle(GuildRefuseInvitPacket guildRefuseInvitPacket);

}
