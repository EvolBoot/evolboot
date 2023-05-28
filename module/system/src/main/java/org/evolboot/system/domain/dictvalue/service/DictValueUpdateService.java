package org.evolboot.system.domain.dictvalue.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.system.domain.dictkey.entity.DictKey;
import org.evolboot.system.domain.dictkey.DictKeyAppService;
import org.evolboot.system.domain.dictvalue.entity.DictValue;
import org.evolboot.system.domain.dictvalue.repository.DictValueRepository;
import org.springframework.stereotype.Service;

/**
 * 字典Value
 *
 * @author evol
 * @date 2023-05-07 12:55:06
 */
@Slf4j
@Service
public class DictValueUpdateService extends DictValueSupportService {

    private final DictKeyAppService dictKeyAppService;

    protected DictValueUpdateService(DictValueRepository repository, DictKeyAppService dictKeyAppService) {
        super(repository);
        this.dictKeyAppService = dictKeyAppService;
    }

    public void execute(Request request) {
        DictKey dictKey = dictKeyAppService.findById(request.getDictKeyId());
        DictValue dictValue = findById(request.getId());
        dictValue.setDictKeyId(dictKey.id());
        dictValue.setDisplayName(request.getDisplayName());
        dictValue.setValue(request.getValue());
        dictValue.setSort(request.getSort());
        dictValue.setRemark(request.getRemark());
        repository.save(dictValue);
    }

    @Getter
    @Setter
    public static class Request extends DictValueRequestBase {
        private Long id;
    }

}
