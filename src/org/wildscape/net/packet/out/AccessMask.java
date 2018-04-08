package org.wildscape.net.packet.out;

import org.wildscape.net.packet.IoBuffer;
import org.wildscape.net.packet.OutgoingPacket;
import org.wildscape.net.packet.context.AccessMaskContext;

/**
 * The access mask outgoing packet.
 * @author Emperor
 */
public class AccessMask implements OutgoingPacket<AccessMaskContext> {

	@Override
	public void send(AccessMaskContext context) {
		IoBuffer buffer = new IoBuffer(165);
		buffer.putLEShort(context.getPlayer().getInterfaceManager().getPacketCount(1));
		buffer.putLEShort(context.getLength());
		buffer.putInt(context.getInterfaceId() << 16 | context.getChildId());
		buffer.putShortA(context.getOffset());
		buffer.putIntA(context.getId());
		context.getPlayer().getSession().write(buffer);
	}
}
