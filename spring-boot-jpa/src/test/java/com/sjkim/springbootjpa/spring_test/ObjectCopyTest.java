package com.sjkim.springbootjpa.spring_test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class ObjectCopyTest {

    @Test
    void useBeanUtilsCopyProperties() {
        var innerSource = new InnerSource("IN_NAME");
        var source = new Source("NAME", 1, innerSource);
        var copySource = new Source();
        BeanUtils.copyProperties(source, copySource); // target은 setter 필요
        log.info("source {} {}", source, source.hashCode());
        log.info("copySource {} {}", copySource, copySource.hashCode());
        assertThat(source.equals(copySource)).isFalse();
        assertThat(source).isNotEqualTo(copySource);

        source.changeName("UPDATE_NAME");
        var sourceName = source.getName();
        log.info("{}", sourceName);
        var copySourceName = copySource.getName();
        log.info("{}", copySourceName);
        assertThat(sourceName).isNotEqualTo(copySourceName); // 필드값이 동일하지 않음

        source.getInnerSource().changeInnerSource("UPDATE_IN_NAME");
        var innerSourceName = source.getInnerSource();
        var copyInnerSourceName = copySource.getInnerSource();
        log.info("{}", innerSourceName);
        log.info("{}", copyInnerSourceName);
        assertThat(innerSourceName).isEqualTo(copyInnerSourceName); // 중첩필드는 값이 동일함 -> not a deep copy
    }
}
