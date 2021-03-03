package Main;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;
public class MyButton extends JButton 
    {
		private static final long serialVersionUID = 1L;
		static Color cc=new Color(45,45,45);
		static Color pp=new Color(95,95,95);
        public MyButton() 
        {
            this(null);
        }
        public MyButton(String text) 
        {
            super(text);
            super.setContentAreaFilled(false);
        }
        protected void paintComponent(Graphics g) 
        {
            if (getModel().isPressed()) 
            {
                g.setColor(Color.GRAY);
            }
            else if (getModel().isRollover()) 
            {
                g.setColor(pp);
            }
            else 
            {
                g.setColor(cc);
            }
            g.fillRect(0, 0, getWidth(), getHeight());
            super.paintComponent(g);
        }
        public void setContentAreaFilled(boolean b) 
        {
        }
        public void setHoverBackgroundColor(Color hoverBackgroundColor) 
        {
        }
        public void setPressedBackgroundColor(Color pressedBackgroundColor) 
        {
        }
    }
