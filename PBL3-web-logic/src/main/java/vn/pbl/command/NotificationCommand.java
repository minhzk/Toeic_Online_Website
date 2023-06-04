package vn.pbl.command;

import vn.pbl.core.dto.NotificationDTO;
import vn.pbl.core.web.command.AbstractCommand;

public class NotificationCommand extends AbstractCommand<NotificationDTO> {
    public  NotificationCommand() {
        this.pojo = new NotificationDTO();
    }
}
