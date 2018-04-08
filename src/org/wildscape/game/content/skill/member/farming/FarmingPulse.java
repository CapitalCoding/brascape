package org.wildscape.game.content.skill.member.farming;

import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.system.task.Pulse;
import org.wildscape.game.world.GameWorld;
import org.wildscape.game.world.callback.CallBack;
import org.wildscape.game.world.repository.Repository;

/**
 * Represents the pulsed used to update all players farming states.
 * @author 'Vexia
 */
public final class FarmingPulse extends Pulse implements CallBack {

	@Override
	public boolean pulse() {
		for (Player p : Repository.getPlayers()) {
			if (p == null) {
				continue;
			}
			p.getFarmingManager().cycle();
		}
		return false;
	}

	@Override
	public boolean call() {
		GameWorld.submit(this);
		return true;
	}

}
