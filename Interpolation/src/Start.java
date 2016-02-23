import java.awt.EventQueue;

public class Start {
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			//main things
			public void run() {
				
				try{
					Interpolate windows = new Interpolate();
					windows.frame.setVisible(true);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
