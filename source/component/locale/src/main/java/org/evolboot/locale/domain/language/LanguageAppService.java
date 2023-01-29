package org.evolboot.locale.domain.language;

import org.evolboot.core.data.Page;

import java.util.List;

/**
 * @author evol
 */
public interface LanguageAppService {

    Long create(LanguageCreateFactory.Request request);

    void update(Long id, LanguageUpdateService.Request request);

    void delete(Long id);

    List<Language> findAllByEnableIsTrue();

    List<Language> list();

    Page<Language> page(LanguageQuery query);

    Language findById(Long id);

    void enable(Long id);

    void disable(Long id);
}
