package vn.myclass.core.utils;

import vn.pbl.core.dto.RoleDTO;
import vn.pbl.core.persistence.entity.RoleEntity;

public class RoleBeanUtil {
    public static RoleDTO entity2Dto(RoleEntity entity) {
        RoleDTO dto = new RoleDTO();
        dto.setRoleId(entity.getRoleId());
        dto.setRoleName(entity.getRoleName());
        return dto;
    }
    public static RoleEntity dto2Entity(RoleDTO dto) {
        RoleEntity entity = new RoleEntity();
        entity.setRoleId(dto.getRoleId());
        entity.setRoleName(dto.getRoleName());
        return entity;
    }
}
