package plugin.interaction.object;

import org.wildscape.cache.def.impl.ObjectDefinition;
import org.wildscape.game.interaction.OptionHandler;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.plugin.Plugin;

/**
 * @author 'Vexia
 */
public class SlayerDangerSignPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(5127).getConfigurations().put("option:read", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		player.getDialogueInterpreter().sendDialogue("<col=FFF0000>WARNING!", "</col>This area contains very dangerous creatures!", "Do not pass unless properly prepared!");
		return true;
	}

}
