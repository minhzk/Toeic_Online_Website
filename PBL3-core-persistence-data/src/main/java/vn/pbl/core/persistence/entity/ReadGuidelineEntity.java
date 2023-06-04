package vn.pbl.core.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "readguideline")
public class ReadGuidelineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer readGuidelineId;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;

    @Column(name = "image")
    private String image;
    @OneToMany(mappedBy = "readGuidelineEntity", fetch = FetchType.LAZY)
    private List<CommentReadEntity> commentReadEntityList;

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

    public List<CommentReadEntity> getCommentReadEntityList() {
        return commentReadEntityList;
    }

    public void setCommentReadEntityList(List<CommentReadEntity> commentReadEntityList) {
        this.commentReadEntityList = commentReadEntityList;
    }
}
