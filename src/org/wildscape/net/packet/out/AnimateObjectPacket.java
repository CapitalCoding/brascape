package org.wildscape.net.packet.out;

import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.object.GameObject;
import org.wildscape.game.world.map.Location;
import org.wildscape.game.world.update.flag.context.Animation;
import org.wildscape.net.packet.IoBuffer;
import org.wildscape.net.packet.OutgoingPacket;
import org.wildscape.net.packet.context.AnimateObjectContext;

/**
 * Represents the packet used to animate an object.
 * @author Vexia
 * @date 10/11/2013
 */
public class AnimateObjectPacket implements OutgoingPacket<AnimateObjectContext> {

	/**
	 * Writes the packet.
	 * @param buffer The buffer.
	 * @param item The item.
	 */
	public static IoBuffer write(IoBuffer buffer, Animation animation) {
		GameObject object = animation.getObject();
		Location l = object.getLocation();
		buffer.put(20);
		buffer.putS((l.getChunkOffsetX() << 4) | (l.getChunkOffsetY() & 0x7));
		buffer.putS((object.getType() << 2) + (object.getRotation() & 0x3));
		buffer.putLEShort(animation.getId());
		return buffer;
	}

	@Override
	public void send(AnimateObjectContext context) {
		Player player = context.getPlayer();
		GameObject object = context.getAnimation().getObject();
		IoBuffer buffer = write(UpdateAreaPosition.getBuffer(player, object.getLocation().getChunkBase()), context.getAnimation());
		player.getSession().write(buffer);
	}
}
