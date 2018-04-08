package plugin.interaction.player;

import org.wildscape.cache.def.impl.NPCDefinition;
import org.wildscape.game.interaction.Option;
import org.wildscape.game.interaction.OptionHandler;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.plugin.Plugin;

/**
 * Represents the attack option plugin handler.
 * @author Emperor
 * @version 1.0
 */
public final class AttackOptionPlugin extends OptionHandler {

	@Override
	public boolean handle(Player player, Node node, String option) {
		player.attack(node);
		return true;
	}

	@Override
	public boolean isWalk() {
		return false;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		Option._P_ATTACK.setHandler(this);
		NPCDefinition.setOptionHandler("attack", this);
		return this;
	}

	@Override
	public boolean isDelayed(Player player) {
		return false;
	}

}
