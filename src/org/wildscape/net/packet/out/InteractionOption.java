package org.wildscape.net.packet.out;

import org.wildscape.net.packet.IoBuffer;
import org.wildscape.net.packet.OutgoingPacket;
import org.wildscape.net.packet.PacketHeader;
import org.wildscape.net.packet.context.InteractionOptionContext;

/**
 * Handles the interaction option changed outgoing packet.
 * @author Emperor
 */
public final class InteractionOption implements OutgoingPacket<InteractionOptionContext> {

	@Override
	public void send(InteractionOptionContext context) {
		IoBuffer buffer = new IoBuffer(44, PacketHeader.BYTE);
		buffer.putLEShortA(-1);
		buffer.put(context.getIndex() == 0 ? 1 : 0);
		buffer.put(context.getIndex() + 1);
		buffer.putString(context.getName());
		context.getPlayer().getSession().write(buffer);
	}

}