package vn.pbl.core.service.impl;

import org.hibernate.exception.ConstraintViolationException;
import vn.myclass.core.utils.ListenGuidelineBeanUtil;
import vn.pbl.core.dao.ListenGuidelineDao;
import vn.pbl.core.daoimpl.ListenGuidelineDaoImpl;
import vn.pbl.core.dto.ListenGuidelineDTO;
import vn.pbl.core.persistence.entity.ListenGuidelineEntity;
import vn.pbl.core.service.ListenGuidelineService;
import vn.pbl.core.service.utils.SingletonDaoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListenGuidelineServiceImpl implements ListenGuidelineService {
    @Override
    public Object[] findListenGuidelineByProperties(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List<ListenGuidelineDTO> result = new ArrayList<ListenGuidelineDTO>();
        Object[] objects = SingletonDaoUtil.getListenGuidelineDaoInstance().findByProperty(property, sortExpression, sortDirection, offset, limit,null);
        for(ListenGuidelineEntity item: (List<ListenGuidelineEntity>)objects[1]) {
            ListenGuidelineDTO dto = ListenGuidelineBeanUtil.entity2Dto(item);
            result.add(dto);
        }
        objects[1] = result;
        return objects;
    }

    @Override
    public ListenGuidelineDTO findByListenGuidelineId(String property, Integer listenGuidelineId) {
        ListenGuidelineEntity entity = SingletonDaoUtil.getListenGuidelineDaoInstance().findEqualUnique(property, listenGuidelineId);
        ListenGuidelineDTO dto = ListenGuidelineBeanUtil.entity2Dto(entity);
        return dto;
    }

    @Override
    public void saveListenGuideline(ListenGuidelineDTO dto) throws ConstraintViolationException {
        ListenGuidelineEntity entity = ListenGuidelineBeanUtil.dto2Entity(dto);
        SingletonDaoUtil.getListenGuidelineDaoInstance().save(entity);
    }

    @Override
    public ListenGuidelineDTO updateListenGuideline(ListenGuidelineDTO dto) {
        ListenGuidelineEntity entity = ListenGuidelineBeanUtil.dto2Entity(dto);
        entity = SingletonDaoUtil.getListenGuidelineDaoInstance().update(entity);
        dto = ListenGuidelineBeanUtil.entity2Dto(entity);
        return dto;
    }

    @Override
    public Integer delete(List<Integer> ids) {
        Integer result  = SingletonDaoUtil.getListenGuidelineDaoInstance().delete(ids);
        return result;
    }

    @Override
    public Integer count() {
        List<ListenGuidelineEntity> list = SingletonDaoUtil.getListenGuidelineDaoInstance().findAll();
        return list.size();
    }
}
