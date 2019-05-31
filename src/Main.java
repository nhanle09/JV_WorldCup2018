import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Vector;
import java.util.Collections;
import java.util.Scanner;

public class Main
{
    // Class variables
    static Connection mysql_connection = null;
    static Statement mysql_statement = null;
    static ResultSet mysql_resultset = null;

    // Establish connection to Database
    public static void ConnectToDB() throws ClassNotFoundException, SQLException
    {
        // Database variables
        
        String db_host = "jdbc:mysql://localhost:3306/wc2018?";
        // String db_user, db_password;
        // Scanner input_scanner = new Scanner( System.in );
        // // Take in user name and password
        // System.out.print( "User: " );
        // db_user = input_scanner.next();
        // System.out.print( "Password: " );
        // db_password = input_scanner.next();
        String db_user = "root";
        String db_password = "sqlroot";

        // Load JDBC Driver
        Class.forName( "com.mysql.cj.jdbc.Driver" );
        System.out.println( "Driver loaded! " );


        // Initiate connection to MySql Server
        mysql_connection = DriverManager.getConnection(db_host, db_user, db_password);
        System.out.println( "Connected to MySQL Server!" );
    }

    // Get data from database and return them into a Vector<String>
    // public static Vector<String> get_data_set( String sql_statement )
    // {
    //     Vector<String>
    // }
    public static void main(String[] args) 
    {
        Vector<String> table_list = new Vector<String>();
        Vector<String> team_list = new Vector<String>();


        // Import some data from database into memory
        try
        {
            ConnectToDB();
            // Import all tables from database into memory
            DatabaseMetaData mysql_tables = mysql_connection.getMetaData();
            String[] types = { "TABLE" };
            mysql_resultset = mysql_tables.getTables( null, null, "%", types );
            while ( mysql_resultset.next() )
            {
                table_list.add( mysql_resultset.getString( "TABLE_NAME" ) );
            }

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
        
            // Import all teams from database into memory
            try
            {
                mysql_statement = mysql_connection.createStatement();
                mysql_resultset = mysql_statement.executeQuery
                (
                    "SELECT * FROM teams"
                );

                while ( mysql_resultset.next() ) 
                {
                    team_list.add( mysql_resultset.getString( 2 ) );
                }

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
        catch ( ClassNotFoundException ex )
        {
            System.out.println( "ClassNotFoundException: " + ex.getMessage() );
        }
        catch ( SQLException ex )
        {
            // Handle errors
            System.out.println( "SQLException: " + ex.getMessage() );
            System.out.println( "SQLState: " + ex.getSQLState() );
            System.out.println( "VendorError: " + ex.getErrorCode() );
        }        


        Collections.sort( team_list );
        for ( String i : team_list )
        {
            System.out.println( i );
        }

        Collections.sort( table_list );
        for ( String i : table_list )
        {
            System.out.println( i );
        }
    }
}