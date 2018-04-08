package plugin.interaction.item;

import org.wildscape.game.content.global.BirdNest;
import org.wildscape.game.interaction.OptionHandler;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.item.Item;
import org.wildscape.plugin.Plugin;

/**
 * Handles the searching of a bird nest item.
 * @author Vexia
 */
public final class BirdNestPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (BirdNest nest : BirdNest.values()) {
			nest.getNest().getDefinition().getConfigurations().put("option:search", this);
		}
		return null;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final Item item = (Item) node;
		BirdNest.forNest(item).search(player, item);
		return true;
	}

	@Override
	public boolean isWalk() {
		return false;
	}
}
