package vn.myclass.core.utils;


import vn.pbl.core.dto.UserDTO;
import vn.pbl.core.persistence.entity.UserEntity;

// ham de chuyen doi giua dto va entity
public class UserBeanUtil {
    public static UserDTO entity2Dto(UserEntity entity) {
        UserDTO dto = new UserDTO();
        dto.setUserId(entity.getUserId());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setGender(entity.getGender());
        dto.setAddress(entity.getAddress());
        dto.setPhone(entity.getPhone());
        dto.setRoleDTO(RoleBeanUtil.entity2Dto(entity.getRoleEntity()));
        dto.setIsVip(entity.getIsVip());
        dto.setIsActive(entity.getIsActive());
        return dto;
    }
    public static UserEntity dto2Entity(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setUserId(dto.getUserId());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setGender(dto.getGender());
        entity.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());
        entity.setRoleId(dto.getRoleDTO().getRoleId());
        entity.setRoleEntity(RoleBeanUtil.dto2Entity(dto.getRoleDTO()));
        entity.setIsVip(dto.getIsVip());
        entity.setIsActive(dto.getIsActive());
        return entity;
    }
}
