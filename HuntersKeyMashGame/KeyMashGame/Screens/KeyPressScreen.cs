using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.IO;

namespace KeyMashGame.Screens
{
    public partial class KeyPressScreen : UserControl
    {
        private readonly string IMAGE_PATH = Application.StartupPath + "\\data\\images\\";

        private object locker = new object();

        private Brush m_TextBackgroundShadowBrush = new SolidBrush(Color.FromArgb(200, 0, 0, 0));
        private Brush m_TextBackgroundBrush = new SolidBrush(Color.FromArgb(255, 0, 0, 0));
        private Font m_WinLossTextFont = new System.Drawing.Font("Tahoma", 40, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Pixel, ((byte)(0)));

        private string m_TextToDisplay = " Hello Hunter\n:)";

        private Image m_BackgroundImageCache = null;
        private Rectangle m_BackgroundImageScaledRectangle;
        private Rectangle m_BackgroundImageRectangle;

        private StringFormat format;

        private Color m_BackgroundColor = Color.Green;
        private Random m_Random = new Random();

        private List<CharacterSprite> m_CharacterSprites = new List<CharacterSprite>();

        public KeyPressScreen()
        {
            InitializeComponent();

            this.MouseWheel += new MouseEventHandler(KeyPressScreen_MouseWheel);
            this.SetStyle(ControlStyles.OptimizedDoubleBuffer | ControlStyles.UserPaint | ControlStyles.AllPaintingInWmPaint, true);

            format = new StringFormat() { FormatFlags = StringFormatFlags.NoWrap | StringFormatFlags.MeasureTrailingSpaces };
            format.Alignment = StringAlignment.Center;
            format.LineAlignment = StringAlignment.Center;            

            timer1.Start();
            BuildImageDirectories();
        }

        private void BuildImageDirectories()
        {
            for (int i = 'a'; i <= 'z'; i++)
            {
                string path = IMAGE_PATH + Convert.ToChar(i);
                if (!Directory.Exists(path))
                {
                    Directory.CreateDirectory(path);
                }
            }
        }

        public void KeyPressEvent(KeyPressEventArgs e)
        {
            ProcessKey(e.KeyChar.ToString());
        }

        internal void KeyUpEvent(Keys keys)
        {            
            ProcessKey(keys.ToString());
        }

        public void KeyUpEvent(KeyEventArgs e)
        {
            KeysConverter kc = new KeysConverter();
            string keyChar = kc.ConvertToString(e.KeyData);

            switch(keyChar)
            {
                case "F1":
                case "F2":
                case "F3":
                case "F4":
                case "F5":
                case "F6":
                case "F7":
                case "F8":
                case "F9":
                case "F10":
                case "F11":
                case "F12":
                case "Enter":
                case "Escape":
                case "Space":
                case "Tab":
                case "Home":
                case "End":
                    ProcessKey(keyChar);
                    break;

                case "Menu":
                    ProcessKey("Alt");
                    break;

                case "Apps":
                    ProcessKey("Menu");
                    break;

                case "Scroll":
                    ProcessKey("Scroll Lock");
                    break;

                case "PrintScreen":
                    ProcessKey("Print Screen");
                    break;

                case "Pause":
                    ProcessKey("Pause Break");
                    break;

                case "PgUp":
                    ProcessKey("Page Up");
                    break;

                case "PgDn":
                    ProcessKey("Page Down");
                    break;

                case "Del":
                    ProcessKey("Delete");
                    break;

                case "ControlKey":
                    ProcessKey("Control");
                    break;

                case "Ins":
                    ProcessKey("Insert");
                    break;

                case "Down":
                    ProcessKey(keyChar + "\n\u2193");
                    break;

                case "Up":
                    ProcessKey(keyChar + "\n\u2191");
                    break;

                case "Right":
                    ProcessKey(keyChar +"\n\u2192");
                    break;

                case "Left":
                    ProcessKey(keyChar + "\n\u2190");
                    break;
    
            }
        }

        private void pickImage(string keyChar)
        {
            int intUseImage = m_Random.Next(0, 3);
            if (intUseImage != 0)
            {
                m_BackgroundImageCache = null;
                return;
            }

            string strPath = IMAGE_PATH + keyChar;

            if (Directory.Exists(strPath))
            {
                string[] fileNames = null;
                try
                {
                    fileNames = Directory.GetFiles(strPath);
                }
                catch (Exception)
                {
                    m_BackgroundImageCache = null;
                    fileNames = null;
                }

                if (fileNames != null && fileNames.Length > 0)
                {
                    int ran = m_Random.Next(0, fileNames.Length);

                    FileInfo info = new FileInfo(fileNames[ran]);

                    if (info.Extension.ToLower() == ".jpg" || info.Extension.ToLower() == ".gif" || info.Extension.ToLower() == ".png" || info.Extension.ToLower() == ".bmp")
                    {
                        m_BackgroundImageCache = Image.FromFile(fileNames[ran]);
                        m_BackgroundImageRectangle = new Rectangle(new Point(0, 0), new Size(m_BackgroundImageCache.Width, m_BackgroundImageCache.Height));
                        m_BackgroundImageScaledRectangle = CalculateScaledBackgroundImageRectangle();
                    }
                    else
                    {
                        m_BackgroundImageCache = null;
                    }
                }
                else
                {
                    m_BackgroundImageCache = null;
                }
            }
            else
            {
                m_BackgroundImageCache = null;
            }
        }

        private Rectangle CalculateScaledBackgroundImageRectangle()
        {
            double ratioX = (double)this.Width / m_BackgroundImageCache.Width;
            double ratioY = (double)this.Height / m_BackgroundImageCache.Height;
            double ratio = Math.Max(ratioX, ratioY);

            int newWidth = (int)(m_BackgroundImageCache.Width * ratio) + 1;
            int newHeight = (int)(m_BackgroundImageCache.Height * ratio) + 1;

            int xPos = 0;
            int yPos = 0;

            if (this.Width != newWidth)
            {
                xPos = (this.Width - newWidth) / 2;
            }

            if (this.Height != newHeight)
            {
                yPos = (this.Height - newHeight) / 2;
            }

            return new Rectangle(new Point(xPos, yPos), new Size(newWidth, newHeight));
        }

        public Color Rainbow(float progress)
        {
            float div = (Math.Abs(progress % 1) * 6);
            int ascending = (int)((div % 1) * 255);
            int descending = 255 - ascending;

            switch ((int)div)
            {
                case 0:
                    return Color.FromArgb(255, 255, ascending, 0);
                case 1:
                    return Color.FromArgb(255, descending, 255, 0);
                case 2:
                    return Color.FromArgb(255, 0, 255, ascending);
                case 3:
                    return Color.FromArgb(255, 0, descending, 255);
                case 4:
                    return Color.FromArgb(255, ascending, 0, 255);
                default: // case 5:
                    return Color.FromArgb(255, 255, 0, descending);
            }
        }

        private void ProcessKey(string keyChar)
        {
            switch (keyChar)
            {
                case "LWin":
                case "RWin":
                    keyChar = "Windows";
                    break;

                case "\r":
                    keyChar = "Enter";
                    break;

                case " ":
                    keyChar = "Space";
                    break;
            }
                   

            if (keyChar == m_TextToDisplay)
            {
                return;
            }

            lock (locker)
            {
                pickImage(keyChar);
                setRandomBackgroundColor();
                setRandomTextBackgroundBrushColor();
                
                m_TextToDisplay = keyChar;

                Graphics graphics = this.CreateGraphics();
                m_WinLossTextFont = Utils.ScaleFont(m_TextToDisplay, graphics, this.Size);              
                graphics.Dispose();
                graphics = null;                
            }

            this.Invalidate();
        }


        private void setRandomTextBackgroundBrushColor()
        {
            m_TextBackgroundBrush = new SolidBrush(Rainbow((float)m_Random.NextDouble()));
        }

        private void setRandomBackgroundColor()
        {
            int ran = m_Random.Next(0, 256);
            int ran1 = m_Random.Next(0, 256);
            int ran2 = m_Random.Next(0, 256);

            m_BackgroundColor = Color.FromArgb(255, ran, ran1, ran2);
            //m_BackgroundColor = Rainbow((float)m_Random.NextDouble());
        }


        private void KeyPressScreen_Paint(object sender, PaintEventArgs e)
        {
            lock (locker)
            {
                if (m_BackgroundImageCache == null)
                {
                    e.Graphics.Clear(m_BackgroundColor);
                }
                else
                {
                    e.Graphics.DrawImage(m_BackgroundImageCache, m_BackgroundImageScaledRectangle, m_BackgroundImageRectangle, GraphicsUnit.Pixel);
                }

                e.Graphics.SmoothingMode = System.Drawing.Drawing2D.SmoothingMode.HighQuality;
                e.Graphics.CompositingQuality = System.Drawing.Drawing2D.CompositingQuality.HighQuality;
                e.Graphics.InterpolationMode = System.Drawing.Drawing2D.InterpolationMode.HighQualityBicubic;
                e.Graphics.TextRenderingHint = System.Drawing.Text.TextRenderingHint.AntiAlias;


                //Text Shadow
                Rectangle r = new Rectangle(e.ClipRectangle.X + 10, e.ClipRectangle.Y + 10, e.ClipRectangle.Width, e.ClipRectangle.Height);
                e.Graphics.DrawString(m_TextToDisplay, m_WinLossTextFont, m_TextBackgroundShadowBrush,r, format);

                //Text
                e.Graphics.DrawString(m_TextToDisplay, m_WinLossTextFont, m_TextBackgroundBrush, e.ClipRectangle, format);

                foreach (CharacterSprite characterSprite in m_CharacterSprites)
                {
                    characterSprite.Render(e.Graphics);
                }
              
            }
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
           this.Invalidate();

           for (int i = m_CharacterSprites.Count - 1; i >= 0; i--)
           {
               m_CharacterSprites[i].StepFrame(this.CreateGraphics());

               if (m_CharacterSprites[i].Active == false)
               {
                   m_CharacterSprites.RemoveAt(i);
               }
           }

           /*foreach (CharacterSprite characterSprite in m_CharacterSprites)
           {
               characterSprite.StepFrame(this.CreateGraphics());
           }
            */
      
        }

        private void KeyPressScreen_Load(object sender, EventArgs e)
        {
        }

        private void KeyPressScreen_Resize(object sender, EventArgs e)
        {
            Graphics graphics = this.CreateGraphics();
            m_WinLossTextFont = Utils.ScaleFont(m_TextToDisplay, graphics, this.Size);
            graphics.Dispose();
            graphics = null;
            this.Invalidate();
        }        

        private void KeyPressScreen_MouseClick(object sender, MouseEventArgs e)
        {
            int size = m_Random.Next(100,400);
            Point l = new Point(e.Location.X - size, e.Location.Y - size);

            if (e.Button == System.Windows.Forms.MouseButtons.Left)
            {

                //  m_CharacterSprites.Add(new CharacterSprite(((char)(m_Random.Next(1, 31))).ToString(), Rainbow((float)m_Random.NextDouble()), new Rectangle(e.Location, new Size(100, 100)), this.CreateGraphics(), format)); 
                m_CharacterSprites.Add(new CharacterSprite('\u263B'.ToString(), Rainbow((float)m_Random.NextDouble()), new Rectangle(l, new Size(size * 2, size * 2)), this.CreateGraphics(), format));
                //  m_CharacterSprites.Add(new CharacterSprite('A'.ToString(), Rainbow((float)m_Random.NextDouble()), new Rectangle(l, new Size(200, 200)), this.CreateGraphics(), format)); 
            }
            else
            {
                m_CharacterSprites.Add(new CharacterSprite('\u265B'.ToString(), Rainbow((float)m_Random.NextDouble()), new Rectangle(l, new Size(size * 2, size * 2)), this.CreateGraphics(), format));
            }
             
        }

        private void KeyPressScreen_MouseWheel(object sender, MouseEventArgs e)
        {
            setRandomTextBackgroundBrushColor();

           /* if (m_BackgroundImageCache == null)
            {
                setRandomBackgroundColor();
            }

            else
            {
                setRandomTextBackgroundBrushColor();
            }*/
        }

       
    }
}
