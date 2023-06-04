package vn.pbl.core.service.impl;

import vn.myclass.core.utils.ExerciseBeanUtil;
import vn.pbl.core.dto.ExerciseDTO;
import vn.pbl.core.persistence.entity.ExerciseEntity;
import vn.pbl.core.service.ExerciseService;
import vn.pbl.core.service.utils.SingletonDaoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExerciseServiceImpl implements ExerciseService {
    @Override
    public Object[] findExerciseByProperties(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List<ExerciseDTO> result = new ArrayList<ExerciseDTO>();
        Object[] objects = SingletonDaoUtil.getExerciseDaoInstance().findByProperty(property, sortExpression, sortDirection, offset, limit,null);
        for (ExerciseEntity item: (List<ExerciseEntity>)objects[1]) {
            ExerciseDTO dto = ExerciseBeanUtil.entity2Dto(item);
            result.add(dto);
        }
        objects[1] = result;
        return objects;
    }
}
