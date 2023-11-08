package org.evolboot.system.domain.dictvalue.service;


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
public class DictValueCreateFactory {

    private final DictValueRepository repository;
    private final DictValueSupportService supportService;

    private final DictKeyQueryService dictKeyQueryService;

    protected DictValueCreateFactory(DictValueRepository repository, DictValueSupportService supportService, DictKeyQueryService dictKeyQueryService) {
        this.repository = repository;
        this.supportService = supportService;
        this.dictKeyQueryService = dictKeyQueryService;
    }

    public DictValue execute(Request request) {
        DictKey dictKey = dictKeyQueryService.findById(request.getDictKeyId());
        DictValue dictValue = new DictValue(
                dictKey.id(),
                request.getDisplayName(),
                request.getValue(),
                request.getSort(),
                request.getRemark()
        );
        repository.save(dictValue);
        return dictValue;
    }

    public static class Request extends DictValueRequestBase {

    }

}
