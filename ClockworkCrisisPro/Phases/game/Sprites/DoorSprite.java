package com.DamnItJenkins.ClockworkCrisisPro.Phases.game.Sprites;

import java.util.Calendar;

import com.DamnItJenkins.ClockworkCrisisPro.RectF;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.KeyFrame;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.KeyFrameSprite;
import com.DamnItJenkins.ClockworkCrisisPro.dataModel.ClockConstants;
import com.DamnItJenkins.ClockworkCrisisPro.dataModel.ClockConstants.ImageNames;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;

public class DoorSprite extends KeyFrameSprite
{
	private String m_ButtonName = "";
	
	private double m_dOpenPercentage = 100;	
	private float m_DoorHeight = 0;
	private float m_DoorWidth = 0;
	
	private Calendar m_AnimationEndTime = null;
	public boolean AllowOpenPercentageStacking = false;
	
	private KeyFrameSprite m_DoorGuard;
	
	public float DOOR_GUARD_HEIGHT= -1;
	
	public float getDoorHeight ()
	{		
		return m_DoorHeight;	
	}
	
	public float getOpenPercentage()
	{		
		if (m_AnimationEndTime != null && Calendar.getInstance().after(m_AnimationEndTime))
		{
			return (float)m_dOpenPercentage;
		}
		
		return -1;
	}
	
	
	public void setOpenPercentage(double d, int inMilliseconds)
	{
		setOpenPercentage(d, 0, inMilliseconds);
	}
	
	public void Reset()
	{
		m_dOpenPercentage = -1;
		this.GetKeyFrames().clear();
		this.m_DoorGuard.GetKeyFrames().clear();
	}
	
	
	public void setOpenPercentage(double d, int startDelay, int inMilliseconds)
	{
		if (d != m_dOpenPercentage)
		{		
			boolean blnPlaySound = false;
			
			if (m_dOpenPercentage > 0.1 && d ==0)
			{
				blnPlaySound = true;
			}
			
			m_dOpenPercentage = d;
			
			// top to bottom
			KeyFrame kfMove = new KeyFrame();
			int lnMilliseconds = startDelay;

			if (!AllowOpenPercentageStacking)
			{
				kfMove.StartRectF = this.getCurrentRectF();
			}
			else
			{
				if (this.GetKeyFrames().size() ==0)
				{
					kfMove.StartRectF = this.getCurrentRectF();
				}
				else
				{
					kfMove.StartRectF = this.GetKeyFrames().get(this.GetKeyFrames().size()-1).EndRectF.Clone();
				}					
			}
			
			kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);	
			
			lnMilliseconds += inMilliseconds;
			kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);		
			
			float fTopPos = (float)(0- ((m_DoorHeight + DOOR_GUARD_HEIGHT) * d));
			
			
			kfMove.EndRectF = new RectF(
					0,
					fTopPos,
					m_DoorWidth,
					fTopPos + m_DoorHeight
				);		
			
			m_AnimationEndTime = (Calendar)kfMove.EndTime.clone();
			m_AnimationEndTime.add(Calendar.SECOND, 1);		
			
			if (blnPlaySound)
			{
			//	kfMove.PlayedEndSound = false;
			//	kfMove.EndSound = ClockConstants.SoundDoorClosed;
			}
			
			if (!AllowOpenPercentageStacking)
			{
				this.GetKeyFrames().clear();
			}
			this.AddKeyFrame(kfMove);
		
		
			
			// top to bottom
			KeyFrame kfMoveGuard = new KeyFrame();
			lnMilliseconds = startDelay;

			if (!AllowOpenPercentageStacking)
			{
				kfMoveGuard.StartRectF = this.m_DoorGuard.getCurrentRectF();
			}
			else
			{
				if (this.m_DoorGuard.GetKeyFrames().size() ==0)
				{
					kfMoveGuard.StartRectF = this.m_DoorGuard.getCurrentRectF();
				}
				else
				{
					kfMoveGuard.StartRectF = this.m_DoorGuard.GetKeyFrames().get(this.m_DoorGuard.GetKeyFrames().size()-1).EndRectF.Clone();
				}
			}
			
			kfMoveGuard.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);	
			
			lnMilliseconds += inMilliseconds;
			kfMoveGuard.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);		
			
			kfMoveGuard.EndRectF = new RectF(
					kfMove.EndRectF.left,
					kfMove.EndRectF.bottom,
					kfMove.EndRectF.right,
					kfMove.EndRectF.bottom + DOOR_GUARD_HEIGHT
				);					
			
			if (!AllowOpenPercentageStacking)
			{
				this.m_DoorGuard.GetKeyFrames().clear();
			}
			//this.m_DoorGuard.setKeyFrameIndex(0);
			this.m_DoorGuard.AddKeyFrame(kfMoveGuard);

		
		
		}
	}
	
	public String getButtonName()
	{
		return m_ButtonName;
	}
	
	public KeyFrameSprite getDoorGuard()
	{
		return m_DoorGuard;
	}
	
	public DoorSprite(Texture t, String buttonName, float doorWidth, float doorHeight, float doorGuardHight) 
	{		
		super(t, 0,0, (int)doorWidth, (int)doorHeight);
		
		DOOR_GUARD_HEIGHT = (float)Math.ceil(Double.parseDouble(""+ doorGuardHight));
		
		m_ButtonName = buttonName;
		m_DoorWidth = doorWidth;
		m_DoorHeight = doorHeight;
		
		m_CurrentRectF = new RectF(
				0,
				0- m_DoorHeight,
				m_DoorWidth,
				0
			);	
		
//		Texture tGuard = new Texture();

		Texture tGuard = ClockConstants.GetTexture(ImageNames.door5_guard_2_512);
		tGuard.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);

		
	
		m_DoorGuard = new KeyFrameSprite(tGuard, 0, -05, (int)doorWidth, (int)DOOR_GUARD_HEIGHT );
		//m_DoorGuard.flip(false, true);
		m_DoorGuard.setCurrentRectF(
				new RectF(
						0,
						0,
						m_DoorWidth,
						DOOR_GUARD_HEIGHT
					));	
	//	m_DoorGuard.setU(3f);
	//	m_DoorGuard.setU2(3f);
		
		this.setV2((float)(3));
		this.flip(false, true);
	}
		
	@Override
	public String toString()
	{
		return null;
	}
}
