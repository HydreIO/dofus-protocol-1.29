package fr.aresrpg.dofus.protocol;

import fr.aresrpg.dofus.protocol.account.AccountKeyPacket;
import fr.aresrpg.dofus.protocol.account.AccountRegionalVersionPacket;
import fr.aresrpg.dofus.protocol.account.server.*;
import fr.aresrpg.dofus.protocol.account.client.*;
import fr.aresrpg.dofus.protocol.basic.server.BasicConfirmPacket;
import fr.aresrpg.dofus.protocol.hello.server.HelloConnectionPacket;
import fr.aresrpg.dofus.protocol.hello.client.HelloGamePacket;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum ProtocolRegistry {

	GAME_HELLO_GAME(Layer.HELLO , 'G', HelloGamePacket.class),
	GAME_HELLO_CONNECTION(Layer.HELLO , 'C', HelloConnectionPacket.class),

	ACCOUNT_AUTHENTICATION(Layer.ACCOUNT , 'f' , true , AccountAuthPacket.class),
	ACCOUNT_QUEUE_POSITION(Layer.ACCOUNT , 'f' , AccountGetQueuePosition.class),
	ACCOUNT_LOGIN_ERROR(Layer.ACCOUNT , 'l' , State.ERROR , AccountLoginErrPacket.class),
	ACCOUNT_LOGIN_OK(Layer.ACCOUNT , 'l' , State.OK , AccountLoginOkPacket.class),
	ACCOUNT_PSEUDO(Layer.ACCOUNT , 'd' , AccountPseudoPacket.class),
	ACCOUNT_COMMUNITY(Layer.ACCOUNT , 'c' , AccountCommunityPacket.class),
	ACCOUNT_HOST(Layer.ACCOUNT , 'H' , AccountHostPacket.class),
	ACCOUNT_QUESTION(Layer.ACCOUNT , 'Q' , AccountQuestionPacket.class),
	ACCOUNT_LIST_SERVER(Layer.ACCOUNT , 'x' , AccountListServersPacket.class),
	ACCOUNT_SERVER_LIST(Layer.ACCOUNT , 'x' , State.OK , AccountServerListPacket.class),
	ACCOUNT_ACCESS_SERVER(Layer.ACCOUNT , 'X' , AccountAccessServerPacket.class),
	ACCOUNT_SET_CHARACTER(Layer.ACCOUNT , 'S' , AccountSetCharacterPacket.class),
	ACCOUNT_SERVER_ENCRYPTED_HOST(Layer.ACCOUNT , 'X' , State.OK , AccountServerEncryptedHostPacket.class),
	ACCOUNT_SERVER_HOST(Layer.ACCOUNT , 'Y' , State.OK , AccountServerHostPacket.class),
	ACCOUNT_TICKET(Layer.ACCOUNT , 'T' , AccountTicketPacket.class),
	ACCOUNT_TICKET_OK(Layer.ACCOUNT , 'T' , State.OK , AccountTicketOkPacket.class),
	ACCOUNT_KEY(Layer.ACCOUNT , 'k' , AccountKeyPacket.class),
	ACCOUNT_REGION_VERSION(Layer.ACCOUNT , 'V' , AccountRegionalVersionPacket.class),
	ACCOUNT_GET_GIFTS(Layer.ACCOUNT , 'g' , AccountGetGiftsPacket.class),
	ACCOUNT_IDENTITY(Layer.ACCOUNT , 'i' , AccountIdentity.class),
	ACCOUNT_GET_CHARACTERS(Layer.ACCOUNT , 'L' , AccountGetCharactersPacket.class),
	ACCOUNT_LIST_CHARACTERS(Layer.ACCOUNT , 'L' , State.OK , AccountCharactersListPacket.class),
	ACCOUNT_SELECT_CHARACTER(Layer.ACCOUNT , 'S' , AccountSelectCharacterPacket.class),

	BASIC_CONFIRM(Layer.BASIC , 'N' , BasicConfirmPacket.class);


	private static final int SIZE = 'z' - 'A';

	@SuppressWarnings("unchecked")
	private static final ProtocolRegistry[][][][] REGISTRY = new ProtocolRegistry[Layer.values().length][SIZE][State.values().length][1];
	private static final Map<Class<? extends Packet> , ProtocolRegistry> IDS = new HashMap<>();

	private final Class<? extends Packet> packet;
	private final Layer layer;
	private final char key;
	private final boolean indexAtEnd;
	private final State state;

	ProtocolRegistry(Layer layer, char key , State state , boolean indexAtEnd , Class<? extends Packet> packet) {
		this.layer = layer;
		this.key = key;
		this.state = state;
		this.indexAtEnd = indexAtEnd;
		this.packet = packet;
	}

	ProtocolRegistry(Layer layer, char key , State state , Class<? extends Packet> packet) {
		this(layer , key , state , false , packet);
	}

	ProtocolRegistry(Layer layer, char key , boolean indexAtEnd , Class<? extends Packet> packet) {
		this(layer , key , State.NO_STATE ,indexAtEnd , packet);
	}

	ProtocolRegistry(Layer layer, char key , Class<? extends Packet> packet) {
		this(layer , key , false , packet);
	}

	public Layer getLayer() {
		return layer;
	}

	public char getKey() {
		return key;
	}

	public State getState() {
		return state;
	}

	public boolean isIndexAtEnd() {
		return indexAtEnd;
	}

	public String getId() {
		if(state.getKey() == '\0')
			return "" + layer.getKey() + key;
		else
		return "" + layer.getKey() + key + state.getKey();
	}

	public Class<? extends Packet> getPacket() {
		return packet;
	}

	static {
		for(ProtocolRegistry registry : values()){
			ProtocolRegistry[] r = REGISTRY[registry.layer.ordinal()]
					[registry.key - 'A']
					[registry.state.ordinal()];
			if(registry.indexAtEnd && r.length != 2)
				r = Arrays.copyOf(r , 2);

			r[registry.indexAtEnd ? 1 : 0] = registry;

			REGISTRY[registry.layer.ordinal()]
					[registry.key - 'A']
					[registry.state.ordinal()] = r;
			IDS.put(registry.getPacket() , registry);
		}
	}

	public static ProtocolRegistry getRegistry(String id, boolean indexAtEnd) {
		if(id.length() != 3 && id.length() != 2)
			throw new IllegalArgumentException("Packet id must have 3 or 2 char");
		char[] code = id.toCharArray();

		Layer layer = Layer.valueOf(code[0]);
		if(layer == null)
			return null;

		State state = code.length == 2 ? State.NO_STATE : State.valueOf(code[2]);

		ProtocolRegistry[] r = REGISTRY[layer.ordinal()][code[1] - 'A'][state.ordinal()];

		if (indexAtEnd && r.length != 2)
			return null;
		return r[indexAtEnd ? 1 : 0];
	}

	public static ProtocolRegistry getRegistry(Class<? extends Packet> packetClass) {
		return IDS.get(packetClass);
	}

	public enum Layer {
		ACCOUNT('A'),
		BASIC('B'),
		CHANNEL('c'),
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
		WAYPOINT('W');

		private final char key;

		Layer(char key) {
			this.key = key;
		}

		public char getKey() {
			return key;
		}

		public static Layer valueOf(char key) {
			for(Layer l : values())
				if(l.getKey() == key)
					return l;
			return null;
		}
	}

	public enum State {
		OK('K'),
		ERROR('E'),
		NO_STATE('\0');

		private final char key;

		State(char key) {
			this.key = key;
		}

		public char getKey() {
			return key;
		}

		public static State valueOf(char key) {
			for(State s : values())
				if(s.getKey() == key)
					return s;
			return NO_STATE;
		}
	}
}
