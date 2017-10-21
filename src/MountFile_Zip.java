import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class MountFile_Zip {

	
	private FileInputStream[] files;
	private ZipEntry entryFile = null;
	public static final String RESULT_OK = "OK";
	public static final String RESULT_KO = "ERROR: Te path contain one file.";
		
	public MountFile_Zip(String[] zipFiles) throws FileNotFoundException {
		
		loadStreams(zipFiles);
	} 
	
	private void loadStreams(String[] streams) throws FileNotFoundException {
		
		files = new FileInputStream[streams.length];
		
		for (int i = 0; i < streams.length; i++) {
			
				files[i] = new FileInputStream(streams[i]);
		}
	}
	
	public String mountFile(String pathOuput) {
		
		String result = null;
		String plataformSeparator = File.separator;
		ZipInputStream zipInputStream = new ZipInputStream(new SequenceInputStream(Collections.enumeration(Arrays.asList(files))));
		ZipEntry entryFile = null;
		FileOutputStream fo = null;
		int len = 0;
		final int BUFFER_SIZE = 1024;
		byte[] buffer = new byte[BUFFER_SIZE];
		BufferedOutputStream boStream = null;
		Path path = Paths.get(pathOuput);
		File file = path.toFile();
		
		if(file.isDirectory()) {
		
		try {
			
			if((entryFile = zipInputStream.getNextEntry()) != null) {
				
				
				fo = new FileOutputStream(pathOuput+plataformSeparator+entryFile.getName());
				boStream = new BufferedOutputStream(fo, BUFFER_SIZE);
				
				while ((len = zipInputStream.read(buffer, 0, BUFFER_SIZE)) != -1) {
					
					boStream.write(buffer, 0, len);
				}
				
				boStream.flush();
				result = RESULT_OK;
					
			}
			
		} catch (Exception e) {
			
			result = e.getMessage();
			
		} finally {
			
			try {
				boStream.close();
				zipInputStream.close();
				fo.close();	
			} catch (IOException e) {
				
				result = e.getMessage();
			}
		}
		
		}else {
			
			result = RESULT_KO;
		}
		
		return result;
		
	}
	
}
