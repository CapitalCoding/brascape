package plugin.interaction.object;

import org.wildscape.cache.def.impl.ObjectDefinition;
import org.wildscape.game.interaction.OptionHandler;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.plugin.Plugin;

/**
 * Represents the plugin used to unlock the sheers cage.
 * @author 'Vexia
 * @versio 1.0
 */
public final class SeersCageUnlockPlugin extends OptionHandler {

	@Override
	public boolean handle(Player player, Node node, String option) {
		player.getPacketDispatch().sendMessage("You can't unlock the pillory, you'll let all the prisoners out!");
		return true;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(6836).getConfigurations().put("option:unlock", this);
		return this;
	}

}
