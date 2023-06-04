package vn.pbl.core.dao;

import vn.pbl.core.data.dao.GenericDao;
import vn.pbl.core.persistence.entity.RoleEntity;

import java.util.List;

public interface RoleDao extends GenericDao<Integer, RoleEntity> {
    List<RoleEntity> findByRoles(List<String> roles);
}
