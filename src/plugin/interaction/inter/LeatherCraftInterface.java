package plugin.interaction.inter;

import org.wildscape.game.component.Component;
import org.wildscape.game.component.ComponentDefinition;
import org.wildscape.game.component.ComponentPlugin;
import org.wildscape.game.content.skill.free.crafting.armour.LeatherCrafting;
import org.wildscape.game.content.skill.free.crafting.armour.SoftCraftPulse;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.entity.player.link.RunScript;
import org.wildscape.game.node.item.Item;
import org.wildscape.plugin.Plugin;

/**
 * Represents the leather crafting interface.
 * @author 'Vexia
 * @version 1.0
 */
public final class LeatherCraftInterface extends ComponentPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(154, this);
		return this;
	}

	@Override
	public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
		int amount = 0;
		final LeatherCrafting.SoftLeather soft = LeatherCrafting.SoftLeather.forButton(button);
		if (soft == null) {
			return true;
		}
		switch (opcode) {
		case 155:
			amount = 1;
			break;
		case 196:
			amount = 5;
			break;
		case 124:
			amount = player.getInventory().getAmount(new Item(LeatherCrafting.LEATHER));
			break;
		case 199:
			player.setAttribute("runscript", new RunScript() {
				@Override
				public boolean handle() {
					player.getPulseManager().run(new SoftCraftPulse(player, new Item(LeatherCrafting.LEATHER), soft, (int) getValue()));
					return true;
				}
			});
			player.getDialogueInterpreter().sendInput(false, "Enter amount:");
			return true;
		}
		player.getPulseManager().run(new SoftCraftPulse(player, null, soft, amount));
		return true;
	}
}
