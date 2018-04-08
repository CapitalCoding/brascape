package org.wildscape.game.world.map.zone;

import org.wildscape.game.world.callback.CallBack;
import org.wildscape.game.world.map.zone.impl.BankZone;
import org.wildscape.game.world.map.zone.impl.DarkZone;
import org.wildscape.game.world.map.zone.impl.DonatorZone;
import org.wildscape.game.world.map.zone.impl.KaramjaZone;
import org.wildscape.game.world.map.zone.impl.ModeratorZone;
import org.wildscape.game.world.map.zone.impl.MultiwayCombatZone;
import org.wildscape.game.world.map.zone.impl.WildernessZone;

/**
 * Loads all the default zones.
 * @author Emperor
 */
public class ZoneBuilder implements CallBack {

	@Override
	public boolean call() {
		configure(WildernessZone.getInstance());
		configure(MultiwayCombatZone.getInstance());
		configure(new ModeratorZone());
		configure(new DarkZone());
		configure(new KaramjaZone());
		configure(new BankZone());
		configure(DonatorZone.getInstance());
		return true;
	}

	/**
	 * Configures the map zone.
	 * @param zone The map zone.
	 */
	public static void configure(MapZone zone) {
		zone.setUid(zone.getName().hashCode());
		zone.configure();
	}
}