package org.wildscape.net.packet.out;

import org.wildscape.net.packet.IoBuffer;
import org.wildscape.net.packet.OutgoingPacket;
import org.wildscape.net.packet.context.PlayerContext;

/**
 * The outgoing logout packet.
 * @author Emperor
 */
public class Logout implements OutgoingPacket<PlayerContext> {

	@Override
	public void send(PlayerContext context) {
		IoBuffer buffer = new IoBuffer(86);
		context.getPlayer().getDetails().getSession().write(buffer);
	}
}