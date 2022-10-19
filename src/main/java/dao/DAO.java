package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
 
public class DAO {
    public static Connection con;
     
    public DAO(){
        if(con == null){
            String databaseName = "contactdb";
            String password = "123456";
            String dbUrl = 
                "jdbc:mysql://localhost:3306/" + databaseName;
            String dbClass = "com.mysql.jdbc.Driver";
            try {
                Class.forName(dbClass);
                con = DriverManager.getConnection (dbUrl, "root", password);
                System.out.println("Ket noi thanh cong");
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    protected void setParameter(PreparedStatement statement, Object... parameters){
        try{
            int i = 1;
            for(Object param: parameters){
                if(param instanceof String){
                    statement.setString(i, (String)param);
                }else if(param instanceof Long){
                    statement.setLong(i, (Long)param);
                }else if(param instanceof Integer){
                    statement.setInt(i, (Integer)param);
                }else if(param instanceof Timestamp){
                    statement.setTimestamp(i, (Timestamp)param);                    
                }else if(param instanceof Date){
                    statement.setDate(i, (Date)param);
                }else if(param instanceof Double){
                    statement.setDouble(i, (Double)param);
                }else if(param == null){
                    statement.setNull(i, java.sql.Types.INTEGER);
                }
                i++;
            }
        
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new DAO();
    }
}