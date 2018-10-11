package com.DamnItJenkins.ClockworkCrisisPro;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.Stack;

import com.DamnItJenkins.ClockworkCrisisPro.Phases.ForegroundGritSprite;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.ForegroundLightSprite;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.IPhase;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.KeyFrame;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.KeyFrameSprite;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.game.ScoreManager;
import com.DamnItJenkins.ClockworkCrisisPro.dataModel.ClockConstants;
import com.DamnItJenkins.ClockworkCrisisPro.dataModel.ClockConstants.ImageNames;
import com.DamnItJenkins.ClockworkCrisisPro.dataModel.Cog;
import com.DamnItJenkins.ClockworkCrisisPro.dataModel.Utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;



public class GameManager
{	
	public final int MINIMUM_COGS = 2;
	
	private int GRID_SIZE = ClockConstants.CogSize[ClockConstants.CogSize.length -1]; //pixels 
	
	private final int COG_SPIN_TIMER_DURATION = 60; //seconds

	private Cog[] m_Cogs;

	private int[] m_PickOrder;

	private boolean m_ShowPath = false;

	private Stack<IPhase> m_StackPhase = new Stack<IPhase>();

	public int widthSize = 0;
	public int heightSize = 0;
	
	private Calendar m_CogSpinTimer = null;
	
	private ScoreManager m_ScoreManager = null;

	public Stack<IPhase> getPhaseStack()
	{
		return m_StackPhase;
	}

	public void setShowPath(boolean b)
	{
		m_ShowPath = b;
	}

	public boolean getShowPath()
	{
		return m_ShowPath;
	}
	
	public ScoreManager getScoreManager()
	{
		return m_ScoreManager;
	}
	
	
	public int getLevel()
	{
		int intLevel = 1;
		if (m_Cogs != null)
		{
			intLevel = m_Cogs.length - MINIMUM_COGS + 1;
		}
			
		return intLevel;
	}
	
	public int getMaxLevel ()
	{
		//return 1;
		return (CalculateMaxGridPositions() - MINIMUM_COGS + 1);
	}
	
	
	public String getLevelText ()
	{
		String strReturn = "";
		if (m_Cogs != null)
		{
			strReturn = ""+ getLevel();
		}
			
		return strReturn;
	}
	
	public String getMaxLevelText()
	{
		return ""+ getMaxLevel();
	}

	public Cog[] getCogs()
	{
		return m_Cogs;
	}

	public int[] getPickOrder()
	{
		return m_PickOrder;
	}
	

	public Calendar getCogSpinTimer()
	{
		return m_CogSpinTimer;
	}
	
	public String getCogSpinTimerCountdownText()
	{		
		Calendar a = getCogSpinTimer();
		Calendar b = Calendar.getInstance();
		
		return "   "+ ((a.getTimeInMillis() - b.getTimeInMillis()) /1000);
		
	}

	
	public void ActivateCogSpin (KeyFrameSprite CogSpinIcon)
	{
		if (m_CogSpinTimer.before(Calendar.getInstance()) && CheckForWin() == false)
		{			
			int intRandomCogIndex = ClockConstants.GloablRandom.nextInt(m_Cogs.length);
			Cog c = m_Cogs[intRandomCogIndex];
			
			c.setIsCogTimerActive(true);

			KeyFrame kf1 =new KeyFrame();
			
			int intRandomRotation = ClockConstants.GloablRandom.nextInt(359) +1;
			
			kf1.StartRotation = c.getRotation();
			kf1.EndRotation = c.getRotation() + 300;
			kf1.StartRectF = c.getRenderPosition().Clone();
			kf1.EndRectF = c.getRenderPosition().Clone();
			kf1.StartTime = Calendar.getInstance();
			kf1.EndTime.add(Calendar.MILLISECOND, 1000);
			kf1.PlayedStartSound = false;
			kf1.StartSound = SoundManager.SoundCogSpinTimerActivate;
			
			KeyFrame kf2 =new KeyFrame();
			
			kf2.StartRotation = c.getRotation() + 300;
			kf2.EndRotation = c.getRotation() + 300 + intRandomRotation;
			kf2.StartRectF = c.getRenderPosition().Clone();
			kf2.EndRectF = c.getRenderPosition().Clone();
			kf2.StartTime.add(Calendar.MILLISECOND, 1001);
			kf2.EndTime.add(Calendar.MILLISECOND, 2000);
			
			
			
			c.AddKeyFrame(kf1);
			c.AddKeyFrame(kf2);
			
			
			KeyFrame kf3 = kf1.Clone();
			kf3.StartRectF = CogSpinIcon.getCurrentRectF();
			kf3.EndRectF = CogSpinIcon.getCurrentRectF();
			KeyFrame kf4 = kf2.Clone();
			kf4.StartRectF = CogSpinIcon.getCurrentRectF();
			kf4.EndRectF = CogSpinIcon.getCurrentRectF();

					
			CogSpinIcon.AddKeyFrame(kf3);
			CogSpinIcon.AddKeyFrame(kf4);
			
			Calendar now = Calendar.getInstance();
			now.add(Calendar.SECOND, COG_SPIN_TIMER_DURATION + this.getLevel());
			m_CogSpinTimer = now;
			m_PlayedSoundCogSpinTimerExpire = false;
			
			m_ScoreManager.IncrementCogSpinTimerExpires();
		}
		else
		{
			if (m_PlayedSoundCogSpinTimerExpire == false)
			{
				Calendar now = Calendar.getInstance();
				now.add(Calendar.SECOND, +2);
				if (m_CogSpinTimer.before(now) && CheckForWin() == false)
				{
					ClockConstants.m_SoundManager.PlaySound(SoundManager.SoundCogSpinTimerExpire);
					m_PlayedSoundCogSpinTimerExpire = true;
				}
			}
		}
	}
	
