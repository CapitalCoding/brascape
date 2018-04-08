package plugin.interaction.npc;

import org.wildscape.cache.def.impl.NPCDefinition;
import org.wildscape.game.interaction.OptionHandler;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.npc.NPC;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.plugin.Plugin;

/**
 * Represents the runescape instructor plugin.
 * @author 'Vexia
 * @date 20.11.2013
 */
public class RunescapeInstructorPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.forId(4707).getConfigurations().put("option:claim", this);
		NPCDefinition.forId(1861).getConfigurations().put("option:claim", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		switch (option) {
		case "claim":
			player.getDialogueInterpreter().open(((NPC) node).getId(), ((NPC) node), true);
			break;
		}
		return true;
	}

}
