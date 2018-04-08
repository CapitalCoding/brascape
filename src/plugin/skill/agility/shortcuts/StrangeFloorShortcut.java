package plugin.skill.agility.shortcuts;

import org.wildscape.game.content.skill.member.agility.AgilityShortcut;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.impl.ForceMovement;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.object.GameObject;
import org.wildscape.game.system.task.Pulse;
import org.wildscape.game.world.GameWorld;
import org.wildscape.game.world.map.Location;
import org.wildscape.game.world.update.flag.context.Animation;

/**
 * Handles the strange floor shortcut.
 * @author Vexia
 */
public class StrangeFloorShortcut extends AgilityShortcut {

	/**
	 * Represents the running animation.
	 */
	private static final Animation RUNNING_ANIM = new Animation(1995);

	/**
	 * Represents the jumping animation.
	 */
	private static final Animation JUMP_ANIM = new Animation(1603);

	/**
	 * Constructs a new {@Code StrangeFloorShortcut} {@Code
	 * Object}
	 */
	public StrangeFloorShortcut() {
		super(new int[] { 42334 }, 80, 0.0, "jump-over");
	}

	@Override
	public void run(final Player player, GameObject object, String option, boolean failed) {
		GameWorld.submit(new Pulse(1, player) {
			@Override
			public boolean pulse() {
				player.getAnimator().forceAnimation(JUMP_ANIM);
				return true;
			}
		});
		GameWorld.submit(new Pulse(2, player) {

			@Override
			public boolean pulse() {
				player.getAnimator().forceAnimation(RUNNING_ANIM);
				return true;
			}

		});
		ForceMovement.run(player, player.getLocation().getX() >= 2880 ? Location.create(2881, 9813, 0) : Location.create(2877, 9813, 0), player.getLocation().getX() >= 2880 ? Location.create(2877, 9813, 0) : Location.create(2881, 9813, 0), RUNNING_ANIM, 13);

	}

	@Override
	public Location getDestination(Node node, Node n) {
		return node.getLocation().getX() >= 2880 ? Location.create(2881, 9813, 0) : Location.create(2877, 9813, 0);
	}

}
