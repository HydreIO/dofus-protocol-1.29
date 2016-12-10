package fr.aresrpg.dofus.structures.game;

public class FightChallenge {
	private int id;
	private boolean showTarget;
	private int targetId;
	private int basicXpBonus;
	private int teamXpBonus;
	private int basicDropBonus;
	private int teamDropBonus;

	public FightChallenge(int id, boolean showTarget, int targetId, int basicXpBonus, int teamXpBonus, int basicDropBonus, int teamDropBonus) {
		this.id = id;
		this.showTarget = showTarget;
		this.targetId = targetId;
		this.basicXpBonus = basicXpBonus;
		this.teamXpBonus = teamXpBonus;
		this.basicDropBonus = basicDropBonus;
		this.teamDropBonus = teamDropBonus;
	}
}
