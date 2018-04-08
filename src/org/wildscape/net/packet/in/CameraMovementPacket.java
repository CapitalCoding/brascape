package org.wildscape.net.packet.in;

import org.wildscape.game.node.entity.player.Player;
import org.wildscape.net.packet.IncomingPacket;
import org.wildscape.net.packet.IoBuffer;

/**
 * Handles an incoming camera movement changed packet.
 * @author Emperor
 */
public final class CameraMovementPacket implements IncomingPacket {

	@Override
	public void decode(Player player, int opcode, IoBuffer buffer) {
		buffer.getShortA();
		buffer.getLEShort();
	}

}