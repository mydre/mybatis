import com.imooc.mybatis.bean.Person;
import com.imooc.mybatis.dao.PersonMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import java.util.*;

public class Demo {

    private SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSessionFactory().openSession();
    private PersonMapper personMapper = sqlSession.getMapper( PersonMapper.class );

    /**
     * 根据ID删除所对应的Person数据，传入单个参数
     */
    @Test
    public void deletePerson(){
        personMapper.deletePerson( 7 );
        sqlSession.commit();
    }


    @Test
    public void selectByNameAndGender(){
        List<Person> list = personMapper.selectByNameAndGender( "tom","F" );
        System.out.println( list );
    }

    /**
     * 出入map参数
     */
    @Test
    public void selectByNameAndGender2(){
        Map<String,Object> map = new HashMap<String,Object>(  );
        map.put( "name","tom" );
        map.put( "sex","F" );
        List<Person> list = personMapper.selectByNameAndGender2(map);
        System.out.println( list );
    }

    /**
     * 通过@Param注解传入参数
     */
    @Test
    public void selectByNameAndGender3(){
        List<Person> list = personMapper.selectByNameAndGender3("tom","F");
        System.out.println( list );
    }

    /**
     * 集合类型作为参数
     */
    @Test
    public void selectByCollection(){
        List<Person> list = personMapper.selectByCollection(Arrays.asList( 2,8 ));
        System.out.println( list );
    }

    /**
     * 以数组作为参数
     */
    @Test
    public void selectByArray(){
        List<Person> list = personMapper.selectByArray(new Integer[]{1,2,8});
        System.out.println( list );
    }

    /**
     * 以数组作为参数，使用foreach循环
     */
    @Test
    public void selectByIds(){
        List<Person> list = personMapper.selectByIds(new Integer[]{1,2,8});
        System.out.println( list );
    }
}
