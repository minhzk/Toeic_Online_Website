package vn.pbl.core.service.impl;

import org.apache.commons.lang.StringUtils;
import vn.myclass.core.utils.UserBeanUtil;
import vn.pbl.core.dao.UserDao;
import vn.pbl.core.daoimpl.UserDaoImpl;
import vn.pbl.core.dto.CheckLogin;
import vn.pbl.core.dto.UserDTO;
import vn.pbl.core.dto.UserImportDTO;
import vn.pbl.core.persistence.entity.RoleEntity;
import vn.pbl.core.persistence.entity.UserEntity;
import vn.pbl.core.service.UserService;
import vn.pbl.core.service.utils.SingletonDaoUtil;

import java.util.*;

public class UserServiceImpl implements UserService {
    @Override
    public Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = SingletonDaoUtil.getUserDaoInstance().findByProperty(property,sortExpression,sortDirection,offset,limit,null);
        List<UserDTO> userDTOS = new ArrayList<UserDTO>();
        for (UserEntity item : (List<UserEntity>)objects[1]) {
            UserDTO userDTO = UserBeanUtil.entity2Dto(item);
            userDTOS.add(userDTO);
        }
        objects[1] = userDTOS;
        return objects;
    }

    @Override
    public UserDTO findById(Integer userId) {
        UserEntity entity = SingletonDaoUtil.getUserDaoInstance().findById(userId);
        UserDTO dto = UserBeanUtil.entity2Dto(entity);
        return dto;
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        UserEntity entity = UserBeanUtil.dto2Entity(userDTO);
        SingletonDaoUtil.getUserDaoInstance().save(entity);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        UserEntity entity = UserBeanUtil.dto2Entity(userDTO);
        entity = SingletonDaoUtil.getUserDaoInstance().update(entity);
        userDTO = UserBeanUtil.entity2Dto(entity);
        return userDTO;
    }

    @Override
    public CheckLogin checkLogin(String email, String password) {
        CheckLogin checkLogin = new CheckLogin();
        if(email != null && password != null) {
            Object[] objects = SingletonDaoUtil.getUserDaoInstance().checkLogin(email, password);
            checkLogin.setUserExist((Boolean) objects[0]);
            if(checkLogin.isUserExist()) {
                checkLogin.setRoleName(objects[1].toString());
            }
        }
        return checkLogin;
    }

    @Override
    public void validateImportUser(List<UserImportDTO> userImportDTOS) {
        List<String> emails = new ArrayList<String>();
        List<String> roles = new ArrayList<String>();

        for(UserImportDTO item: userImportDTOS) {
            if (item.isValid()) {
                emails.add(item.getEmail());
                if (!roles.contains(item.getRoleName())) {
                    roles.add(item.getRoleName());
                }
            }
        }
        Map<String, UserEntity> userEntityMap = new HashMap<String, UserEntity>();
        Map<String, RoleEntity> roleEntityMap = new HashMap<String, RoleEntity>();

        if(emails.size() > 0 ) {
            List<UserEntity> userEntities = SingletonDaoUtil.getUserDaoInstance().findByUsers(emails);
            for(UserEntity item: userEntities) {
                userEntityMap.put(item.getEmail().toUpperCase(), item);
            }
        }
        if(roles.size() > 0) {
            List<RoleEntity> roleEntities = SingletonDaoUtil.getRoleDaoInstance().findByRoles(roles);
            for(RoleEntity item : roleEntities) {
                roleEntityMap.put(item.getRoleName().toUpperCase(), item);
            }
        }
        for(UserImportDTO item: userImportDTOS ) {
            String message = item.getError();
            if(item.isValid()) {
                UserEntity userEntity = userEntityMap.get(item.getEmail().toUpperCase());
                if (userEntity != null) {
                    message += "<br/>";
                    message += "Email tồn tại";
                }
                RoleEntity roleEntity = roleEntityMap.get(item.getRoleName().toUpperCase());
                if (roleEntity == null) {
                    message += "<br/>";
                    message += "Vai trò không tồn tại";
                }
                if (StringUtils.isNotBlank(message)) {
                    item.setValid(false);
                    item.setError(message.substring(5));
                }
            }

        }
    }

    @Override
    public void saveUserImport(List<UserImportDTO> userImportDTOS) {
        for(UserImportDTO item: userImportDTOS) {
            if(item.isValid()) {
                UserEntity userEntity = new UserEntity();
                userEntity.setEmail(item.getEmail());
                userEntity.setPassword(item.getPassword());
                userEntity.setName(item.getName());
                userEntity.setAge(item.getAge());
                userEntity.setGender(item.getGender());
                userEntity.setAddress(item.getAddress());
                userEntity.setPhone(item.getPhone());
                RoleEntity roleEntity = SingletonDaoUtil.getRoleDaoInstance().findEqualUnique("roleName", item.getRoleName().toUpperCase());
                userEntity.setRoleEntity(roleEntity);
                userEntity.setRoleId(roleEntity.getRoleId());
                userEntity.setIsVip(item.getIsVip());
                userEntity.setIsActive(item.getIsActive());
                SingletonDaoUtil.getUserDaoInstance().save(userEntity);
            }
        }
    }

    @Override
    public Long maleCount(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = SingletonDaoUtil.getUserDaoInstance().findByProperty(property,sortExpression,sortDirection,offset,limit," and gender = 'male'");
        return (Long)objects[0];
    }

    @Override
    public Long femaleCount(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = SingletonDaoUtil.getUserDaoInstance().findByProperty(property,sortExpression,sortDirection,offset,limit,"and gender = 'female'");
        return (Long)objects[0];
    }

    @Override
    public Long nullGenderCount(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = SingletonDaoUtil.getUserDaoInstance().findByProperty(property,sortExpression,sortDirection,offset,limit,"and gender  IS NULL");
        return (Long)objects[0];
    }

    @Override
    public UserDTO findEqualUnique(String property, Object value) {
        UserEntity entity = SingletonDaoUtil.getUserDaoInstance().findEqualUnique(property,value);
        UserDTO dto = UserBeanUtil.entity2Dto(entity);
        return dto;
    }
}
