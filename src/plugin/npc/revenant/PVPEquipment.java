package plugin.npc.revenant;

import org.wildscape.game.node.entity.Entity;
import org.wildscape.game.node.entity.combat.equipment.DegradableEquipment;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.item.Item;
import org.wildscape.plugin.PluginManager;

/**
 * Handles the degrading of PVP armour.
 * @author Vexia
 *
 */
public class PVPEquipment extends DegradableEquipment {

	/**
	 * Constructs a new @{Code PVPEquipment} object.
	 * @param slot The slot.
	 * @param itemIds The item ids.
	 */
	public PVPEquipment(int slot, int[] itemIds) {
		super(slot, itemIds);
	}
	
	/**
	 * Initializes the PVP equipment degrading.
	 */
	public static void init() {
		PluginManager.definePlugin(new PVPEquipment(1, null));
	}

	@Override
	public void degrade(Player player, Entity entity, Item item) {
		
	}

	@Override
	public int getDropItem(int itemId) {
		return 0;
	}

}
