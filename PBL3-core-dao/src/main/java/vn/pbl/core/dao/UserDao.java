package vn.pbl.core.dao;

import vn.pbl.core.data.dao.GenericDao;
import vn.pbl.core.persistence.entity.RoleEntity;
import vn.pbl.core.persistence.entity.UserEntity;

import java.util.List;

public interface UserDao extends GenericDao<Integer, UserEntity> {
    Object[] checkLogin(String email, String password);
    List<UserEntity> findByUsers(List<String> emails);
}
