package plugin.interaction.object;

import org.wildscape.cache.def.impl.ItemDefinition;
import org.wildscape.cache.def.impl.ObjectDefinition;
import org.wildscape.game.content.global.tutorial.TutorialSession;
import org.wildscape.game.content.global.tutorial.TutorialStage;
import org.wildscape.game.content.skill.free.gather.SkillingResource;
import org.wildscape.game.interaction.OptionHandler;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.object.GameObject;
import org.wildscape.game.system.task.Pulse;
import org.wildscape.game.world.GameWorld;
import org.wildscape.plugin.Plugin;

/**
 * Handles the prospecting of an ore.
 * @author 'Vexia
 */
public class ProspectOrePlugin extends OptionHandler {

	@Override
	public boolean handle(final Player player, Node node, String option) {
		final GameObject object = (GameObject) node;
		int tut_stage = TutorialSession.getExtension(player).getStage();
		if (tut_stage == 31 && object.getId() == 3043) {
			GameWorld.submit(new Pulse(1) {
				int count = 0;

				@Override
				public boolean pulse() {
					if (count == 1) {
						TutorialStage.load(player, 32, false);
					} else if (count >= 2) {
						TutorialStage.load(player, 33, false);
						return true;
					}
					count++;
					return false;
				}
			});
		}
		if (tut_stage == 33 && object.getId() == 3042) {
			GameWorld.submit(new Pulse(1) {
				int count = 0;

				@Override
				public boolean pulse() {
					if (count == 1) {
						TutorialStage.load(player, 34, false);
						return true;
					}
					count++;
					return false;
				}
			});
		}
		final SkillingResource rock = SkillingResource.forId(object.getId());
		if (rock == null) {
			player.getPacketDispatch().sendMessage("There is no ore currently available in this rock.");
			return true;
		}
		player.getPacketDispatch().sendMessage("You examine the rock for ores...");
		player.getPulseManager().run(new Pulse(3, player, object) {
			@Override
			public boolean pulse() {
				player.getPacketDispatch().sendMessage("This rock contains " + ItemDefinition.forId(rock.getReward()).getName().toLowerCase() + ".");
				return true;
			}
		});
		return true;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.setOptionHandler("prospect", this);
		return this;
	}

}
