/*******************************************************************************
 * BotFather (C) - Dofus 1.29
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.structures.item;

import fr.aresrpg.dofus.structures.job.Jobs;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 
 * @since
 */
public enum Interractable {

	PUITS(7519, null),
	PICHON(7544, null),
	POUBELLE(7352, null),
	OMBRE_ETRANGE(7543, null),
	MOULE(7002, null),
	FM_CAC(7020, null),
	TABLE_A_PATATE(7006, null),
	PECHE_CANARD(7549, null),
	TABLE_MAGIQUE(7037, null),
	ATELIER_MAGIQUE(7038, null),
	MARMITE(7017, null),
	LISTE_ARTISANS(7035, null),
	MACHINE_A_COUDRE_MAGIQUE(7036, null),
	CONCASSEUR(7021, null),
	SOURCE_DE_JOUVENCE(7004, null),
	TAS_DE_PATATES(7510, null),
	MEULE(7005, null),
	ETABLI_PYROTECHNIQUE(7028, null),
	MACHINE_BOUCLIER(7027, Jobs.JOB_F_BOUCLIER),
	MACHINE_PAYSAN(7007, Jobs.JOB_PAYSAN),
	MACHINE_PECHEUR(7024, Jobs.JOB_PECHEUR),
	MACHINE_BOUCHER(7025, Jobs.JOB_BOUCHER),
	MACHINE_POISSONIER(7022, Jobs.JOB_POISSONNIER),
	MACHINE_CHASSEUR(7023, Jobs.JOB_CHASSEUR),
	MACHINE_BUCHERON(7003, Jobs.JOB_BUCHERON),
	MACHINE_ALCHI(7019, Jobs.JOB_ALCHIMISTE),
	MACHINE_FORGERON(7012, Jobs.JOB_F_DAGUE, Jobs.JOB_F_HACHES, Jobs.JOB_F_MARTEAU, Jobs.JOB_F_PELLE, Jobs.JOB_FM_EPEE),
	MACHINE_BRICOLEUR(7039, Jobs.JOB_BRICOLEUR),
	MACHINE_BOULANGER(7001, Jobs.JOB_BOULANGER),
	MACHINE_SCULPTEUR(7013, Jobs.JOB_S_ARC, Jobs.JOB_S_BAGUETTE, Jobs.JOB_S_BATON),
	MACHINE_CORDONIER(7011),
	FLEUR_LIN(7513, Jobs.JOB_ALCHIMISTE),
	TREFLE(7533, Jobs.JOB_ALCHIMISTE),
	FEUILLE_MENTHE(7534, Jobs.JOB_ALCHIMISTE),
	ORCHIDEE(7535, Jobs.JOB_ALCHIMISTE),
	EDELWEISS(7536, Jobs.JOB_ALCHIMISTE),
	GRAINE_PANDOUILLE(7551, Jobs.JOB_ALCHIMISTE),
	FRENE(7500, Jobs.JOB_BUCHERON),
	CHATAIGNIER(7501, Jobs.JOB_BUCHERON),
	NOYER(7502, Jobs.JOB_BUCHERON),
	CHENE(7503, Jobs.JOB_BUCHERON),
	BOMBU(7541, Jobs.JOB_BUCHERON),
	OLIVIOLET(7542, Jobs.JOB_BUCHERON),
	ERABLE(7504, Jobs.JOB_BUCHERON),
	IF(7505, Jobs.JOB_BUCHERON),
	BAMBOU(7553, Jobs.JOB_BUCHERON),
	MERISIER(7506, Jobs.JOB_BUCHERON),
	EBENE(7506, Jobs.JOB_BUCHERON),
	KALIPTUS(7557, Jobs.JOB_BUCHERON),
	CHARME(7508, Jobs.JOB_BUCHERON),
	BAMBOU_SOMBRE(7554, Jobs.JOB_BUCHERON),
	ORME(7509, Jobs.JOB_BUCHERON),
	BAMBOU_SACREE(7552, Jobs.JOB_BUCHERON),
	FER(7520, Jobs.JOB_MINEUR),
	CUIVRE(7522, Jobs.JOB_MINEUR),
	BRONZE(7523, Jobs.JOB_MINEUR),
	KOBALTE(443, Jobs.JOB_MINEUR),
	MANGANESE(7524, Jobs.JOB_MINEUR),
	ETAIN(7521, Jobs.JOB_MINEUR),
	SILICATE(7556, Jobs.JOB_MINEUR),
	ARGENT(7526, Jobs.JOB_MINEUR),
	BAUXITE(7528, Jobs.JOB_MINEUR),
	OR(313, Jobs.JOB_MINEUR), // ID
	DOLOMITE(7555, Jobs.JOB_MINEUR),
	BLE(7511, Jobs.JOB_PAYSAN),
	ORGE(7515, Jobs.JOB_PAYSAN),
	AVOINE(7517, Jobs.JOB_PAYSAN),
	HOUBLON(7512, Jobs.JOB_PAYSAN),
	LIN(7513, Jobs.JOB_PAYSAN),
	RIZ(7550, Jobs.JOB_PAYSAN),
	SEIGLE(7516, Jobs.JOB_PAYSAN),
	MALT(7518, Jobs.JOB_PAYSAN),
	CHANVRE(7514, Jobs.JOB_PAYSAN),
	PETITS_POISSONS_MER(7530, Jobs.JOB_PECHEUR),
	PETITS_POISSONS_RIVIERE(7529, Jobs.JOB_PECHEUR),
	POISSONS_MER(7531, Jobs.JOB_PECHEUR),
	POISSONS_RIVIERE(7532, Jobs.JOB_PECHEUR),
	GROS_POISSONS_MER(7538, Jobs.JOB_PECHEUR),
	GROS_POISSONS_RIVIERE(7537, Jobs.JOB_PECHEUR),
	POISSONS_GEANTS_MER(7540, Jobs.JOB_PECHEUR),
	POISSONS_GEANTS_RIVIERE(7539, Jobs.JOB_PECHEUR),
	PANNEAU_INDICATION(1533),
	AVIS_RECHERCHE_DOUBLE(1988),
	AVIS_RECHERCHE_BAS(1986),
	AVIS_RECHERCHE_HAUT(1985),;

