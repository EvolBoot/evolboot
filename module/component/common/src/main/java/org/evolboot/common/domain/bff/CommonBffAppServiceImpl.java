package org.evolboot.common.domain.bff;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.shared.cache.RedisCacheName;
import org.evolboot.common.domain.bff.dto.BffDict;
import org.evolboot.common.domain.dictkey.DictKeyQueryService;
import org.evolboot.common.domain.dictkey.entity.DictKey;
import org.evolboot.common.domain.dictvalue.DictValueQueryService;
import org.evolboot.common.domain.dictvalue.entity.DictValue;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author evol
 */
@Slf4j
@Service
public class CommonBffAppServiceImpl implements CommonBffAppService {

    private final DictKeyQueryService dictKeyQueryService;
    private final DictValueQueryService dictValueQueryService;

    public CommonBffAppServiceImpl(DictKeyQueryService dictKeyQueryService, DictValueQueryService dictValueQueryService) {
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
