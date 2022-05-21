package next.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import next.model.User;

@WebServlet("/user/updateProceed")
public class UpdateUserProceedServlet extends HttpServlet{

	private static final long serialVersionUID = -5672939786119103126L;
	private static final Logger log = LoggerFactory.getLogger(UpdateUserProceedServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * req에서 userid, password, name, email 가져오기
		 * userid 기반으로 database에서 hm 가져온 뒤 갱신(DB업데이트)
		 * 갱신 성공 후 세션 새로 생성, 
		 * 새로 받고 넘겨주기
		 * */
		log.debug("uid : {}", req.getParameter("userId"));
		String userId = req.getParameter("userId");
		log.debug("isUserExist : {}", DataBase.containsKey(userId));
		
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		
    	User userUpdateInfo = new User(userId, password, name, email);
    	log.debug("userUpdateInfo : {}", userUpdateInfo);
    	
    	if(DataBase.updateUser(userId, userUpdateInfo)) {
    		log.debug("update status : {}", "update success");
    		req.setAttribute("user", userUpdateInfo);    		
    		log.debug("URI path : {}", req.getRequestURI());
    		log.debug("URL path : {}", req.getRequestURL());
    		log.debug("Context path : {}", req.getContextPath());
    		/*
        	RequestDispatcher rd = req.getRequestDispatcher("../list");
    		rd.forward(req, resp);
    		 * */
    		resp.sendRedirect("/user/list");
    	} else {
    		log.debug("update status : {}", "update fail");
    		User user = DataBase.findUserById(req.getParameter("userId"));
    		log.debug("user : {}", user);
    		req.setAttribute("user", user);
    		log.debug("URI path : {}", req.getRequestURI());
    		log.debug("URL path : {}", req.getRequestURL());
    		log.debug("Context path : {}", req.getContextPath());
    		resp.sendRedirect("/user/update");
    	}
    }
}
