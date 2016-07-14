

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBUtil.EmailValidator;
import Model.Guser;


/**
 * Servlet implementation class userprofileServlet
 */
@WebServlet("/userprofileServlet")
public class userprofileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userprofileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		EmailValidator emailvalidator=new EmailValidator();
	
		try
		{
		
			String username=request.getParameter("username");
			String userpassword=request.getParameter("userpassword");
			String userzipcode=request.getParameter("userzipcode");
			String useremail=request.getParameter("useremail");
			
			if(username!=null&&userpassword!=null&&userzipcode!=null&&useremail!=null)
			{
				if(emailvalidator.validate(useremail))
				{
					Guser user=DBUtil.Dataget.getUserByEmail(session.getAttribute("useremail").toString());

					user.setUserName(username);
					user.setUserPassword(userpassword);
					user.setZipcode(userzipcode);
					user.setUserEmail(useremail);
					
					DBUtil.Dataget.updateuser(user);

					 session.setAttribute("userid", user.getUserID());
					 session.setAttribute("useremail",user.getUserEmail());
						session.setAttribute("username", user.getUserName());
						session.setAttribute("userpassword", user.getUserPassword());
						session.setAttribute("userzipcode", user.getZipcode());
						request.setAttribute("messages", user.getUserName());
						session.setAttribute("images", "https://www.gravatar.com/avatar/"+Util.MD5Util.md5Hex(user.getUserEmail())+"?s=80");
					request.setAttribute("result", "Update successfully");
					
					request.getServletContext().getRequestDispatcher("/userprofile.jsp").forward(request, response);;
				}
			}
			else
			{
				session.setAttribute("result", "There is a null text");
			}
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
