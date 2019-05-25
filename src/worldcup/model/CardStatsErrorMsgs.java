package worldcup.model;

public class CardStatsErrorMsgs {

	private String errorMsg;
	private String teamnameError;
	private String colorError;

	public String getTeamnameError() {
		return teamnameError;
	}

	public void setTeamnameError(String teamnameError) {
		this.teamnameError = teamnameError;
	}

	public String getColorError() {
		return colorError;
	}

	public void setColorError(String colorError) {
		this.colorError = colorError;
	}

	public CardStatsErrorMsgs() {
		this.errorMsg = "";
		this.teamnameError = "";
		this.colorError = "";
	}

	public void setErrorMsg() {
		if (!teamnameError.equals("") || !colorError.equals("")) {
			this.errorMsg = "Please correct the following errors";
		}
	}

	public String getErrorMsg() {
		return errorMsg;
	}

}
