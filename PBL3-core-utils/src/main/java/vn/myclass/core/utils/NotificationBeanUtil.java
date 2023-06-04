package vn.myclass.core.utils;

import vn.pbl.core.dto.NotificationDTO;
import vn.pbl.core.persistence.entity.NotificationEntity;

public class NotificationBeanUtil {
    public static NotificationDTO entity2Dto(NotificationEntity entity) {
        NotificationDTO dto = new NotificationDTO();
        dto.setNotificationId(entity.getNotificationId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
    public static NotificationEntity dto2Entity(NotificationDTO dto) {
        NotificationEntity entity = new NotificationEntity();
        entity.setTitle(dto.getTitle());
        entity.setNotificationId(dto.getNotificationId());
        entity.setContent(dto.getContent());
        entity.setCreatedDate(dto.getCreatedDate());
        return entity;
    }
}
