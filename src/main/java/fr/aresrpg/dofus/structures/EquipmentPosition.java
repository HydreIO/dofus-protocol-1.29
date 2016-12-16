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
