package org.wildscape.net.packet.out;

import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.object.GameObject;
import org.wildscape.game.world.map.Location;
import org.wildscape.net.packet.IoBuffer;
import org.wildscape.net.packet.OutgoingPacket;
import org.wildscape.net.packet.context.BuildObjectContext;

/**
 * The clear game object outgoing packet.
 * @author Emperor
 */
public final class ClearObject implements OutgoingPacket<BuildObjectContext> {

	/**
	 * Writes the packet.
	 * @param buffer The buffer.
	 * @param objects The objects.
	 */
	public static IoBuffer write(IoBuffer buffer, GameObject object) {
		Location l = object.getLocation();
		buffer.put(195) // Opcode
				.putC((object.getType() << 2) + (object.getRotation() & 3)).put((l.getChunkOffsetX() << 4) | (l.getChunkOffsetY() & 0x7));
		return buffer;
	}

	@Override
	public void send(BuildObjectContext context) {
		Player player = context.getPlayer();
		GameObject o = context.getGameObject();
		IoBuffer buffer = write(UpdateAreaPosition.getBuffer(player, o.getLocation().getChunkBase()), o);
		player.getSession().write(buffer);

	}
}