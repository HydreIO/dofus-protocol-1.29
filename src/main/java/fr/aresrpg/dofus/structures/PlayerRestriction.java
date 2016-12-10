package fr.aresrpg.dofus.structures;

public enum PlayerRestriction {
	ASSAULT,
	CHALLENGE,
	EXCHANGE,
	ATTACK,
	CHAT_TO_ALL,
	BE_MERCHANT,
	USE_OBJECT,
	INTERACT_WITH_TAX_COLLECTOR,
	USE_INTERACTIVE_OBJECTS,
	SPEAK_NPC,
	ATTACK_DUNGEON_MONSTERS_WHEN_MUTANT,
	MOVE_IN_ALL_DIRECTIONS,
	ATTACK_MONSTERS_ANYWHERE_WHEN_MUTANT,
	INTERACT_WITH_PRISM;

	public int getModifier() {
		return (int) Math.pow(2 , ordinal());
	}

	public boolean isPresent(int rights) {
		int mod = getModifier();
		return (rights & mod) == mod;
	}
}
