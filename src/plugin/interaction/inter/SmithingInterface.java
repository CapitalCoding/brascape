package plugin.interaction.inter;

import org.wildscape.game.component.Component;
import org.wildscape.game.component.ComponentDefinition;
import org.wildscape.game.component.ComponentPlugin;
import org.wildscape.game.content.skill.free.smithing.BarType;
import org.wildscape.game.content.skill.free.smithing.Bars;
import org.wildscape.game.content.skill.free.smithing.SmithingPulse;
import org.wildscape.game.content.skill.free.smithing.SmithingType;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.entity.player.link.RunScript;
import org.wildscape.game.node.item.Item;
import org.wildscape.plugin.Plugin;

/**
 * @author 'Vexia
 */
public class SmithingInterface extends ComponentPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(300, this);
		return this;
	}

	@Override
	public boolean handle(final Player p, Component component, int opcode, int button, int slot, int itemId) {
		final int item = Bars.getItemId(button, (BarType) p.getGameAttributes().getAttribute("smith-type"));
		final Bars bar = Bars.forId(item);
		if (bar == null) {
			return true;
		}
		int amount = SmithingType.forButton(p, bar, button, bar.getBarType().getBarType());
		p.getGameAttributes().setAttribute("smith-bar", bar);
		p.getGameAttributes().setAttribute("smith-item", item);
		if (amount == -1) {
			p.setAttribute("runscript", new RunScript() {
				@Override
				public boolean handle() {
					final int ammount = (int) value;
					p.getPulseManager().run(new SmithingPulse(p, new Item((int) p.getGameAttributes().getAttribute("smith-item"), ammount), (Bars) p.getGameAttributes().getAttribute("smith-bar"), ammount));
					return false;
				}
			});
			p.getDialogueInterpreter().sendInput(false, "Enter the amount.");
			return true;
		}
		p.getPulseManager().run(new SmithingPulse(p, new Item(item, amount), Bars.forId(item), amount));
		return true;
	}
}