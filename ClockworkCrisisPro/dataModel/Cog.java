package com.DamnItJenkins.ClockworkCrisisPro.dataModel;

import java.util.Calendar;
import java.util.Random;

import com.DamnItJenkins.ClockworkCrisisPro.RectF;
import com.DamnItJenkins.ClockworkCrisisPro.SoundManager;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.KeyFrame;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.KeyFrameSprite;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.game.Sprites.CogGuideSprite;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.game.Sprites.CogRotationGuideSprite;
import com.DamnItJenkins.ClockworkCrisisPro.dataModel.ClockConstants.ImageNames;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Cog extends KeyFrameSprite
{
	private ImageNames RenderImage;
	
	private Cog[] m_SubCogs = null;
	private Cog m_CogShadow = null;

	private CogGuideSprite m_CogGuideSprite = null;
	private CogRotationGuideSprite m_CogRotationGuideSprite = null;

	private int m_Index = 0;
	private int m_RenderIndex = 0;
	private int m_ChangeAffectsNCogs = 0;
	private int m_ChangeAffectedSpeed = 0;

	private RectF m_RenderPosition;
	
	private boolean m_IsCogTimerActive = false;
	
	private boolean m_AllowSubObjectRotation = true;
	
	
	private int MIN_WIDHT_FOR_COG_ROTATION_GUIDE = 512;
	
	
	public int Size = 0;
	
	private boolean m_AllowMoveSound = false;
	
	public void setAllowMoveSound (boolean b)
	{
		m_AllowMoveSound = b;
	}

	public Cog[] getSubCogs()
	{
		return m_SubCogs;
	}

	public Cog getShadow()
	{
		return m_CogShadow;
	}

	public CogGuideSprite getGuideSprite()
	{
		return m_CogGuideSprite;
	}
	
	public CogRotationGuideSprite getCogRotationGuideSprite()
	{
		return m_CogRotationGuideSprite;
	}

	public boolean isRatchet(float degree)
	{
		// 360/60 = 6
		if (degree % 12 == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public float getRatchetValue()
	{
		float f = Math.round((getValue() / 360) * 30);
		f = Math.round((f / 30) * 360);

		return f;
	}

	public float getValue()
	{
		return this.getRotation();
	}

	public ImageNames getRenderImage()
	{
		return this.RenderImage;
	}

	public void setRenderImage(ImageNames image)
	{
		this.RenderImage = image;
		this.setTexture(ClockConstants.GetTexture(image));
		
		if (m_CogShadow != null)
		{
			this.m_CogShadow.setRenderImage(ClockConstants.GetShadowForBmp(image));
		}			
	}

	public void setValue(float value)
	{
		float copy = Math.round(value);

		while (copy < 0 || copy >= 360)
		{
			if (copy >= 360)
			{
				copy = copy - 360;
			}

			if (copy < 0)
			{
				copy = copy + 360;
			}
		}

		this.setRotation(copy);
	}

	public int getIndex()
	{
		return m_Index;
	}

	public void setIndex(int value)
	{
		m_Index = value;
	}
	
	public boolean getAllowSubObjectRotation()
	{
		return m_AllowSubObjectRotation;
	}

	public void setAllowSubObjectRotation(boolean value)
	{
		m_AllowSubObjectRotation = value;
	}
	
	
	public boolean getIsCogTimerActive()
	{
		return m_IsCogTimerActive;
	}

	public void setIsCogTimerActive(boolean value)
	{
		m_IsCogTimerActive = value;
	}
	
	@Override 
	public void setIsSelected(boolean value)
	{
		m_IsSelected = value;
		
		if (m_CogRotationGuideSprite != null)
		{
			if (this.getRenderPosition().width() < MIN_WIDHT_FOR_COG_ROTATION_GUIDE)
			{			
				m_CogRotationGuideSprite.setIsHidden(!value);
			}
		}
	}

	public int getRenderIndex()
	{
		return m_RenderIndex;
	}

	public void setRenderIndex(int value)
	{
		m_RenderIndex = value;
	}

	public int getChangeAffectsNCogs()
	{
		return m_ChangeAffectsNCogs;
	}

	public void setChangeAffectsNCogs(int value)
	{
		m_ChangeAffectsNCogs = value;
	}

	public int getChangeAffectedSpeed()
	{
		return m_ChangeAffectedSpeed;
	}

	public void setChangeAffectedSpeed(int value)
	{
		m_ChangeAffectedSpeed = value;
	}

	public RectF getRenderPosition()
	{
		return m_RenderPosition;
	}

	public void setRenderPosition(RectF value)
	{
		m_RenderPosition = value;
		
		//this.setRegion(m_RenderPosition.left, m_RenderPosition.top,512, 512);
		this.setBounds(m_RenderPosition.left, m_RenderPosition.top, m_RenderPosition.width(), m_RenderPosition.height());
		this.setOrigin(m_RenderPosition.width() /2, m_RenderPosition.height() /2);

		/*if (getIndex() >= 0)
		{
			SetupSubCogs();
		}*/

		if (m_CogShadow != null)
		{
			int offset = 10;
			
			offset = (int)(value.width() *0.1);
			
			m_CogShadow.setRenderPosition(new RectF(
					value.left + offset,
					value.top + offset,
					value.left + offset + value.width(),
					value.top + offset + value.height()));			
		}
		
		
		if (m_CogGuideSprite!= null)
		{
			m_CogGuideSprite.setupPosition(value.left, value.top, value.width(), value.height());
		}
		
		if (m_CogRotationGuideSprite!= null)
		{
			m_CogRotationGuideSprite.setupPosition(value.left, value.top, value.width(), value.height());
				
			if (this.getRenderPosition().width() >= MIN_WIDHT_FOR_COG_ROTATION_GUIDE)
			{
				m_CogRotationGuideSprite.setIsHidden(true);
			}
			else
			{
				m_CogRotationGuideSprite.setIsHidden(false);
			}		
			
		}
	}

	
	public void SetupStartAnimation (int startDelay)
	{
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		RectF[] startingRects = new RectF[]
				{
				new RectF(
						-1000,
						-1000,
						-1000 + this.getWidth(),
						-1000 + this.getHeight()
						)
						, 
						new RectF(
								-1000,
								 h + 1000,
								-1000 + this.getWidth(),
								h + 1000 + this.getHeight()
						)
				, 
				new RectF(
						w + 1000,
						 h + 1000,
						w + 1000 + this.getWidth(),
						h + 1000 + this.getHeight()
						)
				, 
				new RectF(
						w + 1000,
						-1000,
						w + 1000 + this.getWidth(),
						-1000 + this.getHeight()
						)
				}
				;
		
		RectF startRectF = startingRects[ClockConstants.GloablRandom.nextInt(startingRects.length)];
		
		
		BuildStartAnimationKeyFrames(this, startDelay, startRectF);
		this.setCurrentRectF(new RectF(
				-1000,
				-1000,
				-1000 + this.getWidth(),
				-1000 + this.getHeight()));
		
				
		if (this.getShadow() != null)
		{
			BuildStartAnimationKeyFrames(this.getShadow(), startDelay, startRectF);
			this.getShadow().setCurrentRectF(new RectF(
					-1000,
					-1000,
					-1000 + this.getWidth(),
					-1000 + this.getHeight()));
		}
		
		if (m_SubCogs != null)
		{
			for (int i = 0; i < m_SubCogs.length; i++)
			{
				m_SubCogs[i].SetupStartAnimation(i* 200);
			}
		}
		
		if (m_CogGuideSprite !=null)
		{
			m_CogGuideSprite.setColor(0, 0, 0, 0);
		}
	}
	
	
	public void SetupEndAnimation (int startDelay)
	{
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		RectF[] startingRects = new RectF[]
				{
				new RectF(
						-1000,
						-1000,
						-1000 + this.getWidth(),
						-1000 + this.getHeight()
						)
						, 
						new RectF(
								-1000,
								 h + 1000,
								-1000 + this.getWidth(),
								h + 1000 + this.getHeight()
						)
				, 
				new RectF(
						w + 1000,
						 h + 1000,
						w + 1000 + this.getWidth(),
						h + 1000 + this.getHeight()
						)
				, 
				new RectF(
						w + 1000,
						-1000,
						w + 1000 + this.getWidth(),
						-1000 + this.getHeight()
						)
				}
				;
		
		RectF startRectF = startingRects[ClockConstants.GloablRandom.nextInt(startingRects.length)];
		
		
		BuildEndAnimationKeyFrames(this, startDelay, startRectF);
	/*	this.setCurrentRectF(new RectF(
				-1000,
				-1000,
				-1000 + this.getWidth(),
				-1000 + this.getHeight()));
		
			*/	
		if (this.getShadow() != null)
		{
			BuildEndAnimationKeyFrames(this.getShadow(), startDelay, startRectF);
			/*this.getShadow().setCurrentRectF(new RectF(
					-1000,
					-1000,
					-1000 + this.getWidth(),
					-1000 + this.getHeight()));*/
		}
		
		if (m_SubCogs != null)
		{
			for (int i = 0; i < m_SubCogs.length; i++)
			{
				m_SubCogs[i].SetupEndAnimation(i* 200);
			}
		}
		
		if (m_CogGuideSprite !=null)
		{
			m_CogGuideSprite.setColor(0, 0, 0, 0);
			m_CogGuideSprite.setIsHidden(true);
		}
	}
	
	private void BuildStartAnimationKeyFrames (Cog c, int startDelay, RectF startRectF)
	{
	
		KeyFrame kfMove = new KeyFrame();
		int lnMilliseconds = 0;

		lnMilliseconds = startDelay;
		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = startRectF.Clone();
		kfMove.StartRotation = c.getRotation() - 359;

		lnMilliseconds += 500;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = c.getRenderPosition().Clone();
		kfMove.EndRotation = c.getRotation();
		
		if (m_AllowMoveSound)
		{
			kfMove.StartSound = SoundManager.SoundCogMove;
			kfMove.PlayedStartSound = false;
		}
		
		c.AddKeyFrame(kfMove);	
	}


	private void BuildEndAnimationKeyFrames (Cog c, int startDelay, RectF endRectF)
	{
	
		KeyFrame kfMove = new KeyFrame();
		int lnMilliseconds = 0;

		lnMilliseconds = startDelay;
		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF =  c.getRenderPosition().Clone();
		kfMove.StartRotation = c.getRotation() - 359;

		lnMilliseconds += 500;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = endRectF.Clone();
		kfMove.EndRotation = c.getRotation();
		
		if (m_AllowMoveSound)
		{
			kfMove.StartSound = SoundManager.SoundCogMove;
			kfMove.PlayedStartSound = false;
		}
		
		c.AddKeyFrame(kfMove);	
	}
	

	
	public Cog(int index, float value, int changeAffectsNCogs, boolean hasShadow)
	{
		Random r = new Random();

		m_Index = index;
		this.setValue(value);
		this.setChangeAffectsNCogs(changeAffectsNCogs);
		this.setChangeAffectedSpeed(r.nextInt(3) + 1);

		if (hasShadow)
		{
			m_CogShadow = new Cog(-1, value, -1, false);
		}

		if (index >= 0)
		{
			TextureRegion tr = new TextureRegion(
					ClockConstants.GetTexture(ImageNames.cog_guide2_16),
					0,
					0,
					16,
					512);
			tr.flip(false, true);

			m_CogGuideSprite = new CogGuideSprite(tr, 0f, 0f, 512f, 512f);
			m_CogGuideSprite.setRotation(value);
			
			
			TextureRegion trRotation = new TextureRegion(
					ClockConstants.GetTexture(ImageNames.cog_rotation_guide_512),
					0,
					0,
					512,
					512);
			trRotation.flip(false, true);
			
			m_CogRotationGuideSprite = new CogRotationGuideSprite(trRotation); 
			m_CogRotationGuideSprite.setRotation(value);		
			m_CogRotationGuideSprite.setIsHidden(true);
		}
		
		if (index ==0)
		{
			setAllowMoveSound(true);
		}
	}

	public void SetupSubCogs(int numSubCogs)
	{
		Direction lastDirectionSubCogPlacement = null;

		m_SubCogs = new Cog[numSubCogs];

		Cog ParentCog = this;

		for (int i = 0; i < m_SubCogs.length; i++)
		{
			float fLeft = 0;
			float fTop = 0;
			int cogSize = 0;// = r.Next(MIN_COG_SIZE, MAX_COG_SIZE);
			Direction directionSubCogPlacement = Direction.Random();

			m_SubCogs[i] = new Cog(-1, this.getValue(), -1, true);
			m_SubCogs[i].setRenderImage(ClockConstants.ImageNames.RandomSubCog());
			
			if (m_AllowMoveSound)
			{
				m_SubCogs[i].setAllowMoveSound(true);
			}

			if (lastDirectionSubCogPlacement != null)
			{
				for (int intRetry = 0; intRetry < 5; intRetry++)
				{
					if ((directionSubCogPlacement == Direction.TOP_LEFT && lastDirectionSubCogPlacement == Direction.BOTTOM_RIGHT) || (directionSubCogPlacement == Direction.BOTTOM_RIGHT && lastDirectionSubCogPlacement == Direction.TOP_LEFT) || (directionSubCogPlacement == Direction.TOP_RIGHT && lastDirectionSubCogPlacement == Direction.BOTTOM_LEFT) || (directionSubCogPlacement == Direction.BOTTOM_LEFT && lastDirectionSubCogPlacement == Direction.TOP_RIGHT))
					{
						directionSubCogPlacement = Direction.Random();
					}
				}
			}

			cogSize =  ClockConstants.CogSize[ClockConstants.GloablRandom.nextInt(ClockConstants.CogSize.length)];// (int)m_SubCogs[i].getRenderPosition().width();

			switch (directionSubCogPlacement)
			{
				case BOTTOM_RIGHT:

					fLeft = ParentCog.getRenderPosition().left + (ParentCog.getRenderPosition().width() / 2) - (cogSize / 7);
					fTop = ParentCog.getRenderPosition().top + (ParentCog.getRenderPosition().height() / 2) - (cogSize / 7);

					m_SubCogs[i].setRenderPosition(new RectF(

					fLeft, fTop, fLeft + cogSize, fTop + cogSize));

					ParentCog = m_SubCogs[i];
					break;

				case BOTTOM_LEFT:

					fLeft = ParentCog.getRenderPosition().left + (ParentCog.getRenderPosition().width() / 2) - cogSize + (cogSize / 7);
					fTop = ParentCog.getRenderPosition().top + (ParentCog.getRenderPosition().height() / 2) - (cogSize / 7);

					m_SubCogs[i].setRenderPosition(new RectF(

					fLeft, fTop, fLeft + cogSize, fTop + cogSize));
					ParentCog = m_SubCogs[i];
					break;

				case TOP_RIGHT:

					fLeft = ParentCog.getRenderPosition().left + (ParentCog.getRenderPosition().width() / 2) - (cogSize / 7);
					fTop = ParentCog.getRenderPosition().top + (ParentCog.getRenderPosition().height() / 2) - cogSize + (cogSize / 7);

					m_SubCogs[i].setRenderPosition(new RectF(
							fLeft,
							fTop,
							fLeft + cogSize,
							fTop + cogSize));
					ParentCog = m_SubCogs[i];
					break;

				case TOP_LEFT:

					fLeft = ParentCog.getRenderPosition().left + (ParentCog.getRenderPosition().width() / 2) - cogSize + (cogSize / 7);
					fTop = ParentCog.getRenderPosition().top + (ParentCog.getRenderPosition().height() / 2) - cogSize + (cogSize / 7);

					m_SubCogs[i].setRenderPosition(new RectF(
							fLeft,
							fTop,
							fLeft + cogSize,
							fTop + cogSize));
					ParentCog = m_SubCogs[i];
					break;
			}

			lastDirectionSubCogPlacement = directionSubCogPlacement;

		}
	}

	private final float COLOR_MERGE_FACTOR = 0.1f;

	@Override
	public void setColor(Color c)
	{
		if (this.getColor() != c)
		{
			Color current = this.getColor();
			float r = current.r;
			float g = current.g;
			float b = current.b;
			float a = current.a;

			if (current.r > c.r)
			{
				r = current.r - COLOR_MERGE_FACTOR;

				if (r < 0)
					r = 0;
			}
			else
				if (current.r < c.r)
				{
					r = current.r + COLOR_MERGE_FACTOR;

					if (r > 1)
						r = 1;
				}

			if (current.g > c.g)
			{
				g = current.g - COLOR_MERGE_FACTOR;

				if (g < 0)
					g = 0;
			}
			else
				if (current.g < c.g)
				{
					g = current.g + COLOR_MERGE_FACTOR;

					if (g > 1)
						g = 1;
				}

			if (current.b > c.b)
			{
				b = current.b - COLOR_MERGE_FACTOR;

				if (b < 0)
					b = 0;
			}
			else
				if (current.b < c.b)
				{
					b = current.b + COLOR_MERGE_FACTOR;

					if (b > 1)
						b = 1;
				}

			if (current.a > c.a)
			{
				a = current.a - COLOR_MERGE_FACTOR;

				if (a < 0)
					a = 0;
			}
			else
				if (current.a < c.a)
				{
					a = current.a + COLOR_MERGE_FACTOR;

					if (a > 1)
						a = 1;
				}

			super.setColor(r, g, b, a);

		}
	}

	@Override
	public void setRotation(float degrees)
	{
		// float oldvalue = this.getRotation();
		float diff = getValue() - degrees;

		float copy = degrees;
		// boolean blnInRange = false;

		copy = Math.round(copy);

		while (copy < 0 || copy >= 360)
		{
			if (copy >= 360)
			{
				copy = copy - 360;
			}

			if (copy < 0)
			{
				copy = copy + 360;
			}
		}

		super.setRotation(copy);

		if (m_AllowSubObjectRotation)
		{		
			if (m_SubCogs != null)
			{
				for (int i = 0; i < m_SubCogs.length; i++)
				{
					Cog subCog = m_SubCogs[i];
	
					if (i % 2 != 0)
					{
						subCog.setValue(subCog.getValue() + (diff * -1));
					}
					else
					{
						subCog.setValue(subCog.getValue() + diff);
					}
				}
			}
		}

		if (m_CogShadow != null)
		{
			m_CogShadow.setValue(copy);
		}

		if (m_CogGuideSprite != null)
		{
			m_CogGuideSprite.setRotation(copy);
		}
		
		if (m_CogRotationGuideSprite != null)
		{
			m_CogRotationGuideSprite.setRotation(copy);
		}
	}
}
