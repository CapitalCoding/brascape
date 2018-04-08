package plugin.interaction.item;

import org.wildscape.cache.def.impl.ItemDefinition;
import org.wildscape.game.content.global.action.PickupHandler;
import org.wildscape.game.interaction.OptionHandler;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.item.GroundItem;
import org.wildscape.game.world.map.Location;
import org.wildscape.plugin.Plugin;

/**
 * Represents the option handler used for ground items.
 * @author Vexia
 * @author Emperor
 */
public final class PickupPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.setOptionHandler("take", this);
		return this;
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		if (player.getAttributes().containsKey("pickup"))
		    return false;	
		player.setAttribute("pickup", "true");
		boolean handleResult = PickupHandler.take(player, (GroundItem) node);
		player.removeAttribute("pickup");
		return handleResult;
	}

	@Override
	public Location getDestination(Node node, Node item) {
		return null;
	}

}