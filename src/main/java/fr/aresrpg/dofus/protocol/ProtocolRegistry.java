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
import fr.aresrpg.dofus.protocol.account.client.*;
import fr.aresrpg.dofus.protocol.account.server.*;
import fr.aresrpg.dofus.protocol.basic.server.BasicConfirmPacket;
import fr.aresrpg.dofus.protocol.chat.ChatSubscribeChannelPacket;
import fr.aresrpg.dofus.protocol.chat.client.BasicUseSmileyPacket;
import fr.aresrpg.dofus.protocol.dialog.DialogLeavePacket;
import fr.aresrpg.dofus.protocol.dialog.client.*;
import fr.aresrpg.dofus.protocol.dialog.server.*;
import fr.aresrpg.dofus.protocol.emote.client.EmoteUsePacket;
import fr.aresrpg.dofus.protocol.exchange.ExchangeLeavePacket;
import fr.aresrpg.dofus.protocol.exchange.client.*;
import fr.aresrpg.dofus.protocol.exchange.server.*;
import fr.aresrpg.dofus.protocol.fight.client.*;
import fr.aresrpg.dofus.protocol.game.client.*;
import fr.aresrpg.dofus.protocol.game.server.*;
import fr.aresrpg.dofus.protocol.guild.server.GuildStatPacket;
import fr.aresrpg.dofus.protocol.hello.server.HelloConnectionPacket;
import fr.aresrpg.dofus.protocol.hello.server.HelloGamePacket;
import fr.aresrpg.dofus.protocol.info.client.InfoMapPacket;
import fr.aresrpg.dofus.protocol.info.server.message.InfoMessagePacket;
import fr.aresrpg.dofus.protocol.item.client.*;
import fr.aresrpg.dofus.protocol.item.server.*;
import fr.aresrpg.dofus.protocol.mount.client.MountPlayerPacket;
import fr.aresrpg.dofus.protocol.mount.server.MountXpPacket;
import fr.aresrpg.dofus.protocol.party.client.*;
import fr.aresrpg.dofus.protocol.party.server.*;
import fr.aresrpg.dofus.protocol.specialization.server.SpecializationSetPacket;
import fr.aresrpg.dofus.protocol.spell.server.SpellChangeOptionPacket;
import fr.aresrpg.dofus.protocol.spell.server.SpellListPacket;
import fr.aresrpg.dofus.protocol.subarea.server.SubareaListPacket;
import fr.aresrpg.dofus.protocol.waypoint.ZaapLeavePacket;
import fr.aresrpg.dofus.protocol.waypoint.client.ZaapUsePacket;
import fr.aresrpg.dofus.protocol.waypoint.server.ZaapCreatePacket;
import fr.aresrpg.dofus.protocol.waypoint.server.ZaapUseErrorPacket;

import java.util.*;

public enum ProtocolRegistry {

	// HELLO ==========================================================
	// server
	HELLO_GAME(Layer.HELLO, 'G', Bound.SERVER, HelloGamePacket.class),
	HELLO_CONNECTION(Layer.HELLO, 'C', Bound.SERVER, HelloConnectionPacket.class),

