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

@WebServlet("/user/updateRequest")
public class UpdateUserFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UpdateUserFormServlet.class);
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	log.debug("uid : {}", req.getParameter("userId"));
    	User user = DataBase.findUserById(req.getParameter("userId"));
    	log.debug("user : {}", user);
    	req.setAttribute("user", user);
    	RequestDispatcher rd = req.getRequestDispatcher("/user/update.jsp");
    	rd.forward(req, resp);
    }
}
