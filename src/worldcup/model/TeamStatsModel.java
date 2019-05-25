package worldcup.model;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/TeamStatsModel")
public class TeamStatsModel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String teamname = "";

	public static String getTeamname() {
		return teamname;
	}

	public static void setTeamname(String teamname) {
		TeamStatsModel.teamname = teamname;
	}

	public void setTeam(String teamname2) {
		setTeamname(teamname2);
	}

	// DB storage values

	private String gameid = "";
	private String pname = "";
	private String pno = "";

	public String getGameid() {
		return gameid;
	}

	public void setGameid(String gameid) {
		this.gameid = gameid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPno() {
		return pno;
	}

	public void setPno(String pno) {
		this.pno = pno;
	}

	public void validateRental(TeamStatsModel teamStatModel, TeamStatsErrorMsgs TSerrorMsgs)
			throws java.text.ParseException {

		TSerrorMsgs.setteamnameError(validateteamname(teamStatModel.getTeamname()));
		TSerrorMsgs.setErrorMsg();
	}

	public String validateteamname(String teamname3) {
		String result = "";
		if (teamname3.length() == 0) {
			result = "Team Name cannot be blank";
		}
		return result;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeamStatsModel() {
		super();
	}

}
