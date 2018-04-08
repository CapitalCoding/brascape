package org.wildscape.net.packet.out;

import org.wildscape.game.system.communication.ClanEntry;
import org.wildscape.game.system.communication.ClanRepository;
import org.wildscape.net.packet.IoBuffer;
import org.wildscape.net.packet.OutgoingPacket;
import org.wildscape.net.packet.PacketHeader;
import org.wildscape.net.packet.context.ClanContext;
import org.wildscape.tools.StringUtils;

/**
 * Handles the update clan chat outgoing packet.
 * @author Emperor
 */
public final class UpdateClanChat implements OutgoingPacket<ClanContext> {

	@Override
	public void send(ClanContext context) {
		IoBuffer buffer = new IoBuffer(55, PacketHeader.SHORT);
		ClanRepository clan = context.getClan();
		if (!context.isLeave()) {
			buffer.putLong(StringUtils.stringToLong(clan.getOwner()));
			buffer.putLong(StringUtils.stringToLong(clan.getName()));
			buffer.put(clan.getKickRequirement().getValue());
			int size = clan.getPlayers().size();
			if (size > 100) {
				size = 100;
			}
			buffer.put(size);
			for (int i = 0; i < size; i++) {
				ClanEntry entry = clan.getPlayers().get(i);
				String name = entry.getName();
				buffer.putLong(StringUtils.stringToLong(name)).putShort(entry.getWorldId());
				buffer.put(clan.getRank(entry).getValue());
				buffer.putString("World " + entry.getWorldId());
			}
		} else {
			buffer.putLong(0);
		}
		context.getPlayer().getSession().write(buffer);
	}

}