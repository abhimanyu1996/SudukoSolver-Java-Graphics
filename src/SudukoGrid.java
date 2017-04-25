import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class SudukoGrid extends JPanel{
	
	int grid[][] = new int[9][9];
	int selected[] = {-1,-1};
	
	SudukoGrid(){
		
		addMouseListener(
				new MouseListener(){

					@Override
					public void mouseClicked(MouseEvent e) {
						
						int x = e.getX()/30;
						int y = e.getY()/30;
						
						selected[0]=x;
						selected[1]=y;
						
						repaint();
					}

					@Override
					public void mouseEntered(MouseEvent arg0) {}

					@Override
					public void mouseExited(MouseEvent arg0) {}

					@Override
					public void mousePressed(MouseEvent arg0) {}

					@Override
					public void mouseReleased(MouseEvent arg0) {}
					
				}
			);
		
		
	}
	
	
	public void updategrid(int i){
		
		if(selected[0] != -1 && selected[1] != -1){
			if(checknum(selected[1],selected[0],i))
				grid[selected[1]][selected[0]] = i;
			if(i==0)
				grid[selected[1]][selected[0]] = 0;
		}
		
		repaint();
	}

	public boolean Solvesduko(){
		boolean t = backtrackingalgo();
		repaint();
		
		return t;
	}
	
	boolean backtrackingalgo(){
		int row=0,col=0;
		boolean flag =false;
		
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(grid[i][j]==0){
					row = i;
					col =j;
					flag = true;
					break;
				}
			}
			
			if(flag== true)
				break;
		}
		
		if(flag==false)
			return true;
		
		for(int num =1 ; num<=9;num++){
			//System.out.println(checknum(row,col,num));
			if(checknum(row,col,num)){
				grid[row][col] = num;
				
				if(backtrackingalgo())
					return true;
				
				grid[row][col]=0;
			}
		}
		
		return false;
	}
	
	private boolean checknum(int x,int y,int num){
		
		for(int i=0;i<9;i++){
			if((grid[x][i]==num))
				return false;
			
		}
		
		for(int i=0;i<9;i++){
			if((grid[i][y]==num) )
				return false;
			
			}
		
		int nx = x/3;
		int ny = y/3;
		for(int i=3*nx;i<3*nx+3;i++){
			for(int j=3*ny;j<(3*ny+3);j++){
				if(grid[i][j]==num)
					return false;
			}
		}
		
		return true;
	}
	
	public void resetgrid(){
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				grid[i][j]=0;
			}
			
		}
		
		repaint();
	}
	
	public void changepos(int i){
		
		switch(i){
			case 1:
				if(selected[1]<8)
					selected[1]++;
				break;
			
			case 2:
				if(selected[1]>0)
					selected[1]--;
				break;
			
			case 3:
				if(selected[0]<8)
					selected[0]++;
				break;
			
			case 4:
				if(selected[0]>0)
					selected[0]--;
				break;
			
			default:
				break;
					
		
		
		}
		
		repaint();
	}
	
	
	
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, 270, 270);
		
		if(selected[0] != -1 && selected[1]!=-1){
			g2d.setColor(Color.YELLOW);
			g2d.fillRect(30*selected[0], 30*selected[1], 30, 30);
		}
		
		
		g2d.setColor(Color.BLACK);
		
		for(int i=0;i<=9;i++){
			if(i%3==0)
				g2d.setStroke(new BasicStroke(3));
			else
				g2d.setStroke(new BasicStroke(1));
			g2d.drawLine(0, 30*i, 270, 30*i);
			g2d.drawLine( 30*i,0, 30*i,270);
		}
		
		Font myFont = new Font ("Courier New", 1, 20);
		g2d.setFont(myFont);
		
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(grid[i][j] != 0)
					g2d.drawString(""+grid[i][j], 30*j+10,30*i+22);
			}
		}
		
		
		
	}
}
