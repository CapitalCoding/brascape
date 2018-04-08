package org.wildscape.net.packet.out;

import org.wildscape.game.world.map.Location;
import org.wildscape.net.packet.IoBuffer;
import org.wildscape.net.packet.OutgoingPacket;
import org.wildscape.net.packet.context.ClearChunkContext;

/**
 * Handles the clear region chunk outgoing packet.
 * @author Emperor
 */
public final class ClearRegionChunk implements OutgoingPacket<ClearChunkContext> {

	@Override
	public void send(ClearChunkContext context) {
		Location l = context.getPlayer().getPlayerFlags().getLastSceneGraph();
		int x = context.getChunk().getCurrentBase().getSceneX(l);
		int y = context.getChunk().getCurrentBase().getSceneY(l);
		if (x >= 0 && y >= 0 && x < 96 && y < 96) {
			IoBuffer buffer = new IoBuffer(112).put(x).putC(y);
			context.getPlayer().getSession().write(buffer);
		}
	}

}
