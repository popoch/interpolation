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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Drawing2 extends JFrame {

	private DrawingPanel drawingpanel;
	private int current_point;
	
	public Drawing2() {
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
			
			for(int count = 1; count < Data.window_normal_inter_data.size(); count++) {
				int x1 = Math.round((float)(count - 1) * 1890 /((float) Data.window_normal_inter_data.size()) + 20);
				float y1 = this.getHeight()/4 - (Float.valueOf(Data.window_normal_inter_data.get(count-1).left))*20;
				
				int x2 = Math.round(((float)count) * 1890 /((float) Data.window_normal_inter_data.size()) + 20);
				float y2 = this.getHeight()/4 - (Float.valueOf(Data.window_normal_inter_data.get(count).left))*20;
								
				
				if(flag == 1) {
					g.setColor(Color.RED);
				} else {
					g.setColor(Color.BLUE);
				}
				g2.draw(new Line2D.Float(x1, y1, x2, y2));
				
				g.setColor(Color.RED);
				if(count == 1) {
					g.drawString("Mean of Left Pupil : "+ String.valueOf(Data.window_pupil_mean_left), 25, this.getHeight()/4-240);
					g.drawString("Window Size : "+ String.valueOf(Data.window_size), 25, this.getHeight()/4-220);
				}
								
				int x3 = Math.round((float)(count - 1) * 1890 /((float) Data.window_normal_inter_data.size()) + 20);
				float y3 = this.getHeight()/4*3 - (Float.valueOf(Data.window_normal_inter_data.get(count-1).right))*20;
				
				int x4 = Math.round(((float)count) * 1890 /((float) Data.window_normal_inter_data.size()) + 20);
				float y4 = this.getHeight()/4*3 - (Float.valueOf(Data.window_normal_inter_data.get(count).right))*20;
								
				
				if(flag == 1) {
					g.setColor(Color.RED);
				} else {
					g.setColor(Color.BLUE);
				}
				g2.draw(new Line2D.Float(x3, y3, x4, y4));
				
				g.setColor(Color.RED);
				if(count == 1) {
					g.drawString("Mean of Right Pupil : "+ String.valueOf(Data.window_pupil_mean_right), 25, this.getHeight()/4*3-240);
					g.drawString("Window Size : "+ String.valueOf(Data.window_size), 25, this.getHeight()/4*3-220);
				}
				
				if(count == Data.window_normal_inter_data.size()-1) {
					g.drawLine(x4, 0, x4, this.getHeight());
				}
				
				g.setColor(Color.BLACK);
				if() {
					String caltime2 = null;
					String[] temp_real = String.valueOf(Data.ac_log_video_recording_start_time).split(" ", -1);
					String[] starttime = temp_real[1].split("\\.", -1);
					String time1 = starttime[0];
					String[] temp_real2 = String.valueOf(Data.nor_pupildata.get(count).timestamp).split(" ", -1);
					String[] tmptime = temp_real2[1].split("\\.", -1);
					String time2 = tmptime[0];
					int difh = 0;
					int difm = 0;
					int difs = 0;
					
					SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
					Date date1;
					Date date2;
					try {
						date1 = format.parse(time1);
						date2 = format.parse(time2);
						long difference = date2.getTime() - date1.getTime(); 

						long diffSeconds = difference / 1000 % 60;
						long diffMinutes = difference / (60 * 1000) % 60;
						long diffHours = difference / (60 * 60 * 1000) % 24;

						difh = (int)diffHours;
						difm = (int)diffMinutes;
						difs = (int)diffSeconds;
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					caltime2 = String.valueOf(difh + ":" + difm + ":" + difs);
				}
				
			}
		}
	}
}
