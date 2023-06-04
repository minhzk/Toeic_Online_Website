package vn.pbl.core.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "listenguideline")
public class ListenGuidelineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer listenGuidelineId;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "listenGuidelineEntity", fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList;

    public Integer getListenGuidelineId() {
        return listenGuidelineId;
    }

    public void setListenGuidelineId(Integer listenGuidelineId) {
        this.listenGuidelineId = listenGuidelineId;
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
    public List<CommentEntity> getCommentEntityList() {
        return commentEntityList;
    }

    public void setCommentEntityList(List<CommentEntity> commentEntityList) {
        this.commentEntityList = commentEntityList;
    }


}
