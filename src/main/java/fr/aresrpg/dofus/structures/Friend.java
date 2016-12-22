package fr.aresrpg.dofus.structures;

import fr.aresrpg.dofus.util.StringJoiner;

/**
 * 
 * @since
 */
public class Friend {

	private String account;
	private FriendState state;
	private String name;
	private int lvl;
	String guild;
	private int align;
	private int sex;
	private String gfxId;

	public Friend(String account, FriendState state, String name, int lvl, String guild, int align, int sex, String gfxId) {
		this.account = account;
		this.state = state;
		this.name = name;
		this.lvl = lvl;
		this.guild = guild;
		this.align = align;
		this.sex = sex;
		this.gfxId = gfxId;
	}

	public static Friend parse(String data) {
		String[] datas = data.split(";");
		if (datas.length < 2) return null;
		String account = datas[0];
		FriendState state = FriendState.valueOf(datas[1].charAt(0));
		String name = datas[2];
		int lvl = datas[3].equals("?") ? -1 : Integer.parseInt(datas[3]);
		int align = Integer.parseInt(datas[4]);
		String guild = datas[5];
		int sex = Integer.parseInt(datas[6]);
		String gfx = datas[7];
		return new Friend(account, state, name, lvl, guild, align, sex, gfx);
	}

	public String serialize() {
		return new StringJoiner(";").add(account).add(state.getCode() + "").add(name).add(lvl == -1 ? "?" : lvl + "").add(align).add(guild).add(sex).add(gfxId).toString();
	}

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account
	 *            the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @return the state
	 */
	public FriendState getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(FriendState state) {
		this.state = state;
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
	 * @return the align
	 */
	public int getAlign() {
		return align;
	}

	/**
	 * @param align
	 *            the align to set
	 */
	public void setAlign(int align) {
		this.align = align;
	}

	/**
	 * @return the sex
	 */
	public int getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(int sex) {
		this.sex = sex;
	}

	/**
	 * @return the guild
	 */
	public String getGuild() {
		return guild;
	}

	/**
	 * @param guild
	 *            the guild to set
	 */
	public void setGuild(String guild) {
		this.guild = guild;
	}

	/**
	 * @return the gfxId
	 */
	public String getGfxId() {
		return gfxId;
	}

	/**
	 * @param gfxId
	 *            the gfxId to set
	 */
	public void setGfxId(String gfxId) {
		this.gfxId = gfxId;
	}

	@Override
	public String toString() {
		return "Friend [account=" + account + ", state=" + state + ", name=" + name + ", lvl=" + lvl + ", guild=" + guild + ", align=" + align + ", sex=" + sex + ", gfxId=" + gfxId + "]";
	}

	public static enum FriendState {
		IN_SOLO('1'),
		IN_MULTI('2'),
		IN_UNKNOW('?');
		private char code;

		private FriendState(char code) {
			this.code = code;
		}

		/**
		 * @return the code
		 */
		public char getCode() {
			return code;
		}

		public static FriendState valueOf(char code) {
			if (code == '1') return IN_SOLO;
			else if (code == '2') return IN_MULTI;
			else return IN_UNKNOW;
		}
	}

}
