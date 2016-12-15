package fr.aresrpg.dofus.protocol;

import fr.aresrpg.dofus.protocol.account.client.*;
import fr.aresrpg.dofus.protocol.chat.client.BasicUseSmileyPacket;
import fr.aresrpg.dofus.protocol.dialog.DialogLeavePacket;
import fr.aresrpg.dofus.protocol.dialog.client.*;
import fr.aresrpg.dofus.protocol.emote.client.EmoteUsePacket;
import fr.aresrpg.dofus.protocol.exchange.client.*;
import fr.aresrpg.dofus.protocol.game.client.*;
import fr.aresrpg.dofus.protocol.hello.client.HelloGamePacket;
import fr.aresrpg.dofus.protocol.info.client.InfoMapPacket;
import fr.aresrpg.dofus.protocol.mount.client.MountPlayerPacket;
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

	void handle(HelloGamePacket helloGamePacket);

	void handle(InfoMapPacket infoMapPacket);

	void handle(MountPlayerPacket playerMountPacket);

	void handle(ExchangeAcceptPacket exchangeAcceptPacket);

	void handle(ZaapUsePacket accountAccessServerPacket);

	void handle(EmoteUsePacket emoteUsePacket);

	void handle(ExchangeRequestPacket exchangeRequestPacket);

	void handle(ExchangeMoveItemsPacket exchangeMovePacket);

	void handle(ExchangeReadyPacket exchangeReadyPacket);

	void handle(ExchangeShopPacket exchangeShopPacket);

	void handle(ExchangeMoveKamasPacket exchangeMoveKamasPacket);

	void handle(ExchangeSellToNpcPacket exchangeSellToNpcPacket);

	void handle(ExchangeBuyToNpcPacket exchangeBuyToNpcPacket);

	void handle(ExchangeDisconnectAsMerchantPacker exchangeDisconnectAsMerchandPacker);

	void handle(ExchangeAskToDisconnectAsMerchantPacket exchangeAskToDisconnectAsMerchantPacket);

	void handle(ExchangeHdvPacket exchangeAskHdvTypePacket);

	void handle(ExchangeGetCrafterForJobPacket exchangeGetCrafterForJobPacket);

	void handle(ExchangeMountPacket exchangeMountPacket);

	void handle(ExchangeParkMountPacket exchangeParkMountPacket);

	void handle(ExchangeReplayCraftPacket exchangeReplayCraftPacket);

	void handle(ExchangeRepeatCraftPacket exchangeRepeatCraftPacket);

	void handle(DialogBeginPacket dialogBeginPacket);

	void handle(DialogCreatePacket dialogCreatePacket);

	void handle(DialogLeavePacket dialogLeavePacket);

	void handle(DialogResponsePacket dialogResponsePacket);
}
