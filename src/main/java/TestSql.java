import com.imooc.mybatis.util.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestSql {

    @Test
    public void demo1(){

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        conn = JdbcUtil.getConnection();
        JdbcUtil.begin( conn );
        String sql = "insert into person(username,email) values(?,?)";
        try {
            preparedStatement = conn.prepareStatement( sql );
            long startTime,endTime;
            startTime = System.currentTimeMillis();

            for(int i=0;i<1000;i++){
                preparedStatement.setString( 1,"user" + (i + 1) );
                preparedStatement.setString( 2,"adog@" + (i+1) + ".com" );
                //这一步不能省略
                preparedStatement.executeUpdate();
            }

            JdbcUtil.commit( conn );
            endTime = System.currentTimeMillis();
            System.out.println( endTime - startTime );



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
