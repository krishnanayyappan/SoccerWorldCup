package worldcup.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

@WebServlet("/CardStatsModel")
public class CardStatsModel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String teamname = "";
	private static String red = "";
	private static String yellow = "";

	public static String getTeamname() {
		return teamname;
	}

	public static void setTeamname(String teamname) {
		CardStatsModel.teamname = teamname;
	}

	public static String getRed() {
		return red;
	}

	public static void setRed(String red) {
		CardStatsModel.red = red;
	}

	public static String getYellow() {
		return yellow;
	}

	public static void setYellow(String yellow) {
		CardStatsModel.yellow = yellow;
	}

	public void setTeam(String teamname2, String red2, String yellow2) {
		setTeamname(teamname2);
		setRed(red2);
		setYellow(yellow2);
	}

	// DB storage values

	private String gameid = "";
	private String pname = "";
	private String color = "";

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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void validateColors(CardStatsModel cardStatModel, CardStatsErrorMsgs CSerrorMsgs) {

		CSerrorMsgs.setTeamnameError(validateteamname(cardStatModel.getTeamname()));
		CSerrorMsgs.setColorError(validatecolors(cardStatModel.getRed(), cardStatModel.getYellow()));
		CSerrorMsgs.setErrorMsg();
	}

	private String validatecolors(String red2, String yellow2) {
		String result = "";
		if (red2.equals("N") && yellow2.equals("N")) {
			result = "Choose at least one color";
		}
		return result;
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
	public CardStatsModel() {
		super();
	}

}
