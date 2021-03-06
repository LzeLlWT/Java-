package User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 项目名称：Try
 * 类名称：TestJSch
 * 类描述：TODO
 * 创建人：李政恩
 * 创建时间：2018年8月6日 下午1:40:50
 */
public class TestJSch {

//  public  DBTest(){};
//  public  DBTest(String a){};

    //ip+port
    static String URL ="jdbc:mysql://111.230.72.15:3306/mydb";
    //uid
    static String userName="root"; //
    //pwd
    static String userPassword="root";   //这里输入自己的密码
    //mysql SQL;
    static String SQL="select age from doctor";
    //mysql connection
    static Connection conn =null;
    //prestatement
    static PreparedStatement pst = null;
    //resultSet
    static ResultSet rs = null;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("驱动加载成功！");
        } catch (ClassNotFoundException e) {
            System.out.println("没有找到驱动！");
            e.printStackTrace();
        }
    }
    public static void conectDB(String URL,String userName,String userPassword){
        try {
            conn = DriverManager.getConnection(URL, userName, userPassword);
            System.out.println("已连接上"+URL+"：\n数据库"+conn);
        } catch (SQLException e) {
            System.out.println("获取链接失败");
            e.printStackTrace();
        }
    }
    public static void querryByid(String SQL){
        try {
            pst = conn.prepareStatement(SQL);
            System.out.println("执行查询语");
            rs = pst.executeQuery();
            while(rs.next()){
                System.out.println( rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("查询失败");
            e.printStackTrace();
        }
    }
    public static void close(Connection conn,PreparedStatement pst, ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                if(pst!=null){
                    try {
                        pst.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }finally{
                        if(conn!=null){
                            try {
                                conn.close();
                                System.out.println("数据库链接已关闭，资源已释放");
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }finally{
                                conn=null;
                                pst= null;
                                rs = null;
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
       /* TestJSch dbUtil = new TestJSch();
        dbUtil.conectDB(URL, userName, userPassword);
        dbUtil.querryByid(SQL);
        dbUtil.close(conn, pst, rs);*/
    }
}
