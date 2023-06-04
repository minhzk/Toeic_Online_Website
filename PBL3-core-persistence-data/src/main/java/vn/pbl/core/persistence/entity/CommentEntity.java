package vn.pbl.core.persistence.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer commentId;

    @Column(name = "content")
    private String content;

    @Column(name = "createddate")
    private Timestamp createdDate;

    @ManyToOne
    @JoinColumn(name="userid")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name="listenguidelineid")
    private ListenGuidelineEntity listenGuidelineEntity;


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

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public UserEntity getUser() {
        return userEntity;
    }

    public void setUser(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ListenGuidelineEntity getListenGuideline() {
        return listenGuidelineEntity;
    }

    public void setListenGuideline(ListenGuidelineEntity listenGuidelineEntity) {
        this.listenGuidelineEntity = listenGuidelineEntity;
    }

}
