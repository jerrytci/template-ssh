package practice.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import practice.dao.BasicDao;

import java.util.List;


@Repository
public class BasicDaoImpl extends HibernateDaoSupport implements BasicDao {

    //    HibernateDaoSupport 有个 private 变量 hibernateTemplate,需要注入或者通过sessionFatory来生成。不如会报异常：'sessionFactory' or 'hibernateTemplate' is required
//    https://blog.csdn.net/geekjoker/article/details/9421863
    @Autowired
    public void setSessionFactoryOverride(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public <T> T get(Class<T> tClass, long id) {
        String sql = "from " + tClass.getTypeName() + " where id = " + id;
        return getSessionFactory().getCurrentSession().createQuery(sql, tClass).uniqueResult();
    }

    @Override
    public <T> List<T> get(Class<T> tClass, List ids) {
        String sql = "from " + tClass.getTypeName() + " where id in (:ids)";
        return getSessionFactory().getCurrentSession().createQuery(sql, tClass)
                .setParameterList("ids", ids)
                .list();
    }

    @Override
    public <T> List<T> getHqlResult(String hql) {
        return (List<T>) getHibernateTemplate().find(hql);
    }

    @Override
    public <T> List<T> list(Class<T> tClass) {
        String sql = "from " + tClass.getTypeName();
        return getSessionFactory().getCurrentSession().createQuery(sql, tClass).list();
    }

    @Override
    public <T> List<T> list(Class<T> tClass, String orderBy) {
        String sql = orderBy == null || orderBy.equals("")
                ? "from " + tClass.getTypeName()
                : "from " + tClass.getTypeName() + " order by " + orderBy + " asc";
        return getSessionFactory().getCurrentSession().createQuery(sql, tClass).list();
    }

    @Override
    public <T> void save(T t) {
        getSessionFactory().getCurrentSession().saveOrUpdate(t);
    }

    @Override
    public <T> void delete(T t) {
        getSessionFactory().getCurrentSession().delete(t);
    }
}
