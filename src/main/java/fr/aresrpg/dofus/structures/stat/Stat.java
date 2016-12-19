/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.structures.stat;

public enum Stat {
	PA,
	PM,
	FORCE,
	VITA,
	SAGESSE,
	CHANCE,
	AGILITE,
	INTELLIGENCE,
	PO,
	INVOCS,
	BONUS_DEGATS,
	BONUS_DEGATS_PHYSIQUE,
	BONUS_MAITRISE,
	PUISSANCE,
	SOINS,
	BONUS_PIEGES,
	BONUS_PIEGES_PER,
	RENVOI_DOMMAGES,
	CC,
	EC,

	ESQUIVE_PA,
	ESQUIVE_PM,

	RES_NEUTRE,
	RES_NEUTRE_PER,
	RES_NEUTRE_PVP,
	RES_NEUTRE_PVP_PER,

	RES_TERRE,
	RES_TERRE_PER,
	RES_TERRE_PVP,
	RES_TERRE_PVP_PER,

	RES_EAU,
	RES_EAU_PER,
	RES_EAU_PVP,
	RES_EAU_PVP_PER,

	RES_AIR,
	RES_AIR_PER,
	RES_AIR_PVP,
	RES_AIR_PVP_PER,

	RES_FEU,
	RES_FEU_PER,
	RES_FEU_PVP,
	RES_FEU_PVP_PER;

	public static Stat valueOf(int code) {
		if (code >= 0 && code < values().length)
			return values()[code];
		else
			return null;
	}
}
