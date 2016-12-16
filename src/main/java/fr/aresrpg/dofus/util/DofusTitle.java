/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.util;

/**
 * 
 * @since
 */
public class DofusTitle {

	private int id;
	private String param;

	/**
	 * @param id
	 * @param param
	 */
	public DofusTitle(int id, String param) {
		this.id = id;
		this.param = param;
	}

	/**
	 * @return the id
	 */
	public int getId() {
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
	 * @return the param
	 */
	public String getParam() {
		return param;
	}

	@Override
	public String toString() {
		return "DofusTitle [id=" + id + ", param=" + param + "]";
	}

	/**
	 * @param param
	 *            the param to set
	 */
	public void setParam(String param) {
		this.param = param;
	}

}
