package worldcup.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import worldcup.model.TeamStatsModel;
import worldcup.util.SQLConnection;

/**
 * Servlet implementation class RequestRentalDAO
 */
@WebServlet("/TeamStatsDAO")
public class TeamStatsDAO extends HttpServlet {

	static SQLConnection DBMgr = SQLConnection.getInstance();

	private static ArrayList<TeamStatsModel> GetInfofromDB(TeamStatsModel teamStatModel) {
		ArrayList<TeamStatsModel> GameList = new ArrayList<TeamStatsModel>();

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			String teamValue = teamStatModel.getTeamname();

			String getTeamvalues = "SELECT s.gameid, p.pname, p.pno FROM starting_lineups s, player p \r\n"
					+ "WHERE p.teamid = s.teamid AND p.pno = s.pno \r\n"
					+ "AND s.gameid IN (SELECT gameid FROM game WHERE teamid1 = (SELECT teamid FROM team WHERE team = '"
					+ teamValue + "')\r\n" + "UNION \r\n"
					+ "SELECT gameid FROM game WHERE teamid2 = (SELECT teamid FROM team WHERE team = '" + teamValue
					+ "')) \r\n" + "AND s.teamid = (SELECT teamid FROM team WHERE team = '" + teamValue
					+ "') ORDER BY s.gameid, s.pno;";

//			System.out.println("getTeamvalues query =" + getTeamvalues);

			ResultSet availableList = stmt.executeQuery(getTeamvalues);

			while (availableList.next()) {
				TeamStatsModel gameModel = new TeamStatsModel();

				gameModel.setGameid(availableList.getString("gameid"));
				gameModel.setPname(availableList.getString("pname"));
				gameModel.setPno(availableList.getString("pno"));

				GameList.add(gameModel);
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
		return GameList;
	}

	public static ArrayList<TeamStatsModel> searchGames(TeamStatsModel teamStatModel) {
		return GetInfofromDB(teamStatModel);
	}

}
