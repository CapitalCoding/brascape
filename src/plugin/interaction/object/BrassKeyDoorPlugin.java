package plugin.interaction.object;

import org.wildscape.cache.def.impl.ObjectDefinition;
import org.wildscape.game.content.global.action.DoorActionHandler;
import org.wildscape.game.interaction.OptionHandler;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.object.GameObject;
import org.wildscape.game.world.map.Location;
import org.wildscape.plugin.Plugin;

/**
 * Represents the plugin used to handle the brass key door plugin.
 * @author 'Vexia
 * @version 1.0
 */
public final class BrassKeyDoorPlugin extends OptionHandler {

	@Override
	public boolean handle(Player player, Node node, String option) {
		if (player.getInventory().contains(983, 1)) {
			DoorActionHandler.handleAutowalkDoor(player, (GameObject) node);
		} else {
			player.getPacketDispatch().sendMessage("This door is locked.");
			return true;
		}
		return true;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(1804).getConfigurations().put("option:open", this);
		return this;
	}

	@Override
	public Location getDestination(Node node, Node n) {
		return DoorActionHandler.getDestination(((Player) node), ((GameObject) n));
	}

}
