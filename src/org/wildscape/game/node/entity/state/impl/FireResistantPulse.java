package org.wildscape.game.node.entity.state.impl;

import java.nio.ByteBuffer;

import org.wildscape.game.node.entity.Entity;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.entity.state.StatePulse;
import org.wildscape.game.world.GameWorld;

/**
 * The pulse used for fire resistant.
 * @author Vexia
 */
public class FireResistantPulse extends StatePulse {

	/**
	 * The time to finish.
	 */
	private static int END_TIME = GameWorld.getSettings().isDevMode() ? 30 : 600;

	/**
	 * The current tick.
	 */
	private int currentTick;
	
	/**
	 * If the potion is an extended antifire.
	 */
	private boolean extended;

	/**
	 * Constructs a new {@Code FireResistantPulse} {@Code Object}
	 * @param entity the entity.
	 * @param ticks the ticks.
	 */
	public FireResistantPulse(Entity entity, int ticks, int currentTick, boolean extended) {
		super(entity, ticks);
		this.extended = extended;
		this.currentTick = currentTick;
	}

	@Override
	public boolean isSaveRequired() {
		return true;
	}

	@Override
	public void save(ByteBuffer buffer) {
		buffer.putInt(currentTick);
	}

	@Override
	public StatePulse parse(Entity entity, ByteBuffer buffer) {
		return new FireResistantPulse(entity, 1, buffer.getInt(), extended);
	}

	@Override
	public StatePulse create(Entity entity, Object... args) {
		return new FireResistantPulse(entity, 1, 0, (boolean) args[0]);
	}

	@Override
	public boolean pulse() {
		if(extended && currentTick == 0 && END_TIME < 1200){
			END_TIME += 600;
		}
		if (entity instanceof Player) {
			if (currentTick == (END_TIME - 25)) {
				entity.asPlayer().getPacketDispatch().sendMessage("<col=7f007f>Your resistance to dragonfire is about to run out.");
			} else if (currentTick == (END_TIME - 1)) {
				entity.asPlayer().getPacketDispatch().sendMessage("<col=7f007f>Your resistance to dragonfire has run out.");
			}
		}
		return ++currentTick >= END_TIME;
	}

}