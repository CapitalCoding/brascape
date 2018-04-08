package plugin.skill.summoning;

import org.wildscape.cache.def.impl.ObjectDefinition;
import org.wildscape.game.content.skill.Skills;
import org.wildscape.game.content.skill.member.summoning.SummoningCreator;
import org.wildscape.game.interaction.OptionHandler;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.world.update.flag.context.Animation;
import org.wildscape.game.world.update.flag.context.Graphics;
import org.wildscape.plugin.Plugin;

/**
 * Represents the option used on the summoning obelisk.
 * @author 'Vexia
 * @author Emperor
 */
public final class ObeliskOptionPlugin extends OptionHandler {

	@Override
	public boolean handle(Player player, Node node, String option) {
		switch (option) {
		case "infuse-pouch":
			SummoningCreator.open(player, true);
			return true;
		case "renew-points":
			if (player.getSkills().getLevel(Skills.SUMMONING) == player.getSkills().getStaticLevel(Skills.SUMMONING)) {
				player.getPacketDispatch().sendMessage("You already have full summoning points.");
				return true;
			}
			player.visualize(Animation.create(8502), Graphics.create(1308));
			player.getSkills().setLevel(Skills.SUMMONING, player.getSkills().getStaticLevel(Skills.SUMMONING));
			player.getPacketDispatch().sendMessage("You renew your summoning points.");
			return true;
		}
		return false;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.setOptionHandler("infuse-pouch", this);
		ObjectDefinition.setOptionHandler("renew-points", this);
		return this;
	}

}
