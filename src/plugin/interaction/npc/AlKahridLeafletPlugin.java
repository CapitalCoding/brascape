package plugin.interaction.npc;

import org.wildscape.cache.def.impl.NPCDefinition;
import org.wildscape.game.interaction.OptionHandler;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.plugin.Plugin;

/**
 * Represents the plugin used for ali the leaflet npc.
 * @author 'Vexia
 * @version 1.0
 */
public final class AlKahridLeafletPlugin extends OptionHandler {

	@Override
	public boolean handle(Player player, Node node, String option) {
		player.getDialogueInterpreter().open(3680, node, true);
		return true;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.forId(3680).getConfigurations().put("option:take-flyer", this);
		return this;
	}

}
