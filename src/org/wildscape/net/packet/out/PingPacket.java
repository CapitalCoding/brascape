package org.wildscape.net.packet.out;

import org.wildscape.net.packet.OutgoingPacket;
import org.wildscape.net.packet.context.PlayerContext;

/**
 * Handles the ping packet sending.
 * @author Emperor
 */
public final class PingPacket implements OutgoingPacket<PlayerContext> {

	@Override
	public void send(PlayerContext context) {
		// TODO: Find real ping packet, this is actually clear minimap flag
		// packet.
		// context.getPlayer().getDetails().getSession().write(new
		// IoBuffer(47));
	}

}