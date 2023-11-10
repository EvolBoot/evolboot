package org.evolboot.system.domain.bff;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.shared.cache.RedisCacheName;
import org.evolboot.system.domain.bff.dto.BffDict;
import org.evolboot.system.domain.dictkey.DictKeyQueryService;
import org.evolboot.system.domain.dictkey.entity.DictKey;
import org.evolboot.system.domain.dictvalue.DictValueQueryService;
import org.evolboot.system.domain.dictvalue.entity.DictValue;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author evol
 */
@Slf4j
@Service
public class SystemBffAppServiceImpl implements SystemBffAppService {

    private final DictKeyQueryService dictKeyQueryService;
    private final DictValueQueryService dictValueQueryService;

    public SystemBffAppServiceImpl(DictKeyQueryService dictKeyQueryService, DictValueQueryService dictValueQueryService) {
        this.dictKeyQueryService = dictKeyQueryService;
        this.dictValueQueryService = dictValueQueryService;
    }

    @Override
    @Cacheable(cacheNames = RedisCacheName.DICT_CACHE_KEY, key = "'all'")
    public List<BffDict> findBffDict() {
        List<DictKey> dictKeys = dictKeyQueryService.findAll();
        List<DictValue> dictValueList = dictValueQueryService.findAll();
        return dictKeys.stream().map(dictKey -> {
            List<BffDict.BffDictValue> values = dictValueList.stream().filter(dictValue -> dictValue.getDictKeyId().equals(dictKey.id())).map(dictValue -> new BffDict.BffDictValue(
                    dictValue.id(),
                    dictValue.getDictKeyId(),
                    dictValue.getDisplayName(),
                    dictValue.getValue(),
                    dictValue.getSort(),
                    dictValue.getRemark()

            )).collect(Collectors.toList());
            return new BffDict(
                    dictKey.id(),
                    dictKey.getDisplayName(),
                    dictKey.getKey(),
                    dictKey.getSort(),
                    dictKey.getRemark(),
                    values
            );
        }).collect(Collectors.toList());
    }
}
