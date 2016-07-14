package Model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "Guser" database table.
 * 
 */
@Entity
@Table(name="\"Guser\"")
@NamedQuery(name="Guser.findAll", query="SELECT g FROM Guser g")
public class Guser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="\"GUSER\"_USERID_GENERATOR", sequenceName="Guser_seq", allocationSize=1  )
	@GeneratedValue(strategy=GenerationType.AUTO, generator="\"GUSER\"_USERID_GENERATOR")
	@Column(name="\"UserID\"")
	private long userID;

	@Column(name="\"UserEmail\"")
	private String userEmail;

	@Column(name="\"UserName\"")
	private String userName;

	@Column(name="\"UserPassword\"")
	private String userPassword;

	@Column(name="\"Zipcode\"")
	private String zipcode;

	//bi-directional many-to-one association to GReview
	@OneToMany(mappedBy="guser")
	private List<GReview> greviews;

	public Guser() {
	}

	public long getUserID() {
		return this.userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public List<GReview> getGreviews() {
		return this.greviews;
	}

	public void setGreviews(List<GReview> greviews) {
		this.greviews = greviews;
	}

	public GReview addGreview(GReview greview) {
		getGreviews().add(greview);
		greview.setGuser(this);

		return greview;
	}

	public GReview removeGreview(GReview greview) {
		getGreviews().remove(greview);
		greview.setGuser(null);

		return greview;
	}

}