package gr.hua.dit.feeding_service_app.api.application.response_helper;

public class ApplicationSubmitResponse {
	Integer appl_id;

	public ApplicationSubmitResponse() {
	}

	public ApplicationSubmitResponse(Integer appl_id) {
		this.appl_id = appl_id;
	}

	public Integer getAppl_id() {
		return appl_id;
	}

	public void setAppl_id(Integer appl_id) {
		this.appl_id = appl_id;
	}

}
