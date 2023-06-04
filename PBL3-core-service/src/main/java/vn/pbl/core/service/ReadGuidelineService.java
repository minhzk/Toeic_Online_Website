package vn.pbl.core.service;

import org.hibernate.exception.ConstraintViolationException;
import vn.pbl.core.dto.ListenGuidelineDTO;
import vn.pbl.core.dto.ReadGuidelineDTO;

import java.util.List;
import java.util.Map;

public interface ReadGuidelineService {
    Object[] findReadGuidelineByProperties(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);
    ReadGuidelineDTO findByReadGuidelineId(String property, Integer listenGuidelineId) ;
    void saveReadGuideline(ReadGuidelineDTO dto) throws ConstraintViolationException;
    ReadGuidelineDTO updateReadGuideline(ReadGuidelineDTO dto);
    Integer delete(List<Integer> ids);
    Integer count();
}