	private int id;
	private Jobs[] requiredJob;
	public static final int ENCLOS[] = { 6766, 6767, 6763, 6772 };
	public static final int ZAAP[] = { 7000, 7026, 7029, 4287 };
	public static final int ATELIER[] = { 7008, 7009, 7010 };
	public static final int MACHINE_TAILLEUR[] = { 7014, 7015, 7016 };
	public static final int ZAAPI[] = { 7030, 7031 };
	public static final int PORTE[] = { 6700, 6701, 6702, 6703, 6704, 6705, 6706, 6707, 6708, 6709, 6710, 6711, 6712, 6713, 6714, 6715, 6716, 6717, 6718, 6719, 6720, 6721, 6722, 6723, 6724, 6725,
			6726, 6727, 6728, 6729, 6730, 6731, 6732, 6733, 6734, 6735, 6736, 6737, 6738, 6739, 6740, 6741, 6742, 6743, 6744, 6745, 6746, 6747, 6748, 6749, 6750, 6751, 6752, 6753, 6754, 6755, 6756,
			6757, 6758, 6759, 6760, 6761, 6762, 6763, 6764, 6765, 6766, 6767, 6768, 6769, 6770, 6771, 6772, 6773, 6774, 6775, 6776 };
	public static final int COFFRE[] = { 7350, 7351, 7353 };
	public static final int LEVIER[] = { 7041, 7042, 7043, 7044, 7045 };
	public static final int STATUE_CLASSE[] = { 1853, 1854, 1855, 1856, 1857, 1858, 1859, 1860, 1861, 1862, 1845, 2319 };
	public static final int MACHINE_FORCE[] = { 7546, 7547 };

	private Interractable(int id, Jobs... job) {
		this.requiredJob = job;
		this.id = id;
	}

	public static Interractable fromId(int id) {
		for (Interractable i : values())
			if (i.getId() == id) return i;
		return null;
	}

	public static boolean isWalkableRessource(int id) {
		Interractable interractable = fromId(id);
		if (interractable == null) return false;
		return interractable.hasAnyOf(Jobs.JOB_PAYSAN, Jobs.JOB_ALCHIMISTE);
	}

