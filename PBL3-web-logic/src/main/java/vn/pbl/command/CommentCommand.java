package vn.pbl.command;

import vn.pbl.core.dto.CommentDTO;
import vn.pbl.core.dto.ListenGuidelineDTO;
import vn.pbl.core.dto.UserDTO;
import vn.pbl.core.web.command.AbstractCommand;

public class CommentCommand extends AbstractCommand<CommentDTO> {
    public CommentCommand() {this.pojo = new CommentDTO();}
    private String content;
    private UserDTO user;
    private ListenGuidelineDTO listenGuidelineDTO;
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

    public ListenGuidelineDTO getListenGuidelineDTO() {
        return listenGuidelineDTO;
    }

    public void setListenGuidelineDTO(ListenGuidelineDTO listenGuidelineDTO) {
        this.listenGuidelineDTO = listenGuidelineDTO;
    }
}
