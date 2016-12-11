package fr.aresrpg.dofus.structures.item;

/**
 * 
 * @since
 */
public class ObjectTemplate {

	private int ID;
	private String StrTemplate;
	private String name;
	private int type;
	private int level;
	private int pod;
	private int prix;
	private int panopID;
	private String conditions;
	private int PACost, POmin, POmax, TauxCC, TauxEC, BonusCC;
	private boolean isTwoHanded;
	private long sold;
	private int avgPrice;

	public ObjectTemplate(int id, String strTemplate, String name, int type, int level, int pod, int prix, int panopID, String conditions, String armesInfos, int sold, int avgPrice) {
		this.ID = id;
		this.StrTemplate = strTemplate;
		this.name = name;
		this.type = type;
		this.level = level;
		this.pod = pod;
		this.prix = prix;
		this.panopID = panopID;
		this.conditions = conditions;
		this.PACost = -1;
		this.POmin = 1;
		this.POmax = 1;
		this.TauxCC = 100;
		this.TauxEC = 2;
		this.BonusCC = 0;
		this.sold = sold;
		this.avgPrice = avgPrice;
		String[] infos = armesInfos.split(";");
		PACost = Integer.parseInt(infos[0]);
		POmin = Integer.parseInt(infos[1]);
		POmax = Integer.parseInt(infos[2]);
		TauxCC = Integer.parseInt(infos[3]);
		TauxEC = Integer.parseInt(infos[4]);
		BonusCC = Integer.parseInt(infos[5]);
		isTwoHanded = infos[6].equals("1");
	}

	/**
	 * @return the avgPrice
	 */
	public int getAvgPrice() {
		return avgPrice;
	}

	/**
	 * @return the bonusCC
	 */
	public int getBonusCC() {
		return BonusCC;
	}

	/**
	 * @return the conditions
	 */
	public String getConditions() {
		return conditions;
	}

	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the pACost
	 */
	public int getPACost() {
		return PACost;
	}

	/**
	 * @return the panopID
	 */
	public int getPanopID() {
		return panopID;
	}

	/**
	 * @return the pod
	 */
	public int getPod() {
		return pod;
	}

	/**
	 * @return the pOmax
	 */
	public int getPOmax() {
		return POmax;
	}

	/**
	 * @return the pOmin
	 */
	public int getPOmin() {
		return POmin;
	}

	/**
	 * @return the prix
	 */
	public int getPrix() {
		return prix;
	}

	/**
	 * @return the sold
	 */
	public long getSold() {
		return sold;
	}

	/**
	 * @return the strTemplate
	 */
	public String getStrTemplate() {
		return StrTemplate;
	}

	/**
	 * @return the tauxCC
	 */
	public int getTauxCC() {
		return TauxCC;
	}

	/**
	 * @return the tauxEC
	 */
	public int getTauxEC() {
		return TauxEC;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
}
