package org.wildscape.net.packet.context;

import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.world.map.Location;
import org.wildscape.game.world.update.flag.context.Graphics;
import org.wildscape.net.packet.Context;

/**
 * The packet context for the positioned graphic packet.
 * @author Emperor
 */
public final class PositionedGraphicContext implements Context {

	/**
	 * The player.
	 */
	private final Player player;

	/**
	 * The graphics.
	 */
	private final Graphics graphic;

	/**
	 * The location.
	 */
	private final Location location;

	/**
	 * Constructs a new {@code PositionedGraphicContext} {@code Object}.
	 * @param player The player.
	 * @param graphic The graphic to display on the given location.
	 * @param location The location to display the graphic on.
	 */
	public PositionedGraphicContext(Player player, Graphics graphic, Location location) {
		this.player = player;
		this.graphic = graphic;
		this.location = location;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	/**
	 * @return the graphic
	 */
	public Graphics getGraphic() {
		return graphic;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

}