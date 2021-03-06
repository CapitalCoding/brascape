package plugin.skill.slayer.dungeon;

import java.util.HashMap;
import java.util.Map;

import org.wildscape.cache.def.impl.NPCDefinition;
import org.wildscape.game.content.skill.member.slayer.Equipment;
import org.wildscape.game.content.skill.member.slayer.Tasks;
import org.wildscape.game.interaction.Option;
import org.wildscape.game.node.Node;
import org.wildscape.game.node.entity.Entity;
import org.wildscape.game.node.entity.combat.CombatStyle;
import org.wildscape.game.node.entity.npc.AbstractNPC;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.system.task.Pulse;
import org.wildscape.game.world.GameWorld;
import org.wildscape.game.world.map.Direction;
import org.wildscape.game.world.map.Location;
import org.wildscape.game.world.map.zone.MapZone;
import org.wildscape.game.world.map.zone.ZoneBorders;
import org.wildscape.game.world.map.zone.ZoneBuilder;
import org.wildscape.game.world.update.flag.context.Animation;
import org.wildscape.plugin.Plugin;
import org.wildscape.plugin.PluginManager;
import org.wildscape.tools.RandomFunction;

/**
 * Handles the lumbridge dungeon.
 * @author Vexia
 */
public final class LumbridgeDungeon extends MapZone implements Plugin<Object> {

	/**
	 * The beast locations to check.
	 */
	private static final Map<Location, WallBeastNPC> BEASTS = new HashMap<>();

	/**
	 * Constructs a new {@code LumbridgeDungeon} {@code Object}.
	 */
	public LumbridgeDungeon() {
		super("lumbridge swamp dungeon", true);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ZoneBuilder.configure(this);
		PluginManager.definePlugin(new WallBeastNPC());
		return this;
	}

	/*
	 * @Override public boolean move(Entity e, Location loc, Location dest) { if
	 * (!e.getLocks().isMovementLocked() && e instanceof Player) { final
	 * WallBeastNPC npc = BEASTS.get(e.getLocation()); if (npc != null) {
	 * e.setDirection(Direction.getLogicalDirection(loc, dest)); return false; }
	 * } return super.move(e, loc, dest); }
	 */

	@Override
	public void locationUpdate(Entity entity, Location last) {
		if (entity instanceof Player) {
			final Player player = (Player) entity;
			final WallBeastNPC npc = BEASTS.get(last);
			if (npc != null && npc.canAttack(player)) {
				npc.trigger(player);
			}
		}
	}

	/**
	 * Checks if the player has the helmet.
	 * @param player the player.
	 * @return {@code True} if so.
	 */
	private boolean hasHelmet(final Player player) {
		return Equipment.SPINY_HELMET.hasEquipment(player);
	}

	@Override
	public boolean interact(Entity e, Node target, Option option) {
		return super.interact(e, target, option);
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

	@Override
	public void configure() {
		register(new ZoneBorders(3137, 9534, 3295, 9602));
	}

	/**
	 * Handles the wall beast npc.
	 * @author Vexia
	 */
	public final class WallBeastNPC extends AbstractNPC {

		/**
		 * Constructs a new {@code WallBeastNPC} {@code Object}.
		 * @param id the id.
		 * @param location the location.
		 */
		public WallBeastNPC(int id, Location location) {
			super(id, location);
		}

		/**
		 * Constructs a new {@code WallBeastNPC} {@code Object}.
		 */
		public WallBeastNPC() {
			super(7823, null);
		}

		@Override
		public AbstractNPC construct(int id, Location location, Object... objects) {
			return new WallBeastNPC(id, location);
		}

		@Override
		public void init() {
			super.init();
			getLocks().lockMovement(Integer.MAX_VALUE);
			BEASTS.put(getLocation().transform(Direction.SOUTH, 1), this);
		}

		@Override
		public void handleTickActions() {
			super.handleTickActions();

		}

		/**
		 * Triggers the beast.
		 * @param player the player.
		 */
		public void trigger(final Player player) {
			boolean isProtected = hasHelmet(player);
			player.face(this);
			if (!isProtected) {
				animate(NPCDefinition.forId(7823).getCombatAnimation(3));
				player.animate(Animation.create(1810));
				GameWorld.submit(new Pulse(8, player) {
					@Override
					public boolean pulse() {
						getAnimator().reset();
						player.getAnimator().reset();
						player.getImpactHandler().handleImpact(WallBeastNPC.this, RandomFunction.random(1, 18), CombatStyle.MELEE);
						return true;
					}
				});
				return;
			} else {
				transform(7823);
				attack(player);
			}
		}
		
		@Override
		public void finalizeDeath(Entity killer) {
			//TODO
		}

		@Override
		public boolean isPoisonImmune() {
			return true;
		}

		@Override
		public int[] getIds() {
			return Tasks.WALL_BEASTS.getTask().getNpcs();
		}

	}

}
