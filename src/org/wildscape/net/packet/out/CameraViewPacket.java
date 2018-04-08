package org.wildscape.net.packet.out;

import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.world.map.Location;
import org.wildscape.net.packet.IoBuffer;
import org.wildscape.net.packet.OutgoingPacket;
import org.wildscape.net.packet.context.CameraContext;
import org.wildscape.net.packet.context.CameraContext.CameraType;

/**
 * Handles the outgoing camera view packets.
 * @author Emperor
 */
public final class CameraViewPacket implements OutgoingPacket<CameraContext> {

	@Override
	public void send(CameraContext context) {
		CameraType type = context.getType();
		IoBuffer buffer = new IoBuffer(type.opcode());
		Location l = Location.create(context.getX(), context.getY(), 0);
		Player p = context.getPlayer();
		switch (type) {
		case ROTATION:
		case POSITION:
			buffer.putShort(context.getPlayer().getInterfaceManager().getPacketCount(1));
			int x = l.getSceneX(p.getPlayerFlags().getLastSceneGraph());
			int y = l.getSceneY(p.getPlayerFlags().getLastSceneGraph());
			buffer.put(x).put(y).putShort(context.getHeight()).put(context.getSpeed()).put(context.getZoomSpeed());
			break;
		case SET:
			buffer.putLEShort(context.getX())
			.putShort(context.getPlayer().getInterfaceManager().getPacketCount(1)).putShort(context.getY());
			break;
		case SHAKE:
			buffer.putShort(context.getPlayer().getInterfaceManager().getPacketCount(1));
			buffer.put(l.getX()).put(l.getY()).put(context.getSpeed()).put(context.getZoomSpeed()).putShort(context.getHeight());
			break;
		case RESET:
			buffer.putShort(context.getPlayer().getInterfaceManager().getPacketCount(1));
			break;
		}
		p.getSession().write(buffer);
	}

}
