package vn.pbl.core.dto;

import java.io.Serializable;
import java.util.List;

public class ReadGuidelineDTO implements Serializable {
    private Integer readGuidelineId;
    private String title;
    private String content;
    private String image;
    private List<CommentReadDTO> commentReads;

    public Integer getReadGuidelineId() {
        return readGuidelineId;
    }

    public void setReadGuidelineId(Integer readGuidelineId) {
        this.readGuidelineId = readGuidelineId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<CommentReadDTO> getCommentReads() {
        return commentReads;
    }

    public void setCommentReads(List<CommentReadDTO> commentReads) {
        this.commentReads = commentReads;
    }
}
