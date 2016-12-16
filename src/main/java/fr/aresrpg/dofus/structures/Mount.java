package fr.aresrpg.dofus.structures;

/**
 * 
 * @since
 */
public class Mount {

	private int id;
	private boolean newborn;
	private int modelId;
	private int chevauchorGfxId;
	private String[] ancestors;
	private String[] capacities;

	public Mount(int model, int chevauchor, boolean newborn) {
		this.newborn = newborn;
		this.modelId = model;
		this.chevauchorGfxId = chevauchor;
	}

	/*
	 * public static Mount parseMount(String data, boolean newborn) {
	 * String[] loc4 = data.split(":");
	 * int loc5 = Integer.parseInt(loc4[1]);
	 * Mount m = new Mount(loc5, -1, newborn);
	 * m.id = Integer.parseInt(loc4[0]);
	 * m.ancestors = loc4[2].split(",");
	 * String[] loc7 = loc4[3].split(",");
	 * for(int loc8=0;loc8<loc7.length;loc8++) {
	 * int loc9 = 0;
	 * try{
	 * loc9=Integer.parseInt(loc7[loc8]);
	 * }catch(Exception e) {}
	 * if(loc9!=0)
	 * }
	 * }
	 */
	/**
	 * @return the newborn
	 */
	public boolean isNewborn() {
		return newborn;
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
	 * @param newborn
	 *            the newborn to set
	 */
	public void setNewborn(boolean newborn) {
		this.newborn = newborn;
	}

	/**
	 * @return the modelId
	 */
	public int getModelId() {
		return modelId;
	}

	/**
	 * @param modelId
	 *            the modelId to set
	 */
	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	/**
	 * @return the chevauchorGfxId
	 */
	public int getChevauchorGfxId() {
		return chevauchorGfxId;
	}

	/**
	 * @param chevauchorGfxId
	 *            the chevauchorGfxId to set
	 */
	public void setChevauchorGfxId(int chevauchorGfxId) {
		this.chevauchorGfxId = chevauchorGfxId;
	}

}
