package com.DamnItJenkins.ClockworkCrisisPro.Phases.game.Sprites;

import java.util.Calendar;

import com.DamnItJenkins.ClockworkCrisisPro.RectF;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.KeyFrame;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.KeyFrameSprite;
import com.DamnItJenkins.ClockworkCrisisPro.dataModel.ClockConstants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class ChainShadowSprite extends KeyFrameSprite
{
	private String m_ButtonName = "";
	
	private float m_Height = 0;
	private float m_Width = 0;
	
	
	public String getButtonName()
	{
		return m_ButtonName;
	}
	
	public ChainShadowSprite(Texture t, String buttonName, float width, float height, float screenWidth) 
	{
		super(t, 0,0, (int)width, (int)height);
		
		m_ButtonName = buttonName;
		m_Width = width;
		m_Height = height;
		
		int xPos = ClockConstants.GloablRandom.nextInt((int)screenWidth);
		
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		float screenSquare = ClockConstants.CalculateScreenSquare(
				(float) Gdx.graphics.getWidth(),
				(float) Gdx.graphics.getHeight());
		
		while(true)
		{
			if (
					(xPos > (w/2) -(screenSquare/2) && xPos < (w/2) +(screenSquare/2) )
					|| (xPos + m_Width  > (w/2) -(screenSquare/2) && xPos + m_Width  < (w/2) +(screenSquare/2) )
					|| (xPos < (w/2) -(screenSquare/2) && xPos + m_Width > (w/2) +(screenSquare/2) )
					
				)
			{
				xPos = ClockConstants.GloablRandom.nextInt((int)screenWidth);
			}
			else
			{
				w=h+1;
				break;
			}				
		}
		
		
		// top to bottom
		KeyFrame kfMove = new KeyFrame();
		int lnMilliseconds = 0;

		kfMove.StartRectF = new RectF(
				xPos,
				0,
				xPos + m_Width,
				m_Height
			);	

		lnMilliseconds += 1;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = m_CurrentRectF = new RectF(
				xPos,
				0,
				xPos + m_Width,
				m_Height
			);	

		this.AddKeyFrame(kfMove);
					 
		
		//this.setV2(3f);
		//this.setU2(3f);
		this.flip(false, true);		
	}
	
	
	@Override
	public String toString()
	{
		return null;
	}
}
