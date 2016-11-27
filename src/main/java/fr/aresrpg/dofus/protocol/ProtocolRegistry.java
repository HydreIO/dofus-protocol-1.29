package fr.aresrpg.dofus.protocol;

import fr.aresrpg.dofus.protocol.account.AccountKeyPacket;
import fr.aresrpg.dofus.protocol.account.AccountRegionalVersionPacket;
import fr.aresrpg.dofus.protocol.account.client.*;
import fr.aresrpg.dofus.protocol.account.server.*;
import fr.aresrpg.dofus.protocol.basic.server.BasicConfirmPacket;
import fr.aresrpg.dofus.protocol.chat.ChatSubscribeChannelPacket;
import fr.aresrpg.dofus.protocol.game.client.*;
import fr.aresrpg.dofus.protocol.game.server.*;
import fr.aresrpg.dofus.protocol.hello.client.HelloGamePacket;
import fr.aresrpg.dofus.protocol.hello.server.HelloConnectionPacket;
import fr.aresrpg.dofus.protocol.info.client.InfoMapPacket;
import fr.aresrpg.dofus.protocol.info.server.message.InfoMessagePacket;
import fr.aresrpg.dofus.protocol.mount.client.PlayerMountPacket;
import fr.aresrpg.dofus.protocol.mount.server.MountXpPacket;
import fr.aresrpg.dofus.protocol.specialization.server.SpecializationSetPacket;

import java.util.*;

public enum ProtocolRegistry {

	GAME_HELLO_GAME(Layer.HELLO, 'G', Bound.SERVER, HelloGamePacket.class),
	GAME_HELLO_CONNECTION(Layer.HELLO, 'C', Bound.SERVER, HelloConnectionPacket.class),

	ACCOUNT_AUTHENTICATION(Layer.ACCOUNT, 'f', true, Bound.CLIENT, AccountAuthPacket.class),
	ACCOUNT_GET_QUEUE_POSITION(Layer.ACCOUNT, 'f', Bound.CLIENT, AccountGetQueuePosition.class),
	ACCOUNT_QUEUE_POSITION(Layer.ACCOUNT, 'f', Bound.SERVER, AccountQueuePosition.class),
	ACCOUNT_LOGIN_ERROR(Layer.ACCOUNT, 'l', State.ERROR, Bound.SERVER, AccountLoginErrPacket.class),
	ACCOUNT_LOGIN_OK(Layer.ACCOUNT, 'l', State.OK, Bound.SERVER, AccountLoginOkPacket.class),
	ACCOUNT_PSEUDO(Layer.ACCOUNT, 'd', Bound.SERVER, AccountPseudoPacket.class),
	ACCOUNT_COMMUNITY(Layer.ACCOUNT, 'c', Bound.SERVER, AccountCommunityPacket.class),
	ACCOUNT_HOST(Layer.ACCOUNT, 'H', Bound.SERVER, AccountHostPacket.class),
	ACCOUNT_QUESTION(Layer.ACCOUNT, 'Q', Bound.SERVER, AccountQuestionPacket.class),
	ACCOUNT_LIST_SERVER(Layer.ACCOUNT, 'x', Bound.CLIENT, AccountListServersPacket.class),
	ACCOUNT_SERVER_LIST(Layer.ACCOUNT, 'x', State.OK, Bound.SERVER, AccountServerListPacket.class),
	ACCOUNT_ACCESS_SERVER(Layer.ACCOUNT, 'X', Bound.CLIENT, AccountAccessServerPacket.class),
	ACCOUNT_SET_CHARACTER(Layer.ACCOUNT, 'S', Bound.CLIENT, AccountSetCharacterPacket.class),
	ACCOUNT_SERVER_ENCRYPTED_HOST(Layer.ACCOUNT, 'X', State.OK, Bound.SERVER, AccountServerEncryptedHostPacket.class),
	ACCOUNT_SERVER_HOST(Layer.ACCOUNT, 'Y', State.OK, Bound.SERVER, AccountServerHostPacket.class),
	ACCOUNT_TICKET(Layer.ACCOUNT, 'T', Bound.SERVER, AccountTicketPacket.class),
	ACCOUNT_TICKET_OK(Layer.ACCOUNT, 'T', State.OK, Bound.SERVER, AccountTicketOkPacket.class),
	ACCOUNT_KEY(Layer.ACCOUNT, 'k', Bound.BOTH, AccountKeyPacket.class),
	ACCOUNT_REGION_VERSION(Layer.ACCOUNT, 'V', Bound.BOTH, AccountRegionalVersionPacket.class),
	ACCOUNT_GET_GIFTS(Layer.ACCOUNT, 'g', Bound.CLIENT, AccountGetGiftsPacket.class),
	ACCOUNT_IDENTITY(Layer.ACCOUNT, 'i', Bound.CLIENT, AccountIdentity.class),
	ACCOUNT_GET_CHARACTERS(Layer.ACCOUNT, 'L', Bound.CLIENT, AccountGetCharactersPacket.class),
	ACCOUNT_LIST_CHARACTERS(Layer.ACCOUNT, 'L', State.OK, Bound.SERVER, AccountCharactersListPacket.class),
	ACCOUNT_SELECT_CHARACTER(Layer.ACCOUNT, 'S', Bound.CLIENT, AccountSelectCharacterPacket.class),
	//ACCOUNT_SELECT_CHARACTER_OK(Layer.ACCOUNT , 'S' , State.OK , Bound.SERVER , AccountSelectCharacterOkPacket.class),

