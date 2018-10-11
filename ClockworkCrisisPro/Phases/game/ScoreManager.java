package com.DamnItJenkins.ClockworkCrisisPro.Phases.game;

import java.util.Calendar;

import com.DamnItJenkins.ClockworkCrisisPro.GameManager;

public class ScoreManager
{
	private GameManager m_GameManger = null;
	
	private int m_CogSpinTimerExpires = 0;
	
	private Calendar m_StartTime;
	
	public ScoreManager(GameManager gm)
	{
		m_GameManger = gm;
		m_StartTime = Calendar.getInstance();
	}
	
	public void IncrementCogSpinTimerExpires()
	{
		m_CogSpinTimerExpires++;
	}
	
	public String getScoreText1()
	{
		String strReturn = "";
				
		strReturn += "Time: \n";
		strReturn += "Difficulty: \n";
		strReturn += "Red Cogs: \n";
		strReturn += "\n";
		strReturn += "Score: ";
		return strReturn;
	}
	
	public String getScoreText2()
	{
		Calendar now = Calendar.getInstance();
	
		String strReturn = "";
		long secs = (now.getTimeInMillis() - m_StartTime.getTimeInMillis()) / 1000;
		
		int intScore = (m_GameManger.getCogs().length * 100) + (m_CogSpinTimerExpires * -50);
				
		
		if (intScore <0)
		{
			intScore = 0;
		}
		
		strReturn += String.format("%02d:%02d:%02d", secs / 3600, (secs % 3600) / 60, (secs % 60)) +"\n";
		strReturn += m_GameManger.getCogs().length +"\n";
		strReturn += Integer.toString(m_CogSpinTimerExpires) +"\n";
		strReturn += "\n";
		strReturn += Integer.toString(intScore);
		return strReturn;
	}

}
