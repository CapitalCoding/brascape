package org.wildscape;

import org.wildscape.game.world.GameSettings;
import org.wildscape.game.world.GameWorld;
import org.wildscape.game.world.map.Location;
import org.wildscape.tools.RandomFunction;

/**
 * A class holding constants of the server.
 * @author Emperor
 * @author Vexia
 * 
 */
public final class ServerConstants {
	
	/**
	 * The cache path.
	 */
	public static final String CACHE_PATH = "data/cache/";

	/**
	 * The store path.
	 */
	public static final String STORE_PATH = "data/store/";
	
	/**
	 * The player account path.
	 */
	public static final String PLAYER_SAVE_PATH = "data/players/";

	/**
	 * The maximum amount of players.
	 */
	public static final int MAX_PLAYERS = (1 << 11) - 1;

	/**
	 * The maximum amount of NPCs.
	 */
	public static final int MAX_NPCS = (1 << 15) - 1;
	
	/**
	 * The start location for a fresh account.
	 */
	public static final Location START_LOCATION = Location.create(3094, 3107, 0);
	
	/**
	 * The main home teleport location.
	 */
	public static final Location HOME_LOCATION = Location.create(3164, 3471 + RandomFunction.random(0, 3), 0);
	
	/**
	 * The teleport destinations.
	 */
	public static final Object[][] TELEPORT_DESTINATIONS = { { Location.create(2974, 4383, 2), "corp", "corporal", "corporeal" }, { Location.create(2659, 2649, 0), "pc", "pest control", "pest" }, { Location.create(3293, 3184, 0), "al kharid", "alkharid", "kharid" }, { Location.create(3222, 3217, 0), "lumbridge", "lumby" }, { Location.create(3110, 3168, 0), "wizard tower", "wizards tower", "tower", "wizards" }, { Location.create(3083, 3249, 0), "draynor", "draynor village" }, { Location.create(3019, 3244, 0), "port sarim", "sarim" }, { Location.create(2956, 3209, 0), "rimmington" }, { Location.create(2965, 3380, 0), "fally", "falador" }, { Location.create(2895, 3436, 0), "taverly" }, { Location.create(3080, 3423, 0), "barbarian village", "barb" }, { Location.create(3213, 3428, 0), "varrock" }, { Location.create(3164, 3485, 0), "grand exchange", "ge" }, { Location.create(2917, 3175, 0), "karamja" }, { Location.create(2450, 5165, 0), "tzhaar" }, { Location.create(2795, 3177, 0), "brimhaven" }, { Location.create(2849, 2961, 0), "shilo village", "shilo" }, { Location.create(2605, 3093, 0), "yanille" }, { Location.create(2663, 3305, 0), "ardougne", "ardy" }, { Location.create(2450, 3422, 0), "gnome stronghold", "gnome" }, { Location.create(2730, 3485, 0), "camelot", "cammy", "seers" }, { Location.create(2805, 3435, 0), "catherby" }, { Location.create(2659, 3657, 0), "rellekka" }, { Location.create(2890, 3676, 0), "trollheim" }, { Location.create(2914, 3746, 0), "godwars", "gwd", "god wars" }, { Location.create(3180, 3684, 0), "bounty hunter", "bh" }, { Location.create(3272, 3687, 0), "clan wars", "clw" }, { Location.create(3090, 3957, 0), "mage arena", "mage", "magearena", "arena" }, { Location.create(3069, 10257, 0), "king black dragon", "kbd" }, { Location.create(3359, 3416, 0), "digsite" }, { Location.create(3488, 3489, 0), "canifis" }, { Location.create(3428, 3526, 0), "slayer tower", "slayer" }, { Location.create(3502, 9483, 2), "kalphite queen", "kq", "kalphite hive", "kalphite" }, { Location.create(3233, 2913, 0), "pyramid" }, { Location.create(3419, 2917, 0), "nardah" }, { Location.create(3482, 3090, 0), "uzer" }, { Location.create(3358, 2970, 0), "pollnivneach", "poln" }, { Location.create(3305, 2788, 0), "sophanem" }, { Location.create(2898, 3544, 0), "burthorpe", "burthorp" }, { Location.create(3088, 3491, 0), "edge", "edgeville" }, { Location.create(3169, 3034, 0), "bedabin" }, { Location.create(3565, 3289, 0), "barrows" } };
	
	/**
	 * The teleport destinations, intended for Grandpa Jack.
	 */
	public static final Object[][] TELEPORT_DESTINATIONS_DONATOR = { {Location.create(2914, 3746, 0), "godwars", "gwd", "god wars"}, { Location.create(2659, 2649, 0), "pc", "pest control", "pest" }, { Location.create(3293, 3184, 0), "al kharid", "alkharid", "kharid" }, { Location.create(3222, 3217, 0), "lumbridge", "lumby" }, { Location.create(3110, 3168, 0), "wizard tower", "wizards tower", "tower", "wizards" }, { Location.create(3083, 3249, 0), "draynor", "draynor village" }, { Location.create(3019, 3244, 0), "port sarim", "sarim" }, { Location.create(2956, 3209, 0), "rimmington" }, { Location.create(2965, 3380, 0), "fally", "falador" }, { Location.create(2895, 3436, 0), "taverly" }, { Location.create(3080, 3423, 0), "barbarian village", "barb" }, { Location.create(3213, 3428, 0), "varrock" }, { Location.create(3164, 3485, 0), "grand exchange", "ge" }, { Location.create(2917, 3175, 0), "karamja" }, { Location.create(2450, 5165, 0), "tzhaar" }, { Location.create(2795, 3177, 0), "brimhaven" }, { Location.create(2849, 2961, 0), "shilo village", "shilo" }, { Location.create(2605, 3093, 0), "yanille" }, { Location.create(2663, 3305, 0), "ardougne", "ardy" }, { Location.create(2450, 3422, 0), "gnome stronghold", "gnome" }, { Location.create(2730, 3485, 0), "camelot", "cammy", "seers" }, { Location.create(2805, 3435, 0), "catherby" }, { Location.create(2659, 3657, 0), "rellekka" }, { Location.create(2890, 3676, 0), "trollheim" },  { Location.create(3180, 3684, 0), "bounty hunter", "bh" }, { Location.create(3272, 3687, 0), "clan wars", "clw" }, { Location.create(3090, 3957, 0), "mage arena", "mage", "magearena", "arena" }, { Location.create(3359, 3416, 0), "digsite" }, { Location.create(3488, 3489, 0), "canifis" }, { Location.create(3428, 3526, 0), "slayer tower", "slayer" }, { Location.create(3233, 2913, 0), "pyramid" }, { Location.create(3419, 2917, 0), "nardah" }, { Location.create(3482, 3090, 0), "uzer" }, { Location.create(3358, 2970, 0), "pollnivneach", "poln" }, { Location.create(3305, 2788, 0), "sophanem" }, { Location.create(2898, 3544, 0), "burthorpe", "burthorp" }, { Location.create(3088, 3491, 0), "edge", "edgeville" }, { Location.create(3169, 3034, 0), "bedabin" }, { Location.create(3565, 3311, 0), "barrows" } };
	
	/**
	 * The string of donation messages displayed on an interface.
	 */
	public static final String[] MESSAGES = new String[] {"Donations on "+GameWorld.getName()+" are different than those elsewhere.", "Here we use a perk system.", "There are many different type of perks that can be bought to", "speed up efficiency, but nothing game breaking. By doing this", "we provide players with ways to support "+GameWorld.getName()+", in a manner" , "that doesn't ruin the economy or provide substantial advantages.", "If you would like to check out our perks please visit", GameWorld.getName()+".com/donate/." };

	public static final String WEBSITE_ADDRESS = "";

	
	/**
	 * Constructs a new {@Code ServerConstants} {@Code Object}
	 */
	private ServerConstants() {
		/*
		 * empty.
		 */
	}
	public enum TeleportDestinations {
		CORPOREAL_BEAST(Location.create(2974, 4383, 2), "corp", "corporal", "corporeal" ),
		PEST_CONTROL(Location.create(2659, 2649, 0), "pc", "pest control", "pest" ),
		AL_KHARID(Location.create(3293, 3184, 0), "al kharid", "alkharid", "kharid"),
		LUMBRIDGE(Location.create(3222, 3217, 0), "lumbridge", "lumby" ),
		LUMBRIDGE_SWAMP(Location.create(3163, 3172, 0), "lumbridge_swamp" ),
		LUMBRIDGE_EAST(Location.create(3238, 3295, 0),"lumbridge_east"),
		WIZARD_TOWER(Location.create(3110, 3168, 0), "wizard tower", "wizards tower", "tower", "wizards"),
		DRAYNOR_VILLAGE(Location.create(3083, 3249, 0), "draynor", "draynor village" ),
		PORT_SARIM(Location.create(3019, 3244, 0), "port sarim", "sarim" ),
		RIMMINGTON(Location.create(2956, 3209, 0), "rimmington" ),
		FALADOR(Location.create(2965, 3380, 0), "fally", "falador"),
		TARVERLEY(Location.create(2895, 3436, 0), "taverly" ),
		BARBARIAN_VILLAGE(Location.create(3080, 3423, 0), "barbarian village", "barb"),
		VARROCK( Location.create(3213, 3428, 0), "varrock"),
		GRAND_EXCHANGE(Location.create(3164, 3485, 0), "grand exchange", "ge" ),
		KARAMJA(Location.create(2917, 3175, 0), "karamja"),
		TZHAAR(Location.create(2450, 5165, 0), "tzhaar" ),
		BRIMHAVEN(Location.create(2795, 3177, 0), "brimhaven" ),
		BRIMHAVEN_DUNGEON(Location.create(2744, 3151, 0), "brimhaven dungeon" ),
		SHILLO_VILLAGE( Location.create(2849, 2961, 0), "shilo village", "shilo"),
		YANILLE(Location.create(2605, 3093, 0), "yanille"),
		ARDOUGNE( Location.create(2663, 3305, 0), "ardougne", "ardy"),
		GNOME_STRONGHOLD(Location.create(2450, 3422, 0), "gnome stronghold", "gnome"),
		CAMELOT(Location.create(2730, 3485, 0), "camelot", "cammy", "seers"),
		CATHERBY(Location.create(2805, 3435, 0), "catherby"),
		RELEKKA(Location.create(2659, 3657, 0), "rellekka"),
		TROLLHEIM(Location.create(2890, 3676, 0), "trollheim" ),
		GODWARS(Location.create(2914, 3746, 0), "godwars", "gwd", "god wars"),
		BOUNTY_HUNTER(Location.create(3180, 3684, 0), "bounty hunter", "bh"),
		CLAN_WARS( Location.create(3272, 3687, 0), "clan wars", "clw"),
		MAGE_ARENA(Location.create(3090, 3957, 0), "mage arena", "mage", "magearena", "arena"),
		KING_BLACK_DRAGON( Location.create(3069, 10257, 0), "king black dragon", "kbd" ),
		DIGSITE(Location.create(3359, 3416, 0), "digsite"),
		CANIFIS(Location.create(3488, 3489, 0), "canifis"),
		SLAYER_TOWER(Location.create(3428, 3526, 0), "slayer tower", "slayer"),
		KALPHITE_QUEEN(Location.create(3502, 9483, 2), "kalphite queen", "kq", "kalphite hive", "kalphite"),
		PYRAMID(Location.create(3233, 2913, 0), "pyramid"),
		NARDAH(Location.create(3419, 2917, 0), "nardah"),
		UZER(Location.create(3482, 3090, 0), "uzer"),
		POLLNIVNEACH(Location.create(3358, 2970, 0), "pollnivneach", "poln" ),
		SOPHANEM( Location.create(3305, 2788, 0), "sophanem"),
		BURTHORPE(Location.create(2898, 3544, 0), "burthorpe", "burthorp"),
		EDGEVILLE( Location.create(3088, 3491, 0), "edge", "edgeville"),
		BEDABIN(Location.create(3169, 3034, 0), "bedabin"),
		BARROWS(Location.create(3565, 3289, 0), "barrows"),
		STRONGHOLD_OF_SECURITY_1ST_FLOOR(Location.create(1861,5242, 0), "stronghold_1"),
		STRONGHOLD_OF_SECURITY_2ND_FLOOR(Location.create(2043, 5243, 0), "stronghold_2"),
		STRONGHOLD_OF_SECURITY_3RD_FLOOR(Location.create(2124, 5254, 0), "stronghold_3"), //TODO: ESSE TB
		STRONGHOLD_OF_SECURITY_4TH_FLOOR(Location.create(2361, 5212, 0), "stronghold_4"),
		RELLEKKA_SLAYER_DUNGEON(Location.create(2792, 3615, 0), "fremennick slayer dungeon"),
		TARVERLEY_DUNGEON(Location.create(2884, 3400, 0), "tarverley dungeon"),
		STEALING_CREATION(Location.create(3135, 3615, 0), "stealing creation"),
		EDGEVILLE_DUNGEON(Location.create(3131, 9915, 0), "edgeville dungeon"),
		MOS_HARMLESS(Location.create(3751, 2973, 0), "mos harmless"),
		DAGGANNOTH_DUNGEON(Location.create(2578, 3740, 0), "daggannoth dungeon"),
		DARK_BEAST_DUNGEON(Location.create(2037,4636, 0), "dark beast dungeon"),
		DESERT_LIZARD(Location.create(3404,3064, 0), "desert lizard"),
		DWARF_WILD(Location.create(3248,3794, 0), "dwarf wild"),
		WATERFALL_DUNGEON(Location.create(2512,3494, 0), "waterfall dungeon"),
		NIEVE_CAVE(Location.create(2432,3424, 0), "nieve cave"),
		GREEN_DRAGONS(Location.create(3349,3675, 0), "green dragons"),
		TAI_BWO_WANNAI(Location.create(2795,3086, 0), "tai bwo wannai"),
		ICE_MOUNTAIN(Location.create(3032,3471, 0), "ice mountain"),
		ASGARNIAN_DUNGEON(Location.create(3011,3150, 0), "asgarnian"),
		KALPHITE_DUNGEON(Location.create(3231,3108, 0), "kalphite dungeon"),
		KARAMJA_DUNGEON(Location.create(2854,3167, 0), "karamja dungeon"),
		ANCIENT_CAVERN(Location.create(2512,3519, 0), "ancient cavern"),
		ARDOUGNE_ZOO(Location.create(2512,3519, 0), "ardougne zoo"),
		CASTLE_WARS(Location.create(2459,3092, 0), "castle wars"),
		ZANARIS_ENTRANCE(Location.create(3197,3169, 0), "zanaris entrance"),
		WILDERNESS_RIFT(Location.create(3061,3549, 0), "wilderness rift"),
		BRINE_CAVERN(Location.create(2748,3733, 0), "brine cavern")
		
		
		;
		private Location location;
		private String tag[];
		private TeleportDestinations(Location location, String... tag) {
			this.location = location;
			this.tag = tag;
		}
		public Location getLocation() {
			return location;
		}
		public String[] getTag() {
			return tag;
		}
		
	}
}
