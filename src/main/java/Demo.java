import com.imooc.mybatis.dao.PersonMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

public class Demo {
    public static SqlSessionFactory sqlSessionFactory = null;

    public static SqlSessionFactory getSqlSessionFactory(){
        if(sqlSessionFactory == null){
            String resource  = "spring.xml";
            try {
                Reader reader = Resources.getResourceAsReader( resource );
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return sqlSessionFactory;
    }

    /**
     * 根据ID删除所对应的Person数据
     */

    @Test
    public void deletePerson(){
        SqlSession sqlSession = this.getSqlSessionFactory().openSession();
        PersonMapper personMapper = sqlSession.getMapper( PersonMapper.class );
        personMapper.deletePerson( 3 );
        sqlSession.commit();
    }
}
