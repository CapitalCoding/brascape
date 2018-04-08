package plugin.dialogue;

import org.wildscape.game.content.dialogue.DialoguePlugin;
import org.wildscape.game.content.dialogue.FacialExpression;
import org.wildscape.game.node.entity.npc.NPC;
import org.wildscape.game.node.entity.player.Player;

/**
 * Represents the entrana church monk.
 * @author 'Vexia
 * @version 1.0
 */
public final class EntranaChurchMonk extends DialoguePlugin {

	/**
	 * Constructs a new {@code EntranaChurchMonk} {@code Object}.
	 */
	public EntranaChurchMonk() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code EntranaChurchMonk} {@code Object}.
	 * @param player the player.
	 */
	public EntranaChurchMonk(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new EntranaChurchMonk(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NORMAL, "Greetings traveller.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 222 };
	}
}