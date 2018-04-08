package plugin.interaction.inter;

import org.wildscape.game.component.Component;
import org.wildscape.game.component.ComponentDefinition;
import org.wildscape.game.component.ComponentPlugin;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.entity.player.link.emote.Emotes;
import org.wildscape.plugin.Plugin;

/**
 * Handles the emote tab interface.
 * @author Vexia
 * 
 */
public final class EmoteTabInterface extends ComponentPlugin {
	
	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(464, this);
		return this;
	}

	@Override
	public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
		Emotes.handle(player, button);
		return true;
	}
}
