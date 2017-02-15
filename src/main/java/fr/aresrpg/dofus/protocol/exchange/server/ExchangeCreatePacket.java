/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.exchange.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.Exchange;
import fr.aresrpg.dofus.structures.Skills;
import fr.aresrpg.dofus.structures.item.ItemCategory;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class ExchangeCreatePacket implements ServerPacket {
	private Exchange type;
	private ExchangeData data;
	private boolean success;

	@Override
	public void read(DofusStream stream) {
		this.setSuccess(stream.peek().charAt(0) == 'K');
		String datas = stream.read().substring(1);
		type = Exchange.valueOf(Integer.parseInt(datas));
		switch (type) {
			case CRAFT:
			case SECURE_CRAFT:
			case SECURE_CRAFT_2:
				String data[] = stream.read().split(";");
				this.data = new CraftExchangeData(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
				break;
			case EXCHANGE_2:
			case EXCHANGE_3:
			case EXCHANGE_4:
			case EXCHANGE_5:
			case NPC_SHOP:
			case PLAYER_SHOP:
			case TAX_COLLECTOR_STORAGE:
				this.data = new BasicExchangeData(Integer.parseInt(stream.read()));
				break;
			case CRAFTER_LIST:
				String d[] = stream.read().split(";");
				int[] jobs = new int[d.length];
				for (int i = 0; i < jobs.length; i++)
					jobs[i] = Integer.parseInt(d[i]);
				this.data = new JobsExchangeData(jobs);
				break;
			case BIG_STORE_BUY:
			case BIG_STORE_SELL:
				this.data = BigExchangeData.parse(stream.read().split(";"));
				break;
			case MOUNT_PARK:
				// TODO
				StringJoiner joiner2 = new StringJoiner("|");
				while (stream.available() > 0)
					joiner2.add(stream.read());
				this.data = new DefaultData(joiner2.toString());
				break;
		}
	}

	@Override
	public void write(DofusStream stream) {
		if (data == null) {
			stream.allocate(1).write((isSuccess() ? "K" : "E") + type.getCode());
			return;
		}
		stream.allocate(2).write((isSuccess() ? "K" : "E") + type.getCode());
		if (data instanceof CraftExchangeData) {
			CraftExchangeData cdata = (CraftExchangeData) data;
			stream.write(cdata.getMaxItem() + ";" + cdata.getSkillId());
		} else if (data instanceof BasicExchangeData)
			stream.writeLong(((BasicExchangeData) data).getEntityId());
		else if (data instanceof JobsExchangeData) {
			JobsExchangeData jdata = (JobsExchangeData) data;
			StringJoiner joiner = new StringJoiner(";");
			for (int j : jdata.getJobs())
				joiner.add(Integer.toString(j));
			stream.write(joiner.toString());
		} else if (data instanceof BigExchangeData) stream.write(((BigExchangeData) data).serialize());
		else if (data instanceof DefaultData) {
			DefaultData ddata = (DefaultData) data;
			stream.write(((DefaultData) data).getDatas());
		}
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	public Exchange getType() {
		return type;
	}

	public ExchangeData getData() {
		return data;
	}

	public ExchangeCreatePacket setData(ExchangeData data) {
		this.data = data;
		return this;
	}

	public ExchangeCreatePacket setType(Exchange type) {
		this.type = type;
		return this;
	}

	@Override
	public String toString() {
		return "ExchangeCreatePacket [type=" + type + ", data=" + data + ", success=" + isSuccess() + "]";
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public interface ExchangeData {}

	public static class DefaultData implements ExchangeData {
		private String datas;

		/**
		 * @param datas
		 */
		public DefaultData(String datas) {
			this.datas = datas;
		}

		/**
		 * @return the datas
		 */
		public String getDatas() {
			return datas;
		}

		@Override
		public String toString() {
			return "DefaultData [datas=" + datas + "]";
		}

	}

	public static class BigExchangeData implements ExchangeData {
		private int quantity1, quantity2, quantity3, maxLevel, maxItemCount, npcID, maxSellTime;
		private double taxe;
		private ItemCategory[] categories;

		public BigExchangeData(int quantity1, int quantity2, int quantity3, ItemCategory[] types, double tax, int maxLevel, int maxItemCount, int npcID, int maxSellTime) {
			this.quantity1 = quantity1;
			this.quantity2 = quantity2;
			this.quantity3 = quantity3;
			this.categories = types;
			this.taxe = tax;
			this.maxLevel = maxLevel;
			this.maxItemCount = maxItemCount;
			this.npcID = npcID;
			this.maxSellTime = maxSellTime;
		}
		// [1,10,100 ; 38,95,96,98,108 ; 2.0 ; 1000 ; 20 ; -1 ; 350]

		public static BigExchangeData parse(String[] datas) {
			String[] l14 = datas[0].split(",");
			int q1 = Integer.parseInt(l14[0]);
			int q2 = Integer.parseInt(l14[1]);
			int q3 = Integer.parseInt(l14[2]);
			ItemCategory[] tp = ItemCategory.makeArray(Arrays.stream(datas[1].split(",")).mapToInt(Integer::parseInt).toArray());
			double tx = Double.parseDouble(datas[2]);
			int ml = Integer.parseInt(datas[3]);
			int mi = Integer.parseInt(datas[4]);
			int npc = Integer.parseInt(datas[5]);
			int ms = Integer.parseInt(datas[6]);
			return new BigExchangeData(q1, q2, q3, tp, tx, ml, mi, npc, ms);
		}

		public String serialize() {
			return new fr.aresrpg.dofus.util.StringJoiner(";")
					.add(
							new fr.aresrpg.dofus.util.StringJoiner(",")
									.add(quantity1)
									.add(quantity2)
									.add(quantity3))
					.add(Arrays.stream(categories).map(ItemCategory::getValue).map(String::valueOf).collect(Collectors.joining(",")))
					.add(taxe)
					.add(maxLevel)
					.add(maxItemCount)
					.add(npcID)
					.add(maxSellTime)
					.toString();
		}

		public int getQuantity1() {
			return quantity1;
		}

		public int getQuantity2() {
			return quantity2;
		}

		public int getQuantity3() {
			return quantity3;
		}

		public int getMaxLevel() {
			return maxLevel;
		}

		public int getMaxItemCount() {
			return maxItemCount;
		}

		public int getNpcID() {
			return npcID;
		}

		public int getMaxSellTime() {
			return maxSellTime;
		}

		public double getTaxe() {
			return taxe;
		}

		public ItemCategory[] getCategories() {
			return categories;
		}

		@Override
		public String toString() {
			return "BigExchangeData [quantity1=" + quantity1 + ", quantity2=" + quantity2 + ", quantity3=" + quantity3 + ", maxLevel=" + maxLevel + ", maxItemCount=" + maxItemCount + ", npcID="
					+ npcID + ", maxSellTime=" + maxSellTime + ", taxe=" + taxe + ", categories=" + Arrays.toString(categories) + "]";
		}

	}

	public static class CraftExchangeData implements ExchangeData {
		private int maxItem;
		private int skillId;

		public CraftExchangeData(int maxItem, int skillId) {
			this.maxItem = maxItem;
			this.skillId = skillId;
		}

		public int getMaxItem() {
			return maxItem;
		}

		public int getSkillId() {
			return skillId;
		}

		@Override
		public String toString() {
			return "CraftExchangeData [maxItem=" + maxItem + ", skill=" + Skills.valueOf(skillId) + "]";
		}

	}

	public static class BasicExchangeData implements ExchangeData {
		private long entityId;

		public BasicExchangeData(long entityId) {
			this.entityId = entityId;
		}

		public long getEntityId() {
			return entityId;
		}

		public void setEntityId(long entityId) {
			this.entityId = entityId;
		}

		@Override
		public String toString() {
			return "BasicExchangeData [entityId=" + entityId + "]";
		}
	}

	public static class JobsExchangeData implements ExchangeData {
		private int[] jobs;

		public JobsExchangeData(int[] jobs) {
			this.jobs = jobs;
		}

		public int[] getJobs() {
			return jobs;
		}

		public void setJobs(int[] jobs) {
			this.jobs = jobs;
		}

		@Override
		public String toString() {
			return "JobsExchangeData [jobs=" + Arrays.toString(jobs) + "]";
		}

	}

	public static class ShopExchangeData implements ExchangeData {

		@Override
		public String toString() {
			return "ShopExchangeData []";
		}
	}
}
