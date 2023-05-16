package org.evolboot.system.domain.dictvalue.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.system.domain.dictkey.DictKey;
import org.evolboot.system.domain.dictkey.DictKeyAppService;
import org.evolboot.system.domain.dictvalue.DictValue;
import org.evolboot.system.domain.dictvalue.DictValueRequestBase;
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

    public void execute(Long id, Request request) {
        DictKey dictKey = dictKeyAppService.findById(request.getDictKeyId());
        DictValue dictValue = findById(id);
        dictValue.setDictKeyId(dictKey.id());
        dictValue.setDisplayName(request.getDisplayName());
        dictValue.setValue(request.getValue());
        dictValue.setSort(request.getSort());
        dictValue.setRemark(request.getRemark());
        repository.save(dictValue);
    }

    public static class Request extends DictValueRequestBase {
    }

}
