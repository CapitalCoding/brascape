package org.wildscape.net.packet.out;

import org.wildscape.net.packet.IoBuffer;
import org.wildscape.net.packet.OutgoingPacket;
import org.wildscape.net.packet.context.MinimapStateContext;

/**
 * Handles the sending of the minimap state outgoing packet.
 * @author Emperor
 */
public final class MinimapState implements OutgoingPacket<MinimapStateContext> {

	@Override
	public void send(MinimapStateContext context) {
		IoBuffer buffer = new IoBuffer(192).put(context.getState());
		context.getPlayer().getDetails().getSession().write(buffer);
	}

}