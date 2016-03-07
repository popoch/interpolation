import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
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
		int mdraw = 0;
		int sdraw = 0;
		int pdraw = 0;
		protected void paintComponent(Graphics g) {

			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			g.setColor(Color.BLACK);
			g.drawString("Source File 1 : " + Data.location[5] + "_" + Data.file_name, 50, this.getHeight()-30);
			g.drawString("Source File 2 : " + Data.location[5] + "_" + "systemlog.csv", 50, this.getHeight()-15);
			
			g.drawLine(0, this.getHeight()/4, this.getWidth(), this.getHeight()/4);
			g.drawLine(0, this.getHeight()/4*3, this.getWidth(), this.getHeight()/4*3);
			g.drawLine(20, 0, 20, this.getHeight());
			g.drawString("Arbitrary Unit", this.getWidth()-200, this.getHeight()/4 - 240);
			g.drawString("Name : " + Data.location[5], this.getWidth()-200, this.getHeight()/4 - 220);
			g.drawString("Gender : " + Data.location[4], this.getWidth()-200, this.getHeight()/4 - 200);
			
			g.setColor(Color.LIGHT_GRAY);
			for(int i = -10; i <= 10; i++) {
				if(i == 0) {
					g.drawString(String.valueOf(i), 0, this.getHeight()/4 - i*20);
					g.drawString(String.valueOf(i), 0, this.getHeight()/4*3 - i*20);
				} else {
					g.drawString(String.valueOf(i), 0, this.getHeight()/4 - i*20);
					g.drawString(String.valueOf(i), 0, this.getHeight()/4*3 - i*20);
					g.drawLine(0, this.getHeight()/4 - i*20, this.getWidth(), this.getHeight()/4 - i*20);
					g.drawLine(0, this.getHeight()/4*3 - i*20, this.getWidth(), this.getHeight()/4*3 - i*20);
				}
			}
			
			//30, this.getHeight()/4*3
			
			for(int count = 1; count < Data.nor_pupildata.size(); count++) {
				int x1 = Math.round((float)(count - 1) * 1890 /((float) Data.nor_pupildata.size()) + 20);
				float y1 = this.getHeight()/4 - (Float.valueOf(Data.nor_pupildata.get(count-1).left))*20;
				
				int x2 = Math.round(((float)count) * 1890 /((float) Data.nor_pupildata.size()) + 20);
				float y2 = this.getHeight()/4 - (Float.valueOf(Data.nor_pupildata.get(count).left))*20;
								
				
				if(flag == 1) {
					g.setColor(Color.RED);
				} else {
					g.setColor(Color.BLUE);
				}
				g2.draw(new Line2D.Float(x1, y1, x2, y2));
				
				g.setColor(Color.RED);
				if(count == 1) {
					g.drawString("Mean of Left Pupil : "+ String.valueOf(Data.pupil_mean_left), 25, this.getHeight()/4-240);
					g.drawString("Mean of Normalized Left Pupil : "+ String.valueOf(Data.left_nor_data_mean), 25, this.getHeight()/4-220);
					g.drawString("Left Data Loss Rate (Overall) : "+ String.valueOf(Data.left_loss_rate) + "%", 25, this.getHeight()/4-200);
					g.drawString("Left Data Loss Rate (Video) : "+ String.valueOf(Data.left_data_loss_rate_during_video_play) + "%", 25, this.getHeight()/4-180);
				}
								
				int x3 = Math.round((float)(count - 1) * 1890 /((float) Data.nor_pupildata.size()) + 20);
				float y3 = this.getHeight()/4*3 - (Float.valueOf(Data.nor_pupildata.get(count-1).right))*20;
				
				int x4 = Math.round(((float)count) * 1890 /((float) Data.nor_pupildata.size()) + 20);
				float y4 = this.getHeight()/4*3 - (Float.valueOf(Data.nor_pupildata.get(count).right))*20;
								
				
				if(flag == 1) {
					g.setColor(Color.RED);
				} else {
					g.setColor(Color.BLUE);
				}
				g2.draw(new Line2D.Float(x3, y3, x4, y4));
				
				g.setColor(Color.RED);
				if(count == 1) {
					g.drawString("Mean of Right Pupil : "+ String.valueOf(Data.pupil_mean_right), 25, this.getHeight()/4*3-240);
					g.drawString("Mean of Normalized Right Pupil : "+ String.valueOf(Data.right_nor_data_mean), 25, this.getHeight()/4*3-220);
					g.drawString("Right Data Loss Rate (Overall) : "+ String.valueOf(Data.right_loss_rate) + "%", 25, this.getHeight()/4*3-200);
					g.drawString("Right Data Loss Rate (Video) : "+ String.valueOf(Data.right_data_loss_rate_during_video_play) + "%", 25, this.getHeight()/4*3-180);
				}
				if(Data.nor_pupildata.get(count).timestamp.equalsIgnoreCase(Data.log_video_time_start)) {
					g.drawLine(x4, 0, x4, this.getHeight());
					g.drawString("Video Start", x4+5, this.getHeight()/4 - 200);
					
					String time_temp[] = Data.nor_pupildata.get(count).timestamp.split(":|\\.", -1);
					String time_text_start = String.valueOf(time_temp[1])+"min "+String.valueOf(time_temp[2])+"sec "+String.valueOf(time_temp[3]);
					g.drawString(time_text_start, x4+5, this.getHeight()/4 - 180);
					flag = 1;
					
					Data.time_check = String.valueOf(Data.nor_pupildata.get(count).timestamp).split(":|\\.", -1);
					
				}
				if(Data.nor_pupildata.get(count).timestamp.equalsIgnoreCase(Data.log_video_time_end)) {
					g.drawLine(x4, 0, x4, this.getHeight());
					g.drawString("Video End", x4+5, this.getHeight()/4 - 200);
					
					String time_temp[] = Data.nor_pupildata.get(count).timestamp.split(":|\\.", -1);
					String time_text_end = String.valueOf(time_temp[1])+"min "+String.valueOf(time_temp[2])+"sec "+String.valueOf(time_temp[3]);
					g.drawString(time_text_end, x4+5, this.getHeight()/4 - 180);
					flag = 0;
				}
				if(count == Data.nor_pupildata.size()-1) {
					g.drawLine(x4, 0, x4, this.getHeight());
				}
				g.setColor(Color.BLACK);
				
				if(flag == 1) {
					Data.temp_time_check = String.valueOf(Data.nor_pupildata.get(count).timestamp).split(":|\\.", -1);
					Data.min = Data.temp_time_check[1];
					Data.sec = Data.temp_time_check[2];
					Data.point_sec = Data.temp_time_check[3];
					
					if(Integer.valueOf(Data.min) == Integer.valueOf(Data.time_check[1])+1 && mdraw == 0) {
						g2.draw(new Line2D.Float(x2, y2, x4, y4));
						g.drawString(Data.min + "min", x2+5, this.getHeight()/4*2);
						g.drawString("Left : " + String.valueOf(Data.nor_pupildata.get(count).left), x2+5, this.getHeight()/4*2 - 40);
						g.drawString("Right : " + String.valueOf(Data.nor_pupildata.get(count).right), x2+5, this.getHeight()/4*2 + 40);
						mdraw++;
					} else if(Integer.valueOf(Data.min) == Integer.valueOf(Data.time_check[1])+2 && mdraw == 1) {
						g2.draw(new Line2D.Float(x2, y2, x4, y4));
						g.drawString(Data.min + "min", x2+5, this.getHeight()/4*2);
						g.drawString(String.valueOf(Data.nor_pupildata.get(count).left), x2+5, this.getHeight()/4*2 - 40);
						g.drawString(String.valueOf(Data.nor_pupildata.get(count).right), x2+5, this.getHeight()/4*2 + 40);
						mdraw++;
					} else if(Integer.valueOf(Data.min) == Integer.valueOf(Data.time_check[1])+1 && Integer.valueOf(Data.sec) == 30 && sdraw == 0) {
						g2.draw(new Line2D.Float(x2, y2, x4, y4));
						g.drawString(Data.sec + "sec", x2+5, this.getHeight()/4*2);
						g.drawString(String.valueOf(Data.nor_pupildata.get(count).left), x2+5, this.getHeight()/4*2 - 40);
						g.drawString(String.valueOf(Data.nor_pupildata.get(count).right), x2+5, this.getHeight()/4*2 + 40);
						sdraw++;
					} else if(Integer.valueOf(Data.min) == Integer.valueOf(Data.time_check[1])+2 && Integer.valueOf(Data.sec) == 30 && sdraw == 1) {
						g2.draw(new Line2D.Float(x2, y2, x4, y4));
						g.drawString(Data.sec + "sec", x2+5, this.getHeight()/4*2);
						g.drawString(String.valueOf(Data.nor_pupildata.get(count).left), x2+5, this.getHeight()/4*2 - 40);
						g.drawString(String.valueOf(Data.nor_pupildata.get(count).right), x2+5, this.getHeight()/4*2 + 40);
						sdraw++;
					}
				}
			}
		}
	}
}
