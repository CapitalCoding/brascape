package plugin.random.drilldemon;

import java.nio.ByteBuffer;

import org.wildscape.game.content.ame.AntiMacroEvent;
import org.wildscape.game.interaction.Option;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.Entity;
import org.wildscape.game.node.entity.npc.NPC;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.system.task.Pulse;
import org.wildscape.game.world.GameWorld;
import org.wildscape.game.world.map.Location;
import org.wildscape.game.world.map.RegionManager;
import org.wildscape.game.world.update.flag.context.Graphics;
import org.wildscape.plugin.PluginManager;

/**
 * Handles the drill demon event.
 * @author Vexia
 */
public final class DrillDemonEvent extends AntiMacroEvent {

	/**
	 * Constructs a new {@code DrillDemonEvent} {@code Object}.
	 */
	public DrillDemonEvent() {
		this(null);
	}

	/**
	 * Constructs a new {@code DrillDemonEvent} {@code Object}.
	 */
	public DrillDemonEvent(Player player) {
		super("drill demon", false, false);
		this.player = player;
	}

	@Override
	public void save(ByteBuffer buffer) {

	}

	@Override
	public void parse(ByteBuffer buffer) {

	}

	@Override
	public boolean start(Player player, boolean login, Object... args) {
		if (login) {
			return true;
		}
		final NPC sergeant = NPC.create(2790, player.getLocation());
		if (!initSergeant(sergeant)) {
			return false;
		}
		player.lock(4);
		sergeant.lock(4);
		sergeant.face(player);
		player.graphics(Graphics.create(86), 3);
		sergeant.sendChat("Private " + player.getUsername() + "! Get yourself to the parade ground ASAP!");
		GameWorld.submit(new Pulse(3, player, sergeant) {
			@Override
			public boolean pulse() {
				return true;
			}
		});
		return true;
	}

	@Override
	public AntiMacroEvent create(Player player) {
		return new DrillDemonEvent(player);
	}

	@Override
	public boolean interact(Entity e, Node target, Option option) {
		return super.interact(e, target, option);
	}

	/**
	 * Initializes the sergeant (temporary).
	 * @param sergeant the sergeant.
	 * @return {@code True} if so.
	 */
	private boolean initSergeant(NPC sergeant) {
		final Location start = RegionManager.getSpawnLocation(player, sergeant);
		if (start == null) {
			terminate();
			return false;
		}
		sergeant.setLocation(start);
		sergeant.init();
		return true;
	}

	@Override
	public void register() {
		PluginManager.definePlugin(new SergeantDamienNPC());
	}

	@Override
	public Location getSpawnLocation() {
		return null;
	}

	@Override
	public void configure() {

	}

}
