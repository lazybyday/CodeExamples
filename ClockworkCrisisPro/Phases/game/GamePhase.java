package com.DamnItJenkins.ClockworkCrisisPro.Phases.game;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import com.DamnItJenkins.ClockworkCrisisPro.FontManager;
import com.DamnItJenkins.ClockworkCrisisPro.GameManager;
import com.DamnItJenkins.ClockworkCrisisPro.RectF;
import com.DamnItJenkins.ClockworkCrisisPro.SoundManager;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.ButtonSprite;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.IPhase;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.KeyFrame;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.KeyFrameSprite;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.TextSprite;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.endingScene.EndingScenePhase;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.game.Sprites.ChainShadowSprite;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.game.Sprites.ColumnedTextSprite;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.game.Sprites.DoorSprite;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.game.Sprites.SlideDoorLeftSprite;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.game.Sprites.SlideDoorRightSprite;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.game.Sprites.VictoryQuoteSprite;
import com.DamnItJenkins.ClockworkCrisisPro.dataModel.ClockConstants;
import com.DamnItJenkins.ClockworkCrisisPro.dataModel.ClockConstants.ImageNames;
import com.DamnItJenkins.ClockworkCrisisPro.dataModel.Cog;
import com.DamnItJenkins.ClockworkCrisisPro.dataModel.Utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GamePhase implements IPhase
{
	private final String BUTTON_TYPE_NEXT_LEVEL = "NextLevel";
	private final String BUTTON_TYPE_QUIT = "Quit";

	private GameManager m_GameManager;

	private int[] m_MouseSelectedCogIndex = new int[2];
	private double[] startAngle = new double[2];

	private ArrayList<Sprite> m_SpriteList;

	private boolean m_IsFirstRender = true;
	private boolean m_WinAnimationStarted = false;
	private boolean m_NextLevelAnimationStarted = false;
	private boolean m_StartAnimationStarted = true;
	
	private int menuButtonOffsetY = 0;
	private int m_LastWinMovementSecond = -1;
	private int m_TitleSpriteLayerIndex = -1;

	private KeyFrameSprite spinCogIcon = null;
	
	private SlideDoorLeftSprite spriteSlideDoorLeft;
	private SlideDoorRightSprite spriteSlideDoorRight;
	
	private Calendar StartAnimationFinishTime = Calendar.getInstance();
	private Calendar m_NextLevelAnimationStartedAt = null;

	@Override
	public ArrayList<Sprite> getSpriteList()
	{
		return m_SpriteList;
	}

	public GamePhase(GameManager value)
	{
		m_SpriteList = new ArrayList<Sprite>();
		m_GameManager = value;

		for (int j = 0; j < m_MouseSelectedCogIndex.length; j++)
		{
			m_MouseSelectedCogIndex[j] = -1;
		}
	}

	@Override
	public String getPhaseName()
	{
		return "MainMenuPhase";
	}
	
	private void SetupForFirstrender()
	{
		m_WinAnimationStarted = false;
		m_StartAnimationStarted = true;
		m_NextLevelAnimationStarted = false;
		StartAnimationFinishTime = Calendar.getInstance();
		StartAnimationFinishTime.add(Calendar.SECOND, 2);
		
		BuildSprites();
		m_IsFirstRender = false;
	}

	@Override
	public void onDraw(SpriteBatch batch)
	{
		if (m_IsFirstRender)
		{
			SetupForFirstrender();
		}

		if (m_GameManager != null)
		{
			synchronized (m_GameManager)
			{
				if (m_GameManager.m_GamePhaseState == GameManager.GameState.Won)
				{								
					if (m_NextLevelAnimationStarted == false)
					{
						Calendar time = Calendar.getInstance();

						if (m_LastWinMovementSecond != time.get(Calendar.SECOND))
						{
							m_LastWinMovementSecond = time.get(Calendar.SECOND);
							m_GameManager.MoveAllCogsBy(6);							
							ClockConstants.m_SoundManager.PlaySound(SoundManager.SoundCogRotate);							
						}
					}
					else
					{						
						if (m_GameManager.getLevel() == m_GameManager.getMaxLevel())
						{
							Calendar time = Calendar.getInstance();
							time.add(Calendar.SECOND, -4);
							if (m_NextLevelAnimationStartedAt.before(time))
							{
								
								IPhase s = m_GameManager.getPhaseStack().pop();
								
								ClockConstants.m_SoundManager.PlaySound(SoundManager.SoundButtonPress);
								m_GameManager.getPhaseStack().push(new EndingScenePhase(m_GameManager));
							}
						}
						else
						{
						
							if (spriteDoor.getOpenPercentage() == 0)
							{
								synchronized (m_GameManager)
								{
									m_GameManager.CreateNewGame(m_GameManager.getCogs().length + 1);
									m_GameManager.SetCogRenderPositions(
											m_GameManager.widthSize,
											m_GameManager.heightSize);
	
									this.getSpriteList().clear();
									this.m_IsFirstRender = true;
									SetupForFirstrender();
								}
							}
						}
					}					
				}
				else if (m_GameManager.m_GamePhaseState == GameManager.GameState.Play)
				{					
					m_GameManager.ActivateCogSpin(this.spinCogIcon);			

					boolean isAtLeastOneSelected = false;
					for (int j = 0; j < m_MouseSelectedCogIndex.length; j++)
					{
						if (m_MouseSelectedCogIndex[j] != -1)
						{
							isAtLeastOneSelected = true;
							break;
						}
					}
			
					if (!isAtLeastOneSelected && m_GameManager.CheckForWin())
					{							
						m_GameManager.m_GamePhaseState = GameManager.GameState.Won;
						m_WinAnimationStarted = true;
						m_GameManager.MoveAllCogsTo(m_GameManager.getCogs()[0].getValue());
						BuildWinSprites();
						m_LastWinMovementSecond = Calendar.getInstance().get(Calendar.SECOND);
					}				
				}

				render2(batch);
			}
		}
	}

	
	private void BuildWinSprites()
	{
		if (spriteHowToPlaySprite != null)
		{
			int lnMilliseconds = 0;
			KeyFrame kfMove = new KeyFrame();
			kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
			kfMove.StartRectF = spriteHowToPlaySprite.getCurrentRectF().Clone();

			lnMilliseconds += 500;
			kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
			kfMove.EndRectF = new RectF(
					 -5000,
					spriteHowToPlaySprite.getCurrentRectF().top,
					-5000 +  spriteHowToPlaySprite.getCurrentRectF().width(),
					spriteHowToPlaySprite.getCurrentRectF().bottom);
			
			spriteHowToPlaySprite.AddKeyFrame(kfMove);
		}
		
		
		m_TitleSpriteLayerIndex = m_SpriteList.size();
		
		for(int i = 0; i< m_SpriteList.size(); i++)
		{
			if (m_SpriteList.get(i) == this.spriteforegroundShadow )
			{
				m_TitleSpriteLayerIndex = i;
			}
		}
		
		menuButtonOffsetY = 0;
		spriteDoor.setOpenPercentage(1, 5000);

		BuildVictoryQuoteSprite();
		BuildVictoryQuoteTitleSprites();
		
		BuildScoreSprite();
		BuildScoreTitleSprites();
		
		BuildNextLevelButtonSprites();
		BuildQuitButtonSprites();
	}
	
	
	private void BuildNextLevelExitAnimationSprites()
	{
		m_KeyFrameSpriteArchway.setIsHidden(false);
		
		for (int i = 0; i < m_GameManager.getCogs().length; i++)
		{
			Cog c = m_GameManager.getCogs()[i];
			c.SetupEndAnimation(500);
		}
		
		//VictoryQute
		// left to right
		KeyFrame kfMove = new KeyFrame();
		int lnMilliseconds = 0;
		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = victoryQuoteSprite.getCurrentRectF().Clone();

		lnMilliseconds += 500;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = new RectF(
				-5000,
				 victoryQuoteSprite.getCurrentRectF().top,
				-5000 +  victoryQuoteSprite.getCurrentRectF().width(),
				victoryQuoteSprite.getCurrentRectF().bottom);

		victoryQuoteSprite.GetKeyFrames().clear();
		victoryQuoteSprite.AddKeyFrame(kfMove);
		
		
		//Score Sprite
		kfMove = new KeyFrame();
		lnMilliseconds = 0;
		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = scoreSprite.getCurrentRectF().Clone();

		lnMilliseconds += 500;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = new RectF(
				scoreSprite.getCurrentRectF().left +5000,
				scoreSprite.getCurrentRectF().top,
				scoreSprite.getCurrentRectF().left +5000 +  scoreSprite.getCurrentRectF().width(),
				scoreSprite.getCurrentRectF().bottom);

		scoreSprite.GetKeyFrames().clear();
		scoreSprite.AddKeyFrame(kfMove);
		
		
		
		//keyFrameSpriteScoreTitle
		kfMove = new KeyFrame();
		lnMilliseconds = 0;
		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = keyFrameSpriteScoreTitle.getCurrentRectF().Clone();

		lnMilliseconds += 500;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = new RectF(
				keyFrameSpriteScoreTitle.getCurrentRectF().left +5000,
				keyFrameSpriteScoreTitle.getCurrentRectF().top,
				keyFrameSpriteScoreTitle.getCurrentRectF().left +5000 + keyFrameSpriteScoreTitle.getCurrentRectF().width(),
				keyFrameSpriteScoreTitle.getCurrentRectF().bottom);
		
		keyFrameSpriteScoreTitle.GetKeyFrames().clear();
		keyFrameSpriteScoreTitle.AddKeyFrame(kfMove);
		
		
		
		//keyFrameSpriteVictoryQuoteTitle
		kfMove = new KeyFrame();
		lnMilliseconds = 0;
		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = keyFrameSpriteVictoryQuoteTitle.getCurrentRectF().Clone();

		lnMilliseconds += 500;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = new RectF(
				-5000,
				keyFrameSpriteVictoryQuoteTitle.getCurrentRectF().top,
				-5000 + keyFrameSpriteVictoryQuoteTitle.getCurrentRectF().width(),
				keyFrameSpriteVictoryQuoteTitle.getCurrentRectF().bottom);
		
		keyFrameSpriteVictoryQuoteTitle.GetKeyFrames().clear();
		keyFrameSpriteVictoryQuoteTitle.AddKeyFrame(kfMove);
		
		
			
		//buttonSpriteNextLevel
		kfMove = new KeyFrame();
		lnMilliseconds = 0;
		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = buttonSpriteNextLevel.getCurrentRectF().Clone();

		lnMilliseconds += 500;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = new RectF(
				+5000 + m_GameManager.widthSize,
				buttonSpriteNextLevel.getCurrentRectF().top,
				+5000 + m_GameManager.widthSize + buttonSpriteNextLevel.getCurrentRectF().width(),
				buttonSpriteNextLevel.getCurrentRectF().bottom);
		
		buttonSpriteNextLevel.GetKeyFrames().clear();
		buttonSpriteNextLevel.AddKeyFrame(kfMove);
		
		
		//buttonSpriteQuit
		kfMove = new KeyFrame();
		lnMilliseconds = 0;
		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = buttonSpriteNextLevel.getCurrentRectF().Clone();

		lnMilliseconds += 500;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = new RectF(
				+5000 + m_GameManager.widthSize,
				buttonSpriteQuit.getCurrentRectF().top,
				+5000 + m_GameManager.widthSize + buttonSpriteQuit.getCurrentRectF().width(),
				buttonSpriteQuit.getCurrentRectF().bottom);
		
		buttonSpriteQuit.GetKeyFrames().clear();
		buttonSpriteQuit.AddKeyFrame(kfMove);

		
		//chainShadowSprite
		kfMove = new KeyFrame();
		lnMilliseconds = 1000;
		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = chainShadowSprite.getCurrentRectF().Clone();
		
		lnMilliseconds += 4000;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		
		if ( chainShadowSprite.getCurrentRectF().left < (m_GameManager.widthSize/2))
		{
			kfMove.EndRectF =  new RectF(
					-5000 ,
					chainShadowSprite.getCurrentRectF().top,
					-5000,
					chainShadowSprite.getCurrentRectF().bottom);
		}
		else		
		{		
			kfMove.EndRectF =  new RectF(
					+5000 + m_GameManager.widthSize,
					chainShadowSprite.getCurrentRectF().top,
					+5000 + m_GameManager.widthSize + chainShadowSprite.getCurrentRectF().width(),
					chainShadowSprite.getCurrentRectF().bottom);
		}

		chainShadowSprite.AddKeyFrame(kfMove);
		
		//m_KeyFrameSpriteArchway
		kfMove = new KeyFrame();
		lnMilliseconds = 2000;
		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = m_KeyFrameSpriteArchway.getCurrentRectF().Clone();

		
		if (m_GameManager.getLevel()  == m_GameManager.getMaxLevel())
		{
			lnMilliseconds += 4000;
			kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
			kfMove.EndRectF = m_GameManager.CalculateArchwayPositionForLevel(m_GameManager.getMaxLevel() * 8);
		}
		else
		{
			lnMilliseconds += 4000;
			kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
			kfMove.EndRectF = m_GameManager.CalculateArchwayPositionForLevel(m_GameManager.getLevel() + 1);
			kfMove.PlayedStartSound = false;
			kfMove.StartSound = SoundManager.SoundFootSteps;
		}
		m_KeyFrameSpriteArchway.AddKeyFrame(kfMove);
		
		
		//door		
		spriteDoor.Reset();
		spriteDoor.setOpenPercentage(1, 1000);
		spriteDoor.AllowOpenPercentageStacking = true;
		
		if (m_GameManager.getLevel() != m_GameManager.getMaxLevel())
		{		
			spriteDoor.setOpenPercentage(0, 4500,500);
		}
		
		spriteSlideDoorLeft.AllowOpenPercentageStacking=true;
		spriteSlideDoorRight.AllowOpenPercentageStacking=true;
		
		spriteSlideDoorLeft.setOpenPercentage(1, 1000, 1000);
		spriteSlideDoorRight.setOpenPercentage(1, 1000, 1000);
		
		if (m_GameManager.getLevel() != m_GameManager.getMaxLevel())
		{
			spriteSlideDoorLeft.setOpenPercentage(0, 4000, 1000);
			spriteSlideDoorRight.setOpenPercentage(0, 4000, 1000);
		}
		
		
	}

	
	private ColumnedTextSprite scoreSprite;

	private void BuildScoreSprite()
	{
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		float spriteWidth = ClockConstants.CalculateScreenSquare(w, h);
		float spriteHeight = spriteWidth;


		TextureRegion tr = new TextureRegion(
				ClockConstants.GetTexture(ImageNames.textblock_512),
				0,
				0,
				512,
				512);
		tr.flip(false, true);
		scoreSprite = new ColumnedTextSprite(tr);

		scoreSprite.setOrigin(spriteWidth / 2, spriteHeight / 2);

		// left to right
		KeyFrame kfMove = new KeyFrame();
		int lnMilliseconds = 0;

		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = new RectF(
				(w / 2) + TEXT_PLATE_PADDNG,
				-spriteHeight,
				(w / 2) + spriteWidth + TEXT_PLATE_PADDNG,
				0);
		// kfMove.StartRotation = 0;

		lnMilliseconds += 500;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = new RectF(
				(w / 2) + TEXT_PLATE_PADDNG,
				h / 5,
				(w / 2) + spriteWidth + TEXT_PLATE_PADDNG,
				h / 5 + spriteHeight);
		// kfMove.EndRotation = 180;
		
		scoreSprite.setColumn1Text(m_GameManager.getScoreManager().getScoreText1());
		scoreSprite.setColumn2Text(m_GameManager.getScoreManager().getScoreText2());
		
		scoreSprite.AddKeyFrame(kfMove);
		m_SpriteList.add(m_TitleSpriteLayerIndex, scoreSprite);
	}
	
	
	private VictoryQuoteSprite victoryQuoteSprite;

	private float TEXT_PLATE_PADDNG =  Gdx.graphics.getWidth() /50;
	
	private void BuildVictoryQuoteSprite()
	{
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		float spriteWidth = ClockConstants.CalculateScreenSquare(w, h);
		float spriteHeight = spriteWidth;


		TextureRegion tr = new TextureRegion(
				ClockConstants.GetTexture(ImageNames.textblock_512),
				0,
				0,
				512,
				512);
		tr.flip(false, true);
		victoryQuoteSprite = new VictoryQuoteSprite(tr);

		victoryQuoteSprite.setOrigin(spriteWidth / 2, spriteHeight / 2);

		// left to right
		KeyFrame kfMove = new KeyFrame();
		int lnMilliseconds = 0;

		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = new RectF(
				(w / 2) - (spriteWidth) - TEXT_PLATE_PADDNG,
				-spriteHeight,
				(w / 2) - TEXT_PLATE_PADDNG,
				0);
		// kfMove.StartRotation = 0;

		lnMilliseconds += 500;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = new RectF(
				(w / 2) - (spriteWidth) - TEXT_PLATE_PADDNG,
				h / 5,
				(w / 2) - TEXT_PLATE_PADDNG,
				h / 5 + spriteHeight);
		// kfMove.EndRotation = 180;

		victoryQuoteSprite.AddKeyFrame(kfMove);
		m_SpriteList.add(m_TitleSpriteLayerIndex, victoryQuoteSprite);
	}
	
	
	private ButtonSprite buttonSpriteNextLevel = null;

	private void BuildNextLevelButtonSprites()
	{
		TextureRegion tr = new TextureRegion(
				ClockConstants.GetTexture(ImageNames.button_next_level),
				0,
				0,
				512,
				128);
		tr.flip(false, true);
		buttonSpriteNextLevel = new ButtonSprite(BUTTON_TYPE_NEXT_LEVEL, tr);

		float spriteWidth = ClockConstants.CalculateScreenSquare(
				(float) Gdx.graphics.getWidth(),
				(float) Gdx.graphics.getHeight());
		float spriteHeight = spriteWidth;

		int w = (int) spriteWidth;
		int h = (int) (128 * (spriteWidth / 512));

		int xOffset = 0;
		int yOffset = menuButtonOffsetY;

		int lnMilliseconds = 0;

		KeyFrame lastTitleKeyFframe = victoryQuoteSprite.GetKeyFrames().get(
				victoryQuoteSprite.GetKeyFrames().size() - 1);
		KeyFrame kfMove = new KeyFrame();

		lnMilliseconds += 0;
		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = new RectF(
				-w,
				lastTitleKeyFframe.EndRectF.bottom + yOffset,
				-w + w,
				lastTitleKeyFframe.EndRectF.bottom + yOffset + h);

		lnMilliseconds += 1000;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = new RectF(
				(Gdx.graphics.getWidth() / 2) - w / 2 + xOffset,
				lastTitleKeyFframe.EndRectF.bottom + yOffset,
				(Gdx.graphics.getWidth() / 2) - w / 2 + w + xOffset,
				lastTitleKeyFframe.EndRectF.bottom + h + yOffset);

		buttonSpriteNextLevel.AddKeyFrame(kfMove);

		m_SpriteList.add(m_TitleSpriteLayerIndex, buttonSpriteNextLevel);

	}

	
	
	private KeyFrameSprite keyFrameSpriteScoreTitle = null;
	
	private void BuildScoreTitleSprites()
	{
		TextureRegion tr = new TextureRegion(
				ClockConstants.GetTexture(ImageNames.score_title_512),
				0,
				0,
				512,
				128);
		tr.flip(false, true);
		
		keyFrameSpriteScoreTitle = new KeyFrameSprite(tr);

		float spriteWidth = ClockConstants.CalculateScreenSquare(
				(float) Gdx.graphics.getWidth(),
				(float) Gdx.graphics.getHeight());
		float spriteHeight = spriteWidth;

		int w = (int) spriteWidth;
		int h = (int) (128 * (spriteWidth / 512));

		int lnMilliseconds = 0;

		KeyFrame lastTitleKeyFframe = scoreSprite.GetKeyFrames().get(
				scoreSprite.GetKeyFrames().size() - 1);

		int xOffset = 0;
		float yOffset = -h;

		KeyFrame kfMove = new KeyFrame();

		lnMilliseconds += 0;
		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = new RectF(
				lastTitleKeyFframe.EndRectF.left + (lastTitleKeyFframe.EndRectF.width() / 2) - w / 2 + xOffset,
				-spriteHeight + yOffset,
				lastTitleKeyFframe.EndRectF.left + (lastTitleKeyFframe.EndRectF.width() / 2) - w / 2 + w + xOffset,
				-spriteHeight + yOffset + h);

		lnMilliseconds += 500;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = new RectF(
				lastTitleKeyFframe.EndRectF.left + (lastTitleKeyFframe.EndRectF.width() / 2) - w / 2 + xOffset,
				lastTitleKeyFframe.EndRectF.top + yOffset,
				lastTitleKeyFframe.EndRectF.left + (lastTitleKeyFframe.EndRectF.width() / 2) - w / 2 + w + xOffset,
				lastTitleKeyFframe.EndRectF.top + h + yOffset);

		keyFrameSpriteScoreTitle.AddKeyFrame(kfMove);

		m_SpriteList.add(m_TitleSpriteLayerIndex, keyFrameSpriteScoreTitle);

	}
	
	
	
	private KeyFrameSprite keyFrameSpriteVictoryQuoteTitle = null;
	
	private void BuildVictoryQuoteTitleSprites()
	{
		TextureRegion tr = new TextureRegion(
				ClockConstants.GetTexture(ImageNames.victory_quote_title_512),
				0,
				0,
				512,
				128);
		tr.flip(false, true);
		keyFrameSpriteVictoryQuoteTitle = new KeyFrameSprite(tr);

		float spriteWidth = ClockConstants.CalculateScreenSquare(
				(float) Gdx.graphics.getWidth(),
				(float) Gdx.graphics.getHeight());
		float spriteHeight = spriteWidth;

		int w = (int) spriteWidth;
		int h = (int) (128 * (spriteWidth / 512));

		int lnMilliseconds = 0;

		KeyFrame lastTitleKeyFframe = victoryQuoteSprite.GetKeyFrames().get(
				victoryQuoteSprite.GetKeyFrames().size() - 1);

		int xOffset = 0;
		float yOffset = -h;

		KeyFrame kfMove = new KeyFrame();

		lnMilliseconds += 0;
		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = new RectF(
				lastTitleKeyFframe.EndRectF.left + (lastTitleKeyFframe.EndRectF.width() / 2) - w / 2 + xOffset,
				-spriteWidth + yOffset,
				lastTitleKeyFframe.EndRectF.left + (lastTitleKeyFframe.EndRectF.width() / 2) - w / 2 + w + xOffset,
				-spriteWidth + yOffset + h);

		lnMilliseconds += 500;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = new RectF(
				lastTitleKeyFframe.EndRectF.left + (lastTitleKeyFframe.EndRectF.width() / 2) - w / 2 + xOffset,
				lastTitleKeyFframe.EndRectF.top + yOffset,
				lastTitleKeyFframe.EndRectF.left + (lastTitleKeyFframe.EndRectF.width() / 2) - w / 2 + w + xOffset,
				lastTitleKeyFframe.EndRectF.top + h + yOffset);

		keyFrameSpriteVictoryQuoteTitle.AddKeyFrame(kfMove);

		m_SpriteList.add(m_TitleSpriteLayerIndex, keyFrameSpriteVictoryQuoteTitle);

	}

	private ButtonSprite buttonSpriteQuit = null;
	
	private void BuildQuitButtonSprites()
	{
		TextureRegion tr = new TextureRegion(
				ClockConstants.GetTexture(ImageNames.button_main_menu),
				0,
				0,
				512,
				128);
		tr.flip(false, true);
		buttonSpriteQuit = new ButtonSprite(BUTTON_TYPE_QUIT, tr);

		float spriteWidth = ClockConstants.CalculateScreenSquare(
				(float) Gdx.graphics.getWidth(),
				(float) Gdx.graphics.getHeight());
		float spriteHeight = spriteWidth;

		int w = (int) spriteWidth;
		int h = (int) (128 * (spriteWidth / 512));

		menuButtonOffsetY += h + 10;

		int xOffset = 0;
		int yOffset = menuButtonOffsetY;

		int lnMilliseconds = 1000;

		KeyFrame lastTitleKeyFframe = victoryQuoteSprite.GetKeyFrames().get(
				victoryQuoteSprite.GetKeyFrames().size() - 1);
		KeyFrame kfMove = new KeyFrame();

		lnMilliseconds += 0;
		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = new RectF(
				-w,
				lastTitleKeyFframe.EndRectF.bottom + yOffset,
				-w + w,
				lastTitleKeyFframe.EndRectF.bottom + yOffset + h);

		lnMilliseconds += 1000;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = new RectF(
				(Gdx.graphics.getWidth() / 2) - w / 2 + xOffset,
				lastTitleKeyFframe.EndRectF.bottom + yOffset,
				(Gdx.graphics.getWidth() / 2) - w / 2 + w + xOffset,
				lastTitleKeyFframe.EndRectF.bottom + h + yOffset);

		buttonSpriteQuit.AddKeyFrame(kfMove);

		m_SpriteList.add(m_TitleSpriteLayerIndex, buttonSpriteQuit);
	}

	BitmapFont m_FontQuote;

	
	KeyFrameSprite m_KeyFrameSpriteArchway = null;
	
	private void BuildSprites()
	{
		m_KeyFrameSpriteArchway = m_GameManager.BuildArchwaySprite(m_SpriteList);
		m_KeyFrameSpriteArchway.setIsHidden(true);
		BuildSlideDoorSprites();
		BuildDoorSprites();
		

		int MAX_SUB_COGS = GameManager.MAX_NUMBER_OF_SUBCOGS;
		// add decorations cogs
		// for (int j = 0; j < c.getSubCogs().length; j++)
		for (int j = MAX_SUB_COGS - 1; j >= 0; j--)
		{
			for (int i = 0; i < m_GameManager.getCogs().length; i++)
			{
				Cog c = m_GameManager.getCogs()[i];

				if (j < c.getSubCogs().length)
				{
					Cog subCog = c.getSubCogs()[j];
					Cog subCogShadow = subCog.getShadow();

					// SubCogShadow
					if (subCogShadow != null)
					{
						ImageNames shadowImage = subCogShadow.getRenderImage();
						if (shadowImage != null)
						{
							TextureRegion tr2 = new TextureRegion(
									subCogShadow.getTexture(),
									0,
									0,
									256,
									256);
							tr2.flip(false, true);
							subCogShadow.setRegion(tr2);
							m_SpriteList.add(subCogShadow);
						}
					}

					// SubCog
					TextureRegion tr = new TextureRegion(subCog.getTexture(), 0, 0, 512, 512);
					tr.flip(false, true);
					subCog.setRegion(tr);
					m_SpriteList.add(subCog);
				}
			}
		}

		// add game cogs
		for (int i = 0; i < m_GameManager.getCogs().length; i++)
		{
			Cog c = m_GameManager.getCogs()[i];
			Cog cogShadow = c.getShadow();

			c.SetupStartAnimation(500);
			c.setAllowSubObjectRotation(false);

			// Shadow
			if (cogShadow != null)
			{
				TextureRegion tr2 = new TextureRegion(cogShadow.getTexture(), 0, 0, 256, 256);
				tr2.flip(false, true);
				cogShadow.setRegion(tr2);
				m_SpriteList.add(cogShadow);
			}

			// Cog
			TextureRegion tr = new TextureRegion(c.getTexture(), 0, 0, 512, 512);
			tr.flip(false, true);
			c.setRegion(tr);
			m_SpriteList.add(c);

		}

		BuildChainShadowSprite();
		//m_GameManager.BuildForegroundShadowSprite(m_SpriteList);


		// add game cogs rotation guide
		for (int i = 0; i < m_GameManager.getCogs().length; i++)
		{
			Cog c = m_GameManager.getCogs()[i];

			if (c.getGuideSprite() != null)
			{
				m_SpriteList.add(c.getGuideSprite());
			}

			if (c.getCogRotationGuideSprite() != null)
			{
				m_SpriteList.add(c.getCogRotationGuideSprite());
			}
		}

		
	
		spriteforegroundShadow = m_GameManager.BuildForegroundCombinedSprite(m_SpriteList);		
		//m_GameManager.BuildForegroundShadowSprite(m_SpriteList);
		//m_GameManager.BuildForegroundLightSprite(m_SpriteList);
		//m_GameManager.BuildForegroundGitSprite(m_SpriteList);
		
		//m_GameManager.BuildForegr342oundLightSprite(m_SpriteList);
		//m_GameManager.BuildForegroundGitSprite(m_SpriteList);
		BuildSpinCogIcon();
		BuildLevelTextSprite();

		if(m_GameManager.getLevel() == 1)
		{
			BuildHowToPlaySprite();			
		}

		
	}
	
	TextSprite spriteHowToPlaySprite;
	
	private void BuildHowToPlaySprite()
	{
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		float spriteWidth = w / 2;
		float spriteHeight = h / 2;

		// square up sprite
		if (spriteWidth > spriteHeight)
		{
			spriteWidth = spriteHeight;
		}

		if (spriteHeight > spriteWidth)
		{
			spriteHeight = spriteWidth;
		}

		int x = (int)(w / 2 - (spriteWidth/2));
		int y = (int) (h/5);
		
		spriteHowToPlaySprite = new TextSprite(
				"Tap and hold your finger on a top level cog to rotate it.\n The aim of the game is to line up all of the red lines in the same direction.",
				0,
				FontManager.Fonts.QuoteFontWhite,
				HAlignment.CENTER);

	//	float spriteWidth = (float) Gdx.graphics.getWidth();
	//	float spriteHeight = ClockConstants.m_FontManager.getFont(FontManager.Fonts.QuoteFontWhite).getLineHeight();

		KeyFrame kfMove = new KeyFrame();

		int lnMilliseconds = 1000;
		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = new RectF(x - 5000, y, spriteWidth + x - 5000, spriteHeight + y);

		lnMilliseconds += 2000;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = new RectF(x, y, spriteWidth + x, spriteHeight + y);

		spriteHowToPlaySprite.AddKeyFrame(kfMove);

		m_SpriteList.add(spriteHowToPlaySprite);

	}

	
	private KeyFrameSprite spriteforegroundShadow;

	private DoorSprite spriteDoor;
	
	private void BuildLevelTextSprite()
	{
		int x = 8;
		int y = 8;

		TextSprite spriteLevelText = new TextSprite(
				"Level " + m_GameManager.getLevelText() + "/" + m_GameManager.getMaxLevelText(),
				0,
				FontManager.Fonts.QuoteFontWhite,
				HAlignment.CENTER);

		float spriteWidth = (float) Gdx.graphics.getWidth();
		float spriteHeight = ClockConstants.m_FontManager.getFont(FontManager.Fonts.QuoteFontWhite).getLineHeight();

		KeyFrame kfMove = new KeyFrame();

		int lnMilliseconds = 0;
		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = new RectF(x, y, spriteWidth + x, spriteHeight + y);

		lnMilliseconds += 1;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = new RectF(x, y, spriteWidth + x, spriteHeight + y);

		spriteLevelText.AddKeyFrame(kfMove);

		m_SpriteList.add(spriteLevelText);
	}

	private void BuildSpinCogIcon()
	{
		TextureRegion tr = new TextureRegion(
				ClockConstants.GetTexture(ImageNames.cog_icon_128),
				0,
				0,
				128,
				128);
		tr.flip(false, true);

		spinCogIcon = new KeyFrameSprite(tr);

		float spriteWidth = ClockConstants.m_FontManager.getFont(FontManager.Fonts.QuoteFontWhite).getLineHeight() * 0.75f;
		float spriteHeight = spriteWidth;

		spinCogIcon.setOrigin(spriteWidth / 2, spriteHeight / 2);

		// spriteWidth = 24;
		// spriteHeight = 24;

		int x = 8;
		int y = 8;

		KeyFrame kfMove = new KeyFrame();

		int lnMilliseconds = 0;
		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = new RectF(x, y, spriteWidth + x, spriteHeight + y);

		lnMilliseconds += 10;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = new RectF(x, y, spriteWidth + x, spriteHeight + y);

		spinCogIcon.AddKeyFrame(kfMove);

		spinCogIcon.setColor(1, 0, 0, 0.9f);

		m_SpriteList.add(spinCogIcon);
	}

	private void BuildDoorSprites()
	{
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		int doorGuardHeight = (int)(h*0.1f);
		
		Texture t = ClockConstants.GetTexture(ImageNames.door5_texture_512);
		t.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);

		spriteDoor = new DoorSprite(t, "DoorSprite", w, h-doorGuardHeight, doorGuardHeight);
		spriteDoor.setSize(w, h);

		spriteDoor.setCurrentRectF(new RectF(0, 0, w, (float)h-doorGuardHeight));
		spriteDoor.getDoorGuard().setCurrentRectF(new RectF(0, h-doorGuardHeight, w, h));
		
		m_SpriteList.add(spriteDoor);
		m_SpriteList.add(spriteDoor.getDoorGuard());
	}

	
	private void BuildSlideDoorSprites()
	{
		BuildSlideDoorLeftSprites();
		BuildSlideDoorRightSprites();
		
		spriteSlideDoorLeft.setOpenPercentage(0, 1);
		spriteSlideDoorRight.setOpenPercentage(0, 1);
		
		m_SpriteList.add(spriteSlideDoorLeft.getDoorGuard());
		m_SpriteList.add(spriteSlideDoorRight.getDoorGuard());
		
		m_SpriteList.add(spriteSlideDoorRight);
		m_SpriteList.add(spriteSlideDoorLeft);
		
	}
	
	private void BuildSlideDoorLeftSprites()
	{
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		int doorGuardHeight = (int)(h*0.1f);

		Texture t = ClockConstants.GetTexture(ImageNames.door8_texture_512);
		t.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		
		spriteSlideDoorLeft = new SlideDoorLeftSprite(t, "SlideDoorSpriteLeft", (w/2), h, doorGuardHeight);	
	}
	
	
	private void BuildSlideDoorRightSprites()
	{
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		int doorGuardHeight = (int)(h*0.1f);

		Texture t = ClockConstants.GetTexture(ImageNames.door8_texture_512);
		t.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		
		spriteSlideDoorRight = new SlideDoorRightSprite(t, "spriteSlideDoorRight", w, (w/2), h, doorGuardHeight);		
	}
	
	ChainShadowSprite chainShadowSprite;

	private void BuildChainShadowSprite()
	{
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		Texture t = ClockConstants.GetTexture(ImageNames.chain_shadow_256);
		t.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);

		chainShadowSprite = new ChainShadowSprite(t, "ChainShadowSprite", 256, h, w);
		chainShadowSprite.setRenderImage(ImageNames.chain_shadow_256);

		m_SpriteList.add(chainShadowSprite);
	}


	private boolean onTouchEventDown_Win(int screenX, int screenY, int pointer, int button)
	{
		Iterator<Sprite> it = m_SpriteList.iterator();
		while (it.hasNext())
		{
			Sprite obj = it.next();

			if (obj instanceof KeyFrameSprite)
			{
				KeyFrameSprite keyFrameSprite = (KeyFrameSprite) obj;

				if (obj instanceof ButtonSprite)
				{
					if (keyFrameSprite.getCurrentRectF().intersects(
							screenX,
							screenY,
							screenX + 1,
							screenY + 1))
					{
						if (obj instanceof ButtonSprite)
						{
							((ButtonSprite) obj).setIsMouseDown(true);
						}
					}
				}
			}
		}

		return false;
	}

	private boolean onTouchEventDown_Game(int screenX, int screenY, int pointer, int button)
	{
		if (pointer >= m_MouseSelectedCogIndex.length)
		{
			return false;
		}

		m_MouseSelectedCogIndex[pointer] = -1;

		for (int i = 0; i < m_GameManager.getCogs().length; i++)
		{
			Cog c = m_GameManager.getCogs()[i];

			if (Utils.CirclesInterset(c.getRenderPosition(), new RectF(
					screenX,
					screenY,
					screenX + 1,
					screenY + 1)))
			{
				m_MouseSelectedCogIndex[pointer] = c.getIndex();
				c.setIsSelected(true);

				startAngle[pointer] = getAngle(
						screenX,
						screenY,
						c.getRenderPosition().width(),
						c.getRenderPosition().height(),
						c.getRenderPosition().left,
						c.getRenderPosition().top);

				return true;
			}
		}

		return false;
	}

	private boolean onTouchEventDrag_Win(int screenX, int screenY, int pointer)
	{
		return false;
	}

	private boolean onTouchEventDrag_Game(int screenX, int screenY, int pointer)
	{
		if (pointer >= m_MouseSelectedCogIndex.length)
		{
			return false;
		}

		if (m_MouseSelectedCogIndex[pointer] != -1)
		{
			for (int i = 0; i < m_GameManager.getCogs().length; i++)
			{
				Cog c = m_GameManager.getCogs()[i];

				if (c.getIndex() == m_MouseSelectedCogIndex[pointer])
				{

					double currentAngle = getAngle(
							screenX,
							screenY,
							c.getRenderPosition().width(),
							c.getRenderPosition().height(),
							c.getRenderPosition().left,
							c.getRenderPosition().top);

					float move = (float) (startAngle[pointer] - currentAngle);

					if (Math.round(move) != 0)
					{
						m_GameManager.MoveCogBy(m_MouseSelectedCogIndex[pointer], (move * -1));
						startAngle[pointer] = currentAngle;
					}
					break;
				}
			}

			return true;
		}
		return false;
	}


	private double getAngle(double xTouch, double yTouch, float dialerWidth, float dialerHeight, float xoffset, float yoffset)
	{
		double x = xTouch - (dialerWidth / 2d) - xoffset;
		double y = yTouch - (dialerHeight / 2d) - yoffset;

		switch (getQuadrant(x, y))
		{
			case 1:
				return Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI;

			case 2:
			case 3:
				return 180 - (Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI);

			case 4:
				return 360 + Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI;

			default:
				// ignore, does not happen
				return 0;
		}
	}

	private static int getQuadrant(double x, double y)
	{
		if (x >= 0)
		{
			return y >= 0 ? 1 : 4;
		}
		else
		{
			return y >= 0 ? 2 : 3;
		}
	}



	private void render2(SpriteBatch batch)
	{
		float intFirstMatchValue = 0;
		int intMatchCount = 0;

		if (m_GameManager != null)
		{
			if (m_StartAnimationStarted)
			{
				Calendar now = Calendar.getInstance();
				
				if (now.after(StartAnimationFinishTime))
				{
					m_StartAnimationStarted = false;
										
					for (int i = 0; i < m_GameManager.getCogs().length; i++)
					{
						Cog c = m_GameManager.getCogs()[i];
						c.setAllowSubObjectRotation(true);
					
					}			
				}
			}
			else
			{

				//find matching value
				for (int i = 0; i < m_GameManager.getCogs().length; i++)
				{
					Cog c = m_GameManager.getCogs()[i];
					for (int j = i+1; j < m_GameManager.getCogs().length; j++)
					{
						Cog cogMatchCheck = m_GameManager.getCogs()[j];
	
						if (c.getRatchetValue() == cogMatchCheck.getRatchetValue())
						{
							intFirstMatchValue = c.getRatchetValue();
							intMatchCount++;
						}
					}
					
					if (intMatchCount >0)
					{
						break;
					}
				}
				
								
				for (int i = 0; i < m_GameManager.getCogs().length; i++)
				{
					Cog c = m_GameManager.getCogs()[i];
					
					if (c.getGuideSprite() != null)
					{
						c.getGuideSprite().setColor(1, 0 ,0, 0.5f);
					}

					boolean blnValueMatches = false;

					if (c.getRatchetValue() == intFirstMatchValue && intMatchCount >=1)
					{
						blnValueMatches = true;
					}
				

					if (c.getIsCogTimerActive())
					{
						c.setColor(Color.RED);

						if (c.GetKeyFrames().get(c.GetKeyFrames().size() - 1).getEndFrameHasBeenProcessedAtLeastOnce() == true)
						{
							c.GetKeyFrames().clear();
							c.setIsCogTimerActive(false);
						}
					}
					else
					if (blnValueMatches && this.m_GameManager.m_GamePhaseState == GameManager.GameState.Play)
					{
						c.setColor(Color.GREEN);

						if (c.getCogRotationGuideSprite() != null)
						{
							if (c.getIsSelected())
							{
								c.getCogRotationGuideSprite().setColor(0, 1, 0, 0.5f); // green
							}
							else
							{
								c.getCogRotationGuideSprite().setColor(0, 0, 0, 0);
							}
						}
					}
					else
					{

						if (!c.getIsSelected())
						{
							c.setColor(Color.WHITE);
							c.getGuideSprite().setColor(1, 0 ,0, 0.5f);
						}
						else
						{
							c.setColor(Color.YELLOW);
							c.getGuideSprite().setColor(1, 0 ,0, 0.5f);
						}

						if (c.getCogRotationGuideSprite() != null)
						{
							if (c.getIsSelected())
							{
								c.getCogRotationGuideSprite().setColor(1, 1, 0, 0.5f); // yellow
							}
							else
							{
								c.getCogRotationGuideSprite().setColor(0, 0, 0, 0);
							}
						}
					}
				}
			}

			if (spriteDoor != null && m_WinAnimationStarted == false)
			{
				spriteDoor.setOpenPercentage(
						(double) ((double) intMatchCount / (double) m_GameManager.getCogs().length) / 2,
						2000);						
			}

			Iterator<Sprite> it1 = m_SpriteList.iterator();
			while (it1.hasNext())
			{
				Sprite obj = it1.next();

				if (obj instanceof KeyFrameSprite)
				{
					((KeyFrameSprite) obj).ProcessKeyFrame();
				}
			}

			batch.begin();

			Iterator<Sprite> it = m_SpriteList.iterator();
			while (it.hasNext())
			{
				Sprite obj = it.next();

				if (obj instanceof KeyFrameSprite)
				{
					KeyFrameSprite keyFrameSprite = (KeyFrameSprite) obj;

					if (keyFrameSprite.getIsHidden() == false)
					{
						if (keyFrameSprite.hasDrawImage())
						{
							keyFrameSprite.draw(batch);
						}

						if (keyFrameSprite.hasDrawText())
						{
							keyFrameSprite.DrawText(batch);
						}
					}
				}
				else
				{
					obj.draw(batch);
				}
			}

			if (!m_WinAnimationStarted)
			{
				ClockConstants.m_FontManager.getFont(FontManager.Fonts.QuoteFontWhite).draw(
						batch,
						m_GameManager.getCogSpinTimerCountdownText(),
						10,
						10);
			}

			batch.end();
		}

	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		if (m_GameManager != null)
		{
			if (m_GameManager.m_GamePhaseState == GameManager.GameState.Won)
			{
				return onTouchEventDown_Win(screenX, screenY, pointer, button);
			}
			else if (m_GameManager.m_GamePhaseState == GameManager.GameState.Play)
			{
				return onTouchEventDown_Game(screenX, screenY, pointer, button);
			}

		}
		return false;
	}
	


	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		if (m_GameManager != null)
		{
			if (m_GameManager.m_GamePhaseState == GameManager.GameState.Won)
			{
				return onTouchEventUp_Win(screenX, screenY, pointer, button);
			}
			else if (m_GameManager.m_GamePhaseState == GameManager.GameState.Play)
			{
				return onTouchEventUp_Game(screenX, screenY, pointer, button);
			}

		}
		return false;
	}
	
	public boolean onTouchEventUp_Win(int screenX, int screenY, int pointer, int button)
	{
		Iterator<Sprite> it = m_SpriteList.iterator();
		while (it.hasNext())
		{
			Sprite obj = it.next();

			if (obj instanceof KeyFrameSprite)
			{
				KeyFrameSprite keyFrameSprite = (KeyFrameSprite) obj;

				if (obj instanceof ButtonSprite)
				{
					ButtonSprite typedObj = ((ButtonSprite) obj);
					if (typedObj.getIsMouseDown() == true && keyFrameSprite.getCurrentRectF().intersects(
							screenX,
							screenY,
							screenX + 1,
							screenY + 1))
					{
						typedObj.setIsMouseDown(false);

						if (obj instanceof ButtonSprite)
						{
							ClockConstants.m_SoundManager.PlaySound(SoundManager.SoundButtonPress);
							
							if (((ButtonSprite) obj).getButtonName() == BUTTON_TYPE_NEXT_LEVEL)
							{
								m_NextLevelAnimationStarted = true;
								m_NextLevelAnimationStartedAt = Calendar.getInstance();
								BuildNextLevelExitAnimationSprites();
								
								return true;
							}
							else
								if (((ButtonSprite) obj).getButtonName() == BUTTON_TYPE_QUIT)
								{
									if (ClockConstants.CoreActivity != null)
									{
										ClockConstants.CoreActivity.showFullScreenAdEvent();
									}

									IPhase s = m_GameManager.getPhaseStack().pop();

									return true;
								}
						}
					}
					typedObj.setIsMouseDown(false);

				}
			}
		}

		return false;
	}
	
	
	public boolean onTouchEventUp_Game(int screenX, int screenY, int pointer, int button)
	{
		if (pointer >= m_MouseSelectedCogIndex.length)
		{
			return false;
		}

		if (m_MouseSelectedCogIndex[pointer] != -1)
		{
			m_GameManager.getCogs()[m_MouseSelectedCogIndex[pointer]].setIsSelected(false);
		}
		m_MouseSelectedCogIndex[pointer] = -1;
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		if (m_GameManager != null)
		{
			if (this.m_GameManager.m_GamePhaseState == GameManager.GameState.Won)
			{
				return onTouchEventDrag_Win(screenX, screenY, pointer);
			}
			else
			{
				return onTouchEventDrag_Game(screenX, screenY, pointer);
			}
		}
		
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character)
	{
		if (character == 'w')
		{
			float winRotation =  m_GameManager.getCogs()[0].getRotation();
			
			for (int i = 0; i < m_GameManager.getCogs().length; i++)
			{
				Cog c = m_GameManager.getCogs()[i];
				c.setRotation(winRotation);
			
			}
			
			return true;
		}
		return false;
	}
}
