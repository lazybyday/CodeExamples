using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Drawing;

namespace KeyMashGame.Screens
{
    public class CharacterSprite
    {
        public string Character { get; set; }
        public Color ForeColour { get; set; }
        public int Step { get; set; }
        public Rectangle Position { get; set; }
        public bool Active { get; set; }

        private Brush m_TextBackgroundBrush = new SolidBrush(Color.FromArgb(255, 0, 0, 0));
        private volatile Font m_WinLossTextFont = new System.Drawing.Font("Tahoma", 40, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Pixel, ((byte)(0)));

        private StringFormat format;

        public CharacterSprite(string c, Color colour, Rectangle rect, Graphics g, StringFormat format)
        {
            Character = c;
            ForeColour = colour;
            Step = 0;
            Position = rect;
            Active = true;


            this.format = format;

            m_TextBackgroundBrush = new SolidBrush(ForeColour);
            m_WinLossTextFont = Utils.ScaleFont(Character, g, Position.Size);
        }



        public void Render(Graphics g)
        {
            if (Active)
            {
                //g.DrawRectangle(new Pen(m_TextBackgroundBrush), Position);
                g.DrawString(Character, m_WinLossTextFont, m_TextBackgroundBrush, Position, format);
            }
        }

        const int inflate = 400;

        const int APLHA_SHIFT = 10;

        public void StepFrame(Graphics g)
        {
            if (Step < 5)
            {
                Step++;
            }
            else if (Step < 30)
            {
               // Position.Inflate(10, 10);
                if (ForeColour.A - APLHA_SHIFT > 0)
                {
                    ForeColour = Color.FromArgb(ForeColour.A - APLHA_SHIFT, ForeColour.R, ForeColour.G, ForeColour.B);
                }
                else
                {
                    ForeColour = Color.FromArgb(0, ForeColour.R, ForeColour.G, ForeColour.B);
                }

                m_TextBackgroundBrush = new SolidBrush(ForeColour);
                //this.Position = new Rectangle(this.Position.X - inflate, this.Position.Y - inflate, this.Position.Width + (inflate * 2), this.Position.Height + (inflate * 2)); 

                m_WinLossTextFont = Utils.ScaleFont(Character, g, Position.Size);
                Step++;
            }
            else
            {
                Active = false;
            }
        }
    }
}
