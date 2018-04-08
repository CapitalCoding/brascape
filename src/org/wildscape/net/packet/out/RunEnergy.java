package org.wildscape.net.packet.out;

import org.wildscape.net.packet.IoBuffer;
import org.wildscape.net.packet.OutgoingPacket;
import org.wildscape.net.packet.context.PlayerContext;

/**
 * The run energy outgoing packet.
 * @author Emperor
 */
public class RunEnergy implements OutgoingPacket<PlayerContext> {

	@Override
	public void send(PlayerContext context) {
		IoBuffer buffer = new IoBuffer(234);
		buffer.put((byte) context.getPlayer().getSettings().getRunEnergy());
		context.getPlayer().getDetails().getSession().write(buffer);
	}
}