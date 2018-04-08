package plugin.interaction.player;

import org.wildscape.game.interaction.Option;
import org.wildscape.game.interaction.OptionHandler;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.entity.player.link.request.RequestType;
import org.wildscape.plugin.Plugin;

/**
 * Represents the plugin used to handle the player option interacting.
 * @author 'Vexia
 */
public final class RequestOptionPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		Option._P_ASSIST.setHandler(this);
		Option._P_TRADE.setHandler(this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		player.getRequestManager().request((Player) node, RequestType.forOption(option));
		return true;
	}
}