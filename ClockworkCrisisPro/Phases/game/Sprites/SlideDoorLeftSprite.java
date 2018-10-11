package com.DamnItJenkins.ClockworkCrisisPro.Phases.game.Sprites;

import java.util.Calendar;

import com.DamnItJenkins.ClockworkCrisisPro.RectF;
import com.DamnItJenkins.ClockworkCrisisPro.SoundManager;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.KeyFrame;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.KeyFrameSprite;
import com.DamnItJenkins.ClockworkCrisisPro.dataModel.ClockConstants;
import com.DamnItJenkins.ClockworkCrisisPro.dataModel.ClockConstants.ImageNames;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;

public class SlideDoorLeftSprite extends KeyFrameSprite
{
	private KeyFrameSprite m_DoorGuard;
	
	public float DOOR_GUARD_WIDTH= -1;
	
	private String m_ButtonName = "";
	
	private double m_dOpenPercentage = 100;	
	private float m_DoorHeight = 0;
	private float m_DoorWidth = 0;
	
	private Calendar m_AnimationEndTime = null;
	public boolean AllowOpenPercentageStacking = false;
	
	public SlideDoorLeftSprite(Texture t, String buttonName, float doorWidth, float doorHeight, float doorGuardHight) 
	{		
		super(t, (int)(512 - doorWidth) , (int)(512 - doorHeight), (int)doorWidth, (int)doorHeight);
		
		DOOR_GUARD_WIDTH = (float)Math.ceil(Double.parseDouble(""+ doorGuardHight));
		
		m_ButtonName = buttonName;
		m_DoorWidth = doorWidth;
		m_DoorHeight = doorHeight;
		
		m_CurrentRectF = new RectF(
				0 - m_DoorWidth - DOOR_GUARD_WIDTH,
				0,
				0 - DOOR_GUARD_WIDTH,
				m_DoorHeight
			);	

		Texture tGuard = ClockConstants.GetTexture(ImageNames.door_slide_guard_2_256);
		tGuard.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
	
		m_DoorGuard = new KeyFrameSprite(tGuard, 0, 0, 256, 512);
		m_DoorGuard.setCurrentRectF(
				new RectF(
						0 - DOOR_GUARD_WIDTH,
						0,
						0,
						m_DoorHeight
					));	
		
		//this.setOrigin(512, 512);
		//this.scroll(10,256);
		//this.translateX(256);
		//this.setRegion(2, 2, 512, 512);
		m_DoorGuard.setV2(6);
		
		//this.setU2((float)(2));
		//this.setV2((float)(2));
		this.flip(false, true);
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
	
	
	public void setOpenPercentage(double d, int startDelay, int inMilliseconds)
	{
		d = 1-d;
		
		if (d != m_dOpenPercentage)
		{		
			boolean blnPlaySound = false;
			
			if (d ==0)
			{
			//	blnPlaySound = true;
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
			
			float fLeftPos = (float)(-m_DoorWidth - DOOR_GUARD_WIDTH + ((m_DoorWidth+ DOOR_GUARD_WIDTH) * m_dOpenPercentage));
			
			kfMove.EndRectF = new RectF(
					fLeftPos,
					0,
					fLeftPos + m_DoorWidth,
					m_DoorHeight				
				);		
			
			m_AnimationEndTime = (Calendar)kfMove.EndTime.clone();
			m_AnimationEndTime.add(Calendar.SECOND, 1);		
			
			if (blnPlaySound)
			{
				kfMove.PlayedEndSound = false;
				kfMove.EndSound = SoundManager.SoundSlideDoorClosed;
			}
			
			if (!AllowOpenPercentageStacking)
			{
				this.GetKeyFrames().clear();
			}
			//this.setKeyFrameIndex(0);
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
					kfMove.EndRectF.right,
					kfMove.EndRectF.top,
					kfMove.EndRectF.right+ DOOR_GUARD_WIDTH,
					kfMove.EndRectF.bottom
				);				
			
			if (!AllowOpenPercentageStacking)
			{
				this.m_DoorGuard.GetKeyFrames().clear();
			}

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

}
