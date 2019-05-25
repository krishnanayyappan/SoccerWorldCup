package worldcup.model;

public class TeamStatsErrorMsgs {

	private String errorMsg;
	private String teamnameError;

	public TeamStatsErrorMsgs() {
		this.errorMsg = "";
		this.teamnameError = "";
	}

	public void setteamnameError(String teamnameError) {
		this.teamnameError = teamnameError;
	}

	public String getteamnameError() {
		return teamnameError;
	}

	public void setErrorMsg() {

		if (!teamnameError.equals("")) {
			this.errorMsg = "Please correct the following errors";
		}

	}

	public String getErrorMsg() {
		return errorMsg;
	}

}
