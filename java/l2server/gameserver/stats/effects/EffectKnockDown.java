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

package l2server.gameserver.stats.effects;

import l2server.Config;
import l2server.gameserver.GeoData;
import l2server.gameserver.model.L2Effect;
import l2server.gameserver.model.Location;
import l2server.gameserver.model.actor.L2Attackable;
import l2server.gameserver.model.actor.instance.L2PcInstance;
import l2server.gameserver.network.serverpackets.FlyToLocation;
import l2server.gameserver.network.serverpackets.FlyToLocation.FlyType;
import l2server.gameserver.network.serverpackets.ValidateLocation;
import l2server.gameserver.stats.Env;
import l2server.gameserver.stats.VisualEffect;
import l2server.gameserver.templates.skills.L2AbnormalType;
import l2server.gameserver.templates.skills.L2EffectTemplate;
import l2server.log.Log;

/**
 * @author Pere
 */
public class EffectKnockDown extends L2Effect
{
	private int _x, _y, _z;

	public EffectKnockDown(Env env, L2EffectTemplate template)
	{
		super(env, template);
	}

	@Override
	public L2AbnormalType getAbnormalType()
	{
		return L2AbnormalType.KNOCK_DOWN;
	}

	/**
	 * @see l2server.gameserver.model.L2Abnormal#onStart()
	 */
	@Override
	public boolean onStart()
	{
		if (getEffected() instanceof L2Attackable && getEffected().isImmobilized() ||
				getEffected().isRaid())
		{
			return false;
		}

		//TW bug restrictions for avoid players with TW flags stuck his char into the walls, under live test
		if (getEffected() instanceof L2PcInstance && ((L2PcInstance) getEffected()).isCombatFlagEquipped())
		{
			return false;
		}

		// Get current position of the L2Character
		final int curX = getEffected().getX() + 1; // + 1 to correct when the effector came using a charge skill
		final int curY = getEffected().getY();
		final int curZ = getEffected().getZ();

		// Calculate distance between effector and effected current position
		double dx = getEffector().getX() - curX;
		double dy = getEffector().getY() - curY;
		double dz = getEffector().getZ() - curZ;
		double distance = Math.sqrt(dx * dx + dy * dy);
		if (distance > 2000)
		{
			Log.info("EffectKnockDown (skill id: " + getSkill().getId() +
					") was going to use invalid coordinates for characters, getEffected: " + curX + "," + curY +
					" and getEffector: " + getEffector().getX() + "," + getEffector().getY());
			return false;
		}
		int offset = Math.min((int) distance + 50, 1400);

		double cos;
		double sin;

		// approximation for moving futher when z coordinates are different
		// TODO: handle Z axis movement better
		offset += Math.abs(dz);
		if (offset < 5)
		{
			offset = 5;
		}

		// If no distance
		if (distance < 1)
		{
			return false;
		}

		// Calculate movement angles needed
		sin = dy / distance;
		cos = dx / distance;

		// Calculate the new destination with offset included
		_x = getEffector().getX() - (int) (offset * cos);
		_y = getEffector().getY() - (int) (offset * sin);
		_z = getEffected().getZ();

		if (Config.GEODATA > 0)
		{
			Location destiny = GeoData.getInstance()
					.moveCheck(getEffected().getX(), getEffected().getY(), getEffected().getZ(), _x, _y, _z,
							getEffected().getInstanceId());
			if (destiny.getX() != _x || destiny.getY() != _y)
			{
				_x = destiny.getX() + (int) (cos * 10);
				_y = destiny.getY() + (int) (sin * 10);
			}
		}
		getEffected().setIsParalyzed(true);
		getEffected().startParalyze();
		getEffected().broadcastPacket(new FlyToLocation(getEffected(), _x, _y, _z, FlyType.KNOCK_DOWN));
		getEffected().startVisualEffect(VisualEffect.S_KNOCK_DOWN);
		getEffected().setXYZ(_x, _y, _z);
		return true;
	}

	/**
	 * @see l2server.gameserver.model.L2Abnormal#onActionTime()
	 */
	@Override
	public boolean onActionTime()
	{
		return false;
	}

	/**
	 * @see l2server.gameserver.model.L2Abnormal#onExit()
	 */
	@Override
	public void onExit()
	{
		getEffected().setIsParalyzed(false);
		getEffected().stopParalyze(false);
		getEffected().stopVisualEffect(VisualEffect.S_KNOCK_DOWN);
		getEffected().broadcastPacket(new ValidateLocation(getEffected()));
	}
}