	private boolean m_PlayedSoundCogSpinTimerExpire = false;

	public GameManager(int w, int h)
	{
		widthSize = w;
		heightSize = h;
	}

	public void CreateNewGame(int numberOfCogs)
	{		
		ClockConstants.m_FontManager.getFont(FontManager.Fonts.QuoteFontBlack).setColor(0, 0, 0, 1);
		
		m_ScoreManager = new ScoreManager(this);
		

		this.m_GamePhaseState =  GameState.Play;
		
		if (numberOfCogs >  CalculateMaxGridPositions())
		{
			numberOfCogs = CalculateMaxGridPositions();
		}
		
		//numberOfCogs = CalculateMaxGridPositions();
		
		m_Cogs = new Cog[numberOfCogs];
		m_PickOrder = new int[numberOfCogs];

		Random r = ClockConstants.GloablRandom;

		m_CogSpinTimer = Calendar.getInstance();
		m_CogSpinTimer.add(Calendar.SECOND, COG_SPIN_TIMER_DURATION+ this.getLevel());
		
		for (int i = 0; i < m_Cogs.length; i++)
		{
			m_Cogs[i] = new Cog(i, r.nextInt(360), r.nextInt(3) + 1, true);
			m_Cogs[i].setRenderImage(ClockConstants.ImageNames.RandomCog());
			m_Cogs[i].Size = ClockConstants.CogSize[0]; //largest
		}

		for (int i = m_PickOrder.length - 1; i > 0; i--)
		{
			m_PickOrder[i] = i;
		}

		for (int i = m_PickOrder.length - 1; i > 0; i--)
		{
			int swapIndex = r.nextInt(i + 1);
			if (swapIndex != i)
			{
				int tmp = m_PickOrder[swapIndex];
				m_PickOrder[swapIndex] = m_PickOrder[i];
				m_PickOrder[i] = tmp;
			}
		}

		//Log.d(TAG, "CreateNewGame(" + numberOfCogs + ") - end");
	}

