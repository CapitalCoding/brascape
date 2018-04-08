package plugin.interaction.inter;

import org.wildscape.game.component.Component;
import org.wildscape.game.component.ComponentDefinition;
import org.wildscape.game.component.ComponentPlugin;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.plugin.Plugin;

/**
 * Handles the world map interface.
 * @author Emperor
 *
 */
public final class WorldMapInterface extends ComponentPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.forId(755).setPlugin(this);
		return this;
	}

	@Override
	public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
		switch (button) {
		case 3:
			player.getInterfaceManager().openWindowsPane(new Component(player.getInterfaceManager().isResizable() ? 746 : 548));
			return true;
		}
		return false;
	}

}