using System;
using System.Drawing;
using System.Windows.Forms;
using KeyMashGame.Screens;

using System.Diagnostics;
using System.Runtime.InteropServices;

namespace KeyMashGame
{
    public partial class Form1 : Form
    {
        [StructLayout(LayoutKind.Sequential)]
        private struct KBDLLHOOKSTRUCT
        {
            public Keys key;
            public int scanCode;
            public int flags;
            public int time;
            public IntPtr extra;
        }

        //System level functions to be used for hook and unhook keyboard input
        private delegate IntPtr LowLevelKeyboardProc(int nCode, IntPtr wParam, IntPtr lParam);
        [DllImport("user32.dll", CharSet = CharSet.Auto, SetLastError = true)]
        private static extern IntPtr SetWindowsHookEx(int id, LowLevelKeyboardProc callback, IntPtr hMod, uint dwThreadId);
        [DllImport("user32.dll", CharSet = CharSet.Auto, SetLastError = true)]
        private static extern bool UnhookWindowsHookEx(IntPtr hook);
        [DllImport("user32.dll", CharSet = CharSet.Auto, SetLastError = true)]
        private static extern IntPtr CallNextHookEx(IntPtr hook, int nCode, IntPtr wp, IntPtr lp);
        [DllImport("kernel32.dll", CharSet = CharSet.Auto, SetLastError = true)]
        private static extern IntPtr GetModuleHandle(string name);
        [DllImport("user32.dll", CharSet = CharSet.Auto)]
        private static extern short GetAsyncKeyState(Keys key);

        //Declaring Global objects
        private IntPtr ptrHook;
        private LowLevelKeyboardProc objKeyboardProcess;

        private KeyPressScreen userControlToDisplay;
        private DateTime m_LastEscapeTime = DateTime.MinValue;

        public Form1()
        {
            ProcessModule objCurrentModule = Process.GetCurrentProcess().MainModule; //Get Current Module
            objKeyboardProcess = new LowLevelKeyboardProc(captureKey); //Assign callback function each time keyboard process
            ptrHook = SetWindowsHookEx(13, objKeyboardProcess, GetModuleHandle(objCurrentModule.ModuleName), 0); //Setting Hook of Keyboard Process for current module

            InitializeComponent();

            try
            {
                this.Location = Screen.AllScreens[1].WorkingArea.Location;
            }
            catch
            {
                this.Location = Screen.AllScreens[0].WorkingArea.Location;
            }
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            if (ptrHook != IntPtr.Zero)
            {
                UnhookWindowsHookEx(ptrHook);
                ptrHook = IntPtr.Zero;
            }
            base.Dispose(disposing);
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            userControlToDisplay = new KeyPressScreen();
            userControlToDisplay.Location = new Point(0, 0);
            userControlToDisplay.Dock = DockStyle.Fill;
            this.Controls.Add(userControlToDisplay);
        }

        private void Form1_KeyPress(object sender, KeyPressEventArgs e)
        {
            userControlToDisplay.KeyPressEvent(e);
        }

        private void Form1_KeyUp(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Escape)
            {
                if (m_LastEscapeTime >= DateTime.Now.AddMilliseconds(-200))
                {
                    this.Close();
                }
                else
                {
                    m_LastEscapeTime = DateTime.Now;
                    userControlToDisplay.KeyUpEvent(e);
                }
            }
            else
            {
                userControlToDisplay.KeyUpEvent(e);
            }
        }

        private IntPtr captureKey(int nCode, IntPtr wp, IntPtr lp)
        {
            if (nCode >= 0)
            {
                KBDLLHOOKSTRUCT objKeyInfo = (KBDLLHOOKSTRUCT)Marshal.PtrToStructure(lp, typeof(KBDLLHOOKSTRUCT));

                if (
                    objKeyInfo.key == Keys.RWin 
                    || objKeyInfo.key == Keys.LWin
                    || objKeyInfo.key == Keys.Sleep
                    ) // Disabling Windows keys
                {
                    userControlToDisplay.KeyUpEvent(objKeyInfo.key);
                    return (IntPtr)1;
                }
            }
            return CallNextHookEx(ptrHook, nCode, wp, lp);
        }

        private void Form1_MouseClick(object sender, MouseEventArgs e)
        {
            MessageBox.Show("Test");
        }
    }
}
