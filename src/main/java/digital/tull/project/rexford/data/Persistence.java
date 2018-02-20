/*
 * Copyright 2017 William Jackson.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package digital.tull.project.rexford.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.wicket.util.string.StringValue;

public class Persistence
{
//    private static final String framework = "embedded";
//    private static final String protocol = "jdbc:derby:";
//    private static final String dbName = "/home/will/.netbeans-derby/rome";
//    private static Connection conn;
//    
//    private PreparedStatement psInsert;
//    private PreparedStatement ps;
//    private PreparedStatement psDelete;
//    private Statement s;
//    private ResultSet rs;
//    
//    private String key;
//    private String table;

    public Persistence ()
    {
        
    }

//    public String getKey()
//    {
//        return key;
//    }
//
//    public void setKey(String key)
//    {
//        this.key = key;
//    }
//    
//    public ResultSet getRS()
//    {
//        return rs;
//    }
//    
//    public void setNull()
//    {
//        
//        try
//        {
//            ps = null;
//            if (rs != null)
//            {
//                rs.close();
//                rs = null;
//            }
//              
////            if (conn != null)
////            {
////                conn.close();
////                conn = null;
////            }
//        }
//                    
//        catch (SQLException sqle)
//        {
//            System.out.println(sqle.toString());
//        }
//        
//    }
    
    public static Connection connect()
    {
        Connection conn = null;
        
        try
        {
            Properties properties = new Properties();
            properties.put("derby.system.home", "/usr/share/rexford");
            properties.put("jdbc.drivers", "org.apache.derby.jdbc.EmbeddedDriver");
            //properties.setProperty("user", "APP");
            //properties.setProperty("password", "APP");
            final String URL = "jdbc:derby:/usr/share/rexford/rome;";
            DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
            conn = DriverManager.getConnection(URL);
            conn.setAutoCommit(false);
        }
        
        catch (SQLException sqle)
        {
            System.out.println(sqle.toString());
        }
        
        return conn;
    }
    
    public static void disconnect()
    {
        try
        {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
            
        }
        
        catch (SQLException se)
        {
            if (( (se.getErrorCode() == 50000) && ("XJ015".equals(se.getSQLState()) )))
            {
                // we got the expected exception
                System.out.println("Derby shut down normally");
                // Note that for single database shutdown, the expected
                // SQL state is "08006", and the error code is 45000.
            }
            
            else
            {
                // if the error code or SQLState is different, we have
                // an unexpected exception (shutdown failed)
                System.err.println("Derby did not shut down normally");
                System.out.println(se.toString());
            }
        }
        
        finally
        {
        // release all open resources to avoid unnecessary memory usage
            // ResultSet
//            try
//            {
//                if (rs != null)
//                {
//                   rs.close();
//                   rs = null;
//                }
//            }
//                    
//            catch (SQLException sqle)
//            {
//                System.out.println(sqle.toString());
//            }

            // Statements and PreparedStatements
//            int i = 0;
//            while (!statements.isEmpty())
//            {
//                // PreparedStatement extend Statement
//                Statement st = (Statement)statements.remove(i);
//               try
//                {
//                    if (st != null)
//                    {
//                        st.close();
//                        st = null;
//                    }
//                }
//                       
//                catch (SQLException sqle)
//                {
//                    System.out.println(sqle.toString());
//                }
//            }

            //Connection
//            try
//            {
//                if (conn != null)
//                {
//                    conn.close();
//                    conn = null;
//                }
//            }
//                    
//            catch (SQLException sqle)
//            {
//                System.out.println(sqle.toString());
//            }
        }
    }
    
    public static List<Article> getArticleList()
    {
        List<Article> articleList = new ArrayList<>();
        
        Connection conn = connect();
        
        try (
                Statement s = conn.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE
                        );
                ResultSet rs = s.executeQuery("SELECT * FROM ARTICLE ORDER BY DATE DESC");
                )
        {
            ResultSetMetaData metaData = rs.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            
            while (rs.next())
            {
                Article article = new Article();
                
                article.setTitle(rs.getString("TITLE"));
                article.setText(rs.getString("TEXT"));
                article.setDate(rs.getString("DATE"));
                article.setAuthorName(rs.getString("AUTHOR_NAME"));
                article.setCaption(rs.getString("CAPTION"));
                
                articleList.add(article);
            }
            
            conn.commit();
            
            conn.close();
        }
        
        catch (SQLException e)
        {
            e.printStackTrace();
            
        }
        
        
        
        disconnect();
        
        return articleList;
    }
    
//    public void create (Article article)
//    {
//        try
//        {
//            key = "ALL";
//            setRS();
//            
//            ResultSetMetaData metaData = rs.getMetaData();
//            int numberOfColumns = metaData.getColumnCount();
//            
//            rs.moveToInsertRow();
            
            
//            for (int i = 1;i <= numberOfColumns;i++)
//            {
//                rs.updateString(metaData.getColumnName(i), entity.getProperties(metaData.getColumnName(i)));
//            }
            
//            rs.insertRow();
//
//            conn.commit();
//            setRS();
//            viewRS(rs);
//            rs.close();
//            key = null;
//        }
//
//        catch (SQLException sqle)
//        {
//            System.out.println(sqle.toString());
//        }
//        
//        finally
//        {
//
//        }
//    }
//    
    public static void deleteArticle (String articleTitle)
    {
    	Connection conn = connect();
        
        try (
                Statement s = conn.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE
                        );
                ResultSet rs = s.executeQuery("SELECT * FROM ARTICLE");
                )
        {
            ResultSetMetaData metaData = rs.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            
            while (rs.next())
            {
                if (rs.getString("TITLE").equals(articleTitle))
                    break;
            }
            
            rs.deleteRow();
            
            conn.commit();
            
            conn.close();
        }
        
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        
        finally
        {
        	disconnect();
        }
    }
    
    public static void updateArticle (Article article)
    {
        Connection conn = connect();
        
        try (
                Statement s = conn.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE
                        );
                ResultSet rs = s.executeQuery("SELECT * FROM ARTICLE");
                )
        {
            ResultSetMetaData metaData = rs.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            
            while (rs.next())
            {
                if (rs.getString("TITLE").equals(article.getTitle()))
                    break;
            }
            
//            psUpdate = conn.prepareStatement(
//            "UPDATE ARTICLE SET text_one=?, text_two=?, text_three=?, text_four=?, caption=? where title=?");

            if (rs.isAfterLast())
            {
                rs.moveToInsertRow();
                rs.updateString("TITLE", article.getTitle());
                rs.updateString("TEXT", article.getText());
                rs.updateString("CAPTION", article.getCaption());
                rs.updateString("DATE", article.getDate());
                rs.updateString("AUTHOR_NAME", article.getAuthorName());
                rs.insertRow();
            }
            
            else //(rs.getString("TITLE").equals(article.getTitle())) //redundancy for assurance
            {
                rs.updateString("TEXT", article.getText());
                rs.updateString("CAPTION", article.getCaption());
                rs.updateString("TITLE", article.getTitle());
                rs.updateString("DATE", article.getDate());
                rs.updateString("AUTHOR_NAME", article.getAuthorName());
                rs.updateRow();
            }
            
            conn.commit();
            
            conn.close();
        }
        
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        
        disconnect();
    }
//    
//    public void setRS ()
//    {
//    
//    if (!key.equals("ALL"))
//    {
//        try
//        {
//            
//            final String SELECT_QUERY =
//                    "SELECT * FROM ARTICLE WHERE TITLE = ?";
//            
//            ps = conn.prepareStatement(SELECT_QUERY,
//                                    ResultSet.TYPE_SCROLL_INSENSITIVE,
//                                    ResultSet.CONCUR_UPDATABLE
//                                    );
//            ps.setString(1, key);
//            
//            rs = ps.executeQuery();
//            
//        }
//        
//        catch (SQLException sqle)
//        {
//            System.out.println(sqle.toString());
//        }
//        
//        finally
//        {
//            
//        }
//    }
//    else
//    {
//         try
//        {
//            
//            final String SELECT_QUERY =
//                    "SELECT * FROM ARTICLE";
//            
//            s = conn.createStatement(
//                                    ResultSet.TYPE_SCROLL_INSENSITIVE,
//                                    ResultSet.CONCUR_UPDATABLE
//                                    );
//            
//            rs = s.executeQuery(SELECT_QUERY);
//
//        }
//        
//        catch (SQLException sqle)
//        {
//            System.out.println(sqle.toString());
//        }
//    }
//
//    }
//    
//    public static void viewRS (ResultSet resultSet)
//    {
//        try
//        {
//        ResultSetMetaData metaData = resultSet.getMetaData();
//            int numberOfColumns = metaData.getColumnCount();
//            
//            while (resultSet.next())
//            {
//                for (int i = 1; i <= numberOfColumns; i++)
//                    System.out.println(metaData.getColumnName(i) + ":  " + resultSet.getString(i));
//                    //System.out.printf("%-8s\t", rs.getObject(i));
//                System.out.println();
//            }
//        }
//        catch (SQLException sqle)
//        {
//            System.out.println(sqle.toString());
//        }
//    }
    /**
     * Reports a data verification failure to System.err with the given message.
     *
     * @param message A message describing what failed.
     */
    private void reportFailure(String message) {
        System.err.println("\nData verification failed:");
        System.err.println('\t' + message);
    }
    
    

    /**
     * Prints details of an SQLException chain to <code>System.err</code>.
     * Details included are SQL State, Error code, Exception message.
     *
     * @param e the SQLException from which to print details.
     */
