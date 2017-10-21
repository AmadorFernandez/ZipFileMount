import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Log {

	private File fileLog;
	private String name;
	
	public String getPath() {
		
		return fileLog.getAbsolutePath();
	}
	
	public Log(String pathLog, Date date) {
	
		Calendar calendar = Calendar.getInstance();
		this.name = "operation_log_"+String.valueOf(calendar.get(Calendar.YEAR))+String.valueOf(calendar.get(Calendar.MONTH+1))+
		String.valueOf(calendar.get(Calendar.DAY_OF_MONTH))+String.valueOf(calendar.get(Calendar.HOUR))+String.valueOf(calendar.get(Calendar.MINUTE))+String.valueOf(calendar.get(Calendar.SECOND))+".txt";
		this.fileLog = new File(pathLog);
		this.fileLog.mkdirs();
		this.fileLog = new File(pathLog,name);
	}
	
	public boolean writeInLog(String text) {
		
		boolean result = true;
		BufferedWriter bw = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		PrintWriter writer = null;
		
		try {
			
			bw = new BufferedWriter(new FileWriter(fileLog, true));
			writer = new PrintWriter(bw);
			writer.println((dateFormat.format(new Date())+" --> "+text));
			writer.flush();
		} catch (IOException e) {
			result = false;
			
		} finally {
			
			if(writer != null) {
			
				writer.close();
			}
		}
		
		return result;
	}
	
}
