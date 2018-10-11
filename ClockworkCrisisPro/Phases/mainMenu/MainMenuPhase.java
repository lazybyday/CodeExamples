package com.DamnItJenkins.ClockworkCrisisPro.Phases.mainMenu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import com.DamnItJenkins.ClockworkCrisisPro.GameManager;
import com.DamnItJenkins.ClockworkCrisisPro.RectF;
import com.DamnItJenkins.ClockworkCrisisPro.SoundManager;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.ButtonSprite;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.IPhase;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.KeyFrame;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.KeyFrameSprite;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.game.GamePhase;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.game.Sprites.ChainShadowSprite;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.game.Sprites.DoorSprite;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.game.Sprites.SlideDoorLeftSprite;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.game.Sprites.SlideDoorRightSprite;
import com.DamnItJenkins.ClockworkCrisisPro.Phases.howToPlay.HowToPlayPhase;
import com.DamnItJenkins.ClockworkCrisisPro.dataModel.ClockConstants;
import com.DamnItJenkins.ClockworkCrisisPro.dataModel.ClockConstants.ImageNames;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MainMenuPhase implements IPhase
{
	private ArrayList<Sprite> m_SpriteList;
	private ArrayList<Sprite> m_DecoSprites;

	private GameManager m_GameManager = null;

	private boolean m_IsFirstRender = true;

	private final String BUTTON_TYPE_NEW_GAME = "NewGame";
	private final String BUTTON_TYPE_RANDOM_GAME = "RandomGame";
	private final String BUTTON_TYPE_HOW_TO_PLAY = "HowToPlay";
	private final String BUTTON_SOUND_MUTE = "SoundMute";

	private int m_LastWinMovementSecond = -1;
	private int menuButtonOffsetY = 0;
	
	private KeyFrameSprite spriteTitle;
	private ChainShadowSprite chainShadowSprite;
	

	public MainMenuPhase(GameManager gameManager)
	{
		m_GameManager = gameManager;

		m_SpriteList = new ArrayList<Sprite>();
		m_DecoSprites = new ArrayList<Sprite>();
	}

	private void BuildSprites()
	{
		m_GameManager.BuildArchwaySprite(m_SpriteList);
		BuildSlideDoorSprites();
		BuildDoorSprites();
		BuildTitleSprite();
		
		for(int i = 0; i< m_SpriteList.size(); i++)
		{
			if (m_SpriteList.get(i) == spriteTitle )
			{
				TITLE_SPRITE_LAYER_INDEX = i;
			}
		}
				
		BuildTitleDeco1Sprite();
		BuildTitleDeco2Sprite();
		BuildTitleDeco3Sprite();
		BuildTitleDecoRight1Sprite();
		BuildTitleDecoRight2Sprite();

		BuildNewGameSprites();
		BuildRandomGameSprites();
		BuildHowToPlaySprites();

		BuildChainShadowSprite();
		
		BuildSoundSprite();
		
		m_GameManager.BuildForegroundCombinedSprite(m_SpriteList);
	}


	private void BuildSoundSprite()
	{
		int w = Gdx.graphics.getWidth();
		int h = Gdx.graphics.getHeight();
		
		float spriteWidth = w * 0.05f;
		float spriteHeight = spriteWidth;
		
		float padding = w * 0.01f;
		
		
		TextureRegion tr = new TextureRegion(
				ClockConstants.GetTexture(ImageNames.icon_sound),
				0,
				0,
				256,
				256);
		tr.flip(false, true);

		ButtonSprite buttonSpriteNewGame = new ButtonSprite(BUTTON_SOUND_MUTE, tr);	
		
		KeyFrame kfMove = new KeyFrame();
		
		int lnMilliseconds = 0;
		
		lnMilliseconds += 0;
		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = new RectF(
				w - spriteWidth - padding,
				h -spriteHeight - padding,
				w - padding,
				h - padding);

		lnMilliseconds += 1000;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = new RectF(
						w - spriteWidth - padding,
						h -spriteHeight - padding,
						w - padding,
						h - padding);
		

		buttonSpriteNewGame.AddKeyFrame(kfMove);

		m_SpriteList.add(buttonSpriteNewGame);
	}
	
	private void BuildNewGameSprites()
	{
		float spriteWidth = ClockConstants.CalculateScreenSquare(
				(float) Gdx.graphics.getWidth(),
				(float) Gdx.graphics.getHeight());
		float spriteHeight = spriteWidth;

		TextureRegion tr = new TextureRegion(
				ClockConstants.GetTexture(ImageNames.button_new_game),
				0,
				0,
				512,
				128);
		tr.flip(false, true);

		ButtonSprite buttonSpriteNewGame = new ButtonSprite(BUTTON_TYPE_NEW_GAME, tr);

		int w = (int) spriteWidth;
		int h = (int) (128 * (spriteWidth / 512));

		int xOffset = 0;
		int yOffset = menuButtonOffsetY;

		int lnMilliseconds = 0;

		KeyFrame lastTitleKeyFframe = spriteTitle.GetKeyFrames().get(
				spriteTitle.GetKeyFrames().size() - 1);
		KeyFrame kfMove = new KeyFrame();

		lnMilliseconds += 500;
		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = new RectF(
				-w,
				lastTitleKeyFframe.EndRectF.bottom + yOffset,
				-w + w,
				lastTitleKeyFframe.EndRectF.bottom + yOffset + h);

		lnMilliseconds += 1000;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = new RectF(
				lastTitleKeyFframe.EndRectF.left + (lastTitleKeyFframe.EndRectF.width() / 2) - w / 2 + xOffset,
				lastTitleKeyFframe.EndRectF.bottom + yOffset,
				lastTitleKeyFframe.EndRectF.left + (lastTitleKeyFframe.EndRectF.width() / 2) - w / 2 + w + xOffset,
				lastTitleKeyFframe.EndRectF.bottom + h + yOffset);

		buttonSpriteNewGame.AddKeyFrame(kfMove);

		m_SpriteList.add(buttonSpriteNewGame);
	}

	private void BuildRandomGameSprites()
	{
		float spriteWidth = ClockConstants.CalculateScreenSquare(
				(float) Gdx.graphics.getWidth(),
				(float) Gdx.graphics.getHeight());
		float spriteHeight = spriteWidth;

		TextureRegion tr = new TextureRegion(
				ClockConstants.GetTexture(ImageNames.button_random_game),
				0,
				0,
				512,
				128);
		tr.flip(false, true);

		ButtonSprite buttonSpriteRandomGame = new ButtonSprite(BUTTON_TYPE_RANDOM_GAME, tr);

		int w = (int) spriteWidth;
		int h = (int) (128 * (spriteWidth / 512));

		menuButtonOffsetY += h + 10;

		int xOffset = 0;
		int yOffset = menuButtonOffsetY;

		int lnMilliseconds = 1000;

		KeyFrame lastTitleKeyFframe = spriteTitle.GetKeyFrames().get(
				spriteTitle.GetKeyFrames().size() - 1);
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
				lastTitleKeyFframe.EndRectF.left + (lastTitleKeyFframe.EndRectF.width() / 2) - w / 2 + xOffset,
				lastTitleKeyFframe.EndRectF.bottom + yOffset,
				lastTitleKeyFframe.EndRectF.left + (lastTitleKeyFframe.EndRectF.width() / 2) - w / 2 + w + xOffset,
				lastTitleKeyFframe.EndRectF.bottom + h + yOffset);

		buttonSpriteRandomGame.AddKeyFrame(kfMove);

		m_SpriteList.add(buttonSpriteRandomGame);
	}

	private void BuildHowToPlaySprites()
	{
		float spriteWidth = ClockConstants.CalculateScreenSquare(
				(float) Gdx.graphics.getWidth(),
				(float) Gdx.graphics.getHeight());
		float spriteHeight = spriteWidth;

		TextureRegion tr = new TextureRegion(
				ClockConstants.GetTexture(ImageNames.button_how_to_play),
				0,
				0,
				512,
				128);
		tr.flip(false, true);
		ButtonSprite buttonSpriteNewGame = new ButtonSprite(BUTTON_TYPE_HOW_TO_PLAY, tr);

		int w = (int) spriteWidth;
		int h = (int) (128 * (spriteWidth / 512));

		menuButtonOffsetY += h + 10;

		int xOffset = 0;
		int yOffset = menuButtonOffsetY;

		int lnMilliseconds = 2000;

		KeyFrame lastTitleKeyFframe = spriteTitle.GetKeyFrames().get(
				spriteTitle.GetKeyFrames().size() - 1);
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
				lastTitleKeyFframe.EndRectF.left + (lastTitleKeyFframe.EndRectF.width() / 2) - w / 2 + xOffset,
				lastTitleKeyFframe.EndRectF.bottom + yOffset,
				lastTitleKeyFframe.EndRectF.left + (lastTitleKeyFframe.EndRectF.width() / 2) - w / 2 + w + xOffset,
				lastTitleKeyFframe.EndRectF.bottom + h + yOffset);

		buttonSpriteNewGame.AddKeyFrame(kfMove);

		m_SpriteList.add(buttonSpriteNewGame);
	}



	private void BuildTitleSprite()
	{
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		float spriteWidth = ClockConstants.CalculateScreenSquare(
				(float) Gdx.graphics.getWidth(),
				(float) Gdx.graphics.getHeight());
		float spriteHeight = spriteWidth;

		TextureRegion tr = new TextureRegion(
				ClockConstants.GetTexture(ImageNames.title_clockwork_crisis),
				0,
				0,
				512,
				512);
		tr.flip(false, true);
		spriteTitle = new KeyFrameSprite(tr);

		spriteTitle.setOrigin(spriteWidth / 2, spriteHeight / 2);

		// left to right
		KeyFrame kfMove = new KeyFrame();
		int lnMilliseconds = 0;

		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = new RectF(
				w / 2 - (spriteWidth / 2),
				-spriteHeight,
				w / 2 + (spriteWidth / 2),
				0);
		// kfMove.StartRotation = 0;

		lnMilliseconds += 500;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = new RectF(
				w / 2 - (spriteWidth / 2),
				h / 30,
				w / 2 + (spriteWidth / 2),
				h / 30 + spriteHeight);
		// kfMove.EndRotation = 180;

		spriteTitle.AddKeyFrame(kfMove);
		m_SpriteList.add(spriteTitle);
	}

	
	


	private void BuildChainShadowSprite()
	{
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		Texture t = ClockConstants.GetTexture(ImageNames.chain_shadow_256);
		t.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);

		chainShadowSprite = new ChainShadowSprite(t, "ChainShadowSprite", 256, h, w);
		chainShadowSprite.setRenderImage(ImageNames.chain_shadow_512);

		m_SpriteList.add(chainShadowSprite);
	}

	

	
	private int TITLE_SPRITE_LAYER_INDEX = 2;
	
	private void BuildTitleDeco1Sprite()
	{
		int lnMilliseconds = 0;

		float spriteWidth = ClockConstants.CalculateScreenSquare(
				(float) Gdx.graphics.getWidth(),
				(float) Gdx.graphics.getHeight());
		float spriteHeight = spriteWidth;

		int xOffset = 0;
		int yOffset = 0;

		ImageNames renderImage = ImageNames.cog2_512;

		spriteWidth = spriteWidth / 2;
		spriteHeight = spriteHeight / 2;

		TextureRegion tr = new TextureRegion(ClockConstants.GetTexture(renderImage), 0, 0, 512, 512);
		tr.flip(false, true);
		KeyFrameSprite kfs = new KeyFrameSprite(tr);
		kfs.setRenderImage(renderImage);

		kfs.setOrigin(spriteWidth / 2, spriteHeight / 2);

		KeyFrame lastTitleKeyFframe = spriteTitle.GetKeyFrames().get(
				spriteTitle.GetKeyFrames().size() - 1);
		KeyFrame kfMove = new KeyFrame();

		lnMilliseconds += 0;
		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = new RectF(-500, -500, -500 + spriteWidth, -500 + spriteHeight);
		kfMove.StartRotation = 0;

		lnMilliseconds += 1000;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = new RectF(
				lastTitleKeyFframe.EndRectF.left - (spriteWidth / 2) + xOffset,
				lastTitleKeyFframe.EndRectF.top + yOffset,
				lastTitleKeyFframe.EndRectF.left - (spriteWidth / 2) + spriteWidth + xOffset,
				lastTitleKeyFframe.EndRectF.top + spriteHeight + yOffset);
		kfMove.EndRotation = 359;

		kfs.AddKeyFrame(kfMove);

		m_SpriteList.add(TITLE_SPRITE_LAYER_INDEX, kfs);
		m_DecoSprites.add(kfs);

		KeyFrameSprite shadow = CreateShadowSprite(kfs);
		m_SpriteList.add(TITLE_SPRITE_LAYER_INDEX, shadow);
		m_DecoSprites.add(shadow);
	}

	KeyFrameSprite m_TitleDecoRight2Sprite = null;
	KeyFrameSprite m_TitleDecoRight2SpriteShadow = null;

	private void BuildTitleDecoRight2Sprite()
	{
		int lnMilliseconds = 0;

		float spriteWidth = ClockConstants.CalculateScreenSquare(
				(float) Gdx.graphics.getWidth(),
				(float) Gdx.graphics.getHeight());
		float spriteHeight = spriteWidth;

		int xOffset = 0;
		int yOffset = 0;

		ImageNames renderImage = ImageNames.cog5_512;

		spriteWidth = spriteWidth / 2;
		spriteHeight = spriteHeight / 2;

		xOffset = (int) ((spriteWidth / 2) * -1 + (spriteWidth / 7) + (spriteWidth / 2));
		yOffset = (int) ((spriteHeight / 2) * -1 + (spriteHeight));

		TextureRegion tr = new TextureRegion(ClockConstants.GetTexture(renderImage), 0, 0, 512, 512);
		tr.flip(false, true);
		m_TitleDecoRight2Sprite = new KeyFrameSprite(tr);
		m_TitleDecoRight2Sprite.setRenderImage(renderImage);
		m_TitleDecoRight2Sprite.setOrigin(spriteWidth / 2, spriteHeight / 2);

		KeyFrame lastTitleKeyFframe = spriteTitle.GetKeyFrames().get(
				spriteTitle.GetKeyFrames().size() - 1);
		KeyFrame kfMove = new KeyFrame();

		lnMilliseconds += 0;
		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = new RectF(-500, -500, -500 + spriteWidth, -500 + spriteHeight);
		kfMove.StartRotation = 0;

		lnMilliseconds += 1000;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = new RectF(
				lastTitleKeyFframe.EndRectF.left + lastTitleKeyFframe.EndRectF.width() - (spriteWidth / 2) + xOffset,
				lastTitleKeyFframe.EndRectF.top + yOffset,
				lastTitleKeyFframe.EndRectF.left + lastTitleKeyFframe.EndRectF.width() - (spriteWidth / 2) + spriteWidth + xOffset,
				lastTitleKeyFframe.EndRectF.top + spriteHeight + yOffset);
		kfMove.EndRotation = 359;

		m_TitleDecoRight2Sprite.AddKeyFrame(kfMove);

		m_SpriteList.add(TITLE_SPRITE_LAYER_INDEX, m_TitleDecoRight2Sprite);
		m_DecoSprites.add(m_TitleDecoRight2Sprite);

		m_TitleDecoRight2SpriteShadow = CreateShadowSprite(m_TitleDecoRight2Sprite);
		m_SpriteList.add(TITLE_SPRITE_LAYER_INDEX, m_TitleDecoRight2SpriteShadow);
		m_DecoSprites.add(m_TitleDecoRight2SpriteShadow);
	}

	private void BuildTitleDecoRight1Sprite()
	{
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		int lnMilliseconds = 0;

		float spriteWidth = ClockConstants.CalculateScreenSquare(
				(float) Gdx.graphics.getWidth(),
				(float) Gdx.graphics.getHeight());
		float spriteHeight = spriteWidth;

		int xOffset = 0;
		int yOffset = 0;

		ImageNames renderImage = ImageNames.cog8_512;

		spriteWidth = spriteWidth / 2;
		spriteHeight = spriteHeight / 2;

		TextureRegion tr = new TextureRegion(ClockConstants.GetTexture(renderImage), 0, 0, 512, 512);
		tr.flip(false, true);
		KeyFrameSprite kfs = new KeyFrameSprite(tr);
		kfs.setRenderImage(renderImage);

		kfs.setOrigin(spriteWidth / 2, spriteHeight / 2);

		KeyFrame lastTitleKeyFframe = spriteTitle.GetKeyFrames().get(
				spriteTitle.GetKeyFrames().size() - 1);
		KeyFrame kfMove = new KeyFrame();

		lnMilliseconds += 0;
		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = new RectF(w + 500, -500, w + 500 + spriteWidth, -500 + spriteHeight);
		kfMove.StartRotation = 0;

		lnMilliseconds += 1000;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = new RectF(
				lastTitleKeyFframe.EndRectF.left + lastTitleKeyFframe.EndRectF.width() - (spriteWidth / 2) + xOffset,
				lastTitleKeyFframe.EndRectF.top + yOffset,
				lastTitleKeyFframe.EndRectF.left + lastTitleKeyFframe.EndRectF.width() - (spriteWidth / 2) + spriteWidth + xOffset,
				lastTitleKeyFframe.EndRectF.top + spriteHeight + yOffset);
		kfMove.EndRotation = 359;

		kfs.AddKeyFrame(kfMove);

		m_SpriteList.add(TITLE_SPRITE_LAYER_INDEX, kfs);
		m_DecoSprites.add(kfs);

		KeyFrameSprite shadow = CreateShadowSprite(kfs);
		m_SpriteList.add(TITLE_SPRITE_LAYER_INDEX, shadow);
		m_DecoSprites.add(shadow);
	}

	private KeyFrameSprite m_TitleDeco2Sprite = null;
	private KeyFrameSprite m_TitleDeco2SpriteShadow = null;

	private void BuildTitleDeco2Sprite()
	{
		int lnMilliseconds = 0;

		float spriteWidth = ClockConstants.CalculateScreenSquare(
				(float) Gdx.graphics.getWidth(),
				(float) Gdx.graphics.getHeight());
		float spriteHeight = spriteWidth;

		int xOffset = 0;
		int yOffset = 0;

		ImageNames renderImage = ImageNames.cog7_512;

		spriteWidth = spriteWidth / 3;
		spriteHeight = spriteHeight / 3;

		xOffset = (int) ((spriteWidth / 2) * -1 + (spriteWidth / 7));
		yOffset = (int) ((spriteHeight / 2) * -1 + (spriteWidth / 7));

		TextureRegion tr = new TextureRegion(ClockConstants.GetTexture(renderImage), 0, 0, 512, 512);
		tr.flip(false, true);
		m_TitleDeco2Sprite = new KeyFrameSprite(tr);
		m_TitleDeco2Sprite.setRenderImage(renderImage);
		m_TitleDeco2Sprite.setOrigin(spriteWidth / 2, spriteHeight / 2);

		KeyFrame lastTitleKeyFframe = spriteTitle.GetKeyFrames().get(
				spriteTitle.GetKeyFrames().size() - 1);
		KeyFrame kfMove = new KeyFrame();

		lnMilliseconds += 0;
		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = new RectF(-500, -500, -500 + spriteWidth, -500 + spriteHeight);
		kfMove.StartRotation = 0;

		lnMilliseconds += 1000;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = new RectF(
				lastTitleKeyFframe.EndRectF.left - (spriteWidth / 2) + xOffset,
				lastTitleKeyFframe.EndRectF.top + yOffset,
				lastTitleKeyFframe.EndRectF.left - (spriteWidth / 2) + spriteWidth + xOffset,
				lastTitleKeyFframe.EndRectF.top + spriteHeight + yOffset);
		kfMove.EndRotation = 359;

		m_TitleDeco2Sprite.AddKeyFrame(kfMove);

		m_SpriteList.add(TITLE_SPRITE_LAYER_INDEX, m_TitleDeco2Sprite);
		m_DecoSprites.add(m_TitleDeco2Sprite);

		m_TitleDeco2SpriteShadow = CreateShadowSprite(m_TitleDeco2Sprite);
		m_SpriteList.add(TITLE_SPRITE_LAYER_INDEX, m_TitleDeco2SpriteShadow);
		m_DecoSprites.add(m_TitleDeco2SpriteShadow);
	}

	private void BuildTitleDeco3Sprite()
	{
		int lnMilliseconds = 0;

		float spriteWidth = ClockConstants.CalculateScreenSquare(
				(float) Gdx.graphics.getWidth(),
				(float) Gdx.graphics.getHeight());
		float spriteHeight = spriteWidth;

		int xOffset = 0;
		int yOffset = 0;

		ImageNames renderImage = ImageNames.cog6_512;

		spriteWidth = spriteWidth * 0.5f;
		spriteHeight = spriteHeight * 0.5f;

		xOffset = (int) ((spriteWidth / 2) * -1 + (spriteWidth / 7) - spriteWidth / 4);
		yOffset = (int) ((spriteHeight / 2) * -1 + (spriteHeight / 7) + spriteHeight / 2);

		TextureRegion tr = new TextureRegion(ClockConstants.GetTexture(renderImage), 0, 0, 512, 512);
		tr.flip(false, true);
		KeyFrameSprite kfs = new KeyFrameSprite(tr);
		kfs.setRenderImage(renderImage);

		kfs.setOrigin(spriteWidth / 2, spriteHeight / 2);

		KeyFrame lastTitleKeyFframe = spriteTitle.GetKeyFrames().get(
				spriteTitle.GetKeyFrames().size() - 1);
		KeyFrame kfMove = new KeyFrame();

		lnMilliseconds += 0;
		kfMove.StartTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.StartRectF = new RectF(-500, -500, -500 + spriteWidth, -500 + spriteHeight);
		kfMove.StartRotation = 0;

		lnMilliseconds += 1000;
		kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);
		kfMove.EndRectF = new RectF(
				lastTitleKeyFframe.EndRectF.left - (spriteWidth / 2) + xOffset,
				lastTitleKeyFframe.EndRectF.top + yOffset,
				lastTitleKeyFframe.EndRectF.left - (spriteWidth / 2) + spriteWidth + xOffset,
				lastTitleKeyFframe.EndRectF.top + spriteHeight + yOffset);
		kfMove.EndRotation = 359;

		kfs.AddKeyFrame(kfMove);

		m_SpriteList.add(TITLE_SPRITE_LAYER_INDEX, kfs);
		m_DecoSprites.add(kfs);

		KeyFrameSprite shadow = CreateShadowSprite(kfs);
		m_SpriteList.add(TITLE_SPRITE_LAYER_INDEX, shadow);
		m_DecoSprites.add(shadow);
	}

	private KeyFrameSprite CreateShadowSprite(KeyFrameSprite kfs)
	{
		ImageNames renderImage = ClockConstants.GetShadowForBmp(kfs.getRenderImage());

		TextureRegion tr = new TextureRegion(ClockConstants.GetTexture(renderImage), 0, 0, 256, 256);
		tr.flip(false, true);

		KeyFrameSprite kfsReturn = new KeyFrameSprite(tr);
		kfsReturn.setOrigin(kfs.getOriginX(), kfs.getOriginY());
		kfsReturn.setRotation(kfs.getRotation());
		kfsReturn.setRenderImage(renderImage);

		KeyFrame lastKeyFrame = kfs.GetKeyFrames().get(kfs.GetKeyFrames().size() - 1);

		int offset = (int) (lastKeyFrame.StartRectF.width() * 0.1);

		KeyFrame kfMove = new KeyFrame();

		kfMove.StartTime = lastKeyFrame.StartTime;
		kfMove.StartRectF = new RectF(
				lastKeyFrame.StartRectF.left + offset,
				lastKeyFrame.StartRectF.top + offset,
				lastKeyFrame.StartRectF.left + offset + lastKeyFrame.StartRectF.width(),
				lastKeyFrame.StartRectF.top + offset + lastKeyFrame.StartRectF.height());
		kfMove.StartRotation = lastKeyFrame.StartRotation;

		kfMove.EndTime = lastKeyFrame.EndTime;
		kfMove.EndRectF = new RectF(
				lastKeyFrame.EndRectF.left + offset,
				lastKeyFrame.EndRectF.top + offset,
				lastKeyFrame.EndRectF.left + offset + lastKeyFrame.EndRectF.width(),
				lastKeyFrame.EndRectF.top + offset + lastKeyFrame.EndRectF.height());
		kfMove.EndRotation = lastKeyFrame.EndRotation;

		kfsReturn.AddKeyFrame(kfMove);

		return kfsReturn;
	}

	private DoorSprite spriteDoor;
	private SlideDoorLeftSprite spriteSlideDoorLeft;
	private SlideDoorRightSprite spriteSlideDoorRight;

	private void BuildDoorSprites()
	{
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		int doorGuardHeight = (int)(h*0.1f);

		Texture t = ClockConstants.GetTexture(ImageNames.door5_texture_512);
		t.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		
		spriteDoor = new DoorSprite(t, "DoorSprite", w, h - doorGuardHeight, doorGuardHeight);
		spriteDoor.setCurrentRectF(new RectF(0, -h -doorGuardHeight, w, - doorGuardHeight));
		spriteDoor.getDoorGuard().setCurrentRectF(new RectF(0, -doorGuardHeight, w, 0));
		
		spriteDoor.setOpenPercentage(0, 1000, 1000);
			
		m_SpriteList.add(spriteDoor);
		m_SpriteList.add(spriteDoor.getDoorGuard());
	}
	
	
	private void BuildSlideDoorSprites()
	{
		BuildSlideDoorLeftSprites();
		BuildSlideDoorRightSprites();
		
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
		spriteSlideDoorLeft.setOpenPercentage(0, 1000);		
	}
	
	
	private void BuildSlideDoorRightSprites()
	{
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		int doorGuardHeight = (int)(h*0.1f);

		Texture t = ClockConstants.GetTexture(ImageNames.door8_texture_512);
		t.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		spriteSlideDoorRight = new SlideDoorRightSprite(t, "spriteSlideDoorRight", w, (w/2), h, doorGuardHeight);	
		spriteSlideDoorRight.setOpenPercentage(0, 1000);		
	}
	
	

	private void RotateDecoSprites()
	{
		Calendar time = Calendar.getInstance();

		if (m_LastWinMovementSecond != time.get(Calendar.SECOND))
		{
			m_LastWinMovementSecond = time.get(Calendar.SECOND);

			for (int i = 0; i < m_DecoSprites.size(); i++)
			{

				int lnMilliseconds = 0;
				KeyFrameSprite c = (KeyFrameSprite) m_DecoSprites.get(i);

				if (c.GetKeyFrames().get(c.GetKeyFrames().size() - 1).EndTime.before(time))
				{
					KeyFrame kfMove = new KeyFrame();
					kfMove.StartRectF = c.getCurrentRectF().Clone();
					kfMove.StartRotation = c.getRotation();
					kfMove.EndRectF = c.getCurrentRectF().Clone();
					
					if (i ==0)
					{
						kfMove.PlayedStartSound = false;
						kfMove.StartSound = SoundManager.SoundCogRotate;
					}

					if (c == m_TitleDecoRight2Sprite || c == m_TitleDecoRight2SpriteShadow || c == m_TitleDeco2SpriteShadow || c == m_TitleDeco2Sprite)
					{
						kfMove.EndRotation = c.getRotation() - 6;
					}
					else
					{
						kfMove.EndRotation = c.getRotation() + 6;
					}

					lnMilliseconds += 200;
					kfMove.EndTime.add(Calendar.MILLISECOND, lnMilliseconds);

					c.AddKeyFrame(kfMove);
				}
			}
		}
	}

	@Override
	public ArrayList<Sprite> getSpriteList()
	{
		return m_SpriteList;
	}

	@Override
	public String getPhaseName()
	{
		return "MainMenuPhase";
	}

	ParticleEffect p;

	@Override
	public void onDraw(SpriteBatch batch)
	{
		if (m_IsFirstRender)
		{
			BuildSprites();
			m_IsFirstRender = false;

			
			/*p = new ParticleEffect();
			 p.load(Gdx.files.internal("data/smokeTest2.p"),
			 Gdx.files.internal("data")); //files.internal loads from the
			// "assets" folder
			 p.setPosition(100, 100);
			 p.setFlip(false, true);
			 p.allowCompletion();
			 p.start();
			// p.setDuration(3000);*/
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
			obj.draw(batch);

		}

	//	 p.draw(batch);
	//	 p.update(Gdx.graphics.getDeltaTime());

		batch.end();

		RotateDecoSprites();

	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
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

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
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
							if (typedObj.getButtonName() == BUTTON_TYPE_NEW_GAME)
							{								
								m_GameManager.CreateNewGame(m_GameManager.MINIMUM_COGS);
								m_GameManager.SetCogRenderPositions(
										m_GameManager.widthSize,
										m_GameManager.heightSize);

								m_GameManager.getPhaseStack().push(new GamePhase(m_GameManager));
								return true;
							}
							else if (typedObj.getButtonName() == BUTTON_TYPE_RANDOM_GAME)
							{
								m_GameManager.CreateNewGame(ClockConstants.GloablRandom.nextInt(m_GameManager.CalculateMaxGridPositions() - m_GameManager.MINIMUM_COGS) + m_GameManager.MINIMUM_COGS);
								//m_GameManager.CreateNewGame(19);
								m_GameManager.SetCogRenderPositions(
										m_GameManager.widthSize,
										m_GameManager.heightSize);

								m_GameManager.getPhaseStack().push(new GamePhase(m_GameManager));
								return true;
							}
							else if (typedObj.getButtonName() == BUTTON_TYPE_HOW_TO_PLAY)
							{													
								m_GameManager.getPhaseStack().push(new HowToPlayPhase(m_GameManager));
								return true;
								
							}
							else if (typedObj.getButtonName() == BUTTON_SOUND_MUTE)
							{
								ClockConstants.m_SoundManager.setMute(!ClockConstants.m_SoundManager.getIsMute());
								
								if (ClockConstants.m_SoundManager.getIsMute())
								{
									TextureRegion tr = new TextureRegion(
											ClockConstants.GetTexture(ImageNames.icon_sound_mute),
											0,
											0,
											256,
											256);
									tr.flip(false, true);
									typedObj.setRegion(tr);
								}
								else
								{
									TextureRegion tr = new TextureRegion(
											ClockConstants.GetTexture(ImageNames.icon_sound),
											0,
											0,
											256,
											256);
									tr.flip(false, true);
									typedObj.setRegion(tr);
								}
							}
						}
					}
					
					typedObj.setIsMouseDown(false);
				}
			}
		}

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return false;
	}
}
