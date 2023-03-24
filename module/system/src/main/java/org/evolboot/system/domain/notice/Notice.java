package org.evolboot.system.domain.notice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.system.domain.notice.repository.jpa.convert.NoticeLocaleListConverter;
import org.evolboot.core.data.jpa.JpaAbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import org.evolboot.core.domain.LocaleDomainPart;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;


/**
 * 公告
 *
 * @author evol
 */
@Table(name = "evoltb_system_notice")
@Getter
@Slf4j
@NoArgsConstructor
@Entity
public class Notice extends JpaAbstractEntity<Long> implements AggregateRoot<Notice>, LocaleDomainPart<NoticeLocale> {

    @Id
    private Long id;

    private Integer sort;

    private Boolean enable = true;

    private Date releasedTime;

    @Convert(converter = NoticeLocaleListConverter.class)
    private List<NoticeLocale> locales;

    private void generateId() {
        this.id = IdGenerate.longId();
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public void setReleasedTime(Date releasedTime) {
        this.releasedTime = releasedTime;
    }

    public void setLocales(List<NoticeLocale> locales) {
        this.locales = locales;
    }

    public Notice(Integer sort, Boolean enable, Date releasedTime, List<NoticeLocale> locales) {
        generateId();
        setSort(sort);
        setEnable(enable);
        setReleasedTime(releasedTime);
        setLocales(locales);
    }


    @Override
    public Long id() {
        return id;
    }

    @Override
    public Notice root() {
        return this;
    }
}