	// ACCOUNT ==========================================================
	// server
	ACCOUNT_QUEUE_POSITION(Layer.ACCOUNT, 'f', Bound.SERVER, AccountQueuePosition.class),
	ACCOUNT_LOGIN_ERROR(Layer.ACCOUNT, 'l', State.ERROR, Bound.SERVER, AccountLoginErrPacket.class),
	ACCOUNT_LOGIN_OK(Layer.ACCOUNT, 'l', State.OK, Bound.SERVER, AccountLoginOkPacket.class),
	ACCOUNT_PSEUDO(Layer.ACCOUNT, 'd', Bound.SERVER, AccountPseudoPacket.class),
	ACCOUNT_COMMUNITY(Layer.ACCOUNT, 'c', Bound.SERVER, AccountCommunityPacket.class),
	ACCOUNT_HOST(Layer.ACCOUNT, 'H', Bound.SERVER, AccountHostPacket.class),
	ACCOUNT_QUESTION(Layer.ACCOUNT, 'Q', Bound.SERVER, AccountQuestionPacket.class),
	ACCOUNT_SERVER_LIST(Layer.ACCOUNT, 'x', State.OK, Bound.SERVER, AccountServerListPacket.class),
	ACCOUNT_SERVER_ENCRYPTED_HOST(Layer.ACCOUNT, 'X', State.OK, Bound.SERVER, AccountServerEncryptedHostPacket.class),
	ACCOUNT_SERVER_HOST(Layer.ACCOUNT, 'Y', State.OK, Bound.SERVER, AccountServerHostPacket.class),
	ACCOUNT_TICKET(Layer.ACCOUNT, 'T', Bound.SERVER, AccountTicketPacket.class),
	ACCOUNT_TICKET_OK(Layer.ACCOUNT, 'T', State.OK, Bound.SERVER, AccountTicketOkPacket.class),
	ACCOUNT_LIST_CHARACTERS(Layer.ACCOUNT, 'L', State.OK, Bound.SERVER, AccountCharactersListPacket.class),
	ACCOUNT_SELECT_CHARACTER_OK(Layer.ACCOUNT, 'S', State.OK, Bound.SERVER, AccountSelectCharacterOkPacket.class),
	ACCOUNT_RESTRICTIONS(Layer.ACCOUNT, 'R', Bound.SERVER, AccountRestrictionsPacket.class),
	ACCOUNT_STATS(Layer.ACCOUNT, 's', Bound.SERVER, AccountStatsPacket.class),
	ACCOUNT_NEW_LVL(Layer.ACCOUNT, 'N', Bound.SERVER, AccountNewLevelPacket.class),
	ACCOUNT_SERVER_QUEUE(Layer.ACCOUNT, 'q', Bound.SERVER, AccountServerQueuePacket.class),

	// client
	ACCOUNT_AUTHENTICATION(Layer.ACCOUNT, 'f', true, Bound.CLIENT, AccountAuthPacket.class),
	ACCOUNT_GET_QUEUE_POSITION(Layer.ACCOUNT, 'f', Bound.CLIENT, AccountGetQueuePosition.class),
	ACCOUNT_LIST_SERVER(Layer.ACCOUNT, 'x', Bound.CLIENT, AccountListServersPacket.class),
	ACCOUNT_ACCESS_SERVER(Layer.ACCOUNT, 'X', Bound.CLIENT, AccountAccessServerPacket.class),
	ACCOUNT_SET_CHARACTER(Layer.ACCOUNT, 'S', Bound.CLIENT, AccountSetCharacterPacket.class),
	ACCOUNT_GET_GIFTS(Layer.ACCOUNT, 'g', Bound.CLIENT, AccountGetGiftsPacket.class),
	ACCOUNT_IDENTITY(Layer.ACCOUNT, 'i', Bound.CLIENT, AccountIdentityPacket.class),
	ACCOUNT_GET_CHARACTERS(Layer.ACCOUNT, 'L', Bound.CLIENT, AccountGetCharactersPacket.class),
	ACCOUNT_SELECT_CHARACTER(Layer.ACCOUNT, 'S', Bound.CLIENT, AccountSelectCharacterPacket.class),

	// both
	ACCOUNT_KEY(Layer.ACCOUNT, 'k', Bound.BOTH, AccountKeyPacket.class),
	ACCOUNT_REGION_VERSION(Layer.ACCOUNT, 'V', Bound.BOTH, AccountRegionalVersionPacket.class),

