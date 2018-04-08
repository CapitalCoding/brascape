package plugin.skill.gather;

import org.wildscape.cache.def.impl.ObjectDefinition;
import org.wildscape.game.content.skill.free.gather.GatheringSkillPulse;
import org.wildscape.game.interaction.OptionHandler;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.object.GameObject;
import org.wildscape.plugin.Plugin;

/**
 * Handles the gathering skill option handler plugin.
 * @author Emperor
 * @version 1.0
 */
public final class GatheringSkillOptionPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.setOptionHandler("chop down", this);
		ObjectDefinition.setOptionHandler("chop-down", this);
		ObjectDefinition.setOptionHandler("mine", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		player.getPulseManager().run(new GatheringSkillPulse(player, (GameObject) node));
		return true;
	}

}