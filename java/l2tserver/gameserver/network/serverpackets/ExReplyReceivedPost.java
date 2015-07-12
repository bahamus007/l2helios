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
package l2tserver.gameserver.network.serverpackets;

import l2tserver.gameserver.model.L2ItemInstance;
import l2tserver.gameserver.model.entity.Message;
import l2tserver.gameserver.model.itemcontainer.ItemContainer;
import l2tserver.log.Log;

/**
 * @author Pere, DS
 */
public class ExReplyReceivedPost extends L2ItemListPacket
{
	private static final String _S__FE_AB_EXSHOWRECEIVEDPOST = "[S] FE:AB ExShowReceivedPost";
	
	private Message _msg;
	private L2ItemInstance[] _items = null;
	
	public ExReplyReceivedPost(Message msg)
	{
		_msg = msg;
		if (msg.hasAttachments())
		{
			final ItemContainer attachments = msg.getAttachments();
			if (attachments != null && attachments.getSize() > 0)
				_items = attachments.getItems();
			else
				Log.warning("Message "+msg.getId()+" has attachments but itemcontainer is empty ("+msg.getSenderName()+ " > " + msg.getReceiverName()+").");
		}
	}
	
	/* (non-Javadoc)
	 * @see l2tserver.gameserver.serverpackets.ServerBasePacket#writeImpl()
	 */
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0xac);
		writeD(_msg.getSendBySystem());
		if (_msg.getSendBySystem() == Message.SendBySystem.SYSTEM.ordinal())
		{
            writeD(0x00);// unknown1
            writeD(0x00);// unknown2
            writeD(0x00);// unknown3
            writeD(0x00);// unknown4
            writeD(0x00);// unknown5
            writeD(0x00);// unknown6
            writeD(0x00);// unknown7
            writeD(0x00);// unknown8
            writeD(_msg.getSystemMessage1());
            writeD(_msg.getSystemMessage2());
		}
		
		writeD(_msg.getId());
		writeD(_msg.isLocked() ? 1 : 0);
		writeD(0x00); //Unknown
		writeS(_msg.getSenderName());
		writeS(_msg.getSubject());
		writeS(_msg.getContent());
		
		if (_items != null && _items.length > 0)
		{
			writeD(_items.length);
			for (L2ItemInstance item : _items)
			{
				writeItem(item);
				writeD(item.getObjectId());
			}
			_items = null;
		}
		else
			writeD(0x00);
		
		writeQ(_msg.getReqAdena());
		writeD(_msg.hasAttachments() ? 1 : 0);
		writeD(_msg.getSendBySystem() > 0 ? 0x00 : 0x01);
		writeD(_msg.getReceiverId());
		
		_msg = null;
	}
	
	/* (non-Javadoc)
	 * @see l2tserver.gameserver.BasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__FE_AB_EXSHOWRECEIVEDPOST;
	}
}