//    public static void printSQLException(SQLException e)
//    {
//        // Unwraps the entire exception chain to unveil the real cause of the
//        // Exception.
//        while (e != null)
//        {
//            System.err.println("\n----- SQLException -----");
//            System.err.println("  SQL State:  " + e.getSQLState());
//            System.err.println("  Error Code: " + e.getErrorCode());
//            System.err.println("  Message:    " + e.getMessage());
//            // for stack traces, refer to derby.log or uncomment this:
//            //e.printStackTrace(System.err);
//            e = e.getNextException();
//        }
//    }

    /**
     * Parses the arguments given and sets the values of this class's instance
     * variables accordingly - that is, which framework to use, the name of the
     * JDBC driver class, and which connection protocol to use. The
     * protocol should be used as part of the JDBC URL when connecting to Derby.
     * <p>
     * If the argument is "embedded" or invalid, this method will not change
     * anything, meaning that the default values will be used.</p>
     * <p>
     * @param args JDBC connection framework, either "embedded" or "derbyclient".
     * Only the first argument will be considered, the rest will be ignored.
     */
//    private void parseArguments(String[] args)
//    {
//        if (args.length > 0) {
//            if (args[0].equalsIgnoreCase("derbyclient"))
//            {
//                framework = "derbyclient";
//                protocol = "jdbc:derby://localhost:1527/";
//            }
//        }
//    }
}
