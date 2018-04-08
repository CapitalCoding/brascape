package plugin.skill.fletching;

import org.wildscape.game.content.dialogue.SkillDialogueHandler;
import org.wildscape.game.content.dialogue.SkillDialogueHandler.SkillDialogue;
import org.wildscape.game.content.skill.member.fletching.items.gem.GemBolt;
import org.wildscape.game.content.skill.member.fletching.items.gem.GemBoltPulse;
import org.wildscape.game.interaction.NodeUsageEvent;
import org.wildscape.game.interaction.UseWithHandler;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.net.packet.PacketRepository;
import org.wildscape.net.packet.context.ChildPositionContext;
import org.wildscape.net.packet.out.RepositionChild;
import org.wildscape.plugin.Plugin;

/**
 * Represents the plugin used to handle gem bolt making.
 * @author 'Vexia
 * @version 1.0
 */
public class BoltGemPlugin extends UseWithHandler {

	/**
	 * Constructs a new {@code BoltGemPlugin} {@code Object}.
	 */
	public BoltGemPlugin() {
		super(45, 46, 9187, 9188, 9189, 9190, 9191, 9192, 9193, 9194);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (GemBolt bolt : GemBolt.values()) {
			addHandler(bolt.getBase().getId(), ITEM_TYPE, this);
		}
		return this;
	}

	@Override
	public boolean handle(final NodeUsageEvent event) {
		final Player player = event.getPlayer();
		final GemBolt bolt =  GemBolt.forItems(event.getUsedItem(), event.getUsedWith().asItem());
		if (bolt == null) {
			return true;
		}
		SkillDialogueHandler handler = new SkillDialogueHandler(player, SkillDialogue.ONE_OPTION, bolt.getProduct()) {
			@Override
			public void create(final int amount, int index) {
				player.getPulseManager().run(new GemBoltPulse(player, event.getUsedItem(), bolt, amount));
			}
			@Override
			public int getAll(int index) {
				return player.getInventory().getAmount(event.getUsedItem());
			}
		};
		handler.open();
		PacketRepository.send(RepositionChild.class, new ChildPositionContext(player, 309, 2, 210, 10));
		return true;
	}

}
