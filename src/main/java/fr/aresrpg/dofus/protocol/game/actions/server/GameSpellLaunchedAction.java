package fr.aresrpg.dofus.protocol.game.actions.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.game.actions.GameAction;

/**
 * 
 * @since
 */
public class GameSpellLaunchedAction implements GameAction {

	private int spellId;
	private int cellId;
	private String sprite;
	private int lvl;
	private int visual;
	private String anim;
	private boolean inFrontOfSprite;

	public GameSpellLaunchedAction() {
	}

	public GameSpellLaunchedAction(int spellId, int cellId, String sprite, int lvl, int visual, String anim, boolean inFrontOfSprite) {
		this.spellId = spellId;
		this.cellId = cellId;
		this.sprite = sprite;
		this.lvl = lvl;
		this.visual = visual;
		this.anim = anim;
		this.inFrontOfSprite = inFrontOfSprite;
	}

	@Override
	public void read(DofusStream stream) {
		String[] datas = stream.read().split(",", -1);
		this.spellId = Integer.parseInt(datas[0]);
		this.cellId = Integer.parseInt(datas[1]);
		this.sprite = datas[2];
		this.lvl = Integer.parseInt(datas[3]);
		this.visual = Integer.parseInt(datas[4]);
		this.anim = datas.length > 5 ? datas[5] : null;
		this.inFrontOfSprite = datas.length > 6 ? "1".equals(datas[6]) : false;
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(spellId + "," + cellId + "," + sprite + "," + lvl + "," + visual + (anim != null ? "," + anim : ",") + (inFrontOfSprite ? ",1" : ",0"));
	}

	/**
	 * @return the spellId
	 */
	public int getSpellId() {
		return spellId;
	}

	/**
	 * @param spellId
	 *            the spellId to set
	 */
	public void setSpellId(int spellId) {
		this.spellId = spellId;
	}

	/**
	 * @return the cellId
	 */
	public int getCellId() {
		return cellId;
	}

	/**
	 * @param cellId
	 *            the cellId to set
	 */
	public void setCellId(int cellId) {
		this.cellId = cellId;
	}

	/**
	 * @return the sprite
	 */
	public String getSprite() {
		return sprite;
	}

	/**
	 * @param sprite
	 *            the sprite to set
	 */
	public void setSprite(String sprite) {
		this.sprite = sprite;
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
	 * @return the visual
	 */
	public int getVisual() {
		return visual;
	}

	/**
	 * @param visual
	 *            the visual to set
	 */
	public void setVisual(int visual) {
		this.visual = visual;
	}

	/**
	 * @return the anim
	 */
	public String getAnim() {
		return anim;
	}

	/**
	 * @param anim
	 *            the anim to set
	 */
	public void setAnim(String anim) {
		this.anim = anim;
	}

	/**
	 * @return the inFrontOfSprite
	 */
	public boolean isInFrontOfSprite() {
		return inFrontOfSprite;
	}

	/**
	 * @param inFrontOfSprite
	 *            the inFrontOfSprite to set
	 */
	public void setInFrontOfSprite(boolean inFrontOfSprite) {
		this.inFrontOfSprite = inFrontOfSprite;
	}

	@Override
	public String toString() {
		return "GameSpellLaunchedAction [spellId=" + spellId + ", cellId=" + cellId + ", sprite=" + sprite + ", lvl=" + lvl + ", visual=" + visual + ", anim=" + anim + ", inFrontOfSprite="
				+ inFrontOfSprite + "]";
	}

}
