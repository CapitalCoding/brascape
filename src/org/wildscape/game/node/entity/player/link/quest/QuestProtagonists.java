package org.wildscape.game.node.entity.player.link.quest;

import org.wildscape.ServerConstants.TeleportDestinations;
import org.wildscape.game.world.map.Location;

public enum QuestProtagonists {

	DUKE_HORACIO("Duke Horacio", TeleportDestinations.LUMBRIDGE.getLocation(), 741, "Lumbridge Castle 2nd floor"),
	SEDRIDOR("Sedridor",  TeleportDestinations.WIZARD_TOWER.getLocation(), 300, "Wizard Tower subfloor"),
	AUBURY("Aubury",  TeleportDestinations.VARROCK.getLocation(), 553, "South of Varrock East Bank"),
	FATHER_AEREK("Father Aerek",  TeleportDestinations.LUMBRIDGE.getLocation(), 456, "Inside Lumbridge Church"),
	FATHER_URHNEY("Father Aerek",  TeleportDestinations.LUMBRIDGE_SWAMP.getLocation(), 458, "Inside West House of Lumbridge Swamp"),
	;
	
	private String npcName;
	private Location location;
	private int npcId;
	private String locationInfo;

	private QuestProtagonists(String name, Location nearestPoint, int npcId, String locationInfo) {
		this.npcName = name;
		this.location = nearestPoint;
		this.npcId = npcId;
		this.locationInfo = locationInfo;
	}

	public String getNpcName() {
		return npcName;
	}

	public Location getLocation() {
		return location;
	}

	public int getNpcId() {
		return npcId;
	}

	public String getLocationInfo() {
		return locationInfo;
	}
}
