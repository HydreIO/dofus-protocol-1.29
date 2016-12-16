/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.structures;

/**
 * 
 * @since
 */
public enum EquipmentPosition {

	NO_EQUIPED, // -1
	AMULETTE, // 0
	ARME, // 1
	ANNEAU_GAUCHE,
	ANNEAU_DROITE,
	CEINTURE,
	BOTTES,
	COIFFRE,
	CAPE,
	FAMILIER,
	DOFUS_1,
	DOFUS_2,
	DOFUS_3,
	DOFUS_4,
	DOFUS_5,
	DOFUS_6,
	BOUCLIER;

	/**
	 * @return the position
	 */
	public int getPosition() {
		return ordinal() - 1;
	}

	public static EquipmentPosition valueOf(int code) {
		return values()[code + 1];
	}
}
