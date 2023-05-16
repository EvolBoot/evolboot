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
public class DictValueCreateFactory extends DictValueSupportService {

    private final DictKeyAppService dictKeyAppService;

    protected DictValueCreateFactory(DictValueRepository repository, DictKeyAppService dictKeyAppService) {
        super(repository);
        this.dictKeyAppService = dictKeyAppService;
    }

    public DictValue execute(Request request) {
        DictKey dictKey = dictKeyAppService.findById(request.getDictKeyId());
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
