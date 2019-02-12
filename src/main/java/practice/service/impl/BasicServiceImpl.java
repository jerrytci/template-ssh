package practice.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.dao.BasicDao;
import practice.service.BasicService;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class BasicServiceImpl implements BasicService {

    @Resource
    @Qualifier("basicDaoImpl")
    private BasicDao basicDao;

    @Override
    public <T> T get(Class<T> tClass, long id) {
        return basicDao.get(tClass, id);
    }

    @Override
    public <T> List<T> get(Class<T> tClass, List ids) {
        return basicDao.get(tClass, ids);
    }

    @Override
    public <T> List<T> get(String hql) {
        return basicDao.getHqlResult(hql);
    }

    @Override
    public <T> List<T> list(Class<T> tClass) {
        return basicDao.list(tClass);
    }

    @Transactional
    @Override
    public <T> List<T> list(Class<T> tClass, String orderBy) {
        return basicDao.list(tClass, orderBy);
    }

    @Override
    public <T> void save(T t) {
        basicDao.save(t);
    }

    @Override
    public <T> void delete(T t) {
        basicDao.delete(t);
    }
}
