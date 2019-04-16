import com.imooc.mybatis.bean.Person;
import com.imooc.mybatis.dao.PersonMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo {
    /**
     * 根据ID删除所对应的Person数据
     */
    private SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSessionFactory().openSession();
    private PersonMapper personMapper = sqlSession.getMapper( PersonMapper.class );
    @Test
    public void deletePerson(){
        personMapper.deletePerson( 6 );
        sqlSession.commit();
    }
    @Test
    public void selectByNameAndGender(){
        List<Person> list = personMapper.selectByNameAndGender( "tom","F" );
        System.out.println( list );
    }

    @Test
    public void selectByNameAndGender2(){
        Map<String,Object> map = new HashMap<String,Object>(  );
        map.put( "name","tom" );
        map.put( "sex","F" );
        List<Person> list = personMapper.selectByNameAndGender2(map);
        System.out.println( list );
    }
}