	public boolean hasAnyOf(Jobs... jobs) {
		if (getRequiredJob() == null) return false;
		for (Jobs j : getRequiredJob())
			for (Jobs jj : jobs)
				if (jj == j) return true;
		return false;
	}

	public static boolean isRessource(int id) {
		Interractable interractable = fromId(id);
		if (interractable == null) return false;
		switch (interractable) {
			case FLEUR_LIN:
			case TREFLE:
			case FEUILLE_MENTHE:
			case ORCHIDEE:
			case EDELWEISS:
			case GRAINE_PANDOUILLE:
			case FRENE:
			case CHATAIGNIER:
			case NOYER:
			case CHENE:
			case BOMBU:
			case OLIVIOLET:
			case ERABLE:
			case IF:
			case BAMBOU:
			case MERISIER:
			case EBENE:
			case KALIPTUS:
			case CHARME:
			case BAMBOU_SOMBRE:
			case ORME:
			case BAMBOU_SACREE:
			case FER:
			case CUIVRE:
			case BRONZE:
			case KOBALTE:
			case MANGANESE:
			case ETAIN:
			case SILICATE:
			case ARGENT:
			case BAUXITE:
			case OR:
			case DOLOMITE:
			case BLE:
			case ORGE:
			case AVOINE:
			case HOUBLON:
			case LIN:
			case RIZ:
			case SEIGLE:
			case MALT:
			case CHANVRE:
			case PETITS_POISSONS_MER:
			case PETITS_POISSONS_RIVIERE:
			case POISSONS_MER:
			case POISSONS_RIVIERE:
			case GROS_POISSONS_MER:
			case GROS_POISSONS_RIVIERE:
			case POISSONS_GEANTS_MER:
			case POISSONS_GEANTS_RIVIERE:
				return true;
			default:
				return false;
		}
	}

	public static boolean isInterractable(int id) {
		return fromId(id) != null || isMachineForce(id) || isStatueClasse(id) || isLevier(id) || isPorte(id) || isZaapi(id) || isMachineTailleur(id) || isAtelier(id) || isZaap(id) || isEnclo(id);
	}

	public static boolean isMachineForce(int id) {
		return contains(id, MACHINE_FORCE);
	}

	public static boolean isStatueClasse(int id) {
		return contains(id, STATUE_CLASSE);
	}

	public static boolean isLevier(int id) {
		return contains(id, LEVIER);
	}

	public static boolean isPorte(int id) {
		return contains(id, PORTE);
	}

	public static boolean isZaapi(int id) {
		return contains(id, ZAAPI);
	}

	public static boolean isMachineTailleur(int id) {
		return contains(id, MACHINE_TAILLEUR);
	}

	public static boolean isAtelier(int id) {
		return contains(id, ATELIER);
	}

	public static boolean isZaap(int id) {
		return contains(id, ZAAP);
	}

	public static boolean isEnclo(int id) {
		return contains(id, ENCLOS);
	}

	private static boolean contains(int id, int[] array) {
		for (int i : array)
			if (i == id) return true;
		return false;
	}

	private boolean is(Interractable i) {
		return i == this;
	}

	public static Interractable[] allWoods() {
		return allWoodsBut();
	}

	public static Interractable[] allWoodsBut(Interractable... except) {
		Set<Interractable> collect = Arrays.stream(except).collect(Collectors.toSet());
		return Interractable.getAllForJob(Jobs.JOB_BUCHERON).stream().filter(i -> !collect.contains(i) && i != Interractable.MACHINE_BUCHERON).toArray(Interractable[]::new);
	}

	public static Set<Interractable> getAllForJob(Jobs job) {
		Set<Interractable> set = new HashSet<>();
		loop: for (Interractable i : values()) {
			if (i.getRequiredJob() == null) continue;
			for (Jobs rj : i.getRequiredJob())
				if (rj == job) {
					set.add(i);
					continue loop;
				}
		}
		return set;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the requiredJob
	 */
	public Jobs[] getRequiredJob() {
		return requiredJob;
	}
}
