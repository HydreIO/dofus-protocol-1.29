package fr.aresrpg.dofus.structures.guild;

public enum GuildRight {
	LEADER,
	MANAGE_BOOST,
	MANAGE_RIGHTS,
	INVITE,
	BAN,
	MANAGE_XP_CONTRIBUTION,
	MANAGE_RANKS,
	HIRE_TAX_COLLECTOR,
	MANAGE_OWN_XP_CONTRIBUTION,
	COLLECT,
	USE_MOUNT_PARK,
	ARRANGE_MOUNT_PARK,
	MANAGE_OTHER_MOUNT;

	public int getModifier() {
		return (int) Math.pow(2 , ordinal());
	}

	public boolean isPresent(int rights) {
		int mod = getModifier();
		return (rights & mod) == mod;
	}
}
