package org.evolboot.system.domain.dictkey.service;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.system.domain.dictkey.entity.DictKey;
import org.evolboot.system.domain.dictkey.repository.DictKeyRepository;
import org.springframework.stereotype.Service;

/**
 * 字典key
 *
 * @author evol
 * @date 2023-05-07 12:29:33
 */
@Slf4j
@Service
public class DictKeyUpdateService extends DictKeySupportService {
    protected DictKeyUpdateService(DictKeyRepository repository) {
        super(repository);
    }

    public void execute(Long id, Request request) {
        DictKey dictKey = findById(id);
        if (!request.getKey().equals(dictKey.getKey())) {
            keyMustNotFound(request.getKey());
        }
        dictKey.setKey(request.getKey());
        dictKey.setSort(request.getSort());
        dictKey.setDisplayName(request.getDisplayName());
        dictKey.setRemark(request.getRemark());
        repository.save(dictKey);
    }

    public static class Request extends DictKeyRequestBase {

    }

}
