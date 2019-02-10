package gr.hua.dit.feeding_service_app.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

	/*
	 * Gets filePath as String. If file is found returns a byte array of the file else returns null
	 * 
	 * @param String
	 * 
	 * @return byte array of file or null
	 */
	@Override
	public byte[] fetchFile(String filePath) {
		
		File file = new File(filePath);
		byte[] contents = null;

		try (FileInputStream in = new FileInputStream(file)) {
			contents = IOUtils.toByteArray(in);
			return contents;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
