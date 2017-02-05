package fr.aresrpg.dofus.structures;

import fr.aresrpg.dofus.structures.item.DofusItems;
import fr.aresrpg.dofus.structures.item.Interractable;

/**
 * 
 * @since
 */
public enum Skills {
	ACHETER_MAISON(97, 1),
	UTILISER_ENCLO(175, 1),
	ACHETER_ENCLOS(176, 1),
	ACTIONNER(179, 1),
	PUISER(102, 1, Interractable.PUITS, DofusItems.EAU),
	AMELIORER_BOTTES(163, 1, Interractable.ATELIER_MAGIQUE),
	AMELIORER_ANNEAU(168, 1, Interractable.ATELIER_MAGIQUE),
	AMELIORER_CHAPEAU(166, 1, Interractable.ATELIER_MAGIQUE),
	AMELIORER_SAC(167, 1, Interractable.ATELIER_MAGIQUE),
	AMELIORER_AMULETTE(169, 1, Interractable.ATELIER_MAGIQUE),
	AMELIORER_CAPE(165, 1, Interractable.ATELIER_MAGIQUE),
	AMELIORER_CEINTURE(164, 1, Interractable.ATELIER_MAGIQUE),
	BRICOLER(171, 1),
	BRISER_OBJET(181, 1, Interractable.CONCASSEUR),
	COLLECTER_FER(24, 1, Interractable.FER, DofusItems.FER),
	COLLECTER_CUIVRE(25, 10, Interractable.CUIVRE, DofusItems.CUIVRE),
	COLLECTER_BRONZE(26, 20, Interractable.BRONZE, DofusItems.BRONZE),
	COLLECTER_KOBALTE(28, 30, Interractable.KOBALTE, DofusItems.KOBALTE),
	COLLECTER_ARGENT(29, 60, Interractable.ARGENT, DofusItems.ARGENT),
	COLLECTER_OR(30, 80, Interractable.OR, DofusItems.OR),
	COLLECTER_BAUXITE(31, 70, Interractable.BAUXITE, DofusItems.BAUXITE),
	COLLECTER_ETEIN(55, 50, Interractable.ETAIN, DofusItems.ETAIN),
	COLLECTER_MANGANESE(56, 40, Interractable.MANGANESE, DofusItems.MANGANESE),
	COLLECTER_DOLOMITE(161, 100, Interractable.DOLOMITE, DofusItems.DOLOMITE),
	COLLECTER_SILICATE(162, 50, Interractable.SILICATE, DofusItems.SILICATE),
	CONCASSER_RESSOURCES(121, 1, Interractable.CONCASSEUR),
	CONFECTIONNER_BOTTES(13, 1, Interractable.MACHINE_CORDONIER),
	CONFECTIONNER_CEINTURE(14, 1, Interractable.MACHINE_CORDONIER),
	CONFECTIONNER_CLEF(182, 1),
	CONSULTER(170, 1, Interractable.LISTE_ARTISANS),
	COUDRE_CHAPEAU(63, 1),
	COUDRE_SAC(123, 1),
	COUDRE_CAPE(64, 1),
	COUPER_FRENE(6, 1, Interractable.FRENE, DofusItems.BOIS_DE_FRENE),
	COUPER_CHENE(10, 30, Interractable.CHENE, DofusItems.BOIS_DE_CHENE),
	COUPER_IF(33, 50, Interractable.IF, DofusItems.BOIS_D_IF),
	COUPER_EBENE(34, 70, Interractable.EBENE, DofusItems.BOIS_D_EBENE),
	COUPER_ORME(35, 90, Interractable.ORME, DofusItems.BOIS_D_ORME),
	COUPER_ERABLE(37, 40, Interractable.ERABLE, DofusItems.BOIS_D_ERABLE),
	COUPER_CHARME(38, 80, Interractable.CHARME, DofusItems.BOIS_DE_CHARME),
	COUPER_CHATAIGNIER(39, 10, Interractable.CHATAIGNIER, DofusItems.BOIS_DE_CHATAIGNIER),
	COUPER_NOYER(40, 20, Interractable.NOYER, DofusItems.BOIS_DE_NOYER),
	COUPER_MERISIER(41, 60, Interractable.MERISIER, DofusItems.BOIS_DE_MERISIER),
	COUPER_BOMBU(139, 35, Interractable.BOMBU, DofusItems.BOIS_DE_BOMBU),
	COUPER_OLIVIOLET(141, 35, Interractable.OLIVIOLET, DofusItems.BOIS_D_OLIVIOLET),
	COUPER_BAMBOU(154, 50, Interractable.BAMBOU, DofusItems.BOIS_DE_BAMBOU),
	COUPER_BAMBOU_SOMBRE(155, 80, Interractable.BAMBOU_SOMBRE, DofusItems.BOIS_DE_BAMBOU_SOMBRE),
	COUPER_BAMBOU_SACRE(158, 100, Interractable.BAMBOU_SACREE, DofusItems.BOIS_DE_BAMBOU_SACRE),
	COUPER_KALIPTUS(174, 75, Interractable.KALIPTUS, DofusItems.BOIS_DE_KALIPTUS),
	CREER_ANNEAU(11, 1),
	CREER_AMULETTE(12, 1),
	CEUILLIR_LIN(68, 1, Interractable.LIN, DofusItems.FLEUR_DE_LIN),
	CEUILLIR_CHANVRE(69, 10, Interractable.CHANVRE, DofusItems.FLEUR_DE_CHANVRE),
	CEUILLIR_TREFLE(71, 20, Interractable.TREFLE, DofusItems.TREFLE_A_5_FEUILLES),
	CEUILLIR_MENTHE(72, 30, Interractable.FEUILLE_MENTHE, DofusItems.FEUILLE_DE_MENTHE_SAUVAGE),
	CEUILLIR_ORCHIDEE(73, 40, Interractable.ORCHIDEE, DofusItems.ORCHIDEE_FREYESQUE),
	CEUILLIR_EDELWEISS(74, 50, Interractable.EDELWEISS, DofusItems.EDELWEISS),
	CEUILLIR_GRAINE_PANDOUILLE(160, 50, Interractable.GRAINE_PANDOUILLE, DofusItems.GRAINE_DE_PANDOUILLE),
	CUIR_PAIN(27, 1),
	DEVEROUILLER(100, 1),
	DEVEROUILLER_2(106, 1),
	EGRENER(122, 1),
	ENTRER(84, 1),
	EPLUCHER(22, 1),
	FAIRE_BIERE(96, 1),
	FAIRE_BONBONS(109, 1),
	FAUCHER_BLE(45, 1),
	FAUCHER_HOUBLON(46, 30),
	FAUCHER_LIN(50, 40),
	FAUCHER_SEIGLE(52, 50),
	FAUCHER_ORGE(53, 10),
	FAUCHER_CHANVRE(54, 70),
	FAUCHER_AVOINE(57, 20),
	FAUCHER_MALT(58, 60),
	FAUCHER_RIZ(159, 50),
	FILER(95, 1),
	FONDRE(32, 1),
	FORGER_BOUCLIER(156, 1),
	FORGER_MARTEAU(19, 1),
	FORGER_DAGUE(18, 1),
	FORGER_EPEE(20, 1),
	FORGER_FAUX(66, 1),
	FORGER_HACHE(65, 1),
	FORGER_PELLE(21, 1),
	FORGER_PIOCHE(67, 1),
	FOUILLER(153, 1),
	INVOQUER_FEE(151, 1),
	JOUER(150, 1),
	MODIFIER_PRIX_VENTE(108, 1),
	MOUDRE(47, 1),
	OUVRIR(104, 1),
	POLIR_PIERRE(48, 30),
	PREPARER(132, 1),
	PREPARER_POISSON(135, 1),
	PREPARER_VIANDE(134, 1),
	PREPARER_POTION(23, 1),
	PECHER_GOUJON(124, 1), // TODO
	PECHER_TRUITE(125, 1), // TODO
	PECHER_POISSON_CHATON(126, 1), // TODO
	PECHER_BROCHET(127, 1), // TODO
	PECHER_GREUVETTE(128, 1), // TODO
	PECHER_CRABE_SOURIMI(129, 1), // TODO
	PECHER_POISSON_PANE(130, 1), // TODO
	PECHER_SARDINE_BRILLANTE(131, 1), // TODO
	PECHER_PICHON_EUD_COMPET(136, 1), // TODO
	PECHER_KRALAMOURE(140, 1), // TODO
	PECHER_SARDINE_BRILLANTE_2(189, 1), // TODO
	PECHER_KOINKOIN(152, 1, Interractable.PECHE_CANARD),
	RAMASSER(42, 1),
	REFORGER_MARTEAU(116, 1),
	REFORGER_DAGUE(1, 1),
	REFORGER_EPEE(113, 1),
	REFORGER_HACHE(115, 1),
	REFORGER_PELLE(117, 1),
	RESCULPTER_ARC(118, 1),
	RESCULPTER_BATON(120, 1),
	RESCULPTER_BAGUETTE(119, 1),
	REPARER_ARC(149, 1),
	REPARER_BATON(147, 1),
	REPARER_MARTEAU(144, 1),
	REPARER_BAGUETTE(148, 1),
	REPARER_DAGUE(142, 1),
	REPARER_EPEE(145, 1),
	REPARER_HACHE(143, 1),
	REPARER_PELLE(146, 1),
	SAUVEGARDER(44, 1),
	SCIER(101, 1),
	SCULPTER_ARC(15, 1),
	SCULPTER_BATON(17, 1),
	SCULPTER_BAGUETTE(16, 1),
	SE_FAIRE_TRANSPORTER(157, 1),
	SE_RENDRE_INCARNAM(183, 1),
	SE_REGENERER(62, 1),
	SE_REGENERER_2(111, 1),
	SORTIR(187, 1),
	UTILISER(114, 1),
	UTILISER_2(184, 1),
	UTILISER_ETABLI(110, 1),
	VENDRE_MAISON(98, 1),
	VENDRE_ENCLOS(177, 1),
	VEROUILLER_MAISON(81, 1),
	VEROUILLER(105, 1),
	VIDER(133, 1),;

