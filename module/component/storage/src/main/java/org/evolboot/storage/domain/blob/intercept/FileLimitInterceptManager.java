package org.evolboot.storage.domain.blob.intercept;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @author evol
 */
@Component
public class FileLimitInterceptManager {

    private final List<FileLimitIntercept> fileLimitIntercepts;

    public FileLimitInterceptManager(List<FileLimitIntercept> fileLimitIntercepts) {
        this.fileLimitIntercepts = fileLimitIntercepts;
    }

    public void allow(String fileExtension, long fileSize, FileLimitType type) {
        FileLimitIntercept fileLimitIntercept = getFileLimitIntercept(type);
        if (Objects.nonNull(fileLimitIntercept)) {
            fileLimitIntercept.allow(fileExtension, fileSize);
        }
    }

    private FileLimitIntercept getFileLimitIntercept(FileLimitType type) {
        return fileLimitIntercepts.stream().filter(intercept -> intercept.supports(type)).findFirst().orElse(null);
    }

}
