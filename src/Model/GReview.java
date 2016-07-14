package Model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the "GReview" database table.
 * 
 */
@Entity
@Table(name="\"GReview\"")
@NamedQuery(name="GReview.findAll", query="SELECT g FROM GReview g")
public class GReview implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="\"GREVIEW\"_REVIEWID_GENERATOR", sequenceName="GReview_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.AUTO, generator="\"GREVIEW\"_REVIEWID_GENERATOR")
	@Column(name="\"ReviewID\"")
	private long reviewID;

	@Column(name="\"RateNumber\"")
	private double rateNumber;

	
	@Temporal(TemporalType.DATE)
	private Date reviewdate;

	@Column(name="\"ReviewText\"")
	private String reviewText;

	

	//bi-directional many-to-one association to Grestaurant
	@ManyToOne
	@JoinColumn(name="\"RestaurantID\"")
	private Grestaurant grestaurant;

	//bi-directional many-to-one association to Guser
	@ManyToOne
	@JoinColumn(name="\"UserID\"")
	private Guser guser;

	public GReview() {
	}

	public long getReviewID() {
		return this.reviewID;
	}

	public void setReviewID(long reviewID) {
		this.reviewID = reviewID;
	}

	public double getRateNumber() {
		return this.rateNumber;
	}

	public void setRateNumber(double rateNumber) {
		this.rateNumber = rateNumber;
	}

	

	public Date getReviewdate() {
		return this.reviewdate;
	}

	public void setReviewdate(Date reviewdate) {
		this.reviewdate = reviewdate;
	}

	public String getReviewText() {
		return this.reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	

	public Grestaurant getGrestaurant() {
		return this.grestaurant;
	}

	public void setGrestaurant(Grestaurant grestaurant) {
		this.grestaurant = grestaurant;
	}

	public Guser getGuser() {
		return this.guser;
	}

	public void setGuser(Guser guser) {
		this.guser = guser;
	}

}