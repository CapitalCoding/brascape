package plugin.npc;

import org.wildscape.game.content.global.tutorial.TutorialSession;
import org.wildscape.game.content.global.tutorial.TutorialStage;
import org.wildscape.game.node.entity.Entity;
import org.wildscape.game.node.entity.combat.BattleState;
import org.wildscape.game.node.entity.combat.CombatStyle;
import org.wildscape.game.node.entity.npc.AbstractNPC;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.world.map.Location;

/**
 * Represents the tutorial chicken npc.
 * @author 'Vexia
 * @version 1.0
 */
public final class TutorialChickenNPC extends AbstractNPC {

	/**
	 * Constructs a new {@code TutorialChickenNPC} {@code Object}.
	 * @param id the id.
	 * @param location the location.
	 */
	public TutorialChickenNPC(int id, Location location) {
		super(id, location, true);
	}

	/**
	 * Constructs a new {@code TutorialChickenNPC} {@code Object}.
	 */
	public TutorialChickenNPC() {
		super(0, null);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new TutorialChickenNPC(id, location);
	}

	@Override
	public void onImpact(Entity entity, BattleState state) {
		super.onImpact(entity, state);
		if (state.getStyle() == CombatStyle.MAGIC) {
			final Player player = (Player) entity;
			if (TutorialSession.getExtension(player).getStage() == 70) {
				TutorialStage.load(player, 71, false);
			}
		}
	}

	@Override
	public void finalizeDeath(Entity killer) {
		super.finalizeDeath(killer);
	}

	@Override
	public int[] getIds() {
		return new int[] { 41 };
	}

}
