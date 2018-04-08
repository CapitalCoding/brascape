package org.wildscape.net.packet.in;

import org.wildscape.game.node.entity.player.Player;
import org.wildscape.net.packet.IncomingPacket;
import org.wildscape.net.packet.IoBuffer;

/**
 * Packet received when a player's region has changed.
 * @author Emperor
 * @author 'Vexia
 */
public class RegionChangePacket implements IncomingPacket {

	@Override
	public void decode(Player player, int opcode, IoBuffer buffer) {
		//TODO: no data is sen't so not sure what to do.
	}

}
