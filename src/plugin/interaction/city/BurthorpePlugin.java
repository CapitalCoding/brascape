package plugin.interaction.city;

import org.wildscape.cache.def.impl.ObjectDefinition;
import org.wildscape.game.content.global.action.ClimbActionHandler;
import org.wildscape.game.interaction.OptionHandler;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.object.GameObject;
import org.wildscape.game.world.map.Location;
import org.wildscape.plugin.Plugin;

/**
 * Handles the burthorpe plugin.
 * @author Emperor
 */
public final class BurthorpePlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(4627).getConfigurations().put("option:climb-up", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		GameObject object = (GameObject) node;
		switch (object.getId()) {
		case 4627:
			ClimbActionHandler.climb(player, null, Location.create(2899, 3565, 0));
			return true;
		}
		return false;
	}

}