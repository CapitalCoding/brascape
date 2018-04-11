package org.wildscape.game.node.entity.player.link.quest;

import org.wildscape.ServerConstants.TeleportDestinations;

public class QuestTeleport {

	private TeleportDestinations destination;
	private String tag;

	public QuestTeleport(TeleportDestinations destination, String tag) {
	this.destination = destination;
	this.tag = tag;
	}
}
