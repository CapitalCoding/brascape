package org.wildscape.net.packet.in;

import org.wildscape.game.node.entity.player.Player;
import org.wildscape.net.packet.IncomingPacket;
import org.wildscape.net.packet.IoBuffer;

/**
 * Handles music-related incoming packets.
 * @author Emperor
 */
public final class MusicPacketHandler implements IncomingPacket {

	@Override
	public void decode(Player player, int opcode, IoBuffer buffer) {
		int musicId = buffer.getLEShortA();
		if (player.getMusicPlayer().isLooping()) {
			player.getMusicPlayer().replay();
			return;
		}
		if (player.getMusicPlayer().getCurrentMusicId() == musicId) {
			player.getMusicPlayer().setPlaying(false);
		}
	}

}