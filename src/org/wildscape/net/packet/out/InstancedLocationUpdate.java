package org.wildscape.net.packet.out;

import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.world.map.Location;
import org.wildscape.net.packet.IoBuffer;
import org.wildscape.net.packet.OutgoingPacket;
import org.wildscape.net.packet.context.LocationContext;

/**
 * Outgoing packet used for updating a player's location solely on his own
 * client.
 * @author Emperor
 */
public final class InstancedLocationUpdate implements OutgoingPacket<LocationContext> {

	@Override
	public void send(LocationContext context) {
		IoBuffer buffer = new IoBuffer(110);
		Location l = context.getLocation();
		Player player = context.getPlayer();
		int flag = l.getZ() << 1;
		if (context.isTeleport()) {
			flag |= 0x1;
		}
		buffer.putS(flag);
		buffer.put(l.getSceneX(player.getPlayerFlags().getLastSceneGraph()));
		buffer.putA(l.getSceneY(player.getPlayerFlags().getLastSceneGraph()));
		// TODO player.getSession().write(buffer);
	}

}