package org.wildscape.net.packet.in;

import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.system.command.CommandSystem;
import org.wildscape.game.system.monitor.PlayerMonitor;
import org.wildscape.game.world.GameWorld;
import org.wildscape.net.packet.IncomingPacket;
import org.wildscape.net.packet.IoBuffer;

/**
 * Handles an incoming command packet.
 * @author Emperor
 * @author 'Vexia
 */
public final class CommandPacket implements IncomingPacket {

	@Override
	public void decode(Player player, int opcode, IoBuffer buffer) {
		final int data = buffer.get();
		if (buffer.toByteBuffer().hasRemaining()) {
			final String message = ((char) data + buffer.getString()).toLowerCase();
			if (!GameWorld.getSettings().isDevMode()) {
				int last = player.getAttribute("commandLast", 0);
				if (last > GameWorld.getTicks()) {
					return;
				}
				player.setAttribute("commandLast", GameWorld.getTicks() + 1);
			}
			if (CommandSystem.getCommandSystem().parse(player, message)) {
				player.getMonitor().log(message, PlayerMonitor.COMMAND_LOG);
			}
		}
	}

}