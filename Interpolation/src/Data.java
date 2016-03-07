import java.util.ArrayList;


public class Data {
	public static ArrayList<String> original = new ArrayList<String>();
	public static ArrayList<String> log_original = new ArrayList<String>();
	public static ArrayList<Pupil> pupildata = new ArrayList<Pupil>();
	public static ArrayList<Pupil> pupildata_temp = new ArrayList<Pupil>();
	public static ArrayList<Npupil> nor_pupildata = new ArrayList<Npupil>();
	public static ArrayList<String> log_video_time = new ArrayList<String>();
	public static ArrayList<Pupil> interpolate_L = new ArrayList<Pupil>();
	public static ArrayList<PointL> inter_calcul_L = new ArrayList<PointL>();
	public static ArrayList<PointL> inter_result_L = new ArrayList<PointL>();
	
	public static ArrayList<Pupil> interpolate_R = new ArrayList<Pupil>();
	public static ArrayList<PointR> inter_result_R = new ArrayList<PointR>();
	public static ArrayList<PointR> inter_calcul_R = new ArrayList<PointR>();
	
	public static String file_name;
	public static int left_loss;
	public static int right_loss;
	public static String log_timestamp;
	
	public static float left_nor_data_mean;
	public static float right_nor_data_mean;
	
	
	public static float pupil_mean_left;
	public static float pupil_sd_left;
	public static float pupil_mean_right;
	public static float pupil_sd_right;
	
	public static String log_video_time_start;
	public static String log_video_time_end;
	
	public static int currentPoint2 = 0;
	public static Drawing DF = new Drawing();
	public static String[] location;
}