using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Drawing;

namespace KeyMashGame
{
    public static class Utils
    {
        public static Font ScaleFont(string text, Graphics graphics, Size s)
        {
            int textWidth = s.Width;
            Font f;
            while (true)
            {
                f = new System.Drawing.Font("Tahoma", textWidth, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Pixel, ((byte)(0)));

                SizeF m_BorderTextSize = graphics.MeasureString(text, f);

                if (m_BorderTextSize.Width <= s.Width && m_BorderTextSize.Height <= s.Height)
                {
                    break;
                }

                textWidth--;
            }

            return f;
        }
    }
}
