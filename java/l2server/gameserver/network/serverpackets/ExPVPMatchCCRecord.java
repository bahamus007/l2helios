package l2server.gameserver.network.serverpackets;

/**
 * @author MegaParzor!
 */
public class ExPVPMatchCCRecord extends L2GameServerPacket
{
	private int _unk;

	public ExPVPMatchCCRecord(int unk)
	{
		_unk = unk;
	}

	@Override
	public void writeImpl()
	{
		writeD(_unk);
	}
}
