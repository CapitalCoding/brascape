package org.wildscape.net.packet.in;

import org.wildscape.game.node.entity.player.Player;
import org.wildscape.net.packet.IncomingPacket;
import org.wildscape.net.packet.IoBuffer;

/**
 * Represents the <b>Incoming</b> packet of the grand exchange.
 * @author Emperor
 */
public class GrandExchangePacket implements IncomingPacket {

	@Override
	public void decode(Player player, int opcode, IoBuffer buffer) {
		int itemId = buffer.getShort();
		player.getGrandExchange().constructBuy(itemId);
		player.getInterfaceManager().closeChatbox();
	}

}
