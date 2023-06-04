package vn.myclass.core.utils;

import vn.pbl.core.dto.ListenGuidelineDTO;
import vn.pbl.core.persistence.entity.ListenGuidelineEntity;

public class ListenGuidelineBeanUtil {
    public static ListenGuidelineDTO entity2Dto(ListenGuidelineEntity entity) {
        ListenGuidelineDTO dto = new ListenGuidelineDTO();
        dto.setListenGuidelineId(entity.getListenGuidelineId());
        dto.setContent(entity.getContent());
        dto.setTitle((entity.getTitle()));
        dto.setImage(entity.getImage());
        return dto;
    }
    public static ListenGuidelineEntity dto2Entity(ListenGuidelineDTO dto) {
        ListenGuidelineEntity entity = new ListenGuidelineEntity();
        entity.setListenGuidelineId(dto.getListenGuidelineId());
        entity.setContent(dto.getContent());
        entity.setTitle(dto.getTitle());
        entity.setImage(dto.getImage());
        return entity;
    }
}
