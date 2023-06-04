package vn.pbl.core.service.impl;

import vn.myclass.core.utils.RoleBeanUtil;
import vn.pbl.core.dao.RoleDao;
import vn.pbl.core.daoimpl.RoleDaoImpl;
import vn.pbl.core.dto.RoleDTO;
import vn.pbl.core.persistence.entity.RoleEntity;
import vn.pbl.core.service.RoleService;
import vn.pbl.core.service.utils.SingletonDaoUtil;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceImpl implements RoleService {
    public List<RoleDTO> findALl() {
        List<RoleEntity> entities = SingletonDaoUtil.getRoleDaoInstance().findAll();
        List<RoleDTO> dtos = new ArrayList<RoleDTO>();
        for(RoleEntity item: entities) {
            RoleDTO dto = RoleBeanUtil.entity2Dto(item);
            dtos.add(dto);
        }
        return dtos;
    }

}
