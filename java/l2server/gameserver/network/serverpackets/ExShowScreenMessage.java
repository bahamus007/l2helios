/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package l2server.gameserver.network.serverpackets;

/**
 * @author Kerberos
 */
public class ExShowScreenMessage extends L2GameServerPacket
{
	private int _type;
	private int _sysMessageId;
	private int _unk1;
	private int _unk2;
	private int _unk3;
	private int _unk4;
	private int _size;
	private int _position;
	private boolean _effect;
	private int _npcStringId;
	private String _text;
	private int _time;

	public ExShowScreenMessage(String text, int time)
	{
		_type = 1;
		_sysMessageId = -1;
		_unk1 = 0;
		_unk2 = 0;
		_unk3 = 0;
		_unk4 = 0;
		_position = 0x02;
		_npcStringId = -1;
		_text = text;
		_time = time;
		_size = 0;
		_effect = true;
	}

	public ExShowScreenMessage(int npcStringId, int time)
	{
		_type = 1;
		_sysMessageId = -1;
		_unk1 = 0;
		_unk2 = 0;
		_unk3 = 0;
		_unk4 = 0;
		_position = 0x02;
		_npcStringId = npcStringId;
		_text = "";
		_time = time;
		_size = 0;
		_effect = false;
	}

	public ExShowScreenMessage(int npcStringId, int unk, boolean effect, int time)
	{
		_type = 1;
		_sysMessageId = -1;
		_unk1 = 0;
		_unk2 = 0;
		_unk3 = unk;
		_unk4 = 0;
		_position = 0x02;
		_npcStringId = npcStringId;
		_text = "";
		_time = time;
		_size = 0;
		_effect = effect;
	}

	public ExShowScreenMessage(int npcStringId, int pos, int time)
	{
		_type = 1;
		_sysMessageId = -1;
		_unk1 = 0;
		_unk2 = 0;
		_unk3 = 0;
		_unk4 = 0;
		_position = pos;
		_npcStringId = npcStringId;
		_text = "";
		_time = time;
		_size = 0;
		_effect = false;
	}

	public ExShowScreenMessage(int type, int messageId, int position, int unk1, int size, int unk2, int unk3, boolean showEffect, int time, int unk4, String text)
	{
		_type = type;
		_sysMessageId = messageId;
		_unk1 = unk1;
		_unk2 = unk2;
		_unk3 = unk3;
		_unk4 = unk4;
		_position = position;
		_npcStringId = -1;
		_text = text;
		_time = time;
		_size = size;
		_effect = showEffect;
	}

	@Override
	protected final void writeImpl()
	{
		writeD(_type); // 0 - system messages, 1 - your defined text
		writeD(_sysMessageId); // system message id (_type must be 0 otherwise no effect)
		writeD(_position); // message position
		writeD(_unk1); // ?
		writeD(_size); // font size 0 - normal, 1 - small
		writeD(_unk2); // ?
		writeD(_unk3); // ?
		writeD(_effect ? 1 :
				0); // upper effect (0 - disabled, 1 enabled) - _position must be 2 (center) otherwise no effect
		writeD(_time); // time
		writeD(_unk4); // ?
		writeD(_npcStringId);
		writeS(_text); // your text (_type must be 1, otherwise no effect)
	}
}