	// DIALOG ==========================================================
	// server
	DIALOG_CREATE_OK(Layer.DIALOG, 'C', State.OK, Bound.SERVER, DialogCreateOkPacket.class),
	DIALOG_QUESTION(Layer.DIALOG, 'Q', Bound.SERVER, DialogQuestionPacket.class),
	DIALOG_PAUSE(Layer.DIALOG, 'P', Bound.SERVER, DialogPausePacket.class),
	// client
	DIALOG_BEGIN(Layer.DIALOG, 'B', Bound.CLIENT, DialogBeginPacket.class),
	DIALOG_CREATE(Layer.DIALOG, 'C', Bound.CLIENT, DialogCreatePacket.class),
	DIALOG_RESPONSE(Layer.DIALOG, 'R', Bound.CLIENT, DialogResponsePacket.class),
	// both
	DIALOG_LEAVE(Layer.DIALOG, 'V', Bound.BOTH, DialogLeavePacket.class),

	// ITEM ==========================================================
	// server
	ITEM_DROP_ERROR(Layer.OBJECT, 'D', State.ERROR, Bound.SERVER, ItemDropErrorPacket.class),
	ITEM_ADD_ERROR(Layer.OBJECT, 'A', State.ERROR, Bound.SERVER, ItemAddErrorPacket.class), // a test
	ITEM_ADD_OK(Layer.OBJECT, 'A', State.OK, Bound.SERVER, ItemAddOkPacket.class),
	ITEM_REMOVE(Layer.OBJECT, 'R', Bound.SERVER, ItemRemovePacket.class),
	ITEM_QUANTITY(Layer.OBJECT, 'Q', Bound.SERVER, ItemQuantityUpdatePacket.class),
	ITEM_MOVEMENT_CONFIRM(Layer.OBJECT, 'M', Bound.SERVER, ItemMovementConfirmPacket.class),
	ITEM_TOOL(Layer.OBJECT, 'T', Bound.SERVER, ItemToolPacket.class),
	ITEM_WEIGHT(Layer.OBJECT, 'w', Bound.SERVER, ItemWeightPacket.class),

	// client
	ITEM_MOVEMENT(Layer.OBJECT, 'M', Bound.CLIENT, ItemMovementPacket.class),
	ITEM_DROP(Layer.OBJECT, 'D', Bound.CLIENT, ItemDropPacket.class),
	ITEM_DESTROY(Layer.OBJECT, 'd', Bound.CLIENT, ItemDestroyPacket.class),
	ITEM_USE(Layer.OBJECT, 'U', Bound.CLIENT, ItemUsePacket.class), // TODO: verif bConfirm ? ("u") : ("U")

	// BASIC ==========================================================
	// server
	BASIC_CONFIRM(Layer.BASIC, 'N', Bound.SERVER, BasicConfirmPacket.class),
	// client
	BASIC_SMILEY(Layer.BASIC, 'S', Bound.CLIENT, BasicUseSmileyPacket.class),

	// MOUNT ==========================================================
	// server
	MOUNT_XP(Layer.MOUNT, 'x', Bound.SERVER, MountXpPacket.class),
	MOUNT_PLAYER(Layer.MOUNT, 'r', Bound.SERVER, MountPlayerPacket.class),

