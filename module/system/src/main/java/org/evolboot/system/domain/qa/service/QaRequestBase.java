package org.evolboot.system.domain.qa.service;

import lombok.Getter;
import lombok.Setter;
import org.evolboot.system.domain.qa.entity.QaLocale;

import java.util.List;

/**
 * QA
 *
 * @author evol
 */
@Setter
@Getter
public abstract class QaRequestBase {

    private List<QaLocale> locales;

    private Boolean enable = true;

    private Integer sort;

    private String link;

}
