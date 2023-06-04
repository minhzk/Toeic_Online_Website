package vn.pbl.core.dto;


import vn.pbl.core.persistence.entity.RoleEntity;

import java.io.Serializable;
import java.util.List;

public class UserDTO implements Serializable {
    private Integer userId;
    private String email;
    private String password;
    private String name;
    private Integer age;
    private String gender;
    private String address;
    private String phone;
    private Integer isVip;
    private Integer isActive;
    private RoleDTO roleDTO;
    private UserImportDTO userImportDTO;
    private List<ResultDTO> results;
    private List<CommentDTO> comments;
    private List<CommentReadDTO> commentReads;
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getIsVip() {
        return isVip;
    }

    public void setIsVip(Integer isVip) {
        this.isVip = isVip;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public RoleDTO getRoleDTO() {
        return roleDTO;
    }

    public void setRoleDTO(RoleDTO roleDTO) {
        this.roleDTO = roleDTO;
    }

    public UserImportDTO getUserImportDTO() {
        return userImportDTO;
    }

    public void setUserImportDTO(UserImportDTO userImportDTO) {
        this.userImportDTO = userImportDTO;
    }

    public List<ResultDTO> getResults() {
        return results;
    }

    public void setResults(List<ResultDTO> results) {
        this.results = results;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public List<CommentReadDTO> getCommentReads() {
        return commentReads;
    }

    public void setCommentReads(List<CommentReadDTO> commentReads) {
        this.commentReads = commentReads;
    }
}
