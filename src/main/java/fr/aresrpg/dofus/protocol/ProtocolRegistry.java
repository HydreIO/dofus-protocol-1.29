package fr.aresrpg.dofus.protocol;

import fr.aresrpg.dofus.protocol.client.AccountAuthPacket;
import fr.aresrpg.dofus.protocol.client.AccountSelectServerPacket;
import fr.aresrpg.dofus.protocol.server.account.*;
import fr.aresrpg.dofus.protocol.server.hello.HelloConnectionPacket;
import fr.aresrpg.dofus.protocol.server.hello.HelloGamePacket;

import java.util.HashMap;
import java.util.Map;

public enum ProtocolRegistry {

	GAME_HELLO_GAME(Layer.HELLO , 'G', HelloGamePacket.class),
	GAME_HELLO_CONNECTION(Layer.HELLO , 'C', HelloConnectionPacket.class),
	AUTHENTICATION(Layer.ACCOUNT , 'f' , true , AccountAuthPacket.class),
	ACCOUNT_LOGIN_ERROR(Layer.ACCOUNT , 'l' , State.ERROR , AccountLoginErrPacket.class),
	ACCOUNT_LOGIN_OK(Layer.ACCOUNT , 'l' , State.OK , AccountLoginOkPacket.class),
	ACCOUNT_NAME(Layer.ACCOUNT , 'd' , AccountNamePacket.class),
	ACCOUNT_COMMUNITY(Layer.ACCOUNT , 'c' , AccountCommunityPacket.class),
	ACCOUNT_HOST(Layer.ACCOUNT , 'H' , AccountHostPacket.class),
	ACCOUNT_QUESTION(Layer.ACCOUNT , 'Q' , AccountQuestionPacket.class),
	ACCOUNT_SELECT_SERVER(Layer.ACCOUNT , 'x' , AccountSelectServerPacket.class);

	private static final int SIZE = 'z' - 'A';

	@SuppressWarnings("unchecked")
	private static final ProtocolRegistry[][][] REGISTRY = new ProtocolRegistry[Layer.values().length][SIZE][State.values().length];
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
			REGISTRY[registry.getLayer().ordinal()]
					[registry.getKey() - 'A']
					[registry.getState().ordinal()] = registry;
			IDS.put(registry.getPacket() , registry);
		}
	}

	public static ProtocolRegistry getRegistry(String id) {
		if(id.length() != 3 && id.length() != 2)
			throw new IllegalArgumentException("Packet id must have 3 or 2 char");
		char[] code = id.toCharArray();

		Layer layer = Layer.valueOf(code[0]);
		if(layer == null)
			return null;

		State state = code.length == 2 ? State.NO_STATE : State.valueOf(code[2]);

		return REGISTRY[layer.ordinal()][code[1] - 'A'][state.ordinal()];
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
