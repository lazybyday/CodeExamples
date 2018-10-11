package com.DamnItJenkins.ClockworkCrisisPro;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundManager
{
	public static Sound SoundButtonPress = null;
	public static Sound SoundCogSpinTimerExpire = null;
	public static Sound SoundCogSpinTimerActivate = null;
	public static Sound SoundCogMove = null;
	public static Sound SoundCogRotate = null;
	public static Sound SoundDoorClosed = null;
	public static Sound SoundSlideDoorClosed = null;
	public static Sound SoundFootSteps = null;
	
	private boolean m_IsMute = false;

	public SoundManager()
	{
	}
	
	public void setMute(boolean b)
	{
		m_IsMute = b;
	}
	
	public boolean getIsMute()
	{
		return m_IsMute;
	}
	
	
	
	public void LoadSounds()
	{
		SoundButtonPress = Gdx.audio.newSound(Gdx.files.internal("data/sounds/sound_button_press.wav"));
		SoundCogSpinTimerExpire = Gdx.audio.newSound(Gdx.files.internal("data/sounds/sound_cog_spin_timer_expire.mp3"));
		SoundCogSpinTimerActivate = Gdx.audio.newSound(Gdx.files.internal("data/sounds/sound_cog_spin_timer_activate.mp3"));
		SoundCogMove = Gdx.audio.newSound(Gdx.files.internal("data/sounds/sound_cog_move.mp3"));
		SoundCogRotate = Gdx.audio.newSound(Gdx.files.internal("data/sounds/sound_cog_rotate.mp3"));
		SoundDoorClosed = Gdx.audio.newSound(Gdx.files.internal("data/sounds/sound_door_closed.mp3"));
		SoundFootSteps = Gdx.audio.newSound(Gdx.files.internal("data/sounds/footsteps2sec.mp3"));
		SoundSlideDoorClosed = Gdx.audio.newSound(Gdx.files.internal("data/sounds/sound_metal_door_slide_closed2.mp3")); 
	}
	
	public void DisposeSounds()
	{
		SoundButtonPress.dispose();
		SoundCogSpinTimerExpire.dispose();
		SoundCogSpinTimerActivate.dispose();
		SoundCogMove.dispose();
		SoundCogRotate.dispose();
		SoundDoorClosed.dispose();
		SoundFootSteps.dispose();
		SoundSlideDoorClosed.dispose();
	}
	
	public void PlaySound(Sound sound)
	{
		final Sound localSound = sound;
		if (!m_IsMute)
		{
			Thread t = new Thread()
			{	
				public void run()
				{
					localSound.play();
				}
			};
			t.start();
			//sound.play();
		}
	}

}
