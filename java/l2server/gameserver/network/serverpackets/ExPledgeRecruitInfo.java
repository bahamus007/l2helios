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

import l2server.gameserver.instancemanager.ClanRecruitManager.ClanRecruitData;

/**
 * @author Pere
 */
public class ExPledgeRecruitInfo extends L2GameServerPacket
{
	private ClanRecruitData _data;

	public ExPledgeRecruitInfo(ClanRecruitData data)
	{
		_data = data;
	}

	@Override
	protected final void writeImpl()
	{
		writeS(_data.clan.getName());
		writeS(_data.clan.getLeaderName());
		writeD(_data.karma);
		writeD(_data.clan.getMembersCount());
		writeD(0x00);
	}
}
