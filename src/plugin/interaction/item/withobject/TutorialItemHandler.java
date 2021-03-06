package plugin.interaction.item.withobject;

import org.wildscape.game.content.global.tutorial.TutorialSession;
import org.wildscape.game.content.global.tutorial.TutorialStage;
import org.wildscape.game.content.skill.Skills;
import org.wildscape.game.content.skill.free.smithing.smelting.Bar;
import org.wildscape.game.interaction.NodeUsageEvent;
import org.wildscape.game.interaction.UseWithHandler;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.item.Item;
import org.wildscape.game.system.task.Pulse;
import org.wildscape.game.world.GameWorld;
import org.wildscape.game.world.update.flag.context.Animation;
import org.wildscape.plugin.Plugin;

/**
 * @author 'Vexia
 */
public class TutorialItemHandler extends UseWithHandler {
	public TutorialItemHandler() {
		super(438, 436);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		addHandler(3044, OBJECT_TYPE, this);
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		final Player player = event.getPlayer();
		if (player.getInventory().containItems(438, 436)) {
			player.animate(new Animation(833));
			GameWorld.submit(new Pulse(2) {
				@Override
				public boolean pulse() {
					player.getInventory().remove(new Item(438, 1));
					player.getInventory().remove(new Item(436, 1));
					player.getInventory().add(Bar.BRONZE.getProduct());
					player.getSkills().addExperience(Skills.SMITHING, Bar.BRONZE.getExperience());
					if (TutorialSession.getExtension(player).getStage() == 39) {
						TutorialStage.load(player, 40, false);
					}
					return true;
				}

			});

		}
		return true;
	}

}
