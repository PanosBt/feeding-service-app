package gr.hua.dit.feeding_service_app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "accompanying_document")
public class AccompanyingDocument {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "doc_id")
	private int doc_id;
	
	@ManyToOne
	@JoinColumn(name = "appl_id", nullable = false)
	private Application application;
	
	@Column(name = "doc_type", nullable = false)
	private String doc_type;
	
	@Column(name = "file_path", nullable = false)
	private String file_path;

}
