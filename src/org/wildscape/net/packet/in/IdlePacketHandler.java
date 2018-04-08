package org.wildscape.net.packet.in;

import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.entity.player.info.Rights;
import org.wildscape.net.packet.IncomingPacket;
import org.wildscape.net.packet.IoBuffer;

/**
 * Handles the idle packet handler.
 * @author Emperor
 */
public final class IdlePacketHandler implements IncomingPacket {

	@Override
	public void decode(Player player, int opcode, IoBuffer buffer) {
		if (player.getDetails().getRights() != Rights.ADMINISTRATOR) {
			player.getPacketDispatch().sendLogout();
		}
	}

}