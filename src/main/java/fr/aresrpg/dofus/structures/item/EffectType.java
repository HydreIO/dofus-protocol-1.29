package fr.aresrpg.dofus.structures.item;

/**
 * 
 * @since
 */
public enum EffectType {

	EMOT(10),
	ITEM(165, 939, 940),
	SPELL_1(293, 294, 787, 616, 624),
	MAP(601),
	JOB_1(614, 615),
	MONSTER(628, 623, 717),
	MONSTER_SUPER_RACE(715),
	MONSTER_RACE(716),
	UNKNOW_WITH_DATE(805, 808, 983),
	LEAN_FAT_NORMAL(806),
	PET(807),
	ITEM_UNIC(814),
	STATE(950, 951),
	SPELL_BOOST_RANGE(281),
	SPELL_BOOST_RANGEABLE(282),
	SPELL_BOOST_DMG(283),
	SPELL_BOOST_HEAL(284),
	SPELL_BOOST_PA_COST(285),
	SPELL_BOOST_CAST_INTVL(286),
	SPELL_BOOST_CC(287),
	SPELL_BOOST_CASTOUTLINE(288),
	SPELL_BOOST_NOLINEOFSIGHT(289),
	SPELL_BOOST_MAXPERTURN(290),
	SPELL_BOOST_MAXPERTARGET(291),
	SPELL_BOOST_SET_INTVL(292),
	NONE(999);
	private int[] type;

	/**
	 * @param type
	 */
	private EffectType(int... type) {
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public int[] getType() {
		return type;
	}

	public boolean hasId(int id) {
		for (int i : type)
			if (id == i) return true;
		return false;
	}

	public static EffectType valueOf(int code) {
		for (EffectType t : values())
			if (t.hasId(code)) return t;
		return null;
	}

}
