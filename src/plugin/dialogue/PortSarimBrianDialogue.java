package plugin.dialogue;

import org.wildscape.game.content.dialogue.DialoguePlugin;
import org.wildscape.game.content.dialogue.FacialExpression;
import org.wildscape.game.node.entity.npc.NPC;
import org.wildscape.game.node.entity.player.Player;

/**
 * Handles the PortSarimBrianDialogue dialogue.
 * @author 'Vexia
 */
public class PortSarimBrianDialogue extends DialoguePlugin {

	public PortSarimBrianDialogue() {

	}

	public PortSarimBrianDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 559 };
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NORMAL, "So, are you selling something?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NORMAL, "'Ello.");
				stage = 20;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "Yep, take a look at these great axes.");
			stage = 11;
			break;
		case 11:
			end();
			npc.openShop(player);
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "'Ello.");
			stage = 21;
			break;
		case 21:
			end();
			break;
		}
		return true;
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new PortSarimBrianDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendOptions("Select an Option", "So, are you selling something?", "'Ello.");
		stage = 0;
		return true;
	}
}