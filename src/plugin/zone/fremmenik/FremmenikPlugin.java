package plugin.zone.fremmenik;

import org.wildscape.cache.def.impl.NPCDefinition;
import org.wildscape.game.interaction.OptionHandler;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.plugin.Plugin;

/**
 * Handles the fremmenik plugin.
 * @author Vexia
 */
public class FremmenikPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.forId(5508).getConfigurations().put("option:ferry-neitiznot", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		switch (option) {
		case "ferry-neitiznot":
			return true;
		}
		return true;
	}

}
