package org.evolboot.system.domain.notice;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 公告
 *
 * @author evol
 * 
 */
@Setter
@Getter
public abstract class NoticeRequestBase {

    private Integer sort;

    private Boolean enable = true;

    private Date releasedTime;

    private List<NoticeLocale> locales;

}
