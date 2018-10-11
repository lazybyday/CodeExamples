package com.DamnItJenkins.ClockworkCrisisPro.Phases;

import java.util.Calendar;

import com.DamnItJenkins.ClockworkCrisisPro.RectF;
import com.DamnItJenkins.ClockworkCrisisPro.dataModel.ClockConstants;
import com.badlogic.gdx.audio.Sound;


public class KeyFrame 
{
	public Calendar StartTime;
	public Calendar EndTime;
	
	public RectF StartRectF;
	public RectF EndRectF;
	
	public float StartRotation;
	public float EndRotation;
	
	public float CurrentRotation;

	public Sound StartSound = null;
	public Sound EndSound = null;
	
	public boolean PlayedStartSound = true;
	public boolean PlayedEndSound = true;
	
	
	
	private boolean m_EndFrameHasBeenProcessedAtLeastOnce = false;
	
	public boolean getEndFrameHasBeenProcessedAtLeastOnce ()
	{
		return m_EndFrameHasBeenProcessedAtLeastOnce;
	}
	
	public KeyFrame() 
	{
		StartTime = Calendar.getInstance();
		EndTime =  Calendar.getInstance();
	}
	
	
	public KeyFrame Clone ()
    {
		KeyFrame clone = new KeyFrame();
		clone.StartTime = this.StartTime;
		clone.EndTime = this.EndTime;
		
		clone.StartRectF = this.StartRectF.Clone();
		clone.EndRectF = this.EndRectF.Clone();
		
		clone.StartRotation = this.StartRotation;
		clone.EndRotation = this.EndRotation;
		
		clone.CurrentRotation = this.CurrentRotation;
		
		clone.m_EndFrameHasBeenProcessedAtLeastOnce = this.m_EndFrameHasBeenProcessedAtLeastOnce;
		
		return clone;
    }
	
	
	public RectF ProcssRectF()
	{
	
		float processPercentage = getFramePercentage();
		
		if (!m_EndFrameHasBeenProcessedAtLeastOnce && processPercentage == 1)
		{
			m_EndFrameHasBeenProcessedAtLeastOnce = true;
			
			if (PlayedEndSound == false && EndSound !=null)
			{
				ClockConstants.m_SoundManager.PlaySound(EndSound);
				//EndSound.play();
			}
		}
		
		RectF currentRectF = new RectF();
		currentRectF.left = StartRectF.left + (EndRectF.left - StartRectF.left) * processPercentage;
		currentRectF.top = StartRectF.top + (EndRectF.top - StartRectF.top) * processPercentage;
		
		//currentRectF.top = (EndRectF.top - StartRectF.top) * processPercentage;
		//currentRectF.top = EndRectF.top;
		currentRectF.right = StartRectF.right + (EndRectF.right - StartRectF.right) * processPercentage;
		
		//if ()
		//currentRectF.bottom = (EndRectF.bottom - StartRectF.bottom) * processPercentage;
		currentRectF.bottom = StartRectF.bottom + (EndRectF.bottom - StartRectF.bottom) * processPercentage;
				
		CurrentRotation = StartRotation + (EndRotation - StartRotation) * processPercentage;
		
		return currentRectF;
	}

	
	private float getFramePercentage()
	{
		Calendar now = Calendar.getInstance() ;
						
		long total = EndTime.getTimeInMillis() - StartTime.getTimeInMillis();
		long duration = now.getTimeInMillis() - StartTime.getTimeInMillis();	

		if (now.after(EndTime))
		{
			return 1; //100%
		}
		else
		{
			return (float)duration / (float) total;
		}
	}
	
}
