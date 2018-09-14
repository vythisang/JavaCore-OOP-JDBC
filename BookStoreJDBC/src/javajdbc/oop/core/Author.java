package javajdbc.oop.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Author {

	private long id;
	private String name;
	private Date dob;
	private long soldNumber;
	private long bookCount;
	private double revenue;
	public long getId() {
		return id;
	}
	
	public Author(ResultSet resulSet)throws SQLException{
		this.setName(resulSet.getString("name"));
		this.setId(resulSet.getLong("id"));
		this.setDob(resulSet.getDate("dob"));
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public long getSoldNumber() {
		return soldNumber;
	}
	public void setSoldNumber(long soldNumber) {
		this.soldNumber = soldNumber;
	}
	public long getBookCount() {
		return bookCount;
	}
	public void setBookCount(long bookCount) {
		this.bookCount = bookCount;
	}
	public double getRevenue() {
		return revenue;
	}
	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}
	public Author(long id, String name, Date dob) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
	}
	public Author() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (soldNumber ^ (soldNumber >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (soldNumber != other.soldNumber)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", dob=" + dob + ", soldNumber=" + soldNumber + ", bookCount="
				+ bookCount + ", revenue=" + revenue + "]";
	}
	
	
	
	
}
