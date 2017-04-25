import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class MainFrame extends JFrame{
	
	MainFrame(){
		super("Suduko Solver");
		setLayout(new FlowLayout());
		
		JLabel err = new JLabel("");
		
		SudukoGrid sg = new SudukoGrid();
		Dimension dm = new Dimension(271,271);
		sg.setMinimumSize(dm);
		sg.setPreferredSize(dm);
		sg.setMaximumSize(dm);
		
		JButton solve = new JButton("Solve");
		solve.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!sg.Solvesduko())
					err.setText("This Puzzle Cannot be Solved");
			
			}
			
		});
			
		add(solve);
		
		JButton resetg = new JButton("Reset");
		resetg.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				sg.resetgrid();
				err.setText("");
				
			}
			
		});
		
		add(resetg);
		
		add(sg);
		
		JPanel numgrid = new JPanel();
		numgrid.setLayout(new GridLayout(4,1));
		
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1,3));
		for(int i=1;i<=9;i++){
			
			JButton b = new JButton(""+i);
			
			b.addActionListener(
					
					new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent arg0) {
							sg.updategrid(Integer.parseInt(b.getText()));
							
						}
						
					}
					
					);
			
			p.add(b);
			if(i%3==0){
				numgrid.add(p);
				p=new JPanel();
				p.setLayout(new GridLayout(1,3));
			}
			
			
		}
		JButton nullify = new JButton("Null");
		nullify.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				sg.updategrid(0);
				}
			
		});
		numgrid.add(nullify);
		
		add(numgrid);
		add(err);
		
		
		/*
		getContentPane().addKeyListener(
				new KeyListener(){

					@Override
					public void keyPressed(KeyEvent e) {
						int k = e.getKeyCode();
						if(k>=48 &&k<=57)
							sg.updategrid(Integer.parseInt(KeyEvent.getKeyText(k)));

					}

					@Override
					public void keyReleased(KeyEvent e) {
						
					}

					@Override
					public void keyTyped(KeyEvent e) {
						
					}
					
				}
				);
		getContentPane().setFocusable(true);
	
	*/
		
		Action actionListener = new AbstractAction() {
		      public void actionPerformed(ActionEvent a) {
		    	  sg.updategrid(Integer.parseInt(a.getActionCommand()));
		      }
		    };
		    KeyStroke stroke;
		    InputMap inputMap;
		    
		    for(int i=0;i<=9;i++){
		    	stroke = KeyStroke.getKeyStroke(""+i);

		    	inputMap = sg.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		    	inputMap.put(stroke, "OPEN");
		    	sg.getActionMap().put("OPEN", actionListener);
		    
		    }
		    
		    Action downaal = new AbstractAction() {
			      public void actionPerformed(ActionEvent a) {
			    	  sg.changepos(1);
			      }
			};
			    
			stroke = KeyStroke.getKeyStroke("DOWN");
			inputMap = sg.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		    inputMap.put(stroke, "downaal");
		    sg.getActionMap().put("downaal", downaal);
			
		    downaal = new AbstractAction() {
			      public void actionPerformed(ActionEvent a) {
			    	  sg.changepos(2);
			     }
			};
			   
			stroke = KeyStroke.getKeyStroke("UP");
			inputMap = sg.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
			inputMap.put(stroke, "upaal");
		    sg.getActionMap().put("upaal", downaal);

		    

		    downaal = new AbstractAction() {
			      public void actionPerformed(ActionEvent a) {
			    	  sg.changepos(3);
			    }
			};
			   
			stroke = KeyStroke.getKeyStroke("RIGHT");
			inputMap = sg.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
			inputMap.put(stroke, "rightaal");
		    sg.getActionMap().put("rightaal", downaal);

		    

		    downaal = new AbstractAction() {
			      public void actionPerformed(ActionEvent a) {
			    	  sg.changepos(4);
			      }
			};
			   
			stroke = KeyStroke.getKeyStroke("LEFT");
			inputMap = sg.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
			inputMap.put(stroke, "leftaal");
		    sg.getActionMap().put("leftaal", downaal);

		    
	}	
	
	public static void main(String arg[]){
		MainFrame gf = new MainFrame();
		
		
		    
		
		gf.setSize(290,470);
		gf.setVisible(true);
		gf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gf.setResizable(false);
	}

}
