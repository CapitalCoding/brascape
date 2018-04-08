package plugin.interaction.inter;

import org.wildscape.game.component.Component;
import org.wildscape.game.component.ComponentDefinition;
import org.wildscape.game.component.ComponentPlugin;
import org.wildscape.game.content.skill.free.magic.MagicSpell;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.entity.player.link.SpellBookManager.SpellBook;
import org.wildscape.game.world.GameWorld;
import org.wildscape.plugin.Plugin;

/**
 * Represents the magic book interface handling of non-combat spells.
 * @author 'Vexia
 * @version 1.0
 */
public final class MagicBookInterface extends ComponentPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(192, this);
		ComponentDefinition.put(193, this);
		ComponentDefinition.put(430, this);
		return this;
	}

	@Override
	public boolean handle(final Player player, Component component, int opcode, int button, int slot, int itemId) {
		if (GameWorld.getTicks() < player.getAttribute("magic:delay", -1)) {
			return true;
		}
		return MagicSpell.castSpell(player, component.getId() == 192 ? SpellBook.MODERN : component.getId() == 193 ? SpellBook.ANCIENT : SpellBook.LUNAR, button, player);
	}
}