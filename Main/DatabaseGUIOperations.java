package Main;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSpinnerUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
public class DatabaseGUIOperations 
{
	DatabaseTesting db;
	JFrame f;
	JPanel p1;
	JTabbedPane tp;
	Color dd=new Color(60,60,60);
	Color cc=new Color(2,117,216);
	Color pp=new Color(95,95,95);
	Color uu=new Color(45,45,45);
	Font font1 = new Font("SansSerif", Font.BOLD, 16);
	Font font2 = new Font("SansSerif", Font.BOLD, 24);
	Font font3 = new Font("SansSerif", Font.BOLD, 17);
	Font font4 = new Font("SansSerif", Font.PLAIN, 15);
	Font font5 = new Font("SansSerif", Font.BOLD, 12);
	Font font6 = new Font("SansSerif", Font.BOLD, 15);
	Object[] columnNames;
	int colSize=0;
	DatabaseGUIOperations(DatabaseTesting db)
	{
		this.db=db;
		ImageIcon img = new ImageIcon("C:\\Users\\hp\\Downloads\\Database.png");  
		f=new JFrame("Guriqbal's Database");
		
		UIDefaults def = UIManager.getLookAndFeelDefaults();
		def.put("TabbedPane.selected",cc);
        def.put("TabbedPane.textIconGap", 10);
        def.put("TabbedPane.tabInsets", new Insets(17,90,17,90));
        def.put("TabbedPane.selectedTabPadInsets", new Insets(17,90,17,90));
        def.put("TabbedPane.borderHightlightColor",dd); 
        def.put("TabbedPane.darkShadow",Color.DARK_GRAY); 
        def.put("TabbedPane.light",Color.DARK_GRAY);
        def.put("TabbedPane.selectHighlight",Color.DARK_GRAY);
        def.put("TabbedPane.lightHighlight",Color.DARK_GRAY);
        def.put("TabbedPane.darkShadow",Color.DARK_GRAY);
        def.put("TabbedPane.focus",Color.DARK_GRAY);
        
		tp=new JTabbedPane(JTabbedPane.LEFT);  
		tp.setBounds(-10,-5,990,618);
		tp.setFocusable(false);
		tp.setFont(font1);
		tp.setBackground(dd);
		tp.setForeground(Color.WHITE);
		Insets insets = UIManager.getInsets("TabbedPane.contentBorderInsets");
		insets.top = -1;
		insets.bottom=-1;
		insets.right=-1;
		UIManager.put("TabbedPane.contentBorderInsets", insets);
		
		p1=new JPanel();
		p1.setBackground(Color.DARK_GRAY);
		JLabel l1=new JLabel("Here is the list of all the available tables in the selected database :)");
		l1.setFont(font3);
		l1.setForeground(Color.WHITE);
		p1.add(l1);
		home();
		tp.add("�?� Home",p1);
		
		create();
		insert();
		update();
		delete();
		sequences();
		views();
		display();
		custom();
		profile();
		tp.addChangeListener(new ChangeListener() 
		{
	        public void stateChanged(ChangeEvent e) 
	        {
	        	if(tp.getSelectedIndex()==0)
	            {
	                p1.remove(1);
	                home();
	            }
	        }
	    });
		
		f.add(tp); 
		f.setSize(994,618);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setIconImage(img.getImage());
		f.setLayout(null);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	public void home()
	{
		DefaultTableModel model = new DefaultTableModel(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {       
			       return false;
		}};
		columnNames=new Object[2];
		columnNames[0]="No.";
		columnNames[1]="Table Name";
        model.setColumnIdentifiers(columnNames); 
        model.addRow(columnNames);
        int count=1;
        ArrayList<Object> a=db.showAll();
        for(Object i:a)
        {
        	columnNames[0]=count;
        	columnNames[1]=i;
        	model.addRow(columnNames);
        	count++;
        }
        
        JTable t1 = new JTable(model){
			private static final long serialVersionUID = 1L;
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
				Component c = super.prepareRenderer(renderer, row, column);
				c.setFont(row == 0 ? font6 : font4);
				c.setBackground(row == 0 ? pp : uu);
				return c;
			}
		};
		t1.getColumnModel().getColumn(0).setPreferredWidth(60);
		t1.getColumnModel().getColumn(1).setPreferredWidth(360);
        t1.setBackground(uu);
		t1.setForeground(Color.WHITE);
		t1.setRowHeight(25);
		t1.setRowHeight(0,27);
		t1.setShowVerticalLines(false);
		t1.setShowHorizontalLines(false);
		t1.setCellSelectionEnabled(false);
        
		p1.add(t1);
	}
	public void create()
	{
		JPanel p2=new JPanel();
		p2.setBackground(Color.DARK_GRAY);
		
		JLabel l2=new JLabel("Enter a tablename and select the column size to get started :)");
		l2.setBounds(90,1,500,30);
		l2.setFont(font3);
		l2.setForeground(Color.WHITE);
		p2.add(l2);
			
		JTextField t1=new JTextField();
		t1.setBounds(115,35,300,27);
		t1.setFont(font3);
		t1.setBackground(pp);
		t1.setCaretColor(Color.WHITE);
		t1.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
		t1.setForeground(Color.WHITE);
		p2.add(t1);
		
		SpinnerModel sm = new SpinnerNumberModel(1,1,999,1);
		JSpinner t2=new JSpinner(sm);
		t2.setBounds(422,35,130,27);
		t2.setFont(font3);
		t2.getEditor().getComponent(0).setBackground(pp);
		t2.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
		t2.getEditor().getComponent(0).setForeground(Color.WHITE);
		((DefaultEditor)t2.getEditor()).getTextField().setEditable(false);
		p2.add(t2);
		
		JLabel l21=new JLabel("Column Name");
		l21.setBounds(53,85,180,15);
		l21.setFont(font5);
		l21.setForeground(Color.WHITE);
		l21.setVisible(false);
		p2.add(l21);
		
		JLabel l22=new JLabel("Data Type");
		l22.setBounds(238,85,150,15);
		l22.setFont(font5);
		l22.setForeground(Color.WHITE);
		l22.setVisible(false);
		p2.add(l22);
		
		JLabel l23=new JLabel("Maxlength");
		l23.setBounds(392,85,100,15);
		l23.setFont(font5);
		l23.setForeground(Color.WHITE);
		l23.setVisible(false);
		p2.add(l23);
		
		JLabel l24=new JLabel("Default");
		l24.setBounds(495,85,130,15);
		l24.setFont(font5);
		l24.setForeground(Color.WHITE);
		l24.setVisible(false);
		p2.add(l24);
				
		MyButton b=new MyButton("➡ Continue to Next Step...");
		b.setBounds(140,75,383,30);
		b.setFont(font1);
		b.setForeground(Color.WHITE);
		b.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		b.setFocusPainted(false);
		b.setVisible(true);
		b.addActionListener(new ActionListener() 
		{
		    public void actionPerformed(ActionEvent e) 
		    {
		    	String tablename=t1.getText();
		    	String columnSize=((DefaultEditor)t2.getEditor()).getTextField().getText();
		    	if(!tablename.isEmpty() && Pattern.compile("^[A-Za-z0-9_@#&]*$").matcher(tablename.trim()).find())
		    	{
		    		l2.setText("Entered tablename & column size are valid, now complete each column details :)");
		    		l2.setBounds(20,1,650,30);
		    		t1.setBackground(uu);
		    		t1.setText(tablename+" ✔");
		    		t1.setEditable(false);
		    		((DefaultEditor)t2.getEditor()).getTextField().setBackground(uu);
		    		((DefaultEditor)t2.getEditor()).getTextField().setEnabled(false);
		    		colSize=Integer.parseInt(columnSize);
		    		((DefaultEditor)t2.getEditor()).getTextField().setText(columnSize+" ✔");
		    		t2.setUI(new BasicSpinnerUI(){
		    			protected Component createNextButton(){return null;}
		            	protected Component createPreviousButton(){return null;}
		        	});
		    		l21.setVisible(true);
		    		l22.setVisible(true);
		    		l23.setVisible(true);
		    		l24.setVisible(true);
		    		int i;
		    		columnNames=new Object[colSize];
		    		for(i=0; i<colSize; i++)
					{
						JPanel temp=new JPanel();
						columnNames[i]=temp;
						temp.setBounds(50,100+(i*35),580,30);
						temp.setLayout(null);
						temp.setBackground(uu);
					
						JTextField t11=new JTextField();
						t11.setBounds(4,4,180,23);
						t11.setFont(font4);
						t11.setBackground(pp);
						t11.setCaretColor(Color.WHITE);
						t11.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
						t11.setForeground(Color.WHITE);
						temp.add(t11);
					
						String country[]={"Select Datatype","• int","• decimal","• char","• varchar","• date","• time","• datetime"};        
						JComboBox<String> cb1=new JComboBox<String>(country);
				    	cb1.setFont(font4);
				    	cb1.setBounds(188,4,150,23);
						cb1.setFocusable(false);
						temp.add(cb1);
					
						SpinnerModel sm = new SpinnerNumberModel(10,1,999,1);
						JSpinner t22=new JSpinner(sm);
						t22.setBounds(342,4,100,23);
						t22.setFont(font4);
						t22.getEditor().getComponent(0).setBackground(pp);
						t22.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
						t22.getEditor().getComponent(0).setForeground(Color.WHITE);
						((DefaultEditor)t22.getEditor()).getTextField().setEditable(false);
						temp.add(t22);
					
						String V[]={"Null","Not Null"};        
						JComboBox<String> cb2=new JComboBox<String>(V);
				    	cb2.setFont(font4);
				    	cb2.setBounds(446,4,130,23);
				    	cb2.setPreferredSize(new Dimension(100,23));
						cb2.setFocusable(false);
						temp.add(cb2);
					
						temp.setVisible(true);
						p2.add(temp);
					}
		    		b.setVisible(false);
		    		
		    		JLabel l25=new JLabel("Note that: Maxlength is only applicable for datatypes such as char and varchar only.");
		    		l25.setBounds(53,100+(i-1)*35+30,500,15);
		    		l25.setFont(font5);
		    		l25.setForeground(Color.WHITE);
		    		l25.setVisible(true);
		    		p2.add(l25);
		    		
		    		MyButton b2=new MyButton("➡ Go Ahead and Create");
		    		b2.setBounds(325,100+(i-1)*35+60,270,30);
		    		b2.setFont(font1);
		    		b2.setForeground(Color.WHITE);
		    		b2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		    		b2.setFocusPainted(false);
					b2.setVisible(true);
					b2.addActionListener(new ActionListener() 
					{
						@SuppressWarnings("unchecked")
						public void actionPerformed(ActionEvent e) 
				    	{
							int i=0;
							StringBuilder q=new StringBuilder("CREATE TABLE ");
							q.append(tablename).append("(");
							for(i=0; i<colSize; i++)
							{
								String colName=((JTextField)((JPanel)columnNames[i]).getComponent(0)).getText();
								String datatype=((String)((JComboBox<String>)((JPanel)columnNames[i]).getComponent(1)).getSelectedItem()).substring(2);
								String maxlength=((DefaultEditor)((JSpinner)((JPanel)columnNames[i]).getComponent(2)).getEditor()).getTextField().getText();
								String constraint=(String)((JComboBox<String>)((JPanel)columnNames[i]).getComponent(3)).getSelectedItem();
								System.out.println(colName+datatype+maxlength+constraint);
								if(!colName.isEmpty() && Pattern.compile("^[A-Za-z0-9_@#&]*$").matcher(colName.trim()).find() && !datatype.equals("lect Datatype"))
								{
									q.append(colName).append(" ");
									if(datatype.equals("char") || datatype.equals("varchar"))
									{
										q.append(datatype).append("("+maxlength+")");
									}
									else if(datatype.equals("decimal"))
									{
										q.append(datatype).append("(38,2)");
									}
									else if(datatype.equals("datetime"))
									{
										if(db.active.equals(DatabaseTesting.Oracle))
										{
											q.append("timestamp");
										}
										else
										{
											q.append(datatype);
										}
									}
									else if(datatype.equals("time"))
									{
										if(db.active.equals(DatabaseTesting.Oracle))
										{
											q.append("varchar(8)");
										}
										else
										{
											q.append(datatype);
										}
									}
									else
									{
										q.append(datatype);
									}
									if(constraint.equals("Not Null"))
									{
										q.append(" Not Null");
									}
									q.append(",");
								}
								else
								{
									if(colName.isEmpty() || datatype.equals("lect Datatype"))
									{
										JOptionPane.showMessageDialog(p2,"Column Name & Datatype both are mandatory fields("+i+")!");
									}
									else 
									{
										JOptionPane.showMessageDialog(p2,"Enter a valid Column Name ("+i+")!");
									}
									break;
								}
							}
							if(i==colSize)
							{
								q.deleteCharAt(q.length()-1);
								q.append(")");
								System.out.println(q);
								try
								{
									db.createNew(q.toString());
									l21.setVisible(false);
									l22.setVisible(false);
									l23.setVisible(false);
									l24.setVisible(false);
									while(8<p2.getComponentCount())
									{
										p2.remove(8);
									}
									p2.revalidate();
									p2.repaint();
									l2.setText("Enter a tablename and select the column size to get started :)");
									l2.setBounds(90,1,500,30);
									t1.setBackground(pp);
						    		t1.setText("");
						    		t1.setEditable(true);
						    		((DefaultEditor)t2.getEditor()).getTextField().setBackground(pp);
						    		((DefaultEditor)t2.getEditor()).getTextField().setEnabled(true);
						    		((DefaultEditor)t2.getEditor()).getTextField().setText("1");
						    		t2.setUI(new BasicSpinnerUI());
									b.setVisible(true);
									JOptionPane.showMessageDialog(p2,"Table has been created successfully :)","Done ✔",JOptionPane.DEFAULT_OPTION);
								}
								catch(Exception p)
								{
									JOptionPane.showMessageDialog(p2,p.toString());
								}
							}
				    	}
					});
					p2.add(b2);	
					
					MyButton b3=new MyButton("↩ Go Back");
		    		b3.setBounds(85,100+(i-1)*35+60,225,30);
		    		b3.setFont(font1);
		    		b3.setForeground(Color.WHITE);
		    		b3.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		    		b3.setFocusPainted(false);
					b3.setVisible(true);
					b3.addActionListener(new ActionListener() 
					{
						public void actionPerformed(ActionEvent e) 
				    	{
							l21.setVisible(false);
							l22.setVisible(false);
							l23.setVisible(false);
							l24.setVisible(false);
							while(8<p2.getComponentCount())
							{
								p2.remove(8);
							}
							p2.revalidate();
							p2.repaint();
							l2.setText("Enter a tablename and select the column size to get started :)");
							l2.setBounds(90,1,500,30);
							t1.setBackground(pp);
				    		t1.setText(tablename);
				    		t1.setEditable(true);
				    		((DefaultEditor)t2.getEditor()).getTextField().setBackground(pp);
				    		((DefaultEditor)t2.getEditor()).getTextField().setEnabled(true);
				    		((DefaultEditor)t2.getEditor()).getTextField().setText(columnSize);
				    		t2.setUI(new BasicSpinnerUI());
							b.setVisible(true);
				    	}
					});
					p2.add(b3);
					
		    		p2.revalidate();
		    		p2.repaint();
		    	}
		    	else
		    	{
		    		JOptionPane.showMessageDialog(p2,"Enter a valid tablename!");
		    	}
		    }
		});
		p2.add(b);		
		p2.setLayout(null);
		tp.add("🔨 New",p2); 
	}
	public void insert()
	{
		JLabel l3=new JLabel("Enter a tablename and enter the value to insert to get started :)");
		l3.setFont(font3);
		l3.setForeground(Color.WHITE);
		
		JPanel p3=new JPanel();
		p3.setBackground(Color.DARK_GRAY);
		p3.add(l3);
		tp.add("📌 Insertion",p3); 	
	}
	public void update()
	{
		JLabel l4=new JLabel("Enter a tablename and enter the value to update to get started :)");
		l4.setFont(font3);
		l4.setForeground(Color.WHITE);
		
		JPanel p4=new JPanel();
		p4.setBackground(Color.DARK_GRAY);
		p4.add(l4);
		tp.add("🔧 Updation",p4);
	}
	public void delete()
	{
		JLabel l5=new JLabel("Enter a tablename and enter the value to delete to get started :)");
		l5.setFont(font3);
		l5.setForeground(Color.WHITE);
		
		JPanel p5=new JPanel();
		p5.setBackground(Color.DARK_GRAY);
		p5.add(l5);
		tp.add("✂ Deletion",p5);
	}
	public void sequences()
	{
		JLabel l6=new JLabel("Give min max and incremental values to get started :)");
		l6.setFont(font3);
		l6.setForeground(Color.WHITE);
		
		JPanel p6=new JPanel();
		p6.setBackground(Color.DARK_GRAY);
		p6.add(l6);
		tp.add("🎞 Sequences",p6);
	}
	public void views()
	{
		JLabel l7=new JLabel("Enter a tablename for the view to get started :)");
		l7.setFont(font3);
		l7.setForeground(Color.WHITE);
		
		JPanel p7=new JPanel();
		p7.setBackground(Color.DARK_GRAY);
		p7.add(l7);
		tp.add("👓 Views",p7);
	}
	public void display()
	{
		JLabel l8=new JLabel("Enter a tablename from which to display the values to get started :)");
		l8.setFont(font3);
		l8.setForeground(Color.WHITE);
		
		JPanel p8=new JPanel();
		p8.setBackground(Color.DARK_GRAY);
		p8.add(l8);
		tp.add("📊 Display",p8);
	}
	public void custom()
	{
		JLabel l9=new JLabel("Enter a custom command to get started :)");
		l9.setFont(font3);
		l9.setForeground(Color.WHITE);
		
		JPanel p9=new JPanel();
		p9.setBackground(Color.DARK_GRAY);
		p9.add(l9);
		tp.add("�? Custom",p9);
	}
	public void profile()
	{
		ImageIcon userimg = new ImageIcon("E:\\Guriqbal\\All\\Me\\AMAF5827.jpg");
		Image image = userimg.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH);
		userimg = new ImageIcon(image);
		BufferedImage bi = new BufferedImage(userimg.getIconWidth(),userimg.getIconHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.createGraphics();
		userimg.paintIcon(null, g, 0,0);
		g.dispose();
		bi = makeRoundedCorner(bi,50);
		userimg = new ImageIcon(bi);
		
		MyButton b=new MyButton("Logout");
		b.setBounds(100,100,150,40);
		b.setPreferredSize(new Dimension(150,45));
		b.setFont(font1);
		b.setForeground(Color.WHITE);
		b.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		b.setFocusPainted(false);
		b.setVisible(true);
		b.addActionListener(new ActionListener() 
		{
		    public void actionPerformed(ActionEvent e) 
		    {
		    	f.dispose();
		    	new DatabaseLogin();
		    }
		});
		
		JPanel p10=new JPanel();
		p10.setBackground(Color.DARK_GRAY);
		p10.add(b);
		tp.addTab("Guriqbal",userimg,p10);
	}
	public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
	    int w = image.getWidth();
	    int h = image.getHeight();
	    BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = output.createGraphics();
	    g2.setComposite(AlphaComposite.Src);
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setColor(Color.WHITE);
	    g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));
	    g2.setComposite(AlphaComposite.SrcAtop);
	    g2.drawImage(image, 0, 0, null);
	    g2.dispose();
	    return output;
	}
	public static void main(String[] args) throws Exception
	{
		new DatabaseGUIOperations(new DatabaseTesting(DatabaseTesting.Oracle,DatabaseTesting.OracleDriver,"GURIQBAL","Prince@2241"));
	}
}