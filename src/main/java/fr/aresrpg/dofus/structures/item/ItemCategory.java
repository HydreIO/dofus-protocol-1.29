/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.structures.item;

public enum ItemCategory {
	AMULET(1),
	BOW(2),
	WAND(3),
	STAFF(4),
	DAGGER(5),
	SWORD(6),
	HAMMER(7),
	SHOVEL(8),
	RING(9),
	BELT(10),
	BOOT(11),
	POTION(12),
	EXPERIENCEPARCHMENT(13),
	GIFT(14),
	RESOURCE(15),
	HAT(16),
	CLOACK(17),
	PET(18),
	AXE(19),
	TOOL(20),
	PICKAXE(21),
	SCYTHE(22),
	DOFUS(23),
	QUEST(24),
	DOCUMENT(25),
	ALCHEMYPOTION(26),
	TRANSFORM(27),
	BOOSTFOOD(28),
	BENEDICTION(29),
	MALEDICTION(30),
	ROLEPLAYGIFT(31),
	FOLLOWER(32),
	BREAD(33),
	CEREAL(34),
	FLOWER(35),
	PLANT(36),
	BEER(37),
	WOOD(38),
	ORE(39),
	ALLOY(40),
	FISH(41),
	CANDY(42),
	FORGETPOTION(43),
	JOBPOTION(44),
	SPELLPOTION(45),
	FRUIT(46),
	BONE(47),
	POWDER(48),
	COMESTIBLEFISH(49),
	PRECIOUSSTONE(50),
	STONE(51),
	FLOUR(52),
	FEATHER(53),
	HAIR(54),
	FABRIC(55),
	LEATHER(56),
	WOOL(57),
	SEED(58),
	SKIN(59),
	OIL(60),
	STUFFEDTOY(61),
	GUTTEDFISH(62),
	MEAT(63),
	PRESERVEDMEAT(64),
	TAIL(65),
	METARIA(66),
	VEGETABLE(68),
	COMESTIBLEMEAT(69),
	DYE(70),
	ALCHEMYEQUIPMENT(71),
	PETEGG(72),
	WEAPONCONTROL(73),
	FEEARTIFICE(74),
	SPELLPARCHMENT(75),
	STATPARCHMENT(76),
	KENNELCERTIFICATE(77),
	SMITHMAGICRUNE(78),
	DRINK(79),
	QUESTOBJECT(80),
	BACKPACK(81),
	SHIELD(82),
	SOULSTONE(83),
	KEY(84),
	FULLSOULSTONE(85),
	PERCEPTEURFORGETPOTION(86),
	PARCHO_RECHERCHE(87),
	MAGICSTONE(88),
	GIFTS(89),
	GHOSTPET(90),
	DRAGODINDE(91),
	BOUFTOU(92),
	BREEDINGOBJECT(93),
	USABLEOBJECT(94),
	PLANK(95),
	BARK(96),
	DRAGODINDECERTIFICATE(97),
	ROOT(98),
	CATCHNET(99),
	RESOURCEBAG(100),
	CROSSBOW(102),
	PAW(103),
	WING(104),
	EGG(105),
	EAR(106),
	CARAPACE(107),
	BUD(108),
	EYE(109),
	JELLY(110),
	SHELL(111),
	PRISM(112),
	OBVIJEVAN(113),
	MAGICWEAPON(114),
	SHUSHUSOULPIECE(115),
	PETPOTION(116);
	private int value;

	ItemCategory(int value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	public static ItemCategory valueOf(int code) {
		for (ItemCategory e : values())
			if (e.getValue() == code) return e;
		return null;
	}

	public boolean isEquipment() {
		return this == AMULET ||
				this == RING ||
				this == BELT ||
				this == BOOT ||
				this == HAT ||
				this == CLOACK ||
				this == PET ||
				this == DOFUS ||
				this == BACKPACK ||
				this == SHIELD ||
				this == DRAGODINDE ||
				this == OBVIJEVAN ||
				this.isWeapon();
	}

	public boolean isWeapon() {
		return this == BOW ||
				this == WAND ||
				this == STAFF ||
				this == DAGGER ||
				this == SWORD ||
				this == HAMMER ||
				this == SHOVEL ||
				this == AXE ||
				this == TOOL ||
				this == PICKAXE ||
				this == SCYTHE ||
				this == CROSSBOW ||
				this == SOULSTONE ||
				this == MAGICWEAPON;
	}

	public boolean isUsable() {
		return this == POTION ||
				this == EXPERIENCEPARCHMENT ||
				this == GIFT ||
				this == BOOSTFOOD ||
				this == BENEDICTION ||
				this == MALEDICTION ||
				this == BREAD ||
				this == BEER ||
				this == CANDY ||
				this == FORGETPOTION ||
				this == JOBPOTION ||
				this == SPELLPOTION ||
				this == GUTTEDFISH ||
				this == MEAT ||
				this == PRESERVEDMEAT ||
				this == COMESTIBLEMEAT ||
				this == WEAPONCONTROL ||
				this == FEEARTIFICE ||
				this == SPELLPARCHMENT ||
				this == STATPARCHMENT ||
				this == DRINK ||
				this == FULLSOULSTONE ||
				this == GIFTS ||
				this == USABLEOBJECT ||
				this == CATCHNET ||
				this == PRISM ||
				this == PETPOTION;
	}

	public boolean isRessource() {
		return !isEquipment() || !isUsable();
	}
}