	public void SetCogRenderPositions(int widthSize, int heightSize)
	{
		int intRetries = 0;
		int intMaxRetires = CalculateMaxGridPositions();
		
		if (this.getLevel() == 1)
		{
			int i = 0;
			Cog c = getCogs()[getPickOrder()[i]];
			c.setRenderIndex(i);		
			c.setRenderPosition(DefineRectFForGridPosition(widthSize/5, heightSize/5, GRID_SIZE, c.Size));
			
			i = 1;
			c = getCogs()[getPickOrder()[i]];
			c.setRenderIndex(i);		
			c.setRenderPosition(DefineRectFForGridPosition((widthSize/2) + (widthSize/5), (heightSize/2)+ (heightSize/5), GRID_SIZE, c.Size));
			
		}
		else
		{		
			for (int i = 0; i < getPickOrder().length; i++)
			{
				Cog c = getCogs()[getPickOrder()[i]];
				c.setRenderIndex(i);
					
				if (GridSearchRenderPosition(i, c, widthSize, heightSize, c.Size) == false)
				{			
					for (int j = 1; j < ClockConstants.CogSize.length; j++)
					{
						if (GridSearchRenderPosition(i, c, widthSize, heightSize, ClockConstants.CogSize[j]) == false)
						{
							if (j == ClockConstants.CogSize.length -1)
							{
								if (intRetries < intMaxRetires)
								{
									if (intMaxRetires - getPickOrder().length <= intRetries)
									{
										Cog oldCog = getCogs()[getPickOrder()[(intMaxRetires - intRetries) % getPickOrder().length]];
										GridSearchRenderPosition(oldCog.getRenderIndex(), oldCog, widthSize, heightSize, ClockConstants.CogSize[ClockConstants.CogSize.length -1]);
										oldCog.Size = ClockConstants.CogSize[ClockConstants.CogSize.length -1]; //smallest
									}
									
									i = 0;
									intRetries++;
								}
							}
						}
						else
						{
							c.Size = ClockConstants.CogSize[j];
							break;
						}
					}
				}		
			}
		}
		
		int numOfSubCogs = MAX_NUMBER_OF_SUBCOGS;
		
		if (getLevel() >0 && getLevel() < (getMaxLevel() /3) )
		{
			numOfSubCogs = 4;
		}
		else if (getLevel() < (getMaxLevel() /3) * 2)
		{
			numOfSubCogs = 3;
		}
		else
		{
			numOfSubCogs = 2;
		}
			
		
		for (int i = 0; i < getPickOrder().length; i++)
		{
			Cog c = getCogs()[getPickOrder()[i]];
			c.SetupSubCogs(numOfSubCogs);
		}
	}

	public static final int MAX_NUMBER_OF_SUBCOGS = 5;
	
	private RectF DefineRectFForGridPosition(int x, int y, int GridSize, int CogSize)
	{
		RectF rectFReturn = new RectF();

		rectFReturn.left = x + (GridSize / 2) - (CogSize / 2);
		rectFReturn.top = y + (GridSize / 2) - (CogSize / 2);
		rectFReturn.right = rectFReturn.left + CogSize;
		rectFReturn.bottom = rectFReturn.top + CogSize;

		return rectFReturn;
	}

	
	
	public int CalculateMaxGridPositions ()
	{
		final int MAX_POSITIONS = 36;
		
		int intNumberOfGridsWide = (widthSize / GRID_SIZE);

		int intNumberOfGridsLong = (heightSize / GRID_SIZE) ;
		
		int intNumberOfPositions = intNumberOfGridsWide * intNumberOfGridsLong;
		
		if (intNumberOfPositions < MAX_POSITIONS)
		{
			return intNumberOfPositions;
		}
		else
		{
			return MAX_POSITIONS;
		}		
	}
	
