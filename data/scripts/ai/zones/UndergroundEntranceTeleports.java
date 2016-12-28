package ai.zones;

//import l2server.gameserver.datatables.DoorTable;
import l2server.gameserver.model.actor.L2Character;
import l2server.gameserver.model.quest.Quest;
import l2server.gameserver.model.zone.L2ZoneType;

/**
 * @author LasTravel
 */

public class UndergroundEntranceTeleports extends Quest
{
	public UndergroundEntranceTeleports(int id, String name, String descr)
	{
		super(id, name, descr);
		addEnterZoneId(200207); //Out of Temple ---> 1 Floor
		addEnterZoneId(200208); //1 Floor ---> Out of Temple
		addEnterZoneId(200209); //1 Floor ----> 2 Floor
		addEnterZoneId(200210); //2 Floor ----> 1 Floor
		addEnterZoneId(200211); //2 Floor ----> 3 Floor
		addEnterZoneId(200212); //3 Floor ----> 2 Floor

		//Keep some doors always open
		//for (int a = 26210003; a <= 26210006; a++)
		//{
		//	DoorTable.getInstance().getDoor(a).openMe();
		//}
	}

	@Override
	public String onEnterZone(L2Character character, L2ZoneType zone)
	{
		//System.out.println("Zone Id = " + zone.getId());
		switch (zone.getId())
		{
			case 200207:
				character.teleToLocation(-49596, -150715, -14472);
				break;
			case 200208:
				character.teleToLocation(17600, -113803, -312);
				break;
			case 200209:
				character.teleToLocation(-55283, -147410, -14728);
				break;
			case 200210:
				character.teleToLocation(17067, -111738, -320);
				break;
			case 200211:
				character.teleToLocation(-46867, -149309, -14216);
				break;
			case 200212:
				character.teleToLocation(18784, -115648, -248);
				break;
		}
		return "";
	}

	public static void main(String[] args)
	{
		new UndergroundEntranceTeleports(-1, "UndergroundEntranceTeleports", "ai");
	}
}