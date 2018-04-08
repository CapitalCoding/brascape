package plugin.activity.pestcontrol.reward;

import org.wildscape.cache.def.impl.NPCDefinition;
import org.wildscape.game.interaction.OptionHandler;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.plugin.Plugin;
import org.wildscape.plugin.PluginManager;

/**
 * Represents the option plugin used to handle the pc island related nodes.
 * @author 'Vexia
 */
public final class PCIslandOptionPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (int id : new int[] { 3786, 3788, 3789, 5956 }) {
			NPCDefinition.forId(id).getConfigurations().put("option:exchange", this);
		}
		PluginManager.definePlugin(new PCRewardInterface());
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		switch (option) {
		case "exchange":
			PCRewardInterface.open(player);
			break;
		}
		return true;
	}

}