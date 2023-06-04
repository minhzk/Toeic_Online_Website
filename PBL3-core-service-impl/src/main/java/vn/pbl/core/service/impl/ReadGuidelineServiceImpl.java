package vn.pbl.core.service.impl;

import org.hibernate.exception.ConstraintViolationException;
import vn.myclass.core.utils.ListenGuidelineBeanUtil;
import vn.myclass.core.utils.ReadGuidelineBeanUtil;
import vn.pbl.core.dto.ListenGuidelineDTO;
import vn.pbl.core.dto.ReadGuidelineDTO;
import vn.pbl.core.persistence.entity.ListenGuidelineEntity;
import vn.pbl.core.persistence.entity.ReadGuidelineEntity;
import vn.pbl.core.service.ReadGuidelineService;
import vn.pbl.core.service.utils.SingletonDaoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReadGuidelineServiceImpl implements ReadGuidelineService {
    @Override
    public Object[] findReadGuidelineByProperties(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List<ReadGuidelineDTO> result = new ArrayList<ReadGuidelineDTO>();
        Object[] objects = SingletonDaoUtil.getReadGuidelineDaoInstance().findByProperty(property, sortExpression, sortDirection, offset, limit,null);
        for(ReadGuidelineEntity item: (List<ReadGuidelineEntity>)objects[1]) {
            ReadGuidelineDTO dto = ReadGuidelineBeanUtil.entity2Dto(item);
            result.add(dto);
        }
        objects[1] = result;
        return objects;
    }

    @Override
    public ReadGuidelineDTO findByReadGuidelineId(String property, Integer readGuidelineId) {
        ReadGuidelineEntity entity = SingletonDaoUtil.getReadGuidelineDaoInstance().findEqualUnique(property, readGuidelineId);
        ReadGuidelineDTO dto = ReadGuidelineBeanUtil.entity2Dto(entity);
        return dto;
    }

    @Override
    public void saveReadGuideline(ReadGuidelineDTO dto) throws ConstraintViolationException {
        ReadGuidelineEntity entity = ReadGuidelineBeanUtil.dto2Entity(dto);
        SingletonDaoUtil.getReadGuidelineDaoInstance().save(entity);
    }

    @Override
    public ReadGuidelineDTO updateReadGuideline(ReadGuidelineDTO dto) {
        ReadGuidelineEntity entity = ReadGuidelineBeanUtil.dto2Entity(dto);
        entity = SingletonDaoUtil.getReadGuidelineDaoInstance().update(entity);
        dto = ReadGuidelineBeanUtil.entity2Dto(entity);
        return dto;
    }

    @Override
    public Integer delete(List<Integer> ids) {
        Integer result  = SingletonDaoUtil.getReadGuidelineDaoInstance().delete(ids);
        return result;
    }
    @Override
    public Integer count() {
        List<ReadGuidelineEntity> list = SingletonDaoUtil.getReadGuidelineDaoInstance().findAll();
        return list.size();
    }
}
