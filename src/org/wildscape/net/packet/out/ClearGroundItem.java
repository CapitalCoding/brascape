package org.wildscape.net.packet.out;

import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.item.Item;
import org.wildscape.game.world.map.Location;
import org.wildscape.net.packet.IoBuffer;
import org.wildscape.net.packet.OutgoingPacket;
import org.wildscape.net.packet.context.BuildItemContext;

/**
 * Represents the outgoing packet of clearing ground items.
 * @author Emperor
 */
public final class ClearGroundItem implements OutgoingPacket<BuildItemContext> {

	/**
	 * Writes the packet.
	 * @param buffer The buffer.
	 * @param item The item.
	 */
	public static IoBuffer write(IoBuffer buffer, Item item) {
		Location l = item.getLocation();
		buffer.put(240);
		buffer.putS((l.getChunkOffsetX() << 4) | (l.getChunkOffsetY() & 0x7)).putShort(item.getId());
		return buffer;
	}

	@Override
	public void send(BuildItemContext context) {
		Player player = context.getPlayer();
		Item item = context.getItem();
		IoBuffer buffer = write(UpdateAreaPosition.getBuffer(player, item.getLocation().getChunkBase()), item);
		player.getSession().write(buffer);
	}
}