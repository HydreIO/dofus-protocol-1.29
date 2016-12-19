package fr.aresrpg.dofus.structures.game;

import fr.aresrpg.dofus.util.StringJoiner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 
 * @since
 */
public class FightDetail {

	private String name;
	private int lvl;
	private int type;

	/**
	 * @param name
	 * @param lvl
	 * @param type
	 */
	public FightDetail(String name, int lvl, int type) {
		super();
		this.name = name;
		this.lvl = lvl;
		this.type = type;
	}

	public static FightDetail parse(String data) {
		String[] datas = data.split("~");
		return new FightDetail(datas[0], Integer.parseInt(datas[1]), Integer.parseInt(datas[2]));
	}

	public String serialize() {
		return new StringJoiner("~").add(name).add(lvl).add(type).toString();
	}

	public static String serializeFew(List<FightDetail> details) {
		StringJoiner joiner = new StringJoiner(";");
		details.forEach(d -> joiner.add(d.serialize()));
		return joiner.toString();
	}

	public static List<FightDetail> parseFew(String data) {
		String[] datas = data.split(";", -1);
		List<FightDetail> details = new ArrayList<>();
		IntStream.range(0, datas.length).forEach(i -> {
			String d = datas[i];
			if (!d.isEmpty()) details.add(parse(d));
		});
		return details;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the lvl
	 */
	public int getLvl() {
		return lvl;
	}

	/**
	 * @param lvl
	 *            the lvl to set
	 */
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "FightDetail [name=" + name + ", lvl=" + lvl + ", type=" + type + "]";
	}

}
