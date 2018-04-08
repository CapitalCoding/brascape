package plugin.skill.crafting;

import org.wildscape.game.interaction.NodeUsageEvent;
import org.wildscape.game.interaction.UseWithHandler;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.item.Item;
import org.wildscape.plugin.Plugin;

/**
 * Handles the bullseye lantern crafting.
 * @author Vexia
 *
 */
public class BullseyeLanternPlugin extends UseWithHandler {
	
	/**
	 * Constructs the {@code BullseyeLanternPlugin}
	 */
	public BullseyeLanternPlugin() {
		super(4542, 1607);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		addHandler(4544, ITEM_TYPE, this);
		return this;
	}
	
	@Override
	public boolean handle(NodeUsageEvent event) {
		final Player player = event.getPlayer();
		final Item with = event.getUsedWith().asItem();
		int rewardId = 4546;
		if (with.getId() == 1607) {
			rewardId = 4700;
		}
		if (player.getInventory().remove(event.getUsedItem())) {
			player.getInventory().replace(new Item(rewardId), event.getUsedWith().asItem().getSlot());
		}
		return true;
	}

}
