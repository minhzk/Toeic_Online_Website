package vn.pbl.core.dto;

import java.io.Serializable;
import java.util.List;

public class ListenGuidelineDTO implements Serializable {
    private Integer listenGuidelineId;
    private String title;
    private String content;
    private String image;
    private List<CommentDTO> comments;
    public Integer getListenGuidelineId() {
        return listenGuidelineId;
    }

    public void setListenGuidelineId(Integer listenGuideline) {
        this.listenGuidelineId = listenGuideline;
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

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }
}