	// GAME ==========================================================
	// server
	GAME_MAP_DATA(Layer.GAME, 'D', 'M', Bound.SERVER, GameMapDataPacket.class),
	GAME_MAP_FRAME(Layer.GAME, 'D', 'F', Bound.SERVER, GameMapFramePacket.class),
	GAME_JOIN(Layer.GAME, 'J', State.OK, Bound.SERVER, GameJoinPacket.class),
	GAME_ON_READY(Layer.GAME, 'R', Bound.SERVER, GameOnReadyPacket.class),
	GAME_START_POSITION_PACKET(Layer.GAME, 'P', Bound.SERVER, GamePositionStartPacket.class),
	GAME_START(Layer.GAME, 'S', Bound.SERVER, GameStartPacket.class),
	GAME_END(Layer.GAME, 'E', Bound.SERVER, GameEndPacket.class),
	GAME_MOVEMENT(Layer.GAME, 'M', Bound.SERVER, GameMovementPacket.class),
	GAME_SERVER_READY(Layer.GAME, 'R', Bound.SERVER, GameServerReadyPacket.class),
	GAME_START_TO_PLAY(Layer.GAME, 'S', Bound.SERVER, GameStartToPlayPacket.class),
	GAME_POSITIONS(Layer.GAME, 'I', 'C', Bound.SERVER, GamePositionsPacket.class),
	GAME_FIGHT_CHALLENGE(Layer.GAME, 'd', Bound.SERVER, GameFightChallengePacket.class),
	GAME_TURN_LIST(Layer.GAME, 'T', 'L', Bound.SERVER, GameTurnListPacket.class),
	GAME_TURN_MIDDLE(Layer.GAME, 'T', 'M', Bound.SERVER, GameTurnMiddlePacket.class),
	GAME_TURN_START(Layer.GAME, 'T', 'S', Bound.SERVER, GameTurnStartPacket.class),
	GAME_TURN_FINISH(Layer.GAME, 'T', 'F', Bound.SERVER, GameTurnFinishPacket.class),
	GAME_TURN_READY(Layer.GAME, 'T', 'R', Bound.SERVER, GameTurnReadyPacket.class),
	GAME_EFFECT(Layer.GAME, 'I', 'E', Bound.SERVER, GameEffectPacket.class),
	GAME_SERVER_ACTION(Layer.GAME, 'A', Bound.SERVER, GameServerActionPacket.class),

	// client
	GAME_GET_EXTRA_INFORMATION(Layer.GAME, 'I', Bound.CLIENT, GameExtraInformationPacket.class),
	GAME_CREATE(Layer.GAME, 'C', Bound.CLIENT, GameCreatePacket.class),
	GAME_END_TURN(Layer.GAME, 't', Bound.CLIENT, GameEndTurnPacket.class),
	GAME_TURN_OK(Layer.GAME, 'T', Bound.CLIENT, GameTurnOkPacket.class),
	GAME_FREE_MY_SOUL(Layer.GAME, 'F', Bound.CLIENT, GameFreeMySoulPacket.class),
	GAME_SET_PLAYER_POSITION(Layer.GAME, 'p', Bound.CLIENT, GameSetPlayerPositionPacket.class),
	GAME_LEAVE(Layer.GAME, 'V', Bound.CLIENT, GameLeavePacket.class),
	GAME_PLAYER_READY(Layer.GAME, 'R', Bound.CLIENT, GameClientReadyPacket.class),
	GAME_TURN_END(Layer.GAME, 't', Bound.CLIENT, GameTurnEndPacket.class),
	GAME_CLIENT_ACTION(Layer.GAME, 'A', Bound.CLIENT, GameClientActionPacket.class),

	// action server
	GAME_ACTION_START(Layer.GAME, 'A', 'S', Bound.SERVER, GameActionStartPacket.class),
	GAME_ACTION_FINISH(Layer.GAME, 'A', 'F', Bound.SERVER, GameActionFinishPacket.class),

	// action client
	GAME_ACTION_ACK(Layer.GAME, 'K', 'K', Bound.CLIENT, GameActionACKPacket.class),
	GAME_ACTION_CANCEL(Layer.GAME, 'K', 'E', Bound.CLIENT, GameActionCancel.class),

	// GUILD ==========================================================
	// server
	GUILD_STATS(Layer.GAME, 'S', Bound.SERVER, GuildStatPacket.class),

	// FIGHT ==========================================================
	// server
	// client
	FIGHT_BLOCK_SPECTATE(Layer.FIGHT, 'S', Bound.CLIENT, FightBlockSpectatePacket.class),
	FIGHT_RESTRICT_GROUP(Layer.FIGHT, 'P', Bound.CLIENT, FightRestrictGroupPacket.class),
	FIGHT_BLOCK_ALL(Layer.FIGHT, 'N', Bound.CLIENT, FightBlockAllPacket.class),
	FIGHT_NEED_HELP(Layer.FIGHT, 'H', Bound.CLIENT, FightNeedHelpPacket.class),
	// both

