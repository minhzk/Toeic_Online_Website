package vn.pbl.core.service.impl;

import vn.myclass.core.utils.ExaminationBeanUtil;
import vn.myclass.core.utils.ExerciseBeanUtil;
import vn.pbl.core.dto.ExaminationDTO;
import vn.pbl.core.dto.ExerciseDTO;
import vn.pbl.core.persistence.entity.ExaminationEntity;
import vn.pbl.core.persistence.entity.ExerciseEntity;
import vn.pbl.core.service.ExaminationService;
import vn.pbl.core.service.utils.SingletonDaoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExaminationServiceImpl implements ExaminationService {
    @Override
    public Object[] findExaminationByProperties(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List<ExaminationDTO> result = new ArrayList<ExaminationDTO>();
        Object[] objects = SingletonDaoUtil.getExaminationDaoInstance().findByProperty(property, sortExpression, sortDirection, offset, limit, null);
        for (ExaminationEntity item: (List<ExaminationEntity>)objects[1]) {
            ExaminationDTO dto = ExaminationBeanUtil.entity2Dto(item);
            result.add(dto);
        }
        objects[1] = result;
        return objects;
    }
}
