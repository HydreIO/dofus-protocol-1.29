package fr.aresrpg.dofus.protocol.exchange.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.ServerPacket;
import fr.aresrpg.dofus.protocol.ServerPacketHandler;
import fr.aresrpg.dofus.structures.Exchange;

import java.util.StringJoiner;

public class ExchangeCreatePacket implements ServerPacket{
	private Exchange type;
	private ExchangeData data;

	@Override
	public void read(DofusStream stream) {
		type = Exchange.valueOf(stream.readInt());
		switch (type){
			case CRAFT:
			case SECURE_CRAFT:
			case SECURE_CRAFT_2:
				String data[] = stream.read().split(";");
				this.data = new CraftExchangeData(Integer.parseInt(data[0]) , Integer.parseInt(data[1]));
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
				for(int i = 0 ; i < jobs.length ; i++)
					jobs[i] = Integer.parseInt(d[i]);
				this.data = new JobsExchangeData(jobs);
				break;
			case BIG_STORE_BUY:
			case BIG_STORE_SELL:
				break;
			case MOUNT_PARK: //TODO
				throw new UnsupportedOperationException("Not yet");
		}
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(2).writeInt(type.ordinal());
		if(data instanceof CraftExchangeData) {
			CraftExchangeData cdata = (CraftExchangeData) data;
			stream.write(cdata.getMaxItem() + ";" + cdata.getSkillId());
		} else if(data instanceof BasicExchangeData)
			stream.writeInt(((BasicExchangeData) data).getEntityId());
		else if(data instanceof JobsExchangeData) {
			JobsExchangeData jdata = (JobsExchangeData) data;
			StringJoiner joiner = new StringJoiner(";");
			for(int j : jdata.getJobs())
				joiner.add(Integer.toString(j));
			stream.write(joiner.toString());
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
		return "ExchangeCreatePacket(" +
				"type=" + type +
				", data=" + data +
				")[" + getId() + ']';
	}

	public interface ExchangeData{}

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
	}

	public static class BasicExchangeData implements ExchangeData {
		private int entityId;

		public BasicExchangeData(int entityId) {
			this.entityId = entityId;
		}

		public int getEntityId() {
			return entityId;
		}

		public void setEntityId(int entityId) {
			this.entityId = entityId;
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
	}

	public static class ShopExchangeData implements ExchangeData {}
}
