package worldcup.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import worldcup.data.TeamStatsDAO;
import worldcup.model.TeamStatsErrorMsgs;
import worldcup.model.TeamStatsModel;

/**
 * Servlet implementation class RequestRental
 */
@WebServlet("/TeamStats")
public class TeamStats extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeamStats() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		TeamStatsModel teamStatModel = new TeamStatsModel();
		TeamStatsErrorMsgs TSerrorMsgs = new TeamStatsErrorMsgs();
		TeamStatsDAO teamStatDAO = new TeamStatsDAO();

		String teamname = request.getParameter("teamname");

		session.removeAttribute("TSerrorMsgs");

		teamStatModel.setTeam(teamname);
		try {
			teamStatModel.validateRental(teamStatModel, TSerrorMsgs);
			TSerrorMsgs.setErrorMsg();
		} catch (ParseException e2) {
			e2.printStackTrace();
		}

		session.setAttribute("teamStatModel", teamStatModel);

		if (TSerrorMsgs.getErrorMsg().equals("")) {
			if (!teamname.equals("")) {

				ArrayList<TeamStatsModel> MatchInfo = new ArrayList<TeamStatsModel>();
				MatchInfo = TeamStatsDAO.searchGames(teamStatModel);

				session.setAttribute("MATCHES", MatchInfo);
				session.removeAttribute("teamStatModel");
				getServletContext().getRequestDispatcher("/teamStats.jsp").forward(request, response);
			}
		} else {
			// session.setAttribute("regModel", regModel);
			teamStatModel.setTeam(teamname);
			session.setAttribute("TSerrorMsgs", TSerrorMsgs);
			response.sendRedirect("teamStats.jsp");

		}

//		doGet(request, response);
	}

}
