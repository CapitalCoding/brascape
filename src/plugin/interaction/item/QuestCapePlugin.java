package plugin.interaction.item;

import org.wildscape.cache.def.impl.ItemDefinition;
import org.wildscape.game.content.global.action.EquipHandler;
import org.wildscape.game.interaction.OptionHandler;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.plugin.Plugin;

/**
 * Represents the plugin used for the quest cape and hood item.
 * @author 'Vexia
 */
public final class QuestCapePlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.forId(9813).getConfigurations().put("option:wear", this);
		ItemDefinition.forId(9814).getConfigurations().put("option:wear", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		if (!player.getQuestRepository().hasCompletedAll()) {
			player.getPacketDispatch().sendMessage("You cannot wear this " + node.getName().toLowerCase() + " yet.");
			return true;
		}
		return EquipHandler.SINGLETON.handle(player, node, option);
	}

	@Override
	public boolean isWalk() {
		return false;
	}
}