	// INFO ==========================================================
	// server
	INFO_MESSAGE(Layer.INFO, 'm', Bound.SERVER, InfoMessagePacket.class),
	// client
	INFO_MAP(Layer.INFO, 'M', Bound.CLIENT, InfoMapPacket.class),

	// CHAT ==========================================================
	// both
	CHAT_SUBSCRIBE_CHANNEL(Layer.CHAT, 'C', Bound.BOTH, ChatSubscribeChannelPacket.class),

	// SPECIALIZATION ==========================================================
	SPECIALIZATION_SET(Layer.SPECIALIZATION, 'S', Bound.SERVER, SpecializationSetPacket.class),

	// PARTY ==========================================================
	// server
	PARTY_INVITE_OK(Layer.PARTY, 'I', State.OK, Bound.SERVER, PartyInviteRequestOkPacket.class),
	PARTY_INVITE_ERROR(Layer.PARTY, 'I', State.ERROR, Bound.SERVER, PartyInviteRequestErrorPacket.class),
	PARTY_LEADER(Layer.PARTY, 'L', Bound.SERVER, PartyLeaderPacket.class),
	PARTY_REFUSED(Layer.PARTY, 'R', Bound.SERVER, PartyRefusedPacket.class),
	PARTY_ACCEPTED(Layer.PARTY, 'A', Bound.SERVER, PartyAcceptedPacket.class),
	PARTY_CREATE_OK(Layer.PARTY, 'C', State.OK, Bound.SERVER, PartyCreateOkPacket.class),
	PARTY_CREATE_ERROR(Layer.PARTY, 'C', State.ERROR, Bound.SERVER, PartyCreateErrorPacket.class),
	PARTY_PLAYER_LEAVE(Layer.PARTY, 'V', Bound.SERVER, PartyPlayerLeavePacket.class),
	PARTY_FOLLOW_RECEIVE(Layer.PARTY, 'F', Bound.SERVER, PartyFollowReceivePacket.class),
	PARTY_MOVEMENT(Layer.PARTY, 'M', Bound.SERVER, PartyMovementPacket.class),

	// client
	PARTY_INVITE(Layer.PARTY, 'I', Bound.CLIENT, PartyInvitePacket.class),
	PARTY_REFUSE_INVITATION(Layer.PARTY, 'R', Bound.CLIENT, PartyRefuseInvitationPacket.class),
	PARTY_ACCEPT_INVITATION(Layer.PARTY, 'A', Bound.CLIENT, PartyAcceptInvitationPacket.class),
	PARTY_LEAVE(Layer.PARTY, 'V', Bound.CLIENT, PartyLeavePacket.class),
	PARTY_FOLLOW(Layer.PARTY, 'F', Bound.CLIENT, PartyFollowPacket.class),
	PARTY_WHERE(Layer.PARTY, 'W', Bound.CLIENT, PartyWherePacket.class),
	PARTY_FOLLOW_ALL(Layer.PARTY, 'G', Bound.CLIENT, PartyFollowAllPacket.class),

	// SPELL ==========================================================
	// server
	SPELL_LIST(Layer.SPELL, 'L', Bound.SERVER, SpellListPacket.class),
	SPELL_CHANGE_OPTION(Layer.SPELL, 'L', 'o', Bound.SERVER, SpellChangeOptionPacket.class),

	// SUBAREA ==========================================================
	// server
	SUBAREA_LIST(Layer.AREA, 'l', Bound.SERVER, SubareaListPacket.class),

	// EMOT
	// client
	EMOTE_USE(Layer.EMOTE, 'U', Bound.CLIENT, EmoteUsePacket.class),

