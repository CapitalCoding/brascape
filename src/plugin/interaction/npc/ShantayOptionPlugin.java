package plugin.interaction.npc;

import org.wildscape.cache.def.impl.NPCDefinition;
import org.wildscape.game.content.dialogue.DialogueInterpreter;
import org.wildscape.game.interaction.OptionHandler;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.item.Item;
import org.wildscape.plugin.Plugin;

/**
 * Represents the option plguin.
 * @author 'Vexia
 */
public class ShantayOptionPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.forId(836).getConfigurations().put("option:buy-pass", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		DialogueInterpreter interpreter = player.getDialogueInterpreter();
		if (player.getInventory().remove(new Item(995, 5))) {
			player.getInventory().add(new Item(1854));
			interpreter.sendItemMessage(1854, "You purchase a Shantay Pass.");
		} else {
			interpreter.sendDialogues(player, null, "Sorry, I don't seem to have enough money.");
		}
		return true;
	}

}
