package test.demo.shujukudemo;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import test.mapper.TbProductsMapper;

import java.sql.PreparedStatement;

/**
 * 数据批处理
 */
public class SqlsessionDemo {
    //原生statement   PreparedStatement 也可以实现批处理
    //statement.addBatch增加数据     statement.executeBatch执行

    private static SqlSessionFactory sqlSessionFactory;
    public static void main(String[] args) {
        //获取sqlsession
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        //获取要执行的mapper
        TbProductsMapper mapper = sqlSession.getMapper(TbProductsMapper.class);
        //填充sql需要的数据
        mapper.insert("a");
        mapper.insert("b");
        //批量执行
        sqlSession.commit();

    }
}
