package plugin.interaction.item;

import org.wildscape.cache.def.impl.ItemDefinition;
import org.wildscape.game.interaction.OptionHandler;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.item.Item;
import org.wildscape.plugin.Plugin;

/**
 * Handles the uncharging of the four imbued rings.
 * @author Splinter
 */
public final class RingUnchargePlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.forId(14807).getConfigurations().put("option:uncharge", this);
		ItemDefinition.forId(14808).getConfigurations().put("option:uncharge", this);
		ItemDefinition.forId(14809).getConfigurations().put("option:uncharge", this);
		ItemDefinition.forId(14810).getConfigurations().put("option:uncharge", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		if(node == null || player == null){
			return true;
		}
		if(player.getInventory().remove(new Item(node.asItem().getId()))){
			player.getInventory().add(new Item(getReward(node.asItem().getId()), 1));
		}
		return true;
	}
	
	/**
	 * The reward to give.
	 * @param node
	 * @return an item ID
	 */
	private final int getReward(int node){
		switch(node){
		case 14810:
			return 6737;
		case 14808:
			return 6733;
		case 14809:
			return 6735;
		case 14807:
			return 6731;
		}
		return -1;
	}
	
}
