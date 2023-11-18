package org.evolboot.common.domain.bff;


import org.evolboot.common.domain.bff.dto.BffDict;

import java.util.List;

/**
 * @author evol
 */
public interface CommonBffAppService {


    List<BffDict> findBffDict();

}
