/*******************************************************************************
 * BotFather (C) - Dofus 1.29
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.actions;

/**
 * 
 * @since
 */
public enum GameActionEnum {

	MOVE(1),
	MAP_CHANGEMENT(2),
	CELL_CHANGEMENT(4),
	CELL_SLIDE(5),
	DIRECTION(11), // pas sur
	CARRIED(50), // aucune idée
	UNKNOWN_ADD_SPRITE(51), // aucune idée
	UNKNOWN_UNCARRIED(52),
	LIFE_CHANGEMENT(100, 108, 110),
	PA_CHANGEMENT(101, 102, 111, 120, 168),
	PM_CHANGEMENT(129, 127, 128, 78, 169),
	KILL(103),
	TACLE(104),
	SPELL_VALIDATION(106), // je croit que c'est quand un sort n'a pas encore le tour de relance
	REDUCE_DAMAGE(105, 164),
	RETURN_DAMAGE(107), // renvoie domage contre/picpic
	BLOCKED_DAMAGE(105),
	STEAL_GOLD(130), // vol de kama dagues/arnaque
	CLEAR_BUFFS(132),
	MESSAGE_PASS(140), // je croit que c'est le msg rouge "si vs avez finit vos actions passez votre tour"
	TIMELINE(147), // acune idée
	INVISIBLE(150),
	INVISIBLE_OBSTACLE(151),
	RETURN_PA(166), // heu ? ptet pour recup les pa utilisée par un allié/ennemi
	SUMMONED(180, 181, 780),
	UNKNOWN_ACTION_ADD(185), // mystere et boule de gomme
	CAST_SPELL(300),
	SPELL_CRITICAL(301, 304),
	UNKNOWN_MESSAGE(117, 116, 115, 122, 112, 142, 145, 138, 160, 161, 114, 182, 118, 157, 123, 152, 126, 155, 119, 154, 124, 156, 125, 153, 162, 163, 606, 607, 608, 609, 610, 611),
	ECHEC_CRITICAL(302),
	UNKNOWN_GFX(149),
	UNKNOWN_NUMBER(165),
	UNKNOWN_VISU_ANIM(208, 228),
	UNKNOWN_OBJECT2FRAME(200),
	MELEE_ATTACK(303, 305),
	ACTIVATE_TRAP(306),
	AURA(501), // ptet l'aura ? sinon je sais pas
	ASK_MARIAGE(617),
	MARIAGE_ACCEPT(618),
	MARIAGE_DECLINE(619),
	ACTIVATE_GLYPH(307),
	ESQUIVE_PA_LOST(308),
	ESQUIVE_PM_LOST(309),
	INTERACTIVE_OBJECT(500),
	ASK_FIGHT(900),
	ACCEPT_FIGHT(901),
	DECLINE_FIGHT(902),
	JOIN_FIGHT(903),
	AGRESSED(905),
	FIGHT_AGGRESSION(906), // peut correspondre a AGRESSED car il y a un check (agressed == p) dans le code du client
	PLAYER_DEFI_INFO(909),
	FIGHTER_STATE(950),
	UNKNOWN_EFFECT(998),
	TURN_LIST(999);
	private int[] ids;

	private GameActionEnum(int... id) {
		this.ids = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return ids[0];
	}

	/**
	 * @return the ids
	 */
	public int[] getIds() {
		return ids;
	}

	public boolean hasId(int id) {
		for (int i : getIds())
			if (id == i) return true;
		return false;
	}

	public static GameActionEnum fromId(int id) {
		for (GameActionEnum ga : values())
			if (ga.hasId(id)) return ga;
		return null;
	}

}