	// EXCHANGE ==========================================================
	// server
	EXCHANGE_LIST(Layer.EXCHANGE, 'L', Bound.SERVER, ExchangeListPacket.class),
	EXCHANGE_CREATE(Layer.EXCHANGE, 'C', Bound.SERVER, ExchangeCreatePacket.class),
	EXCHANGE_REQUEST_OK(Layer.EXCHANGE, 'R', State.OK, Bound.SERVER, ExchangeRequestOkPacket.class),
	EXCHANGE_READY(Layer.EXCHANGE, 'K', Bound.SERVER, ExchangeReadyPacket.class),
	EXCHANGE_CRAFT(Layer.EXCHANGE, 'c', Bound.SERVER, ExchangeCraftPacket.class),
	EXCHANGE_LOCAL_MOVE(Layer.EXCHANGE, 'M', Bound.SERVER, ExchangeLocalMovePacket.class),
	EXCHANGE_DISTANT_MOVE(Layer.EXCHANGE, 'm', Bound.SERVER, ExchangeDistantMovePacket.class),
	EXCHANGE_COOP_MOVE(Layer.EXCHANGE, 'r', Bound.SERVER, ExchangeCoopMovePacket.class),
	EXCHANGE_STORAGE_MOVE(Layer.EXCHANGE, 's', Bound.SERVER, ExchangeStorageMovePacket.class),
	EXCHANGE_SHOP_MOVE(Layer.EXCHANGE, 'i', Bound.SERVER, ExchangeShopMovePacket.class),
	EXCHANGE_CRAFT_PUBLIC(Layer.EXCHANGE, 'W', Bound.SERVER, ExchangeCraftPublicPacket.class),
	// EXCHANGE_MOUNT_STORAGE(Layer.EXCHANGE, 'e', Bound.SERVER, null), useless pour le moment et plutot complex
	// EXCHANGE_MOUNT_POD(Layer.EXCHANGE, 'w', Bound.SERVER, null),
	EXCHANGE_SELL_TO_NPC_RESULT(Layer.EXCHANGE, 'S', Bound.SERVER, ExchangeSellToNpcResultPacket.class),
	EXCHANGE_BUY_TO_NPC_RESULT(Layer.EXCHANGE, 'B', Bound.SERVER, ExchangeBuyToNpcResultPacket.class),
	EXCHANGE_CRAFT_LOOP(Layer.EXCHANGE, 'A', Bound.SERVER, ExchangeCraftLoopPacket.class),
	EXCHANGE_CRAFT_LOOP_END(Layer.EXCHANGE, 'a', Bound.SERVER, ExchangeCraftLoopEndPacket.class),
	EXCHANGE_LEAVE_RESULT(Layer.EXCHANGE, 'V', Bound.SERVER, ExchangeLeaveResultPacket.class),

