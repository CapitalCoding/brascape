package org.wildscape.net.packet.in;

import org.wildscape.game.node.entity.player.Player;
import org.wildscape.net.packet.IncomingPacket;
import org.wildscape.net.packet.IoBuffer;

/**
 * Handles the quick chat packet.
 * @author Vexia
 *
 */
public class QuickChatPacket implements IncomingPacket {

	@Override
	public void decode(Player player, int opcode, IoBuffer buffer) {
		
	}

}
