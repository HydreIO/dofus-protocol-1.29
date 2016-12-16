/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.exchange.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.CraftResult;

/**
 * 
 * @since
 */
public class ExchangeCraftPacket implements ServerPacket {

	private CraftResult result;
	// private int craftedType; // juste pour afficher mais ce n'est pas un item a ajouter dans l'inventaire
	// private String parsedInline;
	private String datas; // c'est bon ce packet ma soulé je renvoi tout

	public ExchangeCraftPacket() {
	}

	/**
	 * @param result
	 * @param datas
	 */
	public ExchangeCraftPacket(CraftResult result, String datas) {
		super();
		this.result = result;
		this.datas = datas;
	}

	/**
	 * @return the result
	 */
	public CraftResult getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(CraftResult result) {
		this.result = result;
	}

	/**
	 * @return the datas
	 */
	public String getDatas() {
		return datas;
	}

	/**
	 * @param datas
	 *            the datas to set
	 */
	public void setDatas(String datas) {
		this.datas = datas;
	}

	@Override
	public void read(DofusStream stream) {
		this.datas = stream.read();
		// boolean success = data.charAt(0) == 'K' // useless
		this.result = CraftResult.valueOf(datas.charAt(1));
		/*
		 * String[] datas = data.substring(2).split(";");
		 * if (datas.length == 1)
		 * this.craftedType = Integer.parseInt(datas[0]);
		 * else {
		 * String loc9 = datas[1].substring(0, 1);
		 * String loc10 = datas[1].substring(1);
		 * int loc11 = Integer.parseInt(datas[0]);
		 * int loc12 = Integer.parseInt(datas[2]);
		 * int[] loc13 = { loc11, loc12 };
		 * switch (loc9) {
		 * case "T": // success target
		 * this.parsedInline = parseInlineItem(loc10, loc13);
		 * break;
		 * case "B": // success other
		 * this.parsedInline = parseInlineItem(loc10, loc13);
		 * default:
		 * break;
		 * }
		 * }
		 * }
		 * private String parseInlineItem(String msg, int[] array) {
		 * for(int loc4 = 0;loc4<array.length;loc4+=2) {
		 * int loc5 = array[loc4];
		 * int loc6 = array[loc4+1];
		 * Item loc7 = new Item(0, loc5, 1, 1, loc6)
		 * }
		 * }
		 */
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(datas); // ntm ya pas ltemps
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "ExchangeCraftPacket [result=" + result + ", datas=" + datas + "]";
	}

}
