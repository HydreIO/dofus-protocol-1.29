/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.spell.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.ServerPacket;
import fr.aresrpg.dofus.protocol.ServerPacketHandler;

public class SpellChangeOptionPacket implements ServerPacket {
	private boolean canUseAllSpell;

	@Override
	public void read(DofusStream stream) {
		canUseAllSpell = stream.read().equals("+");
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(canUseAllSpell ? "+" : "-");
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	public boolean canUseAllSpell() {
		return canUseAllSpell;
	}

	public SpellChangeOptionPacket setCanUseAllSpell(boolean canUseAllSpell) {
		this.canUseAllSpell = canUseAllSpell;
		return this;
	}

	@Override
	public String toString() {
		return "SpellChangeOptionPacket{" +
				"canUseAllSpell=" + canUseAllSpell +
				'}';
	}
}
