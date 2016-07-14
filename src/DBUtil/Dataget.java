package DBUtil;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


import Model.*;










public class Dataget {
	public static Guser getUserByEmail(String email)
	{
		EntityManager em=DBUtil.getEmFactory().createEntityManager();
		String qString="Select u from Guser u "+"where u.userEmail=:useremail";
		TypedQuery<Guser> q=em.createQuery(qString,Guser.class);
		q.setParameter("useremail", email);
		Guser user=null;
		try
		{
			user=q.getSingleResult();
		}catch(NoResultException e)
		{
		   System.out.println(e);
		}finally
		{
			em.close();
		}
		return user;
	}
	public static boolean isValidUser(String email, String pass)
	{
		EntityManager em=DBUtil.getEmFactory().createEntityManager();
		String qString="Select count(b.userID) from Guser b "
				+"where b.userEmail=:useremail"
				+ " and b.userPassword=:userpass";
		TypedQuery<Long> q=em.createQuery(qString,Long.class);
		boolean result=false;
		q.setParameter("useremail", email);
		q.setParameter("userpass", pass);
		try
		{
			long userid=q.getSingleResult();
			if(userid>0)
			{
				result=true;
			}
		}catch(Exception e)
		{
			result=false;
		}
		finally
		{
			em.close();
		}
		return result;
	}
	public static Date todate(String date)
    {
    	DateFormat df = new SimpleDateFormat("yy-MMM-dd"); 
        Date startDate=null;
        try {
            startDate = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate;
    }
	
	public static void updateuser(Guser bhuser) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(bhuser);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
	public static void updaterestaurant(Grestaurant bhuser) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(bhuser);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
	
	public static List<Grestaurant> Grestaurant (){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "select b from Grestaurant b";
        
        List<Grestaurant> restaurants = null;
        try{
            TypedQuery<Grestaurant> query = em.createQuery(qString,Grestaurant.class);
            restaurants = query.getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
                em.close();
            }
        return restaurants;
    }
	
	public static List<GReview> GReview (){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "select b from GReview b";
        
        List<GReview> Reviews = null;
        try{
            TypedQuery<GReview> query = em.createQuery(qString,GReview.class);
            Reviews = query.getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
                em.close();
            }
        return Reviews;
    }
	
	public static List<GReview> MyReview (long userid){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "select b from GReview b where b.guser.userID=:userid";
        
        List<GReview> Reviews = null;
        try{
            TypedQuery<GReview> query = em.createQuery(qString,GReview.class);
            query.setParameter("userid", userid);
            Reviews = query.getResultList();
        
        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
                em.close();
            }
        return Reviews;
    }
	
	public static HashMap<String, String> averageRating ()
    {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        HashMap<String, String> averageRatings =new HashMap<String,String>();
        String qString = "select b.grestaurant.restaurantName , avg(b.rateNumber) from GReview b group by b.grestaurant.restaurantName";
        try{
            Query query = em.createQuery(qString);
            List<Object[]> resultList = query.getResultList();
            for(Object[] result: resultList)
            {
            	
            	averageRatings.put(result[0].toString(),result[1].toString());
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            em.close();
            
        }     
        return averageRatings;
    }
	public static HashMap<String, String> NumberofRatings ()
    {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        HashMap<String, String> NumberofRatings =new HashMap<String,String>();
        String qString = "select  b.grestaurant.restaurantName, count(b.reviewID) from GReview b group by b.grestaurant.restaurantName";
        try{
            Query query = em.createQuery(qString);
            List<Object[]> resultList = query.getResultList();
            for(Object[] result: resultList)
            {
            	
            	NumberofRatings.put(result[0].toString(),result[1].toString());
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            em.close();
            
        }   
        
        return NumberofRatings;
    }
	
	
	
	public static Grestaurant restaurantofrestaurantid (long restaurantid){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "select b from Grestaurant b where b.restaurantID = :restaurantid";
        
        Grestaurant restaurant = null;
        try{
            TypedQuery<Grestaurant> query = em.createQuery(qString,Grestaurant.class);
            query.setParameter("restaurantid", restaurantid);
            restaurant = query.getSingleResult();

        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
                em.close();
            }
        return restaurant;
    }
	public static GReview reviewofreviewid (long reviewid){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "select b from GReview b where b.reviewID = :reviewid";
        
        GReview review = null;
        try{
            TypedQuery<GReview> query = em.createQuery(qString,GReview.class);
            query.setParameter("reviewid", reviewid);
            review = query.getSingleResult();

        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
                em.close();
            }
        return review;
    }
	
	 public static void delete(GReview review) {
	        EntityManager em = DBUtil.getEmFactory().createEntityManager();
	        EntityTransaction trans = em.getTransaction();
	        try {
	            trans.begin();
	            em.remove(em.merge(review));
	            trans.commit();
	        } catch (Exception e) {
	            System.out.println(e);
	            trans.rollback();
	        } finally {
	            em.close();
	        }
	    }
	    public static void update(GReview review) {
	        EntityManager em = DBUtil.getEmFactory().createEntityManager();
	        EntityTransaction trans = em.getTransaction();
	        try {
	            trans.begin();
	            em.merge(review);
	            trans.commit();
	        } catch (Exception e) {
	            trans.rollback();
	        } finally {
	            em.close();
	        }
	    }
	
	
	public static Guser userofuserid (long userid){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "select b from Guser b where b.userID = :userid";
        
        Guser user = null;
        try{
            TypedQuery<Guser> query = em.createQuery(qString,Guser.class);
            query.setParameter("userid", userid);
            user = query.getSingleResult();

        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
                em.close();
            }
        return user;
    }
	
	 public static long getrestaurantid (String restaurantid)
	    {
	        EntityManager em = DBUtil.getEmFactory().createEntityManager();
	        List<Long> restaurantids=new ArrayList<Long>();
	        String qString = "select b.restaurantID from Grestaurant b";
	        long longrestrantpostid=0;
	        try{
	            Query query = em.createQuery(qString,Grestaurant.class);           
	            restaurantids=query.getResultList();
	            
	            for(long a: restaurantids)
	            {
	            	if(Long.toString(a).equals(restaurantid))
	            	{
	            		longrestrantpostid=a;
	            	}
	            }
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	        finally{
	                em.close();
	            }
	        
	        return longrestrantpostid;    
	    }
	 
	 	public static long getuserid (String userid)
	    {
	        EntityManager em = DBUtil.getEmFactory().createEntityManager();
	        List<Long> userids=new ArrayList<Long>();
	        String qString = "select b.userID from Guser b";
	        long longuserid=0;
	        try{
	            Query query = em.createQuery(qString,Guser.class);           
	            userids=query.getResultList();
	            
	            for(long a: userids)
	            {
	            	if(Long.toString(a).equals(userid))
	            	{
	            		longuserid=a;
	            	}
	            }
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	        finally{
	                em.close();
	            }
	        
	        return longuserid;    
	    }
	 	
	 	public static long getReviewid (String Reviewid)
	    {
	        EntityManager em = DBUtil.getEmFactory().createEntityManager();
	        List<Long> Reviewids=new ArrayList<Long>();
	        String qString = "select b.reviewID from GReview b";
	        long longreviewid=0;
	        try{
	            Query query = em.createQuery(qString,GReview.class);           
	            Reviewids=query.getResultList();
	            
	            for(long a: Reviewids)
	            {
	            	if(Long.toString(a).equals(Reviewid))
	            	{
	            		longreviewid=a;
	            	}
	            }
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	        finally{
	                em.close();
	            }
	        
	        return longreviewid;    
	    }
	 
	    public static List<GReview> Reviewsofrestaurant(long restaurantid)
	    {
	        EntityManager em = DBUtil.getEmFactory().createEntityManager();
	        List<GReview> Reviews = null;
	        String qString = "select b from GReview b where b.grestaurant.restaurantID = :restaurantid";
	        
	        try{
	            TypedQuery<GReview> query = em.createQuery(qString,GReview.class);
	            query.setParameter("restaurantid", restaurantid);
	            Reviews = query.getResultList();
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	        finally{
	                em.close();
	            }
	        return Reviews;    
	    }
	    public static void insert(GReview review) {
	        EntityManager em = DBUtil.getEmFactory().createEntityManager();
	        EntityTransaction trans = em.getTransaction();
	        try {
	            trans.begin();
	            em.persist(review);
	            trans.commit();
	        } catch (Exception e) {
	            trans.rollback();
	        } finally {
	            em.close();
	        }
	    }
	    public static void insert(Guser user) {
	        EntityManager em = DBUtil.getEmFactory().createEntityManager();
	        EntityTransaction trans = em.getTransaction();
	        try {
	            trans.begin();
	            em.persist(user);
	            trans.commit();
	        } catch (Exception e) {
	            trans.rollback();
	        } finally {
	            em.close();
	        }
	    }
	    
	    public static List<GReview> searchReviewsbyrestaurantid (String search, long restaurantid)
	    {
	        EntityManager em = DBUtil.getEmFactory().createEntityManager();
	        List<GReview> searchReviews = null;
	        String qString = "select b from GReview b "
	                + "where b.grestaurant.restaurantID=:restaurantid and b.reviewText like :search";
	        
	        try{
	            TypedQuery<GReview> query = em.createQuery(qString,GReview.class);
	            query.setParameter("search", "%" + search + "%");
	            query.setParameter("restaurantid", restaurantid);
	            searchReviews = query.getResultList();
	        }catch (Exception e){
	            e.printStackTrace();
	        }finally{
	            em.close();
	        }return searchReviews;
	    }
	    
	    public static List<GReview> searchReviews (String search)
	    {
	        EntityManager em = DBUtil.getEmFactory().createEntityManager();
	        List<GReview> searchReviews = null;
	        String qString = "select b from GReview b "
	                + "where  b.reviewText like :search";
	        
	        try{
	            TypedQuery<GReview> query = em.createQuery(qString,GReview.class);
	            query.setParameter("search", "%" + search + "%");
	            
	            searchReviews = query.getResultList();
	        }catch (Exception e){
	            e.printStackTrace();
	        }finally{
	            em.close();
	        }return searchReviews;
	    }
	    public static List<GReview> searchMyReviews (String search, long userid)
	    {
	        EntityManager em = DBUtil.getEmFactory().createEntityManager();
	        List<GReview> searchReviews = null;
	        String qString = "select b from GReview b "
	                + "where b.guser.userID=:userid and  b.reviewText like :search";
	        
	        try{
	            TypedQuery<GReview> query = em.createQuery(qString,GReview.class);
	            query.setParameter("search", "%" + search + "%");
	            query.setParameter("userid", userid); 
	            searchReviews = query.getResultList();
	        }catch (Exception e){
	            e.printStackTrace();
	        }finally{
	            em.close();
	        }return searchReviews;
	    }
	    
	    
	    public static List<Grestaurant> searchRestaurants (String search)
	    {
	        EntityManager em = DBUtil.getEmFactory().createEntityManager();
	        List<Grestaurant> searchRestaurants = null;
	        String qString = "select b from Grestaurant b "
	                + "where b.restaurantName like :search";
	        
	        try{
	            TypedQuery<Grestaurant> query = em.createQuery(qString,Grestaurant.class);
	            query.setParameter("search", "%" + search + "%");
	            searchRestaurants = query.getResultList();
	        }catch (Exception e){
	            e.printStackTrace();
	        }finally{
	            em.close();
	        }return searchRestaurants;
	    }
	    public static HashMap<String,String> getGravatarUrl(List<GReview> reviews)
	    {
	    	HashMap<String,String> urls=new HashMap<String,String>();
	    		
	    	for(GReview review: reviews)
	    	{
	    		urls.put(review.getGuser().getUserEmail(), "https://www.gravatar.com/avatar/"+Util.MD5Util.md5Hex(review.getGuser().getUserEmail())+"?s=80");
	    		
	    	} 	
	    			
	    	return urls;
	    	
	    }
	
	
	
}