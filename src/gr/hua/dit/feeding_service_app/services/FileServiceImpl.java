package gr.hua.dit.feeding_service_app.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {
	
	public static String DOCS_PATH = System.getProperty("user.home") + "/feed/documents/";
//	public static String DOCS_PATH = "/media/panos/BACK UP/feed/documents";

	@Override
	public byte[] fetchFile(String filePath) {
		
		byte[] contents = null;
		Path path = Paths.get(DOCS_PATH, filePath);
		try (InputStream in = Files.newInputStream(path, StandardOpenOption.READ)) {
			contents = IOUtils.toByteArray(in);
			return contents;
		} catch (IOException e) {
			e.printStackTrace();
			return contents;
		}
	}

	@Override
	public String store(MultipartFile doc) {
		
		return null;
	}

	@Override
	public String storeAccDoc(Integer id, String doc_type, MultipartFile doc){
		// generate path to save doc in DOCS_PATH/{id}
		Path path = Paths.get(DOCS_PATH, id.toString())
				.toAbsolutePath()
				.normalize();

		// create missing directories (at least /{id})
		try {
			Files.createDirectories(path);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		// resolve the path against the fileName to prepare the final path to save the file
		Path targetPath = path.resolve(doc_type + ".pdf");
		
		try {
			Files.copy(doc.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		// return only generated path (/{id}/{doc_type}.pdf)
		return StringUtils.removeStart(targetPath.toString(), DOCS_PATH);
	}
}




























