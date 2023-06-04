package vn.pbl.core.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class CommentReadDTO implements Serializable {
    private Integer commentId;
    private String content;
    private UserDTO user;
    private ReadGuidelineDTO readGuideline;
    private Timestamp createdDate;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public ReadGuidelineDTO getReadGuideline() {
        return readGuideline;
    }

    public void setReadGuideline(ReadGuidelineDTO readGuideline) {
        this.readGuideline = readGuideline;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
}
