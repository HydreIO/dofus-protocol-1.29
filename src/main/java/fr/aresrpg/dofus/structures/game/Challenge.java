package fr.aresrpg.dofus.structures.game;

/**
 * 
 * @since
 */
public enum Challenge {

	ABNEGATION(43),
	ANACHORETE(39),
	ARAKNOPHILE(15),
	BARBARE(9),
	BLITZKRIEG(38),
	BORNE(24),
	CASINO_ROYAL(14),
	CHACUN_SON_MONSTRE(46),
	CIRCULEZ(21),
	COLLANT(37),
	CONTAMINATION(47),
	CRUEL(10),
	DESIGNE_VOLONTAIRE(3),
	DEUX_POUR_LE_PRIX_D_UN(42),
	DUEL(45),
	ECONOME(5),
	ELEMENTAIRE(20),
	ELITISTE(32),
	FOCUS(31),
	FOSSOYEUR(12),
	HARDI(36),
	IMPREVISIBLE(34),
	INCURABLE(18),
	INTOUCHABLE(17),
	JARDINIER(7),
	LE_CHEAT_DES_DEVS(50),
	LES_MULES_D_ABORD(48),
	LES_PETITS_D_ABORD(30),
	LE_TEMPS_QUI_COURT(22),
	MAIN_PROPRES(19),
	MYSTIQUE(11),
	NI_PIOU_NI_SOUMIS(29),
	NI_PIOUTES_NI_SOUMISES(28),
	NOMADE(8),
	ORDONNE(25),
	PARTAGE(44),
	PERDU_DE_VU(23),
	PROTEGER_VOS_MULES(49),
	PETULANT(41),
	PUSILLANIME(40),
	STATUE(2),
	SURSIS(4),
	SURVIVANT(33),
	TUEUR_A_GAGE(35),
	VERSATILE(6),
	ZOMBIE(1);

	private int id;

	private Challenge(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	public static Challenge fromId(int id) {
		for (Challenge c : values())
			if (c.getId() == id) return c;
		return null;
	}

}
