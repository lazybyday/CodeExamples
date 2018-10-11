package com.DamnItJenkins.ClockworkCrisisPro.Phases;

import java.util.ArrayList;
import java.util.Calendar;

import com.DamnItJenkins.ClockworkCrisisPro.RectF;
import com.DamnItJenkins.ClockworkCrisisPro.dataModel.ClockConstants;
import com.DamnItJenkins.ClockworkCrisisPro.dataModel.ClockConstants.ImageNames;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class KeyFrameSprite extends Sprite implements IKeyFrameSprite 
{
	protected RectF m_CurrentRectF;
	
	private ArrayList<KeyFrame> m_arylstKeyFrame = new ArrayList<KeyFrame>();
	
	private int m_KeyFrameIndex = 0;
	
	protected boolean m_IsSelected = false;
	protected boolean m_IsHidden = false;
	
	private ImageNames m_SpriteImageName = ImageNames.Null_Image;
	
	public KeyFrameSprite ()
	{
		m_CurrentRectF = new RectF();
	}
	
	public KeyFrameSprite (TextureRegion tr)
	{
		super(tr);
		setCurrentRectF(new RectF(-5000, -5000, 1,1));
	}
	
	public KeyFrameSprite (Texture spriteTexture, int left, int top, int width, int height)
	{
		super(spriteTexture, left, top, width, height);
	}
	
	@Override
	public RectF getCurrentRectF()
	{
		return m_CurrentRectF;
	}
	
	
	@Override
	public boolean hasDrawText()
	{
		return false;
	}

	public void setCurrentRectF(RectF f)
	{
		this.setSize(f.width(), f.height());
		this.setPosition(f.left, f.top);
		m_CurrentRectF = f;
	}
	
	public boolean getIsSelected ()
	{
		return m_IsSelected;
	}
	
	public void setIsSelected(boolean value)
	{
		m_IsSelected = value;
	}
	
	public boolean getIsHidden ()
	{
		return m_IsHidden;
	}
	
	public void setIsHidden(boolean value)
	{
		m_IsHidden = value;
	}

	@Override
	public ImageNames getRenderImage() 
	{
		return m_SpriteImageName;
	}
	
	
	public void setRenderImage(ImageNames value) 
	{
		m_SpriteImageName = value;
	}	
	

	@Override
	public void AddKeyFrame(KeyFrame keyFrame) 
	{
		m_arylstKeyFrame.add(keyFrame);
		
	}
	
	@Override
	public ArrayList<KeyFrame> GetKeyFrames()
	{
		return m_arylstKeyFrame;		
	}
	

	@Override
	public void ProcessKeyFrame() 
	{
		boolean blnHasProcessed = false;
		m_KeyFrameIndex = 0;
		
		while (!blnHasProcessed && m_arylstKeyFrame.size() >=1 && m_KeyFrameIndex < m_arylstKeyFrame.size())
		{
			KeyFrame obj = m_arylstKeyFrame.get(m_KeyFrameIndex);
			
			if (Calendar.getInstance().after(obj.EndTime) && m_arylstKeyFrame.size() -1  != m_KeyFrameIndex)
			{
				m_arylstKeyFrame.remove(m_KeyFrameIndex);
			}	
			
			
			if ((Calendar.getInstance().before(obj.EndTime) && Calendar.getInstance().after(obj.StartTime))||  Calendar.getInstance().equals(obj.StartTime) ||  Calendar.getInstance().equals(obj.EndTime))
			{
				ActionKeyFrame(obj);
				break;
			}
			
			if (Calendar.getInstance().after(obj.EndTime) && m_arylstKeyFrame.size() -1  == m_KeyFrameIndex && obj.getEndFrameHasBeenProcessedAtLeastOnce() == false)
			{
				ActionKeyFrame(obj); 
				//m_KeyFrameIndex++;
				break;
			} 
				

			m_KeyFrameIndex++;
			
		
		}
	}
	
	private void ActionKeyFrame (KeyFrame obj)
	{
		RectF newPos = obj.ProcssRectF();
		
		setCurrentRectF(newPos);
		
		if (this.getRotation() != obj.CurrentRotation)
		{
			setRotation(obj.CurrentRotation);	
		}
		
		if (obj.PlayedStartSound == false && obj.StartSound != null)
		{
			ClockConstants.m_SoundManager.PlaySound(obj.StartSound);
			//obj.StartSound.play();
			obj.PlayedStartSound = true;
		}
	}

	@Override
	public void DrawText(SpriteBatch batch)
	{	
	}

	@Override
	public boolean hasDrawImage()
	{
		return true;
	}
}
