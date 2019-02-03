package mk;

//@Entity // This tells Hibernate to make a table out of this class
//@Table(name = "users")
public class User {

	
//    @Id
//    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
/*	
	@Bean
	public DataSource dataSource() {
	    return DataSourceBuilder
	        .create()
	        .username("")
	        .password("")
	        .url("")
	        .driverClassName("")
	        .build();
	}
	*/
	

    private String name;

    private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// Public methods
	  
	  public User() { }

	  public User(long id) { 
	    this.id = id;
	  }
	  
	  public User(String email, String name) {
	    this.email = email;
	    this.name = name;
	  }
	
}