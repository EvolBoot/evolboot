package org.evolboot.system.domain.dictvalue.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.system.domain.dictkey.DictKeyQueryService;
import org.evolboot.system.domain.dictkey.entity.DictKey;
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
public class DictValueUpdateService {

    private final DictValueRepository repository;
    private final DictValueSupportService supportService;

    private final DictKeyQueryService dictKeyQueryService;

    protected DictValueUpdateService(DictValueRepository repository, DictValueSupportService supportService, DictKeyQueryService dictKeyQueryService) {
        this.repository = repository;
        this.supportService = supportService;
        this.dictKeyQueryService = dictKeyQueryService;
    }

    public void execute(Request request) {
        DictKey dictKey = dictKeyQueryService.findById(request.getDictKeyId());
        DictValue dictValue = supportService.findById(request.getId());
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
