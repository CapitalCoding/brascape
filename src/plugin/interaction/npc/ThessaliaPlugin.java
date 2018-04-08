package plugin.interaction.npc;

import org.wildscape.cache.def.impl.NPCDefinition;
import org.wildscape.game.interaction.OptionHandler;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.plugin.Plugin;

/**
 * @author 'Vexia.
 */
public class ThessaliaPlugin extends OptionHandler {

	@Override
	public boolean handle(Player player, Node node, String option) {
		player.getDialogueInterpreter().open(548, true, true, true);
		return true;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.forId(548).getConfigurations().put("option:change-clothes", this);
		return this;
	}

}