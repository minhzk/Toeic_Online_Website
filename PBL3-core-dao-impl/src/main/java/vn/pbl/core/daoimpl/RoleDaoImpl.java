package vn.pbl.core.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import vn.pbl.core.common.util.HibernateUtil;
import vn.pbl.core.dao.RoleDao;
import vn.pbl.core.data.daoimpl.AbstractDao;
import vn.pbl.core.persistence.entity.RoleEntity;


import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl extends AbstractDao<Integer, RoleEntity> implements RoleDao {
    @Override
    public List<RoleEntity> findByRoles(List<String> roles) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<RoleEntity> roleEntities = new ArrayList<RoleEntity>();
        try {
            StringBuilder sql = new StringBuilder(" FROM RoleEntity re WHERE re.roleName IN(:roles) ");
            Query query = session.createQuery(sql.toString());
            query.setParameterList("roles", roles);
            roleEntities = query.list();
        }catch (HibernateException e) {
            transaction.rollback();
            throw  e;
        }finally {
            session.close();
        }
        return roleEntities;
    }
}
