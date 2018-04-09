package org.wildscape.game.content.skill.member.slayer;

import java.util.Arrays;

import org.wildscape.ServerConstants.TeleportDestinations;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.world.map.Location;

public enum SlayerTeleport {
	ABERRANT_SPECTRES(TeleportDestinations.SLAYER_TOWER),
	ABYSSAL_DEMONS(TeleportDestinations.SLAYER_TOWER),
	ANKOU(TeleportDestinations.STRONGHOLD_OF_SECURITY_4TH_FLOOR),
	BIRDS(TeleportDestinations.LUMBRIDGE_EAST),
	AVIANSIES(TeleportDestinations.TROLLHEIM),
	BANSHEE(TeleportDestinations.SLAYER_TOWER),
	BASILISKS(TeleportDestinations.RELLEKKA_SLAYER_DUNGEON),
	BATS(TeleportDestinations.TARVERLEY_DUNGEON),
	BEARS(TeleportDestinations.STEALING_CREATION),
	BLACK_DEMONS(TeleportDestinations.EDGEVILLE_DUNGEON),
	BLACK_DRAGONS(TeleportDestinations.TARVERLEY_DUNGEON),
	BLOODVELDS(TeleportDestinations.SLAYER_TOWER),
	BLUE_DRAGONS(TeleportDestinations.TARVERLEY_DUNGEON),
	BRINE_RATS(TeleportDestinations.BRINE_CAVERN),
	BRONZE_DRAGONS(TeleportDestinations.BRIMHAVEN_DUNGEON),
	CATABLEPONS(TeleportDestinations.STRONGHOLD_OF_SECURITY_3RD_FLOOR),
	CAVE_BUG(TeleportDestinations.LUMBRIDGE_SWAMP),
	CAVE_CRAWLERS(TeleportDestinations.RELLEKKA_SLAYER_DUNGEON),
	CAVE_HORRORS(TeleportDestinations.MOS_HARMLESS),
	CAVE_SLIMES(TeleportDestinations.LUMBRIDGE_SWAMP),
	COCKATRICES(TeleportDestinations.RELLEKKA_SLAYER_DUNGEON),
	COWS(TeleportDestinations.LUMBRIDGE_EAST),
	CRAWLING_HAND(TeleportDestinations.SLAYER_TOWER),
	CROCODILES(TeleportDestinations.POLLNIVNEACH),
	CYCLOPES(TeleportDestinations.BURTHORPE),
	DAGANNOTHS(TeleportDestinations.DAGGANNOTH_DUNGEON),
	DARK_BEASTS(TeleportDestinations.DARK_BEAST_DUNGEON),
	DESERT_LIZARDS(TeleportDestinations.DESERT_LIZARD),
	DOG(TeleportDestinations.BRIMHAVEN_DUNGEON),
	DUST_DEVILS(TeleportDestinations.POLLNIVNEACH),
	DWARF(TeleportDestinations.ICE_MOUNTAIN),
	EARTH_WARRIORS(TeleportDestinations.EDGEVILLE_DUNGEON),
	FIRE_GIANTS(TeleportDestinations.WATERFALL_DUNGEON),
	FLESH_CRAWLERS(TeleportDestinations.STRONGHOLD_OF_SECURITY_2ND_FLOOR),
	GARGOYLES(TeleportDestinations.SLAYER_TOWER),
	GHOSTS(TeleportDestinations.TARVERLEY_DUNGEON),
	GHOULS(TeleportDestinations.SLAYER_TOWER),
	GOBLINS(TeleportDestinations.LUMBRIDGE_EAST),
	GORAKS(TeleportDestinations.TROLLHEIM),
	GREATER_DEMONS(TeleportDestinations.NIEVE_CAVE),
	GREEN_DRAGON(TeleportDestinations.GREEN_DRAGONS),
	HARPIE_BUG_SWARMS(TeleportDestinations.TAI_BWO_WANNAI),
	HELLHOUNDS(TeleportDestinations.NIEVE_CAVE),
	HILL_GIANTS(TeleportDestinations.EDGEVILLE_DUNGEON),
	HOBGOBLINS(TeleportDestinations.EDGEVILLE_DUNGEON),
	ICE_FIENDS(TeleportDestinations.ICE_MOUNTAIN),
	ICE_GIANTS(TeleportDestinations.ASGARNIAN_DUNGEON),
	ICE_WARRIOR(TeleportDestinations.ASGARNIAN_DUNGEON),
	INFERNAL_MAGES(TeleportDestinations.SLAYER_TOWER),
	IRON_DRAGONS(TeleportDestinations.NIEVE_CAVE),
	JELLIES(TeleportDestinations.RELLEKKA_SLAYER_DUNGEON),
	JUNGLE_HORRORS(TeleportDestinations.MOS_HARMLESS),
	KALPHITES(TeleportDestinations.KALPHITE_DUNGEON),
	KURASKS(TeleportDestinations.RELLEKKA_SLAYER_DUNGEON),
	LESSER_DEMONS(TeleportDestinations.KARAMJA_DUNGEON),
	MITHRIL_DRAGON(TeleportDestinations.ANCIENT_CAVERN),
	MINOTAURS(TeleportDestinations.STRONGHOLD_OF_SECURITY_1ST_FLOOR),
	MOGRES(TeleportDestinations.ASGARNIAN_DUNGEON),
	MONKEYS(TeleportDestinations.ARDOUGNE_ZOO),
	MOSS_GIANTS(TeleportDestinations.EDGEVILLE_DUNGEON),
	NECHRYAELS(TeleportDestinations.SLAYER_TOWER),
	OGRES(TeleportDestinations.CASTLE_WARS),
	OTHERWORDLY_BEING(TeleportDestinations.ZANARIS_ENTRANCE),
	PYREFIENDS(TeleportDestinations.RELLEKKA_SLAYER_DUNGEON),
	RATS(TeleportDestinations.LUMBRIDGE),
	RED_DRAGONS(TeleportDestinations.BRIMHAVEN_DUNGEON),
	ROCK_SLUGS(TeleportDestinations.RELLEKKA_SLAYER_DUNGEON),
	SCORPIONS(TeleportDestinations.ARDOUGNE_ZOO),
	SHADE(TeleportDestinations.STRONGHOLD_OF_SECURITY_4TH_FLOOR),
	SKELETONS(TeleportDestinations.STRONGHOLD_OF_SECURITY_4TH_FLOOR),
	SPIDERS(TeleportDestinations.STRONGHOLD_OF_SECURITY_3RD_FLOOR),
	SPIRTUAL_MAGES(TeleportDestinations.TROLLHEIM),
	SPIRTUAL_RANGERS(TeleportDestinations.TROLLHEIM),
	SPIRTUAL_WARRIORS(TeleportDestinations.TROLLHEIM),
	STEEL_DRAGONS(TeleportDestinations.BRIMHAVEN_DUNGEON),
	TROLLS(TeleportDestinations.TROLLHEIM),
	TUROTHS(TeleportDestinations.RELLEKKA_SLAYER_DUNGEON),
	TZHAAR(TeleportDestinations.TZHAAR),
	VAMPIRES(TeleportDestinations.CANIFIS),
	WATERFIENDS(TeleportDestinations.ANCIENT_CAVERN),
	WEREWOLFS(TeleportDestinations.CANIFIS),
	WOLVES(TeleportDestinations.STRONGHOLD_OF_SECURITY_1ST_FLOOR),
	ZOMBIES(TeleportDestinations.STRONGHOLD_OF_SECURITY_2ND_FLOOR),
	JAD(TeleportDestinations.TZHAAR),
	CHAOS_ELEMENTAL(TeleportDestinations.BOUNTY_HUNTER),
	GIANT_MOLE(TeleportDestinations.FALADOR),
	KING_BLACK_DRAGON(TeleportDestinations.BOUNTY_HUNTER),
	COMMANDER_ZILYANA(TeleportDestinations.TROLLHEIM),
	GENERAL_GRARDOOR(TeleportDestinations.TROLLHEIM),
	KRIL_TSUTSAROTH(TeleportDestinations.TROLLHEIM),
	KREE_ARRA(TeleportDestinations.TROLLHEIM),
	SKELETAL_WYVERN(TeleportDestinations.ASGARNIAN_DUNGEON);
			
	private TeleportDestinations tp;

	public TeleportDestinations getTp() {
		return tp;
	}

	private SlayerTeleport(TeleportDestinations tp) {
		this.tp = tp;
	}
	
	public static SlayerTeleport getByAssignedTask(Player p) {
		if(!p.getSlayer().hasTask())
			return null;
		String taskName = Tasks.forValue(p.getSlayer().getTask()).name();
		for(SlayerTeleport slayerTps : SlayerTeleport.values()) {
			if(slayerTps.name().equals(taskName)) {
				return slayerTps;
			}
		}
		//Arrays.asList(SlayerTeleport.values()).stream().filter(i->i.name().equals(p.getSlayer().getTask().getName()));
		
		return null;
	}
}