	private int id;
	private int minLvlToUse;
	private Interractable type;
	private int recoltedId;

	private Skills(int id, int minLvl) {
		this(id, minLvl, null, -1);
	}

	private Skills(int id, int minLvl, int recolted) {
		this(id, minLvl, null, recolted);
	}

	private Skills(int id, int minLvl, Interractable type) {
		this(id, minLvl, type, -1);
	}

	private Skills(int id, int minLvl, Interractable type, int recolted) {
		this.id = id;
		this.minLvlToUse = minLvl;
		this.type = type;
		this.recoltedId = recolted;
	}

	/**
	 * @return the recoltedId
	 */
	public int getRecoltedId() {
		return recoltedId;
	}

	/**
	 * @return the type
	 */
	public Interractable getType() {
		return type;
	}

	/**
	 * @return the minLvlToUse
	 */
	public int getMinLvlToUse() {
		return minLvlToUse;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	public static Skills valueOf(int code) {
		for (Skills i : values())
			if (i.getId() == code) return i;
		return null;
	}

	public static Skills fromRecolted(int type) {
		for (Skills i : values())
			if (i.getRecoltedId() == type) return i;
		throw new IllegalArgumentException("Invalid type " + type);
	}

	public static boolean isHarvestSkill(Skills s) {
		switch (s) {
			case COLLECTER_FER:
			case COLLECTER_CUIVRE:
			case COLLECTER_BRONZE:
			case COLLECTER_ARGENT:
			case COLLECTER_KOBALTE:
			case COLLECTER_OR:
			case COLLECTER_BAUXITE:
			case COLLECTER_ETEIN:
			case COLLECTER_MANGANESE:
			case COLLECTER_DOLOMITE:
			case COLLECTER_SILICATE:
			case COUPER_FRENE:
			case COUPER_CHENE:
			case COUPER_IF:
			case COUPER_EBENE:
			case COUPER_ORME:
			case COUPER_ERABLE:
			case COUPER_CHARME:
			case COUPER_CHATAIGNIER:
			case COUPER_NOYER:
			case COUPER_MERISIER:
			case COUPER_BOMBU:
			case COUPER_OLIVIOLET:
			case COUPER_BAMBOU:
			case COUPER_BAMBOU_SOMBRE:
			case COUPER_BAMBOU_SACRE:
			case COUPER_KALIPTUS:
			case CEUILLIR_LIN:
			case CEUILLIR_CHANVRE:
			case CEUILLIR_TREFLE:
			case CEUILLIR_MENTHE:
			case CEUILLIR_ORCHIDEE:
			case CEUILLIR_EDELWEISS:
			case CEUILLIR_GRAINE_PANDOUILLE:
			case FAUCHER_BLE:
			case FAUCHER_HOUBLON:
			case FAUCHER_LIN:
			case FAUCHER_SEIGLE:
			case FAUCHER_ORGE:
			case FAUCHER_CHANVRE:
			case FAUCHER_AVOINE:
			case FAUCHER_MALT:
			case FAUCHER_RIZ:
			case PECHER_GOUJON:
			case PECHER_TRUITE:
			case PECHER_POISSON_CHATON:
			case PECHER_BROCHET:
			case PECHER_GREUVETTE:
			case PECHER_POISSON_PANE:
			case PECHER_CRABE_SOURIMI:
			case PECHER_SARDINE_BRILLANTE:
			case PECHER_PICHON_EUD_COMPET:
			case PECHER_KRALAMOURE:
			case PECHER_SARDINE_BRILLANTE_2:
				return true;
			default:
				return false;
		}
	}

	public static boolean isJobSkill(Skills s) {
		switch (s) {
			case AMELIORER_BOTTES:
			case AMELIORER_ANNEAU:
			case AMELIORER_CHAPEAU:
			case AMELIORER_SAC:
			case AMELIORER_AMULETTE:
			case AMELIORER_CAPE:
			case AMELIORER_CEINTURE:
			case BRICOLER:
			case COLLECTER_FER:
			case COLLECTER_CUIVRE:
			case COLLECTER_BRONZE:
			case COLLECTER_ARGENT:
			case COLLECTER_KOBALTE:
			case COLLECTER_OR:
			case COLLECTER_BAUXITE:
			case COLLECTER_ETEIN:
			case COLLECTER_MANGANESE:
			case COLLECTER_DOLOMITE:
			case COLLECTER_SILICATE:
			case CONFECTIONNER_BOTTES:
			case CONFECTIONNER_CEINTURE:
			case CONFECTIONNER_CLEF:
			case COUDRE_CHAPEAU:
			case COUDRE_SAC:
			case COUDRE_CAPE:
			case COUPER_FRENE:
			case COUPER_CHENE:
			case COUPER_IF:
			case COUPER_EBENE:
			case COUPER_ORME:
			case COUPER_ERABLE:
			case COUPER_CHARME:
			case COUPER_CHATAIGNIER:
			case COUPER_NOYER:
			case COUPER_MERISIER:
			case COUPER_BOMBU:
			case COUPER_OLIVIOLET:
			case COUPER_BAMBOU:
			case COUPER_BAMBOU_SOMBRE:
			case COUPER_BAMBOU_SACRE:
			case COUPER_KALIPTUS:
			case CREER_ANNEAU:
			case CREER_AMULETTE:
			case CEUILLIR_LIN:
			case CEUILLIR_CHANVRE:
			case CEUILLIR_TREFLE:
			case CEUILLIR_MENTHE:
			case CEUILLIR_ORCHIDEE:
			case CEUILLIR_EDELWEISS:
			case CEUILLIR_GRAINE_PANDOUILLE:
			case CUIR_PAIN:
			case EGRENER:
			case FAIRE_BONBONS:
			case FAUCHER_BLE:
			case FAUCHER_HOUBLON:
			case FAUCHER_LIN:
			case FAUCHER_SEIGLE:
			case FAUCHER_ORGE:
			case FAUCHER_CHANVRE:
			case FAUCHER_AVOINE:
			case FAUCHER_MALT:
			case FAUCHER_RIZ:
			case FORGER_BOUCLIER:
			case FORGER_MARTEAU:
			case FORGER_DAGUE:
			case FORGER_EPEE:
			case FORGER_FAUX:
			case FORGER_HACHE:
			case FORGER_PELLE:
			case FORGER_PIOCHE:
			case MOUDRE:
			case POLIR_PIERRE:
			case PREPARER:
			case PREPARER_POISSON:
			case PREPARER_VIANDE:
			case PREPARER_POTION:
			case PECHER_GOUJON:
			case PECHER_TRUITE:
			case PECHER_POISSON_CHATON:
			case PECHER_BROCHET:
			case PECHER_GREUVETTE:
			case PECHER_POISSON_PANE:
			case PECHER_CRABE_SOURIMI:
			case PECHER_SARDINE_BRILLANTE:
			case PECHER_PICHON_EUD_COMPET:
			case PECHER_KRALAMOURE:
			case PECHER_SARDINE_BRILLANTE_2:
			case SCIER:
			case SCULPTER_ARC:
			case SCULPTER_BATON:
			case SCULPTER_BAGUETTE:
				return true;
			default:
				return false;
		}
	}

}
