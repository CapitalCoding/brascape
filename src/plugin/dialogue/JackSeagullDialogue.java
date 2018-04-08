package plugin.dialogue;

import org.wildscape.game.content.dialogue.DialoguePlugin;
import org.wildscape.game.content.dialogue.FacialExpression;
import org.wildscape.game.node.entity.npc.NPC;
import org.wildscape.game.node.entity.player.Player;

/**
 * Represents the hack seafull dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
public final class JackSeagullDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code JackSeagullDialogue} {@code Object}.
	 */
	public JackSeagullDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code JackSeagullDialogue} {@code Object}.
	 * @param player the player.
	 */
	public JackSeagullDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new JackSeagullDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NORMAL, "Arrr, matey!");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("What would you like to say?", "What are you doing here?", "Have you got any quests I could do?");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NORMAL, "What are you doing here?");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NORMAL, "Have you got any quests I could do?");
				stage = 20;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "Drinking.");
			stage = 11;
			break;
		case 11:
			interpreter.sendDialogues(player, FacialExpression.NORMAL, "Fair enough.");
			stage = 12;
			break;
		case 12:
			end();
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "Nay, I've nothing for ye to do.");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(player, FacialExpression.NORMAL, "Thanks.");
			stage = 22;
			break;
		case 22:
			end();
			break;
		}

		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 2690 };
	}

}