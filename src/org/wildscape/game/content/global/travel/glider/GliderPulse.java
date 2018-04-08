package org.wildscape.game.content.global.travel.glider;

import org.wildscape.game.component.Component;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.entity.player.link.diary.DiaryType;
import org.wildscape.game.system.task.Pulse;
import org.wildscape.net.packet.PacketRepository;
import org.wildscape.net.packet.context.CameraContext;
import org.wildscape.net.packet.context.MinimapStateContext;
import org.wildscape.net.packet.context.CameraContext.CameraType;
import org.wildscape.net.packet.out.CameraViewPacket;
import org.wildscape.net.packet.out.MinimapState;

/**
 * Represents the pulse used for a glider.
 * @author 'Vexia
 */
public final class GliderPulse extends Pulse {

	/**
	 * Represents the player.
	 */
	private final Player player;

	/**
	 * Represents the glider.
	 */
	private final Gliders glider;

	/**
	 * Represents the count.
	 */
	private int count;

	/**
	 * Constructs a new {@code GliderPulse.java} {@Code Object}
	 * @param delay
	 * @param checks
	 */
	public GliderPulse(int delay, Player player, Gliders glider) {
		super(delay, player);
		this.player = player;
		this.glider = glider;
		player.lock();
	}

	@Override
	public boolean pulse() {
		final boolean crash = glider == Gliders.LEMANTO_ADRA;
		if (count == 1) {
			player.getConfigManager().set(153, glider.getConfig());
			PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 2));
		} else if (count == 2 && crash) {
			PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.SHAKE, 4, 4, 1200, 4, 4));
			player.getPacketDispatch().sendMessage("The glider almost gets blown from its path as it withstands heavy winds.");
		}
		if (count == 3) {
			player.getInterfaceManager().openOverlay(new Component(115));
		} else if (count == 4) {
			player.unlock();
			player.getProperties().setTeleportLocation(glider.getLocation());
		} else if (count == 5) {
			if (crash) {
				player.getPacketDispatch().sendMessage("The glider becomes uncontrolable and crashes down...");
				PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.RESET, 0, 0, 0, 0, 0));
			}
			player.getInterfaceManager().closeOverlay();
			player.getInterfaceManager().close();
			PacketRepository.send(MinimapState.class, new MinimapStateContext(player, 0));
			player.getConfigManager().set(0, glider.getConfig());
			if (!crash && glider == Gliders.GANDIUS && !player.getAchievementDiaryManager().getDiary(DiaryType.KARAMJA).isComplete(1, 6)) {
				player.getAchievementDiaryManager().getDiary(DiaryType.KARAMJA).updateTask(player, 1, 6, true);
			}
			return true;
		}
		count++;
		return false;
	}
}
