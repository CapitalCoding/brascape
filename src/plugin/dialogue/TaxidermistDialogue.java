package plugin.dialogue;

import org.wildscape.game.content.dialogue.DialoguePlugin;
import org.wildscape.game.content.dialogue.FacialExpression;
import org.wildscape.game.node.entity.npc.NPC;
import org.wildscape.game.node.entity.player.Player;

/**
 * Handles the TaxidermistDialogue dialogue.
 * @author 'Vexia
 */
public class TaxidermistDialogue extends DialoguePlugin {

	public TaxidermistDialogue() {

	}

	public TaxidermistDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new TaxidermistDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NORMAL, "Oh, hello. Have you got something you want", "preserving?");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Yes please", "Not right now");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NORMAL, "Yes please.");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NORMAL, "Not right now.");
				stage = 20;
				break;

			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "Give it to me to look at then.");
			stage = 11;
			break;
		case 11:
			end();
			break;
		case 20:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 4246 };
	}
}