package worldcup.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import worldcup.data.CardStatsDAO;
import worldcup.model.CardStatsErrorMsgs;
import worldcup.model.CardStatsModel;

/**
 * Servlet implementation class RequestRental
 */
@WebServlet("/CardStats")
public class CardStats extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CardStats() {
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
		CardStatsModel cardStatModel = new CardStatsModel();
		CardStatsErrorMsgs CSerrorMsgs = new CardStatsErrorMsgs();
		CardStatsDAO cardStatDAO = new CardStatsDAO();

		String teamname = request.getParameter("teamname");
		String red = request.getParameter("red");
		String yellow = request.getParameter("yellow");

		if (red == null) {
			red = "N";
		}

		if (yellow == null) {
			yellow = "N";
		}

		session.removeAttribute("CSerrorMsgs");

		cardStatModel.setTeam(teamname, red, yellow);
		// TODO uncomment this and continue
		cardStatModel.validateColors(cardStatModel, CSerrorMsgs);
//		CSerrorMsgs.setErrorMsg();

		session.setAttribute("cardStatModel", cardStatModel);

		if (CSerrorMsgs.getErrorMsg().equals("")) {
			if (!teamname.equals("") || !red.equals("") || !yellow.equals("")) {

				ArrayList<CardStatsModel> CardsInfo = new ArrayList<CardStatsModel>();
				CardsInfo = CardStatsDAO.searchColors(cardStatModel);

				session.setAttribute("CARDS", CardsInfo);
				session.removeAttribute("cardStatModel");
				getServletContext().getRequestDispatcher("/cardStats.jsp").forward(request, response);
			}
		} else {
			// session.setAttribute("regModel", regModel);
			cardStatModel.setTeam(teamname, red, yellow);
			session.setAttribute("CSerrorMsgs", CSerrorMsgs);
			response.sendRedirect("cardStats.jsp");

		}

//		doGet(request, response);
	}

}
