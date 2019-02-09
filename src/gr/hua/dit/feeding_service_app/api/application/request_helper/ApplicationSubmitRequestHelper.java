package gr.hua.dit.feeding_service_app.api.application.request_helper;

public class ApplicationSubmitRequestHelper {

	private Integer student_id;
	private Integer familyIncome;
	private Integer num_siblings;
	private String origin_city;
	private Boolean mother_employeed;
	private Boolean father_employeed;

	public ApplicationSubmitRequestHelper() {
	}

	public ApplicationSubmitRequestHelper(Integer student_id, Integer familyIncome, Integer num_siblings,
			String origin_city, Boolean mother_employeed, Boolean father_employeed) {
		this.student_id = student_id;
		this.familyIncome = familyIncome;
		this.num_siblings = num_siblings;
		this.origin_city = origin_city;
		this.mother_employeed = mother_employeed;
		this.father_employeed = father_employeed;
	}

	public Integer getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}

	public Integer getFamilyIncome() {
		return familyIncome;
	}

	public void setFamilyIncome(Integer familyIncome) {
		this.familyIncome = familyIncome;
	}

	public Integer getNum_siblings() {
		return num_siblings;
	}

	public void setNum_siblings(Integer num_siblings) {
		this.num_siblings = num_siblings;
	}

	public String getOrigin_city() {
		return origin_city;
	}

	public void setOrigin_city(String origin_city) {
		this.origin_city = origin_city;
	}

	public Boolean getMother_employeed() {
		return mother_employeed;
	}

	public void setMother_employeed(Boolean mother_employeed) {
		this.mother_employeed = mother_employeed;
	}

	public Boolean getFather_employeed() {
		return father_employeed;
	}

	public void setFather_employeed(Boolean father_employeed) {
		this.father_employeed = father_employeed;
	}

}
