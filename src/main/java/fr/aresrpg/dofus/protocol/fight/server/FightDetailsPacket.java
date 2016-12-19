package fr.aresrpg.dofus.protocol.fight.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.game.FightDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @since
 */
public class FightDetailsPacket implements ServerPacket {

	private int id;
	private List<FightDetail> t0 = new ArrayList<>();
	private List<FightDetail> t1 = new ArrayList<>();

	@Override
	public void read(DofusStream stream) {
		this.id = stream.readInt();
		this.t0 = FightDetail.parseFew(stream.read());
		this.t1 = FightDetail.parseFew(stream.read());
	}

	/**
	 * @return the id
	 */
	public int getDetailsId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the t0
	 */
	public List<FightDetail> getT0() {
		return t0;
	}

	/**
	 * @param t0
	 *            the t0 to set
	 */
	public void setT0(List<FightDetail> t0) {
		this.t0 = t0;
	}

	/**
	 * @return the t1
	 */
	public List<FightDetail> getT1() {
		return t1;
	}

	/**
	 * @param t1
	 *            the t1 to set
	 */
	public void setT1(List<FightDetail> t1) {
		this.t1 = t1;
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(3).writeInt(id).write(FightDetail.serializeFew(t0)).write(FightDetail.serializeFew(t1));
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "FightDetailsPacket [id=" + id + ", t0=" + t0 + ", t1=" + t1 + "]";
	}
}
