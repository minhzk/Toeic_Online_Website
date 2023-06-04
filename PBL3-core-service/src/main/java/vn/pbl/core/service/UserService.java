package vn.pbl.core.service;

import vn.pbl.core.dto.CheckLogin;
import vn.pbl.core.dto.UserDTO;
import vn.pbl.core.dto.UserImportDTO;

import java.util.List;
import java.util.Map;

public interface UserService {
    Object[] findByProperty(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    UserDTO findById(Integer userId);
    void saveUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO);
    CheckLogin checkLogin(String email, String password);
    void validateImportUser(List<UserImportDTO> userImportDTOS);
    void saveUserImport(List<UserImportDTO> userImportDTOS);
    Long maleCount(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    Long femaleCount(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    Long nullGenderCount(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    UserDTO findEqualUnique(String property, Object value);
}
