package controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CommandHandler;
import service.BoardService;
import service.BoardServiceImpl;

public class Delete  implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		BoardService bs = new BoardServiceImpl();
		
		HttpSession session = req.getSession();
		String uId = (String)session.getAttribute("uId");
		int bno = Integer.parseInt(req.getParameter("bno"));
		
		if (uId == null && !bs.isLogin(uId, bno)) {
			req.setAttribute("errorMsg", "로그인 후 사용하실 수 있습니다.");
			req.setAttribute("do", "select");
			req.setAttribute("login", "false");
			return "/viewPage/board.jsp";				
		}
		
		bs.delete(uId, bno);
		
		return "/viewPage/bbs.jsp";
	}
}