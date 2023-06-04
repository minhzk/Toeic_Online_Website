package vn.pbl.core.service.impl;

import vn.myclass.core.utils.NotificationBeanUtil;
import vn.pbl.core.dto.NotificationDTO;
import vn.pbl.core.persistence.entity.NotificationEntity;
import vn.pbl.core.service.NotificationService;
import vn.pbl.core.service.utils.SingletonDaoUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class NotificationServiceImpl implements NotificationService {

    @Override
    public List<NotificationDTO> findAll() {
        List<NotificationEntity> notificationEntities = SingletonDaoUtil.getNotificationDaoInstance().findAll();
        List<NotificationDTO> notificationDTOS = new ArrayList<NotificationDTO>();
        for(NotificationEntity entity : notificationEntities) {
            NotificationDTO dto = NotificationBeanUtil.entity2Dto(entity);
            notificationDTOS.add(dto);
        }
        return notificationDTOS;
    }

    @Override
    public Integer delete(List<Integer> ids) {
        Integer result  = SingletonDaoUtil.getNotificationDaoInstance().delete(ids);
        return result;
    }

    @Override
    public void add(NotificationDTO dto) {
        NotificationEntity entity = NotificationBeanUtil.dto2Entity(dto);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        entity.setCreatedDate(timestamp);
        SingletonDaoUtil.getNotificationDaoInstance().save(entity);
    }
}