	BASIC_CONFIRM(Layer.BASIC, 'N', Bound.SERVER, BasicConfirmPacket.class),

	MOUNT_XP(Layer.MOUNT, 'x', Bound.SERVER, MountXpPacket.class),

	PLAYER_MOUNT_PACKET(Layer.MOUNT, 'r', Bound.SERVER, PlayerMountPacket.class),

	GAME_GET_EXTRA_INFORMATION(Layer.GAME, 'I', Bound.CLIENT, GameExtraInformationPacket.class),
	GAME_CREATE(Layer.GAME, 'C', Bound.CLIENT, GameCreatePacket.class),
	GAME_MAP_DATA(Layer.GAME, 'D', 'M', Bound.SERVER, GameMapDataPacket.class),
	GAME_JOIN(Layer.GAME, 'J', State.OK, Bound.SERVER, GameJoinPacket.class),
	GAME_END_TURN(Layer.GAME, 't', Bound.CLIENT, GameEndTurnPacket.class),
	GAME_TURN_OK(Layer.GAME, 'T', Bound.CLIENT, GameTurnOkPacket.class),
	GAME_FREE_MY_SOUL(Layer.GAME, 'F', Bound.CLIENT, FreeMySoulPacket.class),
	GAME_SET_PLAYER_POSITION(Layer.GAME, 'p', Bound.CLIENT, GameSetPlayerPositionPacket.class),
	GAME_ON_READY(Layer.GAME, 'R', Bound.SERVER, GameOnReadyPacket.class),
	GAME_START_POSITION_PACKET(Layer.GAME, 'P', Bound.SERVER, GamePositionStartPacket.class),
	GAME_LEAVE(Layer.GAME, 'V', Bound.CLIENT, LeaveGamePacket.class),
	GAME_START(Layer.GAME, 'S', Bound.SERVER, GameStartPacket.class),
	GAME_END(Layer.GAME, 'E', Bound.SERVER, GameEndPacket.class),

	INFO_MESSAGE(Layer.INFO, 'm', Bound.SERVER, InfoMessagePacket.class),
	INFO_MAP(Layer.INFO, 'M', Bound.CLIENT, InfoMapPacket.class),

	CHAT_SUBSCRIBE_CHANNEL(Layer.CHAT , 'C' , Bound.BOTH , ChatSubscribeChannelPacket.class),

	SPECIALIZATION_SET(Layer.SPECIALIZATION, 'S', Bound.SERVER, SpecializationSetPacket.class);

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
	private final Bound bound; // Celui qui reçoit

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

		if (code[1] <= 'A' || code[1] >= 'z')
			return null;
		Layer layer = Layer.valueOf(code[0]);
		if (layer == null)
			return null;

		int state = SIZE;

		if (code.length == 3 && code[2] >= 'A' && code[2] <= 'z')
			state = code[2] - 'A';

		ProtocolRegistry[][] reg = (bound == Bound.SERVER ? SERVER_REGISTRY : CLIENT_REGISTRY)[layer.ordinal()][code[1] - 'A'];
		ProtocolRegistry[] r = reg[state];
		if (indexAtEnd && (r.length != 2 && reg[SIZE].length != 2))
			return null;

		if (r[indexAtEnd ? 1 : 0] != null)
			return r[indexAtEnd ? 1 : 0];
		else
			return reg[SIZE][indexAtEnd ? 1 : 0];
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
		ENVIRONMENT('e'),
		FRIEND('F'),
		FIGHT('f'),
		GAME('G'),
		GUILD('g'),
		HOUSE('h'),
		ENEMY('i'),
		HOUSE_CODE('k'),
		OBJECT('O'),
		GROUP('P'),
		MOUNT('R'),
		SPELL('S'),
		HELLO('H'),
		ALIGNMENT('a'),
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