	private boolean GridSearchRenderPosition(int intCogIndex, Cog c, int widthSize, int heightSize, int cogSize)
	{		
		int i = intCogIndex;
		boolean blnOverlaps = false;

		ArrayList<PointF> freePoints = new ArrayList<PointF>();

		//int cogSize =  (int)c.getRenderPosition().width();//  ClockConstants.CogSize[0]; //  ClockConstants.GetImageNameSize(c.getRenderImage());

		int intUsableWidth = (widthSize / GRID_SIZE) * GRID_SIZE;
		int intExcessWidth = widthSize % GRID_SIZE;

		int intUsableHeight = (heightSize / GRID_SIZE) * GRID_SIZE;
		int intExcessHeight = heightSize % GRID_SIZE;

		for (int intWidthSearch = 0 + intExcessWidth / 2; intWidthSearch < intUsableWidth + intExcessWidth / 2; intWidthSearch += GRID_SIZE)
		{
			for (int intHeightSearch = 0 + intExcessHeight / 2; intHeightSearch < intUsableHeight + intExcessHeight / 2; intHeightSearch += GRID_SIZE)
			{
				blnOverlaps = false;
				RectF pos = DefineRectFForGridPosition(intWidthSearch, intHeightSearch, GRID_SIZE, cogSize);
				//c.setRenderPosition);
				/*
				 * new RectF ( intWidthSearch, intHeightSearch, intWidthSearch +
				 * cogSize, intHeightSearch + cogSize ));
				 */

				for (int checkPreviousLayout = 0; checkPreviousLayout < i; checkPreviousLayout++)
				{
					// if (RectF.intersects(c.getRenderPosition(),
					// m_GameManger.getCogs()[m_GameManger.getPickOrder()[checkPreviousLayout]].getRenderPosition()))
					if (Utils.CirclesInterset(pos, getCogs()[getPickOrder()[checkPreviousLayout]].getRenderPosition()))
					{
						blnOverlaps = true;
						break;
					}
				}

				if (!blnOverlaps)
				{
					freePoints.add(new PointF(intWidthSearch, intHeightSearch));
					// break;
				}
			}

			if (!blnOverlaps)
			{
				// break;
			}
		}

		if (!freePoints.isEmpty())
		{
			//random distribution
			/*int intRandomSum = 0 ;
			int intTotalDistribution = 10;
			
			for (int intDistribution= 0 ; intDistribution < intTotalDistribution ; intDistribution++ )
			{
				intRandomSum +=	ClockConstants.GloablRandom.nextInt(freePoints.size());
			}
					
			PointF p = freePoints.get(ClockConstants.GloablRandom.nextInt(Math.round(intRandomSum/intTotalDistribution)));
			*/
			
			PointF p = freePoints.get(ClockConstants.GloablRandom.nextInt(freePoints.size()));
			// PointF p = freePoints.get(0);

			float intWidthSearch = p.x;
			float intHeightSearch = p.y;

			// c.setRenderPosition(new RectF(intWidthSearch, intHeightSearch,
			// intWidthSearch + cogSize, intHeightSearch + cogSize));
			c.setRenderPosition(DefineRectFForGridPosition((int) intWidthSearch, (int) intHeightSearch, GRID_SIZE, cogSize));
			
			return true;

		}
		else
		{
			return false;

		}	
	}

	

	
	public static enum GameState
	{
		Play,
		Won,
	}
	
	public GameState m_GamePhaseState = GameState.Play;
	
	
	public boolean CheckForWin()
	{
		float expectedValue = m_Cogs[0].getRatchetValue();

		for (int i = 0; i < m_Cogs.length; i++)
		{
			if (m_Cogs[i].getRatchetValue() != expectedValue || m_Cogs[i].getIsCogTimerActive())
			{
				m_GamePhaseState = GameState.Play;
				return false;
			}
		}
		
		return true;
	}

	public void MoveAllCogsBy(float moveValue)
	{
		for (int i = 0; i < m_Cogs.length; i++)
		{
			int lnMilliseconds = 0;
			Cog c = m_Cogs[i];
			KeyFrame kfMove = new KeyFrame();
			kfMove.StartRectF = c.getRenderPosition().Clone();
			kfMove.StartRotation = c.getValue();
			kfMove.EndRectF = c.getRenderPosition().Clone();
			kfMove.EndRotation = c.getValue() + moveValue;
			lnMilliseconds += 200;
			kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
			
			c.AddKeyFrame(kfMove);
			
			//c.setValue(c.getValue() + moveValue);
		}
	}
	
	public void MoveAllCogsTo(float moveValue)
	{
		for (int i = 0; i < m_Cogs.length; i++)
		{
			m_Cogs[i].setValue(moveValue);
		}
	}

