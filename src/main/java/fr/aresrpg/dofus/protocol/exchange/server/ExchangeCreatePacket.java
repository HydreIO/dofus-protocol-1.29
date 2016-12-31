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

import java.util.Arrays;
import java.util.StringJoiner;

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
				// TODO
				StringJoiner joiner = new StringJoiner("|");
				while (stream.available() > 0)
					joiner.add(stream.read());
				this.data = new DefaultData(joiner.toString());
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
		} else if (data instanceof DefaultData) {
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
	 * @param success the success to set
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
