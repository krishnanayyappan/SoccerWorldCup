package worldcup.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import worldcup.model.CardStatsModel;
import worldcup.util.SQLConnection;

/**
 * Servlet implementation class RequestRentalDAO
 */
@WebServlet("/CardStatsDAO")
public class CardStatsDAO extends HttpServlet {

	static SQLConnection DBMgr = SQLConnection.getInstance();

	private static ArrayList<CardStatsModel> GetCardsfromDB(CardStatsModel cardStatModel) {
		ArrayList<CardStatsModel> CardList = new ArrayList<CardStatsModel>();

		String DAORed = cardStatModel.getRed();
		String DAOYellow = cardStatModel.getYellow();
		String getCardvalues = "";
		String teamValue = cardStatModel.getTeamname();

		if (DAORed.equals("Y") && DAOYellow.equals("Y")) {
			getCardvalues = "SELECT c.gameid, p.pname, c.color \r\n" + "FROM cards as c , player AS p \r\n"
					+ "WHERE p.teamid = c.teamid \r\n" + "and p.pno = c.pno \r\n" + "and p.team = '" + teamValue
					+ "' ;";
		} else if (DAORed.equals("Y") && DAOYellow.equals("N")) {
			getCardvalues = "SELECT c.gameid, p.pname, c.color \r\n" + "FROM cards as c , player AS p \r\n"
					+ "WHERE p.teamid = c.teamid \r\n" + "and p.pno = c.pno \r\n" + "and p.team = '" + teamValue
					+ "' \r\n" + "and c.color = 'Red' ;";
		} else if (DAORed.equals("N") && DAOYellow.equals("Y")) {
			getCardvalues = "SELECT c.gameid, p.pname, c.color \r\n" + "FROM cards as c , player AS p \r\n"
					+ "WHERE p.teamid = c.teamid \r\n" + "and p.pno = c.pno \r\n" + "and p.team = '" + teamValue
					+ "' \r\n" + "and c.color = 'Yellow' ;";
		}

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();

//			System.out.println("GetCardsfromDB query =" + getCardvalues);

			ResultSet availableList = stmt.executeQuery(getCardvalues);

			while (availableList.next()) {
				CardStatsModel cardModel = new CardStatsModel();

				cardModel.setGameid(availableList.getString("gameid"));
				cardModel.setPname(availableList.getString("pname"));
				cardModel.setColor(availableList.getString("color"));

				CardList.add(cardModel);
			}

			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		;
		return CardList;
	}

	public static ArrayList<CardStatsModel> searchColors(CardStatsModel cardStatModel) {
		return GetCardsfromDB(cardStatModel);
	}

}
