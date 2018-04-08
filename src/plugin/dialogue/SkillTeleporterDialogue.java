package plugin.dialogue;

import java.util.concurrent.TimeUnit;

import org.wildscape.game.component.Component;
import org.wildscape.game.content.dialogue.DialogueInterpreter;
import org.wildscape.game.content.dialogue.DialoguePlugin;
import org.wildscape.game.content.skill.LevelUp;
import org.wildscape.game.content.skill.Skills;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.entity.player.info.portal.DonatorType;
import org.wildscape.game.node.entity.player.link.TeleportManager.TeleportType;
import org.wildscape.game.system.task.Pulse;
import org.wildscape.game.world.GameWorld;
import org.wildscape.game.world.map.Location;
import org.wildscape.plugin.PluginManifest;
import org.wildscape.plugin.PluginType;

import plugin.interaction.inter.SkillTabInterface.SkillConfig;


public class SkillTeleporterDialogue extends DialoguePlugin {
	
	private static final Object[][][] TELEPORTS = new Object[][][] {
		{{"Training"}, {"Cows", Location.create(3256, 3266, 0)}, 
			{"Stronghold of Security", Location.create(3080, 3421, 0)}, 
			{"Rock crabs", Location.create(2672, 3712, 0)}, 
			{"Al-Kharid Warriors", Location.create(3293, 3180, 0)}, 
			{"Neitiznot Yaks", Location.create(2326, 3803, 0)}, 
			{"Desert Bandits", Location.create(3176, 2987, 0)}, 
			{"TzHaar", Location.create(2450, 5165, 0)}, 
			{"Fremmenik Slayer Dungeon", Location.create(2796, 3615, 0)}, 
			{"H.A.M. Hideout", Location.create(3149, 9652, 0)},
			{"Armoured Zombies", Location.create(3239, 3606, 0)}, 
			{"Asgarnian Ice Caves", Location.create(3014, 9579, 0)}, 
			{"Slayer Tower", Location.create(3429, 3535, 0)}, 
			{"Brimhaven Dungeon (bring 875 gp)", Location.create(2744, 3152, 0)}, 
			{"Brine Rat Cave", Location.create(2690, 10124, 0)}, 
			{"Ape Atoll Dungeon", Location.create(2764, 9103, 0)}},
		{{"Ranged"}, 
				{"Rangers Guild", Location.create(2653, 3442, 0)}, 
				{"Stronghold of Security", Location.create(3080, 3421, 0)}, 
				{"Rock crabs", Location.create(2672, 3712, 0)}, 
				{"Al-Kharid Warriors", Location.create(3293, 3180, 0)}, 
				{"Neitiznot Yaks", Location.create(2326, 3803, 0)}, 
				{"Desert Bandits", Location.create(3176, 2987, 0)}, 
				{"TzHaar", Location.create(2450, 5165, 0)}, 
				{"Fremmenik Slayer Dungeon", Location.create(2796, 3615, 0)}, 
				{"H.A.M. Hideout", Location.create(3149, 9652, 0)},
				{"Armoured Zombies", Location.create(3239, 3606, 0)}, 
				{"Asgarnian Ice Caves", Location.create(3014, 9579, 0)}, 
				{"Slayer Tower", Location.create(3429, 3535, 0)}, 
				{"Brimhaven Dungeon (bring 875 gp)", Location.create(2744, 3152, 0)}, 
				{"Brine Rat Cave", Location.create(2690, 10124, 0)}, 
				{"Ape Atoll Dungeon", Location.create(2764, 9103, 0)}},
		{{"Magic"}, //3
					{"Magic Training Arena", Location.create(3363, 3298, 0)},
					{"Stronghold of Security", Location.create(3080, 3421, 0)}, 
					{"Rock crabs", Location.create(2672, 3712, 0)}, 
					{"Al-Kharid Warriors", Location.create(3293, 3180, 0)}, 
					{"Neitiznot Yaks", Location.create(2326, 3803, 0)}, 
					{"Desert Bandits", Location.create(3176, 2987, 0)}, 
					{"TzHaar", Location.create(2450, 5165, 0)}, 
					{"Fremmenik Slayer Dungeon", Location.create(2796, 3615, 0)}, 
					{"H.A.M. Hideout", Location.create(3149, 9652, 0)},
					{"Armoured Zombies", Location.create(3239, 3606, 0)}, 
					{"Asgarnian Ice Caves", Location.create(3014, 9579, 0)}, 
					{"Slayer Tower", Location.create(3429, 3535, 0)}, 
					{"Brimhaven Dungeon (bring 875 gp)", Location.create(2744, 3152, 0)}, 
					{"Brine Rat Cave", Location.create(2690, 10124, 0)}, 
					{"Ape Atoll Dungeon", Location.create(2764, 9103, 0)}},		
			
		{{"Runecrafting"}, 
				{"Abyss", Location.create(3048, 4809, 0)}},
		{{"Construction"}, //5
			{"Rimmington", Location.create(2956, 3215, 0)},
			{"Yanille", Location.create(2605, 3093, 0)},
			{"Brimhaven", Location.create(2761, 3177, 0)}
		},
		{{"Agility"},
			{"Gnome Stronghold course", Location.create(2461, 3444, 0)},
			{"Agility Arena", Location.create(2808, 3182, 0)},
			{"Agility Pyramid [DESERT]", Location.create(2808, 3182, 0)},
			{"Barbarian Agility Course", Location.create(2543, 3577, 0)},
			{"Wilderness Agility Course [Level 49]", Location.create(2999, 3909, 0)},
			{"Werewolf Agility Course", Location.create(3543, 3467, 0)}
		},
		{{"Thieving"},
			{"Edgeville North House", Location.create(3104, 3509, 0)},
				{"Ardougne Market", Location.create(2653, 3307, 0)},
				{"Draynor Market", Location.create(3080, 3250, 0)},
				{"Pyramid Plunder", Location.create(3298, 2786, 0)}
				},
		{{"Slayer"},
			{"Turael", Location.create(3104, 3509, 0)},
			{"Mazchna", Location.create(3510, 3511, 0)},
			{"Vannaka", Location.create(3146, 9913, 0)},
			{"Chaeldar", Location.create(2447, 4429, 0)},
			{"Duradel", Location.create(2862, 2982, 3)}
			},
		{{"Hunter"},
			{"Feldip Hills East", Location.create(2606, 2925, 0)},
			{"Piscatoris Hunter Area", Location.create(2320, 3581, 0)},
			{"Feldip Hills West", Location.create(2503, 2907, 0)}
			},
		{{"Mining"}, //10
			{"Lumbdrige Swamp", Location.create(3231, 3151, 0)},
			{"Mining Guild", Location.create(3029, 3336, 0)},
			{"Desert Mining Camp [Desert Duh]", Location.create(3175, 2928, 0)}
			},
		{{"Smithing"},
				{"Home Smithing Area", Location.create(3145, 3488, 0)}
		},
		{{"Fishing"},
			{"Lumbdrige Swamp", Location.create(3239, 3162, 0)},
			{"Barbarial Village", Location.create(3101, 3434, 0)},
			{"Catherby East", Location.create(2853, 3430, 0)},
			{"Fishing's Guild", Location.create(2611, 3389, 0)},
			},
		{{"Cooking"},
				{"Cooking's Guild", Location.create(3143, 3441, 0)}
				},
		{{"Woodcutting"},
					{"Seer's Village Bank", Location.create(2725, 3485, 0)},
					{"Seer's Village South", Location.create(2700, 3492, 0)}
					},
		{{"Farming"}, //15
			{"Falador South East", Location.create(3050, 3303, 0)},
			{"Seer's Village South", Location.create(2700, 3492, 0)},
			{"Port Phasmatics North West", Location.create(3597, 3522, 0)},
			{"Catherby", Location.create(2818, 3468, 0)},
			{"Ardougne North", Location.create(2673, 3379, 0)}
						},
		{{"Summoning"},
			{"Pikkupstix House", Location.create(2919, 3445, 0)}}
			/*{{"Cities"}, {"Lumbridge", Location.create(3222, 3217, 0)}, 
				{"Falador", Location.create(2965, 3380, 0)}, 
				{"Edgeville", Location.create(3088, 3491, 0)}, 
				{"Varrock", Location.create(3213, 3428, 0)}, 
				{"Ardougne", Location.create(2663, 3305, 0)}, 
				{"Seer's Village", Location.create(2713, 3484, 0)}, 
				{"Burthorpe", Location.create(2898, 3544, 0)}, 
				{"Rellekka", Location.create(2659, 3657, 0)}, 
				{"Neitiznot", Location.create(2335, 3803, 0)}, 
				{"Al-Kharid", Location.create(3293, 3184, 0)}, 
				{"Karamja/Brimhaven", Location.create(2910, 3152, 0)}, 
				{"Yanille", Location.create(2605, 3093, 0)}, 
				{"Mos'le Harmless", Location.create(3676, 2975, 0)}, 
				{"Tree Gnome Stronghold", Location.create(2450, 3422, 0)}, 
				{"Shilo Village", Location.create(2849, 2961, 0)}, 
				{"Piscatoris Fishing Colony", Location.create(2344, 3655, 0)}},
		{{"Minigames"}, {"Pest Control", Location.create(2659, 2649, 0)}, 
					{"Duel Arena", Location.create(3352, 3265, 0)}, 
					{"Sorceress' Garden", Location.create(3315, 3163, 0)}, 
					{"Warrior's Guild", Location.create(2881, 3546, 0)},
					{"Puro Puro(requires Lost City)", Location.create(2592, 4317, 0)}, 
					{"Fight Pits", Location.create(2399, 5178, 0)}, 
					{"Clan Wars", Location.create(3272, 3687, 0)}, 
					{"Magic Training Arena", Location.create(3363, 3298, 0)}}, */
	};
	int skillButtonClicked;
	/**
	 * The lastIndex displayed.
	 */
	private int firstIndex;

	/**
	 * The option index.
	 */
	private int optionIndex;
	public SkillTeleporterDialogue() {
		// TODO Auto-generated constructor stub
	}
	public SkillTeleporterDialogue(Player player) {
		super(player);
	}
	@Override
	public DialoguePlugin newInstance(Player player) {
		return new SkillTeleporterDialogue(player);
	}

	@Override
	public boolean open(Object... args) {	
		this.skillButtonClicked = (int)args[0];
		interpreter.sendOptions("Select an Option", "Skill Guide", "Use teleporter");
		stage = -5;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		System.out.println("ButtonId:"+buttonId+" "+stage);
		int index;
		switch(stage) {
		case -5:
		switch(buttonId) {	
		case 1:
			final SkillConfig config = SkillConfig.forId(this.skillButtonClicked);
			if (config == null) {
				return true;
			}
			if (player.getAttribute("levelup:" + config.getSkillId(), false)) {
				player.removeAttribute("levelup:" + config.getSkillId());
				LevelUp.sendFlashingIcons(player, -1);
				player.getConfigManager().set(1230, ADVANCE_CONFIGS[config.getSkillId()]);
				player.getInterfaceManager().open(new Component(741));
			} else {
				player.getPulseManager().clear();
				player.getInterfaceManager().open(new Component(499));
				player.getConfigManager().set(965, config.getConfig());
				player.getAttributes().put("skillMenu", config.getConfig());
			}
			player.getDialogueInterpreter().close();
			return true;
		case 2:
			//optionIndex = this.skillButtonClicked >= 125 ? 0 : this.skillButtonClicked >= 130 ? 1 : 2;
			optionIndex = this.skillButtonClicked == 148 ? 15 : this.skillButtonClicked == 147 ? 14 : 
				this.skillButtonClicked == 146 ? 13 : this.skillButtonClicked == 144 ? 12 : this.skillButtonClicked == 143 ? 11 :
				this.skillButtonClicked == 142 ? 10 : this.skillButtonClicked == 141 ? 9 : this.skillButtonClicked == 140 ? 8 :
				this.skillButtonClicked == 139 ? 7 : this.skillButtonClicked == 136 ? 6 : this.skillButtonClicked == 134 ? 5 :
				this.skillButtonClicked == 132 ? 4 : this.skillButtonClicked == 131 ? 3 : this.skillButtonClicked == 130 ? 2 :
				this.skillButtonClicked == 128 ? 1 : this.skillButtonClicked >= 125  && this.skillButtonClicked <= 127 ? 0 :  -1 ;
			sendOptions();
			stage = 2;
			break;
		}

			break;
		case 0:
			switch(buttonId) {
			case 2:
				if (player.getSavedData().getGlobalData().getGlobalTeleporterDelay() > System.currentTimeMillis()) {
					long millis = player.getSavedData().getGlobalData().getGlobalTeleporterDelay() - System.currentTimeMillis();
					int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(millis);
					if (minutes < 1) {
						minutes = 1;
					}
					interpreter.sendDialogue("You need to wait " + minutes + " more minute" + (minutes > 1 ? "s" : "") + " in order to use the free", "teleportation system again.");
					stage = -10;
					return true;
				}
				player.removeAttribute("global_teleporter");
				String[] options = new String[TELEPORTS.length];
				for (int i = 0; i < TELEPORTS.length; i++) {
					options[i] = (String) TELEPORTS[i][0][0];
				}
				interpreter.sendOptions("Select an Option", options);
				stage = 1;
				break;
			}
			break;
		case 1:
			index = buttonId - 1;
			optionIndex = index;
			sendOptions();
			stage = 2;
			break;
		case 2:
			if(optionIndex == -1) {
				interpreter.close();
				break;
			}
			index = buttonId - 1;
			int teleIndex = (firstIndex + index) + 1;
			int optionSize = 3;
			for (int i = 0; i < 3; i++) {
				if (firstIndex + 1 + i > TELEPORTS[optionIndex].length-1) {
					optionSize = i;
					break;
				}
			}
			if (teleIndex > firstIndex + optionSize) {
				firstIndex = firstIndex + optionSize;
				sendOptions();
				break;
			}
			Object teleports[] = TELEPORTS[optionIndex][teleIndex];
			if (optionIndex == 2 && teleports[teleIndex] == "Puro Puro(requires Lost City)" && !player.getQuestRepository().isComplete("Lost City")) {
				interpreter.sendDialogue("You need to have completed Lost City to teleport here.");
				break;
			}
			send(player, (Location) teleports[1]);
			break;
			}
			return true;
		}
/**
 * Sends the options.
 */
	private void sendOptions() {
		if(optionIndex == -1) {
			interpreter.sendItemMessage(562, "This is a secondary Skill, you don't need teleport anywhere to level.");
			stage = -1;
			return;
		}
	if (firstIndex >= TELEPORTS[optionIndex].length -1) {
		firstIndex = 0;
	}
	int optionSize = 3;
	Object[][] data = TELEPORTS[optionIndex];
	for (int i = 0; i < 3; i++) {
		if (firstIndex + 1 + i > data.length-1) {
			optionSize = i;
			break;
		}
	}
	String [] options = new String[optionSize + 1];
	for (int i = 0; i < optionSize; i++) {
		options[i] = (String) data[firstIndex + (i + 1)][0];
	}
	
	options[options.length - 1] = options.length > 3 ? "More Options" : "";
	
	interpreter.sendOptions("Select an Option", options);
}


/**
 * Sends the player to the specified location.
 */
private void send(final Player player, final Location loc){
	player.getDialogueInterpreter().close();
	player.getPulseManager().run(new Pulse(1, player) {
		int counter = 5;
		@Override
		public boolean pulse() {
			switch(--counter){
			case -1:
				player.getInterfaceManager().closeChatbox();
				player.getSavedData().getGlobalData().setGlobalTeleporterDelay(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(player.getDonatorType() == DonatorType.EXTREME ? 1 : player.getDonatorType() == DonatorType.REGULAR ? 3 : 5));
				player.getTeleporter().send(loc, TeleportType.TELE_OTHER);
				return true;
			default:
				player.getDialogueInterpreter().sendPlainMessage(true, "You will be teleported in... "+counter+".", "Move away to cancel.");
				return false;
			}
		}
	});
}
	@Override
	public int[] getIds() {
		return new int[] { DialogueInterpreter.getDialogueKey("skill_teleporter") };
	}
	/**
	 * Holds all the config values of the skill advances.
	 */
	public static final int[] ADVANCE_CONFIGS = {
		9, 40, 17, 49,
		25, 57, 33, 641,
		659, 664, 121, 649,
		89, 114, 107, 72,
		64, 80, 673, 680,
		99, 698, 689, 705, 
	};

	public enum SkillConfig {
		ATTACK(125, 1, Skills.ATTACK), 
		STRENGTH(126, 2, Skills.STRENGTH), 
		DEFENCE(127, 5, Skills.DEFENCE), 
		RANGE(128, 3, Skills.RANGE), 
		PRAYER(129, 7, Skills.PRAYER), 
		MAGIC(130, 4, Skills.MAGIC), 
		RUNECRAFTING(131, 12, Skills.RUNECRAFTING), 
		HITPOINTS(133, 6, Skills.HITPOINTS), 
		AGILITY(134, 8, Skills.AGILITY), 
		HERBLORE(135, 9, Skills.HERBLORE), 
		THIEVING(136, 10, Skills.THIEVING), 
		CRAFTING(137, 11, Skills.CRAFTING), 
		FLETCHING(138, 19, Skills.FLETCHING), 
		SLAYER(139, 20, Skills.SLAYER), 
		MINING(141, 13, Skills.MINING), 
		SMITHING(142, 14, Skills.SMITHING), 
		FISHING(143, 15, Skills.FISHING), 
		COOKING(144, 16, Skills.COOKING), 
		FIREMAKING(145, 17, Skills.FIREMAKING),
		WOODCUTTING(146, 18, Skills.WOODCUTTING),
		FARMING(147, 21, Skills.FARMING), 
		CONSTRUCTION(132, 22, Skills.CONSTRUCTION), 
		HUNTER(140, 23, Skills.HUNTER), 
		SUMMONING(148, 24, Skills.SUMMONING);

		/**
		 * Constructs a new {@code SkillConfig} {@code Object}.
		 * @param button the button.
		 * @param config the config.
		 */
		SkillConfig(int button, int config, int skillId) {
			this.button = button;
			this.config = config;
			this.skillId = skillId;
		}

		/**
		 * Represents the button.
		 */
		private int button;

		/**
		 * Represents the config.
		 */
		private int config;

		/**
		 * The skill id.
		 */
		private int skillId;
		
		/**
		 * Gets the skill config.
		 * @param id the id.
		 * @return the skill config.
		 */
		public static SkillConfig forId(int id) {
			for (SkillConfig config : SkillConfig.values()) {
				if (config.button == id)
					return config;
			}
			return null;
		}

		/**
		 * Gets the button.
		 * @return the button.
		 */
		public int getButton() {
			return button;
		}

		/**
		 * Gets the config.
		 * @return the config.
		 */
		public int getConfig() {
			return config;
		}
		
		/**
		 * Gets the skill id.
		 * @return The skill id.
		 */
		public int getSkillId() {
			return skillId;
		}
	}
}
