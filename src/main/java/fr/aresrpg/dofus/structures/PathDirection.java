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

public enum PathDirection {
	DOWN_RIGHT(0.07f, 0.23f, 0.17f),
	DOWN(0.06f, 0.20f, 0.15f),
	DOWN_LEFT(0.06f, 0.20f, 0.15f),
	LEFT(0.06f, 0.20f, 0.15f),
	UP_LEFT(0.07f, 0.23f, 0.17f),
	UP(0.06f, 0.20f, 0.15f),
	UP_RIGHT(0.06f, 0.20f, 0.15f),
	RIGHT(0.06f, 0.20f, 0.15f);

	private final float walkSpeed;
	private final float mountSpeed;
	private final float runSpeed;

	PathDirection(float walkSpeed, float mountSpeed, float runSpeed){
		this.walkSpeed = walkSpeed;
		this.mountSpeed = mountSpeed;
		this.runSpeed = runSpeed;
	}

	public float getWalkSpeed() {
		return walkSpeed;
	}

	public float getMountSpeed() {
		return mountSpeed;
	}

	public float getRunSpeed() {
		return runSpeed;
	}

	public static PathDirection valueOf(int code) {
		if(code >= 0 && code < values().length)
			return values()[code];
		else
			return null;
	}

}
