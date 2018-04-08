package plugin.interaction.npc;

import org.wildscape.cache.def.impl.NPCDefinition;
import org.wildscape.game.component.Component;
import org.wildscape.game.component.ComponentDefinition;
import org.wildscape.game.component.ComponentPlugin;
import org.wildscape.game.interaction.OptionHandler;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.plugin.Plugin;

/**
 * Represents the option plugin used to toggle the doomsayer interface.
 * @author 'Vexia
 * @version 1.0
 */
public class DoomsayerTogglePlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.forId(3777).getConfigurations().put("option:toggle-warnings", this);
		new WarningMessagePlugin().newInstance(arg);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		player.getWarningMessages().open(player);
		return true;
	}

	/**
	 * Represents the plugin used to handle the warning message plugin.
	 * @author 'Vexia
	 */
	public final class WarningMessagePlugin extends ComponentPlugin {

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			ComponentDefinition.put(583, this);
			return this;
		}

		@Override
		public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
			if (button > 45 && button < 74) {
				player.getWarningMessages().getMessage(button).toggle(player);
			}
			return true;
		}

	}
}
