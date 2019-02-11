package gr.hua.dit.feeding_service_app.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	/*
	 * Gets filePath as String. If file is found returns a byte array of the file else returns null
	 * 
	 * @param String
	 * 
	 * @return byte array of file or null
	 */
	public byte[] fetchFile(String filePath);

	public String store(MultipartFile doc);

	/**
	 * stores given document to filesystem organised based on
	 * application id and it's type
	 * the document is stored in DOCS_PATH/{id}/{doc_type}.pdf
	 * @param appl_id
	 * @param doc_type
	 * @param doc
	 * @return the generated part of the path that the document is saved (/{id}/{doc_type}.pdf)
	 * @throws IOException 
	 */
	public String storeAccDoc(Integer appl_id, String doc_type, MultipartFile doc);
	
}
