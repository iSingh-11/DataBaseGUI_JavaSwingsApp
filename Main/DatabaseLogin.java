package Main;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
public class DatabaseLogin
{
	JFrame f;
	JTextField t1;
	JPasswordField t2;
	JComboBox<String> cb;
	Color pp=new Color(45,45,45);
	DatabaseLogin()
	{
		ImageIcon img = new ImageIcon("C:\\Users\\hp\\Downloads\\Database.png");  
		f=new JFrame("Guriqbal's Database");	
		
		JLabel head=new JLabel(img);
		head.setBounds(110,30,100,100);
		f.add(head);
		
		Font font1 = new Font("SansSerif", Font.BOLD, 24);
		JLabel l=new JLabel("Login to Database");
		l.setFont(font1);
		l.setForeground(Color.WHITE);
		l.setBounds(65,100,250,100);
		f.add(l);
		
		Font font2 = new Font("SansSerif", Font.BOLD, 16);
		JLabel l1=new JLabel("Username :");
		l1.setFont(font2);
		l1.setForeground(Color.WHITE);
		l1.setBounds(25,190,120,20);
		f.add(l1);
				
		JLabel l2=new JLabel("Password :");
		l2.setFont(font2);
		l2.setForeground(Color.WHITE);
		l2.setBounds(25,225,120,20);
		f.add(l2);
		
		t1=new JTextField();
		t1.setFont(font2);
		t1.setBounds(130,190,180,25);
		t1.setBackground(pp);
		t1.setCaretColor(Color.WHITE);
		t1.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
		t1.setForeground(Color.WHITE);
		f.add(t1);
		
		t2=new JPasswordField();
		t2.setFont(font2);
		t2.setBounds(130,225,180,25);
		t2.setBackground(pp);
		t2.setCaretColor(Color.WHITE);
		t2.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
		t2.setForeground(Color.WHITE);
		f.add(t2);
		
		Font font3 = new Font("SansSerif", Font.BOLD, 14);
		String country[]={"Select Database","• MySQL via PHPMyAdmin","• Oracle Application Express"};        
	    cb=new JComboBox<String>(country);
	    cb.setFont(font3);
	    cb.setBounds(80,263,230,25);
		cb.setFocusable(false);
	    f.add(cb); 
	    
	    MyButton b=new MyButton("Login 🔑");  
	    b.setFont(font2);
	    b.setBounds(25,305,285,30);  
		b.setForeground(Color.WHITE);
		b.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		b.setFocusPainted(false);
		b.addActionListener(new ActionListener() 
		{
		    public void actionPerformed(ActionEvent e) 
		    {
				try
				{
					String u=t1.getText();
					String p=new String(t2.getPassword());
					if(u.isEmpty() || p.isEmpty()) throw new Exception();
					String d=(String)cb.getSelectedItem();
					if(d.equals(country[1]) || d.equals(country[2]))
					{
						try
						{
							DatabaseTesting db;
							if(d.equals(country[1]))
							{
								db=new DatabaseTesting(DatabaseTesting.MySQL,DatabaseTesting.MySQLDriver,u,p);
							}
							else
							{
								db=new DatabaseTesting(DatabaseTesting.Oracle,DatabaseTesting.OracleDriver,u,p);
							}
							ProgressBar pb=new ProgressBar(db);
							pb.createProgressBar();
							pb.iterate();
							f.dispose();
						}
						catch(CommunicationsException q)
						{
							JOptionPane.showMessageDialog(f,"Connection Failed from Database");
						}
						catch(Exception q)
						{
							JOptionPane.showMessageDialog(f,"Invalid Username Password");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(f,"Database is not selected");
					}
				}
				catch(Exception p)
				{
					JOptionPane.showMessageDialog(f,"Enter both Username and Password!");
				}
		    }
		});
	    f.add(b);  
		f.setSize(348,400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setIconImage(img.getImage());
		f.setLayout(null);
		f.setResizable(false);
		f.getContentPane().setBackground(Color.DARK_GRAY);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	} 
	public static void main(String[] args) 
	{
		new DatabaseLogin();
	}
}