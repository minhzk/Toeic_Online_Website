package vn.pbl.command;

import vn.pbl.core.dto.RoleDTO;
import vn.pbl.core.dto.UserDTO;
import vn.pbl.core.dto.UserImportDTO;
import vn.pbl.core.web.command.AbstractCommand;

import java.util.List;

public class UserCommand extends AbstractCommand<UserDTO> {

    public UserCommand() {
        this.pojo = new UserDTO();

    }
    private String confirmPassword;
    private List<RoleDTO> roles;
    private Integer roleId;
    private List<UserImportDTO> userImportDTOS;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<UserImportDTO> getUserImportDTOS() {
        return userImportDTOS;
    }

    public void setUserImportDTOS(List<UserImportDTO> userImportDTOS) {
        this.userImportDTOS = userImportDTOS;
    }
}

//checked<%--checked: 4/3 22:22--%>