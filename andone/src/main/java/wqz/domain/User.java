package wqz.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="user",schema="sadb")
public class User{
	
	/**
	 * 
	 */
	@Id
	@GeneratedValue
	private Integer id ;
	@Column(nullable=false ,unique=true)
	private String username;
	@Column(nullable=false)
	private String password;
	@Column(nullable=false)
	private Boolean status;
	public User(){}
	public User(String username, String password, boolean status) {
		this.username = username;
		this.password = password;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id = " + id +";\tusername = " + username +";\tpassword = "+ password + ";\tstatus = " + status;
	}

}
