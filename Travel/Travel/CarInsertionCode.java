import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CarInsertionCode 
{
	
	public static Connection getConnection() 
	{
		try {
		Connection con = null ;
		final String Driver = "com.mysql.cj.jdbc.Driver";
		final String Url = "jdbc:mysql://localhost:3306/taxi?autoReconnect=true&useSSL=false";
		final String User = "root";
		final String Password = "root";
			
			Class.forName(Driver);
			con = DriverManager.getConnection(Url,User,Password);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return con;
	}
	static void insert() throws Exception
	{
		String cars = "";
		FileReader fr = new FileReader(--File Path--) ;
		int i;    
		while((i=fr.read())!=-1)    
		{
			cars+= (char)i ;
		}  
		String[] car_list = cars.split("=");

		for ( String s : car_list)
		{
			String brands = s.split(":")[0];
			String[] models = s.split(":")[1].split(",");
			int id = 0;

			System.out.println(brands+" ::" + models );

			try {

				PreparedStatement ps = getConnection().prepareStatement("INSERT INTO CAR_BRAND VALUES (null,?)",Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, brands);

				System.out.println(ps);

				int result = ps.executeUpdate() ;
				ResultSet rs = ps.getGeneratedKeys();

				if ( result > 0)
				{
					System.out.println("Brand Insertion Success");

					while( rs.next() )
					{
						System.out.println(rs.getInt(1));
						id = rs.getInt(1);
					}
				}
				else
				{
					System.out.println("Brand failed");
				}

				if ( id > 0)
				{
					for ( String s1 : models )
					{
						PreparedStatement ps2 = getConnection().prepareStatement("INSERT INTO MODEL VALUES (null,?,?)");
						int c1 = 0;
						ps2.setInt(++c1, id );
						ps2.setString(++c1,s1.trim());

						System.out.println(ps2);

						int result2 = ps2.executeUpdate() ;

						if ( result2 > 0 )
						{
							System.out.println("Model Insertion Success");
						}
					}
				}
				else
				{
					System.out.println("Model failed ");
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		fr.close();
	}
	public static void main(String[] args) throws Exception
	{
		insert();
	}

}