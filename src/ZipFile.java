import java.io.FileNotFoundException;
import java.util.Date;

public class ZipFile {

	
	public static void main(String[] args) {
		
		MountFile_Zip mountFile_Zip = null;
		String result = null;
		Log log = new Log("./logs", new Date());
		
		
		if(args.length > 0) {
			
			log.writeInLog("Iniciando montaje de archivo");
			
			try {
				mountFile_Zip = new MountFile_Zip(args);
				result = mountFile_Zip.mountFile("C:\\Users\\Amador\\Desktop\\");
				
				switch (result) {
				case MountFile_Zip.RESULT_OK:
					log.writeInLog("Montaje de archivos correcto");
					break;
				default:
					printErrorMessage(log.getPath());
					log.writeInLog(result);
					break;
					
				}
				
			} catch (FileNotFoundException e) {
				
				printErrorMessage(log.getPath());
				log.writeInLog(e.fillInStackTrace().getMessage());
				
			} catch (Exception e) {
				printErrorMessage(log.getPath());
				log.writeInLog(e.fillInStackTrace().getMessage());
				
			}
			
		}else {
			
			printErrorMessage(log.getPath());
			log.writeInLog("No se indicarón las rutas a los archivos en los argumentos de entrada");
		}

	
	}
	
	public static void printErrorMessage(String logName) {
		
		System.err.println("Error revisar log "+logName);
				
	}

}
