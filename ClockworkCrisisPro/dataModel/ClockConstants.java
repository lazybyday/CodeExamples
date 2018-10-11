package com.DamnItJenkins.ClockworkCrisisPro.dataModel;

import java.util.Random;

import com.DamnItJenkins.ClockworkCrisisPro.ClockworkCrisisPro;
import com.DamnItJenkins.ClockworkCrisisPro.FontManager;
import com.DamnItJenkins.ClockworkCrisisPro.RectF;
import com.DamnItJenkins.ClockworkCrisisPro.SoundManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public final class ClockConstants
{

	public static SoundManager m_SoundManager = new SoundManager();
	public static FontManager m_FontManager = new FontManager();
	
	public static ClockworkCrisisPro CoreActivity = null;

	public static int[] CogSize = null;

	public static AssetManager m_AssetManager = new AssetManager();

	public static Random GloablRandom = new Random();


	public static enum ImageNames
	{
		Null_Image,
		blank,
		cog2_512,
		cog2_shadow_512,
		cog2_shadow_256,

		tileable_metal_textures_6_512,
		cog5_512,
		cog5_shadow_512,
		cog5_shadow_256,

		cog6_512,
		cog6_shadow_512,
		cog6_shadow_256,
		
		cog7_512,
		cog7_shadow_512,
		cog7_shadow_256,
		
		cog8_512,
		cog8_shadow_512,
		cog8_shadow_256,
				
		cog9_512,
		cog9_shadow_512,
		cog9_shadow_256,
		
		cog10_512,
		cog10_shadow_512,
		cog10_shadow_256,


		
		door_slide_guard_2_256,
		door_slide_right_guard_2_256,
		door2_texture_512,
		door3_texture_512,
		door4_texture_512,
		door5_texture_512,
		door6_texture_512,
		door7_texture_512,
		door8_texture_512,
		door9_texture_512,
		door5_guard_2_512,
		chain_shadow_512,
		chain_shadow_256,
		title_clockwork_crisis,
		button_new_game,
		button_random_game,
		button_how_to_play,
		button_next_level,
		button_main_menu,
		

		icon_sound,
		icon_sound_mute,
		icon_finger,
		
		archway,
		rotate_cogs_title,
		score_title_512,
		victory_quote_title_512,
		foreground_combined_1_512,
		foreground_shadow_1_512,
		foreground_light_1_512,
		foreground_grit_1_512,
		textblock_512,
		cog_rotation_guide_512,
		cog_icon_128,
		cog_guide2_16;

		// Mapping difficulty to difficulty id
		private static final ImageNames[] map_MainCogs;

		private static final ImageNames[] map_SubCogs;

		static
		{
			map_MainCogs = new ImageNames[] { cog2_512, cog5_512, cog6_512, cog7_512, cog8_512,  cog10_512};

			map_SubCogs = new ImageNames[] { cog2_512, cog5_512,

			cog6_512, cog7_512, cog8_512, cog10_512

			};
		}

		public static ImageNames RandomCog()
		{
			int max = map_MainCogs.length;

			ImageNames pick = map_MainCogs[GloablRandom.nextInt(max)];

			while (pick == Null_Image)
			{
				pick = map_MainCogs[GloablRandom.nextInt(max)];
			}

			return pick;
		}

		public static ImageNames RandomSubCog()
		{
			int max = map_SubCogs.length;

			return map_SubCogs[GloablRandom.nextInt(max)];
		}

	}

	public static ImageNames GetShadowForBmp(ImageNames name)
	{
		switch (name)
		{
			case cog2_512:
				return ImageNames.cog2_shadow_256;

			case cog5_512:
				return ImageNames.cog5_shadow_256;

			case cog6_512:
				return ImageNames.cog6_shadow_256;
				
			case cog7_512:
				return ImageNames.cog7_shadow_256;		
				
			case cog8_512:
				return ImageNames.cog8_shadow_256;
				
			case cog9_512:
				return ImageNames.cog9_shadow_256;
				
			case cog10_512:
				return ImageNames.cog10_shadow_256;						

			default:
			case Null_Image:
				return ImageNames.Null_Image;
		}
	}

	public static RectF getBmpRectF(ImageNames name)
	{
		String[] parts = name.toString().split("_");

		return new RectF(
				0,
				0,
				Integer.parseInt(parts[parts.length - 1]),
				Integer.parseInt(parts[parts.length - 1]));
	}

	public static Texture GetTexture(ImageNames name)
	{
		String strAssetPath = GetAssetPath(name);
		
		if (!m_AssetManager.isLoaded(strAssetPath, Texture.class))
		{
			m_AssetManager.load(strAssetPath, Texture.class);
			m_AssetManager.finishLoading();
			//Texture t = m_AssetManager.get(strAssetPath, Texture.class);
			//t.setFilter(TextureFilter.Nearest, TextureFilter.MipMapLinearNearest);
		}

		return m_AssetManager.get(strAssetPath, Texture.class);
	}

	public static String GetAssetPath(ImageNames name)
	{
		switch (name)
		{
			case title_clockwork_crisis:
				return "data/title_v2.png";

			default:
				return "data/" + name.toString() + ".png";

		}
	}

	public static FileHandle GetBmp(ImageNames name)
	{
		return Gdx.files.internal(GetAssetPath(name));
	}

	public static float CalculateScreenSquare(float w, float h)
	{
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

		return spriteWidth;

	}
}
