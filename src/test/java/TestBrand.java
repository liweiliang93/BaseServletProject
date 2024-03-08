import com.weiliang.entity.Brand;
import com.weiliang.mapper.BrandMapper;
import com.weiliang.service.BrandService;
import com.weiliang.service.impl.BrandServcieImpl;
import com.weiliang.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

/**
 * @author liweiliang
 * @description 1.0
 * @date 2024-03-07 17:23:20
 */
public class TestBrand {

    BrandService brandServcie = new BrandServcieImpl();
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
    @Test
    public void testSelectAll() {
        System.out.println(brandServcie.selectAll());
    }
    @Test
    public void testSelectById(){
        int id = 16;
        System.out.println(brandServcie.selectById(id));
    }
    @Test
    public void testSelectByPage(){
        int currentPage = 1;
        int pageSize = 5;
        System.out.println(brandServcie.selectByPage(currentPage, pageSize));
    }
    @Test
    public void testSelectTotalCountByCondition(){
        Brand brand = new Brand();
        brand = null;
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        System.out.println(brandMapper.selectTotalCountByCondition(brand));
    }
    @Test
    public void testSelectByCondition(){
        Brand brand = new Brand();
        brand.setBrandName("华为");
        brand.setStatus(null);
        brand.setCompanyName(null);
        System.out.println(brandServcie.selectByCondition(brand));

    }
    @Test
    public void testSelectByPageAndCondition(){
        Brand brand = new Brand();
        brand.setBrandName("华为");
        brand.setStatus(null);
        brand.setCompanyName(null);
        int currentPage = 1;
        int pageSize = 5;
        System.out.println(brandServcie.selectByPageAndCondition(currentPage, pageSize, brand));
    }
}
