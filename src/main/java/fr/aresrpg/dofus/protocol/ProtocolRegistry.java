package fr.aresrpg.dofus.protocol;

import fr.aresrpg.dofus.protocol.server.HelloConnectionPacket;
import fr.aresrpg.dofus.protocol.server.HelloGamePacket;

import java.util.BitSet;

public enum ProtocolRegistry {
	GAME_HELLO_GAME("HG" , HelloGamePacket.class),
	GAME_HELLO_CONNECTION("HC" ,HelloConnectionPacket.class);


	private static final int SIZE = 'z' - 'A';

	@SuppressWarnings("unchecked")
	private static final ProtocolRegistry[][][] REGISTRY = new ProtocolRegistry[SIZE][SIZE][SIZE + 1];

	private final String id;
	private final Class<? extends Packet> packet;

	ProtocolRegistry(String id, Class<? extends Packet> packet) {
		if(id.length() != 3 && id.length() != 2)
			throw new IllegalArgumentException("Packet id must have 3 or 2 char");
		this.id = id;
		this.packet = packet;
	}

	public String getId() {
		return id;
	}

	public Class<? extends Packet> getPacket() {
		return packet;
	}

	static {
		for(ProtocolRegistry registry : values()){
			char[] code = registry.getId().toCharArray();
			int index = code.length == 2 ? SIZE : code[2] - 'A';
			REGISTRY[code[0] - 'A'][code[1] - 'A'][index] = registry;
		}
	}

	public static ProtocolRegistry getPacket(String id) {
		if(id.length() != 3 && id.length() != 2)
			throw new IllegalArgumentException("Packet id must have 3 or 2 char");
		char[] code = id.toCharArray();
		int index = code.length == 2 ? SIZE : code[2]  - 'A';
		ProtocolRegistry first = REGISTRY[code[0] - 'A'][code[1] - 'A'][index];
		return first == null ? REGISTRY[code[0] - 'A'][code[1] - 'A'][SIZE] : first;
	}

}
