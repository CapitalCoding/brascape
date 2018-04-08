package org.wildscape.net.packet.out;

import org.wildscape.net.packet.IoBuffer;
import org.wildscape.net.packet.OutgoingPacket;
import org.wildscape.net.packet.PacketHeader;
import org.wildscape.net.packet.context.GameMessageContext;

/**
 * The game message outgoing packet.
 * @author Emperor
 */
public class GameMessage implements OutgoingPacket<GameMessageContext> {

	@Override
	public void send(GameMessageContext context) {
		IoBuffer buffer = new IoBuffer(70, PacketHeader.BYTE);
		buffer.putString(context.getMessage());
		context.getPlayer().getSession().write(buffer);
	}
}