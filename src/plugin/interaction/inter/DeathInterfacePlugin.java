package plugin.interaction.inter;

import org.wildscape.game.component.Component;
import org.wildscape.game.component.ComponentDefinition;
import org.wildscape.game.component.ComponentPlugin;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.plugin.Plugin;

/**
 * Handles the death interface.
 * @author Vexia
 */
public final class DeathInterfacePlugin extends ComponentPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.forId(153).setPlugin(this);
		return this;
	}

	@Override
	public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
		if (button == 1) {
			player.getSavedData().getGlobalData().setDisableDeathScreen(true);
			player.getInterfaceManager().close();
		}
		return true;
	}

}
