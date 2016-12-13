package fr.aresrpg.dofus.protocol;

import fr.aresrpg.dofus.protocol.account.client.*;
import fr.aresrpg.dofus.protocol.chat.client.ChatUseSmileyPacket;
import fr.aresrpg.dofus.protocol.emote.client.EmoteUsePacket;
import fr.aresrpg.dofus.protocol.exchange.client.ExchangeAcceptPacket;
import fr.aresrpg.dofus.protocol.exchange.client.ExchangeAskPacket;
import fr.aresrpg.dofus.protocol.exchange.client.ExchangeBuyItemPacket;
import fr.aresrpg.dofus.protocol.exchange.client.ExchangeHdvPacket;
import fr.aresrpg.dofus.protocol.game.client.*;
import fr.aresrpg.dofus.protocol.hello.client.HelloGamePacket;
import fr.aresrpg.dofus.protocol.info.client.InfoMapPacket;
import fr.aresrpg.dofus.protocol.mount.client.PlayerMountPacket;
import fr.aresrpg.dofus.protocol.waypoint.client.ZaapUsePacket;

public interface ClientPacketHandler extends PacketHandler {
	void handle(ChatUseSmileyPacket chatUseSmileyPacket);

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

	void handle(PlayerMountPacket playerMountPacket);

	void handle(ExchangeAcceptPacket exchangeAcceptPacket);

	void handle(ZaapUsePacket accountAccessServerPacket);

	void handle(ExchangeBuyItemPacket exchangeBuyItemPacket);

	void handle(ExchangeHdvPacket exchangeHdvPacket);

	void handle(ExchangeAskPacket exchangeAskPacket);

	void handle(EmoteUsePacket emoteUsePacket);
}
