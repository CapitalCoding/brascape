package plugin.interaction.npc;

import org.wildscape.cache.def.impl.NPCDefinition;
import org.wildscape.game.content.skill.free.crafting.TanningProduct;
import org.wildscape.game.interaction.OptionHandler;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.npc.NPC;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.plugin.Plugin;

/**
 * @author 'Vexia
 */
public class TanningNPC extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.forId(1041).getConfigurations().put("option:trade", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		TanningProduct.open(player, ((NPC) node).getId());
		return true;
	}

}
