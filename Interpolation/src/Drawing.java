import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;


public class Drawing extends JFrame {

	private DrawingPanel drawingpanel;
	private int current_point;
	
	public Drawing() {
		setResizable(false);
		setBounds(0,0,1920,1080);
		drawingpanel = new DrawingPanel();
		setContentPane(drawingpanel);
		drawingpanel.repaint();
	}
	
	public void Input_data(int input_start_point) {
		current_point = input_start_point;
	}
	
	public class DrawingPanel extends JPanel {
		int flag = 0;
		
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			g.setColor(Color.BLACK);
			g.drawString("Source File : " + Data.file_name, 50, this.getHeight()-30);
			
			g.drawLine(0, this.getHeight()/4, this.getWidth(), this.getHeight()/4);
			g.drawLine(0, this.getHeight()/4*3, this.getWidth(), this.getHeight()/4*3);
			g.drawLine(10, 0, 10, this.getHeight());
			
			//30, this.getHeight()/4*3
			
			for(int count = 1; count < Data.nor_pupildata.size(); count++) {
				int x1 = Math.round((float)(count - 1) * 1900 /((float) Data.nor_pupildata.size()) + 10);
				float y1 = this.getHeight()/4 - (Float.valueOf(Data.nor_pupildata.get(count-1).left))*30;
				
				int x2 = Math.round(((float)count) * 1900 /((float) Data.nor_pupildata.size()) + 10);
				float y2 = this.getHeight()/4 - (Float.valueOf(Data.nor_pupildata.get(count).left))*30;
								
				
				if(flag == 1) {
					g.setColor(Color.RED);
				} else {
					g.setColor(Color.BLUE);
				}
				g2.draw(new Line2D.Float(x1, y1, x2, y2));
				
				g.setColor(Color.RED);
				if(count == 1) {
					g.drawString("Left Pupil "+ String.valueOf(Data.nor_pupildata.get(count-1).left), 20, this.getHeight()/4-200);
				}
								
				int x3 = Math.round((float)(count - 1) * 1900 /((float) Data.nor_pupildata.size()) + 10);
				float y3 = this.getHeight()/4*3 - (Float.valueOf(Data.nor_pupildata.get(count-1).right))*30;
				
				int x4 = Math.round(((float)count) * 1900 /((float) Data.nor_pupildata.size()) + 10);
				float y4 = this.getHeight()/4*3 - (Float.valueOf(Data.nor_pupildata.get(count).right))*30;
								
				
				if(flag == 1) {
					g.setColor(Color.RED);
				} else {
					g.setColor(Color.BLUE);
				}
				g2.draw(new Line2D.Float(x3, y3, x4, y4));
				
				g.setColor(Color.RED);
				if(count == 1) {
					g.drawString("Right Pupil "+ String.valueOf(Data.nor_pupildata.get(count-1).right), 20, this.getHeight()/4*3-200);
				}
				if(Data.nor_pupildata.get(count).timestamp.equalsIgnoreCase(Data.log_video_time_start)) {
					g.drawLine(x4, 0, x4, this.getHeight()/4*3);		
					flag = 1;
				}
				if(Data.nor_pupildata.get(count).timestamp.equalsIgnoreCase(Data.log_video_time_end)) {
					g.drawLine(x4, 0, x4, this.getHeight()/4*3);
					flag = 0;
				}
			}
		}
	}
}