	// client
	EXCHANGE_SHOP(Layer.EXCHANGE, 's', Bound.CLIENT, ExchangeShopPacket.class),
	EXCHANGE_REQUEST(Layer.EXCHANGE, 'R', Bound.CLIENT, ExchangeRequestPacket.class),
	EXCHANGE_ACCEPT(Layer.EXCHANGE, 'A', Bound.CLIENT, ExchangeAcceptPacket.class),
	EXCHANGE_SEND_READY(Layer.EXCHANGE, 'K', Bound.CLIENT, ExchangeSendReadyPacket.class),
	EXCHANGE_MOVE_ITEMS(Layer.EXCHANGE, 'M', 'O', Bound.CLIENT, ExchangeMoveItemsPacket.class),
	EXCHANGE_MOVE_KAMAS(Layer.EXCHANGE, 'M', 'G', Bound.CLIENT, ExchangeMoveKamasPacket.class),
	EXHANGE_BUY_TO_NPC(Layer.EXCHANGE, 'B', Bound.CLIENT, ExchangeBuyToNpcPacket.class),
	EXCHANGE_SELL_TO_NPC(Layer.EXCHANGE, 'S', Bound.CLIENT, ExchangeSellToNpcPacket.class),
	EXHANGE_DISCONNECT_AS_MERCHANT(Layer.EXCHANGE, 'Q', Bound.CLIENT, ExchangeDisconnectAsMerchantPacker.class),
	EXCHANGE_ASK_DISCONNECT_AS_MERCHANT(Layer.EXCHANGE, 'q', Bound.CLIENT, ExchangeAskToDisconnectAsMerchantPacket.class),
	EXCHANGE_ASK_HDV_TYPE(Layer.EXCHANGE, 'H', Bound.CLIENT, ExchangeHdvPacket.class),
	EXCHANGE_GET_CRAFTER_FOR_JOB(Layer.EXCHANGE, 'J', 'F', Bound.CLIENT, ExchangeGetCrafterForJobPacket.class),
	EXCHANGE_MOUNT(Layer.EXCHANGE, 'r', Bound.CLIENT, ExchangeMountPacket.class),
	EXCHANGE_PARK_MOUNT(Layer.EXCHANGE, 'f', Bound.CLIENT, ExchangeParkMountPacket.class),
	EXCHANGE_REPLAY_CRAFT(Layer.EXCHANGE, 'L', Bound.CLIENT, ExchangeReplayCraftPacket.class),
	EXCHANGE_LEAVE(Layer.EXCHANGE, 'V', Bound.CLIENT, ExchangeLeavePacket.class),

	// ZAAP ==========================================================
	// server
	ZAAP_CREATE(Layer.WAYPOINT, 'C', Bound.SERVER, ZaapCreatePacket.class),
	ZAAP_USE_ERROR(Layer.WAYPOINT, 'U', Bound.SERVER, ZaapUseErrorPacket.class),

	// client
	ZAAP_USE(Layer.WAYPOINT, 'U', Bound.CLIENT, ZaapUsePacket.class),
	// both
	ZAAP_LEAVE(Layer.WAYPOINT, 'V', Bound.BOTH, ZaapLeavePacket.class),

	// <<<< END
	;
	public static class State {
		private State() {
		}

		public static final char OK = 'K';
		public static final char ERROR = 'E';
	}

	private static final int SIZE = 'z' - 'A';

	@SuppressWarnings("unchecked")
	private static final ProtocolRegistry[][][][] SERVER_REGISTRY = new ProtocolRegistry[Layer.values().length][SIZE][SIZE + 1][1];
	private static final ProtocolRegistry[][][][] CLIENT_REGISTRY = new ProtocolRegistry[Layer.values().length][SIZE][SIZE + 1][1];

	private static final Map<Class<? extends Packet>, ProtocolRegistry> IDS = new HashMap<>();

	private final Class<? extends Packet> packet;
	private final Layer layer;
	private final char key;
	private final boolean indexAtEnd;
	private final char state;
	private final Bound bound;

	ProtocolRegistry(Layer layer, char key, char state, boolean indexAtEnd, Bound bound, Class<? extends Packet> packet) {
		this.layer = layer;
		this.key = key;
		this.state = state;
		this.indexAtEnd = indexAtEnd;
		this.bound = bound;
		this.packet = packet;
	}

	ProtocolRegistry(Layer layer, char key, char state, Bound bound, Class<? extends Packet> packet) {
		this(layer, key, state, false, bound, packet);
	}

	ProtocolRegistry(Layer layer, char key, boolean indexAtEnd, Bound bound, Class<? extends Packet> packet) {
		this(layer, key, '\0', indexAtEnd, bound, packet);
	}

	ProtocolRegistry(Layer layer, char key, Bound bound, Class<? extends Packet> packet) {
		this(layer, key, false, bound, packet);
	}

	public Layer getLayer() {
		return layer;
	}

	public char getKey() {
		return key;
	}

	public char getState() {
		return state;
	}

	public boolean isIndexAtEnd() {
		return indexAtEnd;
	}

	public String getId() {
		if (state == '\0')
			return "" + layer.getKey() + key;
		else
			return "" + layer.getKey() + key + state;
	}

	public Class<? extends Packet> getPacket() {
		return packet;
	}

	static {
		for (ProtocolRegistry registry : values()) {
			if (registry.bound == Bound.CLIENT || registry.bound == null)
				registerRegistry(CLIENT_REGISTRY, registry);
			if (registry.bound == Bound.SERVER || registry.bound == null)
				registerRegistry(SERVER_REGISTRY, registry);
		}
	}

	private static void registerRegistry(ProtocolRegistry[][][][] curRegistry, ProtocolRegistry registry) {
		int state = registry.state == '\0' ? SIZE : registry.state - 'A';
		ProtocolRegistry[] r = curRegistry[registry.layer.ordinal()][registry.key - 'A'][state];
		if (registry.indexAtEnd && r.length != 2)
			r = Arrays.copyOf(r, 2);

		r[registry.indexAtEnd ? 1 : 0] = registry;

		curRegistry[registry.layer.ordinal()][registry.key - 'A'][state] = r;
		IDS.put(registry.getPacket(), registry);
	}

	public static ProtocolRegistry getRegistry(String id, boolean indexAtEnd, Bound bound) {
		if (id.length() != 3 && id.length() != 2)
			throw new IllegalArgumentException("Packet id must have 3 or 2 char");
		char[] code = id.toCharArray();

		if (code[1] < 'A' || code[1] > 'z')
			return null;
		Layer layer = Layer.valueOf(code[0]);
		if (layer == null)
			return null;

		int state = SIZE;

		if (code.length == 3 && code[2] >= 'A' && code[2] <= 'z')
			state = code[2] - 'A';

		try {
			ProtocolRegistry[][] reg = (bound == Bound.SERVER ? SERVER_REGISTRY : CLIENT_REGISTRY)[layer.ordinal()][code[1] - 'A'];
			ProtocolRegistry[] r = reg[state];
			if (indexAtEnd && (r.length != 2 && reg[SIZE].length != 2))
				return null;
			if (r[indexAtEnd ? 1 : 0] != null)
				return r[indexAtEnd ? 1 : 0];
			else
				return reg[SIZE][indexAtEnd ? 1 : 0];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(code[1]);
			return null;
		}
	}

	public static ProtocolRegistry getRegistry(Class<? extends Packet> packetClass) {
		return IDS.get(packetClass);
	}

	public enum Layer {
		ACCOUNT('A'),
		BASIC('B'),
		CHAT('c'),
		DIALOG('D'),
		EXCHANGE('E'),
		EMOTE('e'),
		FRIEND('F'),
		FIGHT('f'),
		GAME('G'),
		GUILD('g'),
		HOUSE('h'),
		ENEMY('i'),
		HOUSE_CODE('k'),
		OBJECT('O'),
		PARTY('P'),
		MOUNT('R'),
		SPELL('S'),
		HELLO('H'),
		AREA('a'),
		WAYPOINT('W'),
		INFO('I'),
		SPECIALIZATION('Z');

		private final char key;

		Layer(char key) {
			this.key = key;
		}

		public char getKey() {
			return key;
		}

		public static Layer valueOf(char key) {
			for (Layer l : values())
				if (l.getKey() == key)
					return l;
			return null;
		}
	}

	public enum Bound {
		CLIENT("\n\0"),
		SERVER("\0");

		public static final Bound BOTH = null;
		private final String delimiter;

		Bound(String delimiter) {
			this.delimiter = delimiter;
		}

		public String getDelimiter() {
			return delimiter;
		}

		public Bound getOther() {
			return this == CLIENT ? SERVER : CLIENT;
		}
	}
}
