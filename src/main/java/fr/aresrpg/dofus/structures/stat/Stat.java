package fr.aresrpg.dofus.structures.stat;

public enum  Stat {
	Prospection,
	Initiative,
	ActionPoints,
	MovementPoints,
	Strength,
	Vitality,
	Wisdom,
	Chance,
	Agility,
	Intelligence,
	RangePoints,
	Summons,
	Damage,
	PhysicalDamage,
	WeaponControl,
	DamagePer,
	HealPoints,
	TrapDamage,
	TrapDamagePer,
	DamageReturn,
	CriticalHit,
	CriticalFailure,

	DodgeActionPoints,
	DodgeMovementPoints,

	ResistanceNeutral,
	ResistancePercentNeutral,
	ResistancePvpNeutral,
	ResistancePercentPvpNeutral,

	ResistanceEarth,
	ResistancePercentEarth,
	ResistancePvpEarth,
	ResistancePercentPvpEarth,

	ResistanceWater,
	ResistancePercentWater,
	ResistancePvpWater,
	ResistancePercentPvpWater,

	ResistanceWind,
	ResistancePercentWind,
	ResistancePvpWind,
	ResistancePercentPvpWind,

	ResistanceFire,
	ResistancePercentFire,
	ResistancePvpFire,
	ResistancePercentPvpFire;

	public static Stat valueOf(int code) {
		if (code >= 0 && code < values().length)
			return values()[code];
		else
			return null;
	}
}
