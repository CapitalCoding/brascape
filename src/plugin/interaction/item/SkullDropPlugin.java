package plugin.interaction.item;

import org.wildscape.cache.def.impl.ItemDefinition;
import org.wildscape.game.interaction.OptionHandler;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.plugin.Plugin;

/*
 * 8
 */
public class SkullDropPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.forId(964).getConfigurations().put("option:drop", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		player.getPacketDispatch().sendMessage("You can't drop this! Return it to the ghost.");
		return true;
	}

	@Override
	public boolean isWalk() {
		return false;
	}
}
