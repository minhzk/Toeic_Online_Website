package vn.myclass.core.utils;

import vn.pbl.core.dto.ListenGuidelineDTO;
import vn.pbl.core.dto.ReadGuidelineDTO;
import vn.pbl.core.persistence.entity.ListenGuidelineEntity;
import vn.pbl.core.persistence.entity.ReadGuidelineEntity;

public class ReadGuidelineBeanUtil {
    public static ReadGuidelineDTO entity2Dto(ReadGuidelineEntity entity) {
        ReadGuidelineDTO dto = new ReadGuidelineDTO();
        dto.setReadGuidelineId(entity.getReadGuidelineId());
        dto.setContent(entity.getContent());
        dto.setTitle((entity.getTitle()));
        dto.setImage(entity.getImage());
        return dto;
    }
    public static ReadGuidelineEntity dto2Entity(ReadGuidelineDTO dto) {
        ReadGuidelineEntity entity = new ReadGuidelineEntity();
        entity.setReadGuidelineId(dto.getReadGuidelineId());
        entity.setContent(dto.getContent());
        entity.setTitle(dto.getTitle());
        entity.setImage(dto.getImage());
        return entity;
    }
}