	public void MoveCogBy(int index, float moveValue)
	{
		Cog c = m_Cogs[index];
		c.setValue(c.getValue() + moveValue);

		//for (int i = index + 1; i < m_Cogs.length; i++)
		for (int i = index + 1; i < m_Cogs.length && i < index + 1 + c.getChangeAffectsNCogs() ; i++)
		{
			Cog childCog = m_Cogs[i];
			childCog.setValue(childCog.getValue() + (moveValue * childCog.getChangeAffectedSpeed()));
		}
	}
	
	
	public KeyFrameSprite BuildForegroundCombinedSprite (ArrayList<Sprite> m_SpriteList)
	{
		float w = (float)Gdx.graphics.getWidth();
		float h = (float)Gdx.graphics.getHeight();
		float spriteHeight =h; 
		float spriteWidth = w;

		TextureRegion tr = new TextureRegion(
				ClockConstants.GetTexture(ImageNames.foreground_combined_1_512),
				0,
				0,
				512,
				512);
		tr.flip(false, true);

		KeyFrameSprite spriteforegroundShadow = new KeyFrameSprite(tr);
		
		spriteforegroundShadow.setCurrentRectF(new RectF(0,0,spriteWidth,  spriteHeight));
		m_SpriteList.add(spriteforegroundShadow);
		
		return spriteforegroundShadow;
	}
	
	
	public void BuildForegroundShadowSprite (ArrayList<Sprite> m_SpriteList)
	{
		float w = (float)Gdx.graphics.getWidth();
		float h = (float)Gdx.graphics.getHeight();
		float spriteHeight =h; 
		float spriteWidth = w;

		TextureRegion tr = new TextureRegion(
				ClockConstants.GetTexture(ImageNames.foreground_shadow_1_512),
				0,
				0,
				512,
				512);
		tr.flip(false, true);

		KeyFrameSprite spriteforegroundShadow = new KeyFrameSprite(tr);
		
		spriteforegroundShadow.setCurrentRectF(new RectF(0,0,spriteWidth,  spriteHeight));
		spriteforegroundShadow.setColor(1,1,1, 0.4f);
		m_SpriteList.add(spriteforegroundShadow);
	}
	
	
	public void BuildForegroundLightSprite (ArrayList<Sprite> m_SpriteList)
	{
		float w = (float)Gdx.graphics.getWidth();
		float h = (float)Gdx.graphics.getHeight();
		float spriteHeight =h; 
		float spriteWidth = w;

		TextureRegion tr = new TextureRegion(
				ClockConstants.GetTexture(ImageNames.foreground_light_1_512),
				0,
				0,
				512,
				512);
		tr.flip(false, true);

		ForegroundLightSprite foregroundLightSprite = new ForegroundLightSprite(tr);		
		foregroundLightSprite.setCurrentRectF(new RectF(0,0,spriteWidth,  spriteHeight));
		m_SpriteList.add(foregroundLightSprite);
	}
	
	
	public void BuildForegroundGitSprite (ArrayList<Sprite> m_SpriteList)
	{
		float w = (float)Gdx.graphics.getWidth();
		float h = (float)Gdx.graphics.getHeight();
		float spriteHeight =h; 
		float spriteWidth = w;

		TextureRegion tr = new TextureRegion(
				ClockConstants.GetTexture(ImageNames.foreground_grit_1_512),
				0,
				0,
				512,
				512);
		tr.flip(false, true);

		ForegroundGritSprite foregroundGritSprite = new ForegroundGritSprite(tr);		
		foregroundGritSprite.setCurrentRectF(new RectF(0,0,spriteWidth,  spriteHeight));
		m_SpriteList.add(foregroundGritSprite);
	}
	
	public RectF CalculateArchwayPositionForLevel(int intLevel)
	{
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		float spriteHeight = (((h/2)/ this.getMaxLevel()) * intLevel)+(h/2) ;
		
		float spriteWidth = spriteHeight;
		
		return new RectF(
				w / 2 - (spriteWidth / 2),
				h / 2 - (spriteHeight/2),
				w / 2 + (spriteWidth / 2),
				h / 2 + (spriteHeight/2));
	}
	
	public KeyFrameSprite BuildArchwaySprite(ArrayList<Sprite> m_SpriteList)
	{
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		float spriteHeight = (((h/2)/ this.getMaxLevel()) * this.getLevel())+(h/2) ;
		
		float spriteWidth = spriteHeight;
	

		TextureRegion tr = new TextureRegion(
				ClockConstants.GetTexture(ImageNames.archway),
				0,
				0,
				512,
				512);
		tr.flip(false, true);
		KeyFrameSprite archwaySprite = new KeyFrameSprite(tr);

		archwaySprite.setOrigin(spriteWidth / 2, spriteHeight / 2);

		// left to right
		KeyFrame kfMove = new KeyFrame();
		int lnMilliseconds = 0;

		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = CalculateArchwayPositionForLevel(this.getLevel());

		lnMilliseconds += 10;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = CalculateArchwayPositionForLevel(this.getLevel());

		archwaySprite.AddKeyFrame(kfMove);
		m_SpriteList.add(archwaySprite);
		
		return archwaySprite;
	}
}
