package org.wildscape.net.packet.out;

import org.wildscape.net.packet.OutgoingPacket;
import org.wildscape.net.packet.context.WalkOptionContext;

/**
 * Handles the sending of the "Set walk-to option" packet.
 * @author Emperor
 */
public final class SetWalkOption implements OutgoingPacket<WalkOptionContext> {

	@Override
	public void send(WalkOptionContext context) {
		// TODO IoBuffer buffer = new IoBuffer(10,
		// PacketHeader.BYTE).putString(context.getOption());
		// context.getPlayer().getDetails().getSession().write(buffer);
	}

}