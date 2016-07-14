

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBUtil.Dataget;
import Model.GReview;
import Model.Grestaurant;
import Model.Guser;


/**
 * Servlet implementation class restaurantServlet
 */
@WebServlet("/restaurantServlet")
public class restaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public restaurantServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		String method = request.getParameter("method");
		
		String restaurantid=session.getAttribute("myrestaurantid").toString();
		long longrestrantid=Dataget.getrestaurantid(restaurantid);
		
		
		if(method!=null){ 
		if (method.equals("Add")) { 
			
			String dratenumber=request.getParameter("stars");
			try
			{
				if(dratenumber.equals(null)!=true)
				{
					double ratenumber=Double.parseDouble(dratenumber);
					String newreview= request.getParameter("limitedtextarea");	
					GReview greview=new GReview(); 
					Grestaurant grestaurant=Dataget.restaurantofrestaurantid(longrestrantid);
					String userid=session.getAttribute("userid").toString();
					long longuserid=Dataget.getuserid(userid);
					Guser guser=Dataget.userofuserid(longuserid);
					
					
					
					java.util.Date reviewdate=new java.util.Date(); 
					
					greview.setGrestaurant(grestaurant);
					greview.setGuser(guser);
					greview.setRateNumber(ratenumber);
					greview.setReviewdate(reviewdate);
					greview.setReviewText(newreview);
					
					Dataget.insert(greview);
					
					List<GReview> Reviews=Dataget.Reviewsofrestaurant(longrestrantid);
					session.setAttribute("Reviewsofrestaurant", Reviews);
					
					HashMap<String,String> imageurls=new HashMap<String,String>();
				    imageurls=Dataget.getGravatarUrl(Reviews);	 
				   
				   session.setAttribute("imageurlsofrestaurant",imageurls ); 
					request.getServletContext().getRequestDispatcher("/restaurant.jsp").forward(request, response);;
				}
			}catch(Exception e)
			{
				
			}
			
			

		 }
		 else if(method.equals("Search"))
		 {
			 String searchreview= request.getParameter("limitedtextarea"); 
			 List<GReview> searchReviews=new ArrayList<GReview>();
			 
			 try
			 {
				 if(searchreview.equals(null))
				 {
					 List<GReview> Reviews=Dataget.Reviewsofrestaurant(longrestrantid);
					session.setAttribute("Reviewsofrestaurant", Reviews);	
					HashMap<String,String> imageurls=new HashMap<String,String>();
				    imageurls=Dataget.getGravatarUrl(Reviews);	 
				   
				   session.setAttribute("imageurlsofrestaurant",imageurls ); 
						
				 }
				 else
				 {
					 searchReviews=Dataget.searchReviewsbyrestaurantid(searchreview, longrestrantid);
					 session.setAttribute("Reviewsofrestaurant", searchReviews);
					 HashMap<String,String> imageurls=new HashMap<String,String>();
					    imageurls=Dataget.getGravatarUrl(searchReviews);	 
					   
					   session.setAttribute("imageurlsofrestaurant",imageurls ); 
					 getServletContext().getRequestDispatcher("/restaurant.jsp").forward(request, response);;
				 }
				 
			 }
			 catch(Exception e)
			 {
				 
			 }
		 } 
		 else if(method.equals("Update"))
		 {
			 request.getServletContext().getRequestDispatcher("/restaurant.jsp").forward(request, response);;
		 }
		 else if(method.equals("Save"))
		 {
			 Grestaurant restaurant=Dataget.restaurantofrestaurantid(longrestrantid);
			 
			 String restaurantName=request.getParameter("restaurantName");
			 String restaurantAddress=request.getParameter("restaurantAddress");
			 String restaurantDescription=request.getParameter("restaurantDescription");
			 restaurant.setRestaurantName(restaurantName);
			 restaurant.setRestaurantDiscription(restaurantDescription);
			 restaurant.setRestaurantAddress(restaurantAddress);
			
			 Dataget.updaterestaurant(restaurant); 
			 
			 
				session.setAttribute("myrestaurantid", restaurant.getRestaurantID());
				session.setAttribute("restaurantname", restaurant.getRestaurantName());
				session.setAttribute("restaurantaddress", restaurant.getRestaurantAddress());
				session.setAttribute("restaurantdescription", restaurant.getRestaurantDiscription());
				HashMap<String, String> averageRatings=Dataget.averageRating();
				session.setAttribute("AverageRatings", averageRatings);
				HashMap<String, String> NumberofRatings =Dataget.NumberofRatings();
				session.setAttribute("NumberofRatings", NumberofRatings);
				
				
			
				
			 List<GReview> Reviews=Dataget.Reviewsofrestaurant(longrestrantid);
			 session.setAttribute("Reviewsofrestaurant", Reviews);	
			 HashMap<String,String> imageurls=new HashMap<String,String>();
			    imageurls=Dataget.getGravatarUrl(Reviews);	 
			   
			   session.setAttribute("imageurlsofrestaurant",imageurls ); 
				getServletContext().getRequestDispatcher("/restaurant.jsp").forward(request, response);;
		 }
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
