import com.imooc.mybatis.util.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcTest {

    private Connection conn = JdbcUtil.getConnection();
    private PreparedStatement preparedStatement = null;
    private String sql = "insert into person(username,email) values(?,?)";
    private long startTime,endTime;

    /**
     * 方式一，使用for循环，来一条一条的插入数据，用时两千多毫秒
     */
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
                preparedStatement.executeUpdate();//执行
            }

            JdbcUtil.commit( conn );//提交事务
            //关闭连接
            JdbcUtil.closeResources( conn,preparedStatement,null );
            endTime = System.currentTimeMillis();
            System.out.println( endTime - startTime );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 批量插入，用时两百多毫秒
     */
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
                if((i+1)%1000 == 0){//批量处理，1000条数据作为一批，时间254毫秒
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

