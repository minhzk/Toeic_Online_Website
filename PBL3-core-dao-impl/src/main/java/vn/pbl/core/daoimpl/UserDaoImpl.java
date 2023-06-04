package vn.pbl.core.daoimpl;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import vn.pbl.core.common.util.HibernateUtil;
import vn.pbl.core.dao.UserDao;
import vn.pbl.core.data.daoimpl.AbstractDao;
import vn.pbl.core.persistence.entity.RoleEntity;
import vn.pbl.core.persistence.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl extends AbstractDao<Integer, UserEntity> implements UserDao {
    @Override
    public Object[] checkLogin(String email, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        boolean isUserExist = false;
        String roleName = null;
        try {
            StringBuilder sql = new StringBuilder("FROM UserEntity ue WHERE ue.email= :email AND ue.password= :password  ");
            Query query = session.createQuery(sql.toString());
            query.setParameter("email", email);
            query.setParameter("password", password);
            if(query.list().size() >0 ) {
                isUserExist = true;
                UserEntity userEntity = (UserEntity) query.uniqueResult();
                roleName = userEntity.getRoleEntity().getRoleName();
            }
        }catch (HibernateException e) {
            transaction.rollback();
            throw  e;
        }finally {
            session.close();
        }
        return new Object[]{isUserExist, roleName};
    }

    @Override
    public List<UserEntity> findByUsers(List<String> emails) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<UserEntity> userEntities = new ArrayList<UserEntity>();
        try {
            StringBuilder sql = new StringBuilder("FROM UserEntity ue WHERE ue.email IN(:emails) ");
            Query query = session.createQuery(sql.toString());
            query.setParameterList("emails", emails);
            userEntities = query.list();
        }catch (HibernateException e) {
            transaction.rollback();
            throw  e;
        }finally {
            session.close();
        }
        return userEntities;
    }
}
