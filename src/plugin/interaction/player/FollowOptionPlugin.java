package plugin.interaction.player;

import org.wildscape.game.interaction.DestinationFlag;
import org.wildscape.game.interaction.MovementPulse;
import org.wildscape.game.interaction.Option;
import org.wildscape.game.interaction.OptionHandler;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.plugin.Plugin;

/**
 * Represents the plugin used to start following a node.
 * @author 'Vexia
 * @version 1.0
 */
public final class FollowOptionPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		Option._P_FOLLOW.setHandler(this);
		return this;
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		final Player target = ((Player) node);
		player.getPulseManager().run(new MovementPulse(player, target, DestinationFlag.FOLLOW_ENTITY) {
			@Override
			public boolean pulse() {
				player.face(target);
				return false;
			}

			@Override
			public void stop() {
				super.stop();
				mover.face(null);
			}
		}, "movement");
		return true;
	}
}
