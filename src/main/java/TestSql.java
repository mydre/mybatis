import com.imooc.mybatis.util.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestSql {

    private Connection conn = JdbcUtil.getConnection();
    private PreparedStatement preparedStatement = null;
    private String sql = "insert into person(username,email) values(?,?)";
    private long startTime,endTime;

    @Test
    public void demo1(){

        JdbcUtil.begin( conn );//set AutoCommit = False
        try {
            preparedStatement = conn.prepareStatement( sql );

            startTime = System.currentTimeMillis();

            for(int i=0;i<10000;i++){
                preparedStatement.setString( 1,"user" + (i + 1) );
                preparedStatement.setString( 2,"adog@" + (i+1) + ".com" );
                //这一步不能省略
                preparedStatement.executeUpdate();
            }

            JdbcUtil.commit( conn );
            //关闭连接
            JdbcUtil.closeResources( conn,preparedStatement,null );
            endTime = System.currentTimeMillis();
            System.out.println( endTime - startTime );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testBatchUpdate(){

        JdbcUtil.begin( conn );//set AutoCommit = False
        try {
            preparedStatement = conn.prepareStatement( sql );

            startTime = System.currentTimeMillis();

            for(int i=0;i<10000;i++){
                preparedStatement.setString( 1,"user" + (i + 1) );
                preparedStatement.setString( 2,"adog@" + (i+1) + ".com" );
                preparedStatement.addBatch();
                //这一步不能省略(进行批量更新)
                if((i+1)%1000 == 0){
                    preparedStatement.executeBatch();
                    preparedStatement.clearBatch();
                }
            }

            JdbcUtil.commit( conn );
            //关闭连接
            JdbcUtil.closeResources( conn,preparedStatement,null );
            endTime = System.currentTimeMillis();
            System.out.println( endTime - startTime );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

