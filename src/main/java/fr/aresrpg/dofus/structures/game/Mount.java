/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.structures.game;

/**
 * 
 * @since
 */
public class Mount {

	private int color1;
	private int color2;
	private int color3;

	public Mount(int color1, int color2, int color3) {
		this.color1 = color1;
		this.color2 = color2;
		this.color3 = color3;
	}

	/**
	 * @return the color1
	 */
	public int getColor1() {
		return color1;
	}

	/**
	 * @return the color2
	 */
	public int getColor2() {
		return color2;
	}

	/**
	 * @return the color3
	 */
	public int getColor3() {
		return color3;
	}

}
