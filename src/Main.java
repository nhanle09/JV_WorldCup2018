import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main
{
    // Establish connection to Database
    public static Connection ConnectToDB()
    {
        // Database variables
        Connection mysql_connection = null;
        String db_host = "jdbc:mysql://localhost:3306/wc2018?";
        String db_user = "root";
        String db_password = "sqlroot";

        // Load JDBC Driver
        try
        {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            System.out.println( "Driver loaded! " );
        }
        catch ( ClassNotFoundException ex )
        {
            System.out.println( "ClassNotFoundException: " + ex.getMessage() );
        }

        // Initiate connection to MySql Server
        try
        {
            mysql_connection = DriverManager.getConnection(db_host, db_user, db_password);
            System.out.println( "Connected to MySQL Server!" );
            return mysql_connection;
        }
        catch ( SQLException ex )
        {
            // Handle errors
            System.out.println( "SQLException: " + ex.getMessage() );
            System.out.println( "SQLState: " + ex.getSQLState() );
            System.out.println( "VendorError: " + ex.getErrorCode() );
        }
    }

    public static void main(String[] args) 
    {
        // Variables
        Connection mysql_connection = ConnectToDB();
        
        Statement mysql_statement = null;
        ResultSet mysql_resultset = null;
        try
        {
            mysql_statement = mysql_connection.createStatement();
            mysql_resultset = mysql_statement.executeQuery
            (
                "SELECT * FROM stadiums"
            );

            System.out.println( mysql_resultset );

        }
        catch ( SQLException ex )
        {
            // Handle errors
            System.out.println( "SQLException: " + ex.getMessage() );
            System.out.println( "SQLState: " + ex.getSQLState() );
            System.out.println( "VendorError: " + ex.getErrorCode() );
        }
        finally
        {
            // Nullify ResultSet
            if ( mysql_resultset != null )
            {
                try
                {
                    mysql_resultset.close();
                }
                catch ( SQLException sqlex ){ }
                mysql_resultset = null;
            }

            // Nullify Statement
            if ( mysql_statement != null )
            {
                try
                {
                    mysql_statement.close();
                }
                catch ( SQLException sqlex ){ }
                mysql_statement = null;
            } 
        }
    }
}