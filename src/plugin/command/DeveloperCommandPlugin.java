package plugin.command;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.wildscape.cache.Cache;
import org.wildscape.cache.def.impl.ItemDefinition;
import org.wildscape.cache.def.impl.NPCDefinition;
import org.wildscape.game.container.impl.EquipmentContainer;
import org.wildscape.game.content.activity.ActivityManager;
import org.wildscape.game.content.eco.ge.GrandExchangeDatabase;
import org.wildscape.game.content.eco.ge.GrandExchangeEntry;
import org.wildscape.game.content.eco.ge.ResourceManager;
import org.wildscape.game.content.holiday.HolidayItem;
import org.wildscape.game.content.holiday.ItemLimitation;
import org.wildscape.game.content.skill.Skills;
import org.wildscape.game.content.skill.free.smithing.smelting.Bar;
import org.wildscape.game.content.skill.member.construction.HouseLocation;
import org.wildscape.game.content.skill.member.summoning.pet.Pets;
import org.wildscape.game.node.entity.combat.ImpactHandler.HitsplatType;
import org.wildscape.game.node.entity.npc.NPC;
import org.wildscape.game.node.entity.player.Player;
import org.wildscape.game.node.entity.player.info.login.PlayerParser;
import org.wildscape.game.node.entity.player.link.IronmanMode;
import org.wildscape.game.node.entity.state.EntityState;
import org.wildscape.game.node.item.Item;
import org.wildscape.game.node.object.GameObject;
import org.wildscape.game.system.command.CommandPlugin;
import org.wildscape.game.system.command.CommandSet;
import org.wildscape.game.system.mysql.impl.ItemConfigSQLHandler;
import org.wildscape.game.system.script.ScriptManager;
import org.wildscape.game.system.task.Pulse;
import org.wildscape.game.world.GameWorld;
import org.wildscape.game.world.map.Location;
import org.wildscape.game.world.map.RegionPlane;
import org.wildscape.game.world.map.zone.RegionZone;
import org.wildscape.game.world.repository.Repository;
import org.wildscape.game.world.update.flag.context.Animation;
import org.wildscape.game.world.update.flag.context.Graphics;
import org.wildscape.plugin.Plugin;

import plugin.skill.herblore.PotionDecantingPlugin;

/**
 * Handles the developer commands.
 * @author Vexia
 * 
 */
public final class DeveloperCommandPlugin extends CommandPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		link(CommandSet.DEVELOPER);
		return this;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean parse(final Player player, String name, String[] args) {
		int value;
		switch (name) {
		case "starteco":
			ResourceManager.kickStartEconomy();
			break;
		case "g":
			Location l = player.getLocation();
			int xz = l.getX();
			int yz = l.getY();
			int er = Integer.parseInt(args[1]);
			for (int w = 10; w > 0; w--) {
				Graphics gfx = new Graphics(er + w);
				Graphics.send(gfx, new Location(xz - (w > 5 ? - (w - 5) : 5 - w), yz));
			}
		case "v":
			Map<Integer, ArrayList<NPC>> zz = new HashMap<>();
			for (NPC n : Repository.getNpcs()) {
				if (n == null) {
					continue;
				}
				if (n.getAttribute("spawned:npc", false) && n.getName().startsWith("Kalphite") && n.getViewport().getRegion().getId() == 14939) {
					if (!zz.containsKey(n.getId())) {
						ArrayList<NPC> ooo = new ArrayList<NPC>();
						ooo.add(n);
						zz.put(n.getId(),ooo);
					} else {
						ArrayList<NPC> nn = zz.get(n.getId());
						nn.add(n);
						zz.put(n.getId(), nn);
					}
				}
			}
			for (Entry<Integer, ArrayList<NPC>> e : zz.entrySet()) {
				System.out.println("--- npc -> " + e.getKey() + "   ----");
				List<NPC> bn = e.getValue();
				String ll = "";
				for (NPC n : bn) {
					Location hu = n.getProperties().getSpawnLocation();
					ll += "{" + hu.getX() + "," + hu.getY() + "," + hu.getZ() + "," + (n.isWalks() ? "1" : "0") + "," + n.getDirection().toInteger() + "}-";
				}
				System.out.println(ll);
			}
			break;
		case "fillbank":
			for (int zy = 0; zy < 100; zy++) {
				player.getBank().add(new Item(zy + 2000));
			}
			break;
			
		case "em":
			final int[] y = new int[] { 16044, 16045, 16256, 16259, 16260, 16334, 16381, 16556, 16564, 16638, 16642, 16673, 16710, 16713, 16715, 16722, 16796, 16797, 16805, 16831, 16886, 16890, 16926, 16938, 16942 };
			GameWorld.submit(new Pulse(3) {
				int anim = 0;
				@Override
				public boolean pulse() {
					if (anim == (y.length - 1)) {
						player.sendChat("Done");
						return true;
					}
					player.animate(new Animation(y[anim]));
					player.sendChat("Animation: " + y[anim]);
					anim++;
					return false;
				}
				
			});
			break;
		case "c":
			for (Bar bar : Bar.values()) {
				System.out.println(bar.getProduct().getId() + ",");
			}
			break;
			/*	case "zulrahzone":
			DynamicRegion y = DynamicRegion.create(9007, 9008);
			if (y != null) {
				Location l = y.getBaseLocation();
				int xOffset = 2240 - l.getX();
				int yOffset = 3008 - l.getY();
				System.out.println(y.getBaseLocation());
				player.teleport(y.getBaseLocation());
				ZulrahPattern.startZulrahSpawn(ZulrahPattern.getPatterns().get(0), player, xOffset, yOffset);
			} else {
				System.out.println("No zone");
			}
			break;*/
		case "damage":
			player.getImpactHandler().manualHit(player, 5, HitsplatType.VENOM);
			break;
		case "zul":
			ActivityManager.start(player, "zulrah cutscene", false);
			break;
		case "region":
			player.getPacketDispatch().sendMessage("You're in region: " + player.getLocation().getRegionId() + " " + player.getLocation().getRegionX() + " " + player.getLocation().getRegionY());
			break;
		case "pots":
			for (int i = 0; i < ItemDefinition.getDefinitions().size(); i++) {
				if (ItemDefinition.getDefinitions().get(i).getName().contains("(1)")) {
					System.out.println(ItemDefinition.getDefinitions().get(i).getName());
				}
			}
			break;
		case "t":

			RegionPlane plane = player.getViewport().getCurrentPlane();
			int replace = 2103;
			System.out.println("----------------------");
			for (GameObject[] o : plane.getObjects()) {
				if (o == null) {
					continue;
				}
				for (GameObject oo : o) {
					if (oo == null) {
						continue;
					} 
					if (oo.getId() == 42036) {
						System.out.println("{new GameObject(" + oo.getId() + "," + oo.getLocation().getX() + "," + oo.getLocation().getY() + ",0," + oo.getType() + "," + oo.getRotation() + "),new GameObject(" + replace + "," + oo.getLocation().getX() + "," + oo.getLocation().getY() + ",0," + oo.getType() + "," + oo.getRotation() + ")},");
					}
				}
			}
			System.out.println("----------------------");
			break;
		case "decant":
			PotionDecantingPlugin.decant(player);
			break;
		case "removeiron":
			Player t = Repository.getPlayer(args[1], true);
			if (t == null) {
				return true;
			}
			t.getIronmanManager().setMode(IronmanMode.NONE);
			if (t.isActive()) {
				t.sendMessage("Iron man mode removed.");
			}
			player.sendMessage("Removed ironman mode.");
			PlayerParser.dump(t);
			break;
		case "holidayitemloc":
			if (args.length < 1) {
				player.sendMessage("Syntax error - please do ::holidayitemloc itemId");
				return true;
			}
			int holidayItemId = Integer.parseInt(args[1]);
			Location itemLoc = HolidayItem.getHolidayItemLocation(holidayItemId);
			player.sendMessage("The location of the holiday item is - " + (itemLoc != null ? itemLoc : "null") + ".");
			return true;			
		case "addpets":
			player.getFamiliarManager().getInsuredPets().add(Pets.BABY_MOLE);
			player.getFamiliarManager().getInsuredPets().add(Pets.KREE_JR);
			player.getFamiliarManager().getInsuredPets().add(Pets.KQ_FORM_1);
			player.getFamiliarManager().getInsuredPets().add(Pets.TZREK_JAD);
			return true;
		case "clearpets":
			player.getFamiliarManager().getInsuredPets().clear();
			return true;
		case "holidayitemamount":
			if (args.length < 1) {
				player.sendMessage("Syntax error - please do ::holidayitemamount itemId");
				return true;
			}
			int itemAmount = Integer.parseInt(args[1]);
			player.sendMessage("There are " + ItemLimitation.getAmountLeft(itemAmount) + " left."); 
			return true;
		case "iparse":
			try {
				new ItemConfigSQLHandler().parse();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "l":
			player.sendMessage("" + player.getSavedData().getGlobalData().getLowAlchemyCharges());
			//player.getSavedData().getGlobalData().setLowAlchemyCharges(2);
			break;
		case "o":
			String[] stock = {"Bronze arrow", "Iron arrow", "Steel arrow", "Mithril arrow", "Adamant arrow", "Bronze bolts", "Iron bolts", "Steel bolts", "Shortbow", "Oak shortbow", "Willow shortbow", "Maple shortbow", "Yew shortbow", "Magic shortbow", "Crossbow", "Iron crossbow", "Steel crossbow", "Mith crossbow", "Adamant crossbow", "Rune crossbow", "Green dragonhide set", "Blue dragonhide set", "Red dragonhide set", "Black dragonhide set", "Leather vambraces", "Leather chaps", "Leather body", "Leather body", "Hardleather body", "Leather cowl", "Studded body", "Studded chaps", "Snakeskin boots", "Archer helm", "Amulet of accuracy", "Dorgeshuun c'bow", "Bone bolts", "Iron knife", "Rune knife"};
			String output = "";
			for (String string : stock) {
				for (int i = 0; i < Cache.getItemDefinitionsSize(); i++) {
					if (ItemDefinition.forId(i).getName().toLowerCase().equals(string.toLowerCase())) {
						output += "{" + i + ",10}-";
						break;
					}
					if (i == Cache.getItemDefinitionsSize()-1) {
						System.err.println("You failed on " + string);
					}
				}
			}
			System.out.println(output);
			break;
		case "rls":
			ScriptManager.load();
			break;
		case "k":
			String[] types = {"Adamant", "Rune", "Dragon"};
			String[] items = {"dagger", "spear", "pickaxe", "sword", "longsword", "2h sword", "scimitar", "warhammer", "axe", "battleaxe"};
			String out = "";
			for (String type : types) {
				for (String item : items) {
					for (int i = 0; i < Cache.getItemDefinitionsSize(); i++) {
						if (ItemDefinition.forId(i).getName().equals(type + " " + item)) {
							out += "{" + i + ",10}-";
							break;
						}
						if (i == Cache.getItemDefinitionsSize()) {
							System.err.println("Could not do " + type + ", & " + item);
						}
					}
				}
			}
			System.out.println(out);
			break;
		case "i":
			for (int i = 0; i < Cache.getNPCDefinitionsSize(); i++) {
				if (NPCDefinition.forId(i).getName().equals("Gnome traveller")) {
					System.out.println(i + ", " + NPCDefinition.forId(i).getExamine());
				}
			}
			break;
		case "setpin":
			if (args.length < 2) {
				player.sendMessage("Syntax error - ::setpin username 1234");
				return true;
			}
			Player p = super.getTarget(args[1], true);
			if (p == null) {
				player.sendMessage("Could not find player " + args[1] + "!");
				return true;
			}
			String pin = args.length > 2 ? args[2] : null;
			p.getBankPinManager().setPin(pin == "" ? null : pin);
			p.sendMessage("Your pin has been set by an administrator.");
			player.sendMessage("Set "+p.getUsername()+"'s pin to "+pin+".");
			return true;
		case "special":
		case "spec":
			int amount = args.length > 1 ? Integer.parseInt(args[1]) : 100;
			player.getSettings().setSpecialEnergy(amount);
			return true;
		case "god":
			player.setAttribute("godMode", !player.getAttribute("godMode", false));
			player.sendMessage("God mode=<col=ff0000>" + player.getAttribute("godMode", false));
			break;
		case "1hit":
			int[] bonuses = new int[15];
			for (int z = 0; z < 15; z++) {
				if (player.getProperties().getBonuses()[z] > 1000) {
					EquipmentContainer.updateBonuses(player);
					break;
				}
				bonuses[z] = 10000;
			}
			if (bonuses[1] > 1000) {
				player.getProperties().setBonuses(bonuses);
			}
			return true;
		case "heal":
		case "hp":
		case "life":
			player.getSettings().setSpecialEnergy(100);
			player.getSettings().updateRunEnergy(-100);
			player.getSkills().setLifepointsIncrease(0);
			player.getSkills().rechargePrayerPoints();
			player.getSkills().heal(100);
			player.getSkills().setLevel(Skills.SUMMONING, player.getSkills().getStaticLevel(Skills.SUMMONING));
			player.getStateManager().remove(EntityState.TELEBLOCK);
			if (player.getFamiliarManager().hasFamiliar()) {
				player.getFamiliarManager().getFamiliar().updateSpecialPoints(-200);
			}
			return true;
		case "slayerpoints":
			player.getSlayer().setSlayerPoints(10000);
			return true;
		case "bank":
			player.getBank().open();
			return true;
		case "debugil":
			for (int itemId : ItemLimitation.getItems().keySet()) {
				player.getPacketDispatch().sendMessage(itemId + ": " + ItemDefinition.forId(itemId).getName() + " is limited to " + ItemLimitation.getItems().get(itemId) + ".");
				System.out.println(itemId + ": " + ItemDefinition.forId(itemId).getName() + " is limited to " + ItemLimitation.getItems().get(itemId) + ".");
			}
			return true;
		case "setil":
			if (args.length < 3) {
				player.getPacketDispatch().sendMessage("Syntax is ::setil itemId limit.");
				return true;
			}
			player.getPacketDispatch().sendMessage("Limited item " + args[1] + " to " + args[2] + ".");
			ItemLimitation.register(toInteger(args[1]), toInteger(args[2]));
			return true;
		case "unlock":
			player.unlock();
			return true;
		case "modroom":
			player.teleport(new Location(2843, 5214, 0));
			return true;
		case "poh":
			if (!player.getHouseManager().hasHouse()) {
				player.getHouseManager().create(HouseLocation.RIMMINGTON);
			}
			player.getHouseManager().enter(player, true);
			return true;
		case "resethouse":
		case "rh":
			player.getHouseManager().clearRooms();
			player.getHouseManager().create(HouseLocation.RIMMINGTON);
			player.getHouseManager().enter(player, true);;
			return true;
		case "debug":
			player.toggleDebug();
			return true;
		case "zones":
			for (RegionZone z : player.getZoneMonitor().getZones()) {
				player.getPacketDispatch().sendMessage("Region zone " + z.getZone().getName() + " active...");
			}
			return true;
		case "additem":
		case "deleteitem":
		case "deleteitemb":
			if (name.equals("additem")) {
				addItem(player, args);
			} else {
				deleteItem(player, args);
			}
			break;
		case "setvalue":
			int itemId = toInteger(args[1]);
			value = toInteger(args[2]);
			Item item = new Item(itemId);
			GrandExchangeEntry entry = GrandExchangeDatabase.getDatabase().get(itemId);
			if (entry == null) {
				player.getPacketDispatch().sendMessage("Could not find G.E entry for item [id=" + itemId + ", name=" + item.getName() + "]!");
				break;
			}
			entry.setValue(value);
			player.getPacketDispatch().sendMessage("Set Grand Exchange value for item [id=" + itemId + ", name=" + item.getName() + "] to " + value + "gp!");
			break;
		case "roar":
			player.getPacketDispatch().sendInterfaceConfig(762, 20, false);
			break;
		}
		return false;
	}

	/**
	 * Adds an item to a players item.
	 * @param player the player.
	 * @param args the args.
	 */
	private void addItem(Player player, String[] args) {
		Player t = Repository.getPlayer(args[1]);
		if (t == null) {
			return;
		}
		int id = toInteger(args[2]);
		int amount = toInteger(args[3]);
		Item item = new Item(id, amount);
		t.getInventory().add(item);
		player.getPacketDispatch().sendMessage("You just gave " + t.getUsername() + " the item - " + item);
	}

	/**
	 * Deletes an item from a players item.
	 * @param player the player.
	 * @param args the args.
	 */
	private void deleteItem(Player player, String[] args) {
		Player t = Repository.getPlayer(args[1]);
		if (t == null) {
			return;
		}
		int id = toInteger(args[2]);
		int amount = toInteger(args[3]);
		Item item = new Item(id, amount);
		if (args[0].equals("deleteitemb")) {
			t.getBank().remove(item);
		} else {
			t.getInventory().remove(item);
		}
		player.getPacketDispatch().sendMessage("You just removed" + t.getUsername() + " the item - " + item);
	}

}
