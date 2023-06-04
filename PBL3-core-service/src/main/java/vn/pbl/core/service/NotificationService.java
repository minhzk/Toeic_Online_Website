package vn.pbl.core.service;

import vn.pbl.core.dto.NotificationDTO;

import java.util.List;

public interface NotificationService {
    List<NotificationDTO> findAll();
    Integer delete(List<Integer> ids);
    void add(NotificationDTO dto);
}
