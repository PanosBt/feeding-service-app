package gr.hua.dit.feeding_service_app.api.student.response_helper;

public class ApplicationStatusResponse {

	private Boolean submited;
	private Boolean checked;
	private Integer rank;
	private Integer score;
	private Integer studentLimit;
	private Boolean feed_qualified;

	public ApplicationStatusResponse() {
	}

	public ApplicationStatusResponse(Boolean submited, Boolean checked, Integer rank, Integer score,
			Integer studentLimit, Boolean feed_qualified) {
		super();
		this.submited = submited;
		this.checked = checked;
		this.rank = rank;
		this.score = score;
		this.studentLimit = studentLimit;
		this.feed_qualified = feed_qualified;
	}

	public Boolean getSubmited() {
		return submited;
	}

	public void setSubmited(Boolean submited) {
		this.submited = submited;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getStudentLimit() {
		return studentLimit;
	}

	public void setStudentLimit(Integer studentLimit) {
		this.studentLimit = studentLimit;
	}

	public Boolean getFeed_qualified() {
		return feed_qualified;
	}

	public void setFeed_qualified(Boolean feed_qualified) {
		this.feed_qualified = feed_qualified;
	}

}
