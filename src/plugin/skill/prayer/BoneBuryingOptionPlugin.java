package plugin.skill.prayer;

import org.wildscape.cache.def.impl.ItemDefinition;
import org.wildscape.game.content.global.Bones;
import org.wildscape.game.content.skill.Skills;
import org.wildscape.game.interaction.OptionHandler;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.entity.player.info.portal.Perks;
import org.wildscape.game.node.entity.player.link.audio.Audio;
import org.wildscape.game.node.item.Item;
import org.wildscape.game.system.task.Pulse;
import org.wildscape.game.world.GameWorld;
import org.wildscape.game.world.update.flag.context.Animation;
import org.wildscape.plugin.Plugin;
import org.wildscape.tools.RandomFunction;

/**
 * Represents the bone bury option plugin.
 * @author Vexia
 */
public final class BoneBuryingOptionPlugin extends OptionHandler {

	/**
	 * Represents the animation to use.
	 */
	private static final Animation ANIMATION = new Animation(827);

	/**
	 * Represents the sound to use.
	 */
	private static final Audio SOUND = new Audio(2738, 10, 1);

	@Override
	public boolean handle(final Player player, Node node, String option) {
		if (player.getAttribute("delay:bury", -1) > GameWorld.getTicks()) {
			return true;
		}
		player.setAttribute("delay:bury", GameWorld.getTicks() + 2);
		final Item item = (Item) node;
		Bones bone = Bones.forId(item.getId());
		if (bone == null) {
			bone = Bones.BONES;
		}
		if (item.getSlot() < 0) {
			return false;
		}
		boolean remove = true;
		if (player.getDetails().getShop().hasPerk(Perks.DIVINE_INTERVENTION) && RandomFunction.random(140) < 10) {
			remove = false;
		}
		if (!remove) {
			player.sendMessage("The gods intervene and you keep your bones!");
		}
		if (remove) {
			if (player.getInventory().replace(null, item.getSlot()) != item) {
				return false;
			}
		}
		player.lock(2);
		player.animate(ANIMATION);
		player.getPacketDispatch().sendMessage("You dig a hole in the ground...");
		player.getAudioManager().send(SOUND);
		final Bones bonee = bone;
		GameWorld.submit(new Pulse(2, player) {
			@Override
			public boolean pulse() {
				player.getPacketDispatch().sendMessage("You bury the bones.");
				player.getSkills().addExperience(Skills.PRAYER, bonee.equals(Bones.LAVA_DRAGON_BONES) && player.getLocation().getY() >= 3794 && player.getLocation().getY() <= 3859 ? bonee.getExperience() * 3 : bonee.getExperience(), true);	
				return true;
			}
		});
		return true;
	}

	@Override
	public boolean isWalk() {
		return false;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.setOptionHandler("bury", this);
		return this;
	}

}
