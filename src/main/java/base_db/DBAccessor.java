package base_db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @ClassName: JdbcUtils_DBCP
* @Description: 数据库连接工具类
* @author: 孤傲苍狼
* @date: 2014-10-4 下午6:04:36
*
*/ 
public class DBAccessor {
	
	private static final Logger logger  =  LoggerFactory.getLogger(DBAccessor.class);
	
    /**
     * 在java中，编写数据库连接池需实现java.sql.DataSource接口，每一种数据库连接池都是DataSource接口的实现
     * DBCP连接池就是java.sql.DataSource接口的一个具体实现
     */
    private static DataSource ds = null;
    //在静态代码块中创建数据库连接池
    static{
        try{
            //加载dbcpconfig.properties配置文件
            InputStream in = DBAccessor.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
            Properties prop = new Properties();
            prop.load(in);

            prop.setProperty("driverClassName", "org.postgresql.Driver");
            prop.setProperty("url", "jdbc:postgresql://testdb.cur5zf1lggee.ap-northeast-1.rds.amazonaws.com:5432/mydbtest01");
            prop.setProperty("username", "pfs");
            prop.setProperty("password", "zaq1xsw2");
            //创建数据源
            ds = BasicDataSourceFactory.createDataSource(prop);
        }catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    
    /**
    * @Method: getConnection
    * @Description: 从数据源中获取数据库连接
    * @Anthor:孤傲苍狼
    * @return Connection
    * @throws SQLException
    */ 
    public static Connection getConnection() throws SQLException{
    	logger.info("getConnection");
        //从数据源中获取数据库连接
        return ds.getConnection();
    }
    
    /**
    * @Method: release
    * @Description: 释放资源，
    * 释放的资源包括Connection数据库连接对象，负责执行SQL命令的Statement对象，存储查询结果的ResultSet对象
    * @Anthor:孤傲苍狼
    *
    * @param conn
    * @param st
    * @param rs
    */ 
    public static void release(Connection conn,Statement st,ResultSet rs){
    	logger.info("release");
        if(rs!=null){
            try{
                //关闭存储查询结果的ResultSet对象
                rs.close();                
            }catch (Exception e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if(st!=null){
            try{
                //关闭负责执行SQL命令的Statement对象
                st.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        if(conn!=null){
            try{
                //将Connection连接对象还给数据库连接池
                conn.close();
                logger.info("conn.close()");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}