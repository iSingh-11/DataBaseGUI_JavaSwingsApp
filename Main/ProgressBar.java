package Main;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;    
public class ProgressBar implements ActionListener
{
	static ProgressBar m;
	static JProgressBar jb;
	static JLabel l;
	static JFrame f;
	static Timer timer;
	static int i=0,num=0;
	DatabaseTesting db;
	public ProgressBar(DatabaseTesting db)
	{
		this.db=db;
	}
	public void createProgressBar()
	{    
		l=new JLabel("Loading...Please Wait");
		l.setBounds(2,0,220,14);
		l.setForeground(Color.WHITE);
		jb=new JProgressBar(0,1000);    
		jb.setBounds(2,15,220,25);         
		jb.setValue(0);    
		jb.setStringPainted(true);
		jb.setForeground(Color.DARK_GRAY);
		jb.setBackground(Color.WHITE);
		f=new JFrame();
		f.setUndecorated(true);
		f.add(jb);  
		f.add(l);
		f.setSize(224,42);    
		f.setLayout(null);
		f.getContentPane().setBackground(Color.DARK_GRAY);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	public void iterate()
	{
		timer = new Timer(100,this);
		timer.start(); 
	}
	public void actionPerformed(ActionEvent e) 
	{
		jb.setValue(i);
		i=i+50; 
		if(i>1050)
		{
			timer.stop();
			f.dispose();
			new DatabaseGUIOperations(db);
		}
	}
}    