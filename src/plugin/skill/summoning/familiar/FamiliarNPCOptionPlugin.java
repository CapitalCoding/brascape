package plugin.skill.summoning.familiar;

import org.wildscape.cache.def.impl.NPCDefinition;
import org.wildscape.game.content.skill.member.summoning.familiar.BurdenBeast;
import org.wildscape.game.content.skill.member.summoning.familiar.Familiar;
import org.wildscape.game.interaction.OptionHandler;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.world.update.flag.player.FaceLocationFlag;
import org.wildscape.plugin.Plugin;

/**
 * Represents the class used for handling the npc options of familiars.
 * @author Emperor
 * @author 'Vexia
 * @version 2.0
 */
public final class FamiliarNPCOptionPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.setOptionHandler("pick-up", this);
		NPCDefinition.setOptionHandler("interact-with", this);
		NPCDefinition.setOptionHandler("interact", this);
		NPCDefinition.setOptionHandler("store", this);
		NPCDefinition.setOptionHandler("withdraw", this);
		return this;
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		if (!(node instanceof Familiar)) {
			return false;
		}
		Familiar familiar = (Familiar) node;
		if (familiar.getOwner() != player) {
			player.getPacketDispatch().sendMessage("This is not your familiar.");
			return true;
		}
		switch (option) {
		case "pick-up":
			player.faceLocation(FaceLocationFlag.getFaceLocation(player, familiar));
			player.getFamiliarManager().pickup();
			break;
		case "interact-with":
			player.getDialogueInterpreter().open(343823);
			break;
		case "interact":
			player.getDialogueInterpreter().open(node.getId(), node);
			break;
		case "store":
		case "withdraw":
			if (!familiar.isBurdenBeast()) {
				player.getPacketDispatch().sendMessage("This is not a beast of burden.");
				break;
			}
			((BurdenBeast) familiar).openInterface();
			break;
		}
		return true;
	}

}
