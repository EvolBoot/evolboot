package org.evolboot.storage.domain.blob.intercept;

import com.google.common.collect.Lists;
import org.apache.commons.io.FilenameUtils;
import org.evolboot.storage.StorageI18nMessage;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;

import java.util.List;

/**
 * @author evol
 */
@Component
public class ImageFileLimitIntercept implements FileLimitIntercept {

    private List<String> extensions = Lists
            .newArrayList("png", "jpg", "jpeg", "bmp", "gif");

    /**
     * Bytes per Kilobyte.
     */
    private static final long BYTES_PER_KB = 1024;

    /**
     * Bytes per Megabyte.
     */
    private static final long BYTES_PER_MB = BYTES_PER_KB * 1024;

    /**
     * 最大图片5M
     */
    private static final long MAX_FILE_SIZE = BYTES_PER_MB * 5;

    @Override
    public boolean allow(String fullFilenameWithExtension, Long fileSize) {
        boolean extension = FilenameUtils.isExtension(fullFilenameWithExtension, extensions);
        if (!extension) {
            throw new FileContentTypeException(StorageI18nMessage.contentTypeInterceptError((FilenameUtils.getExtension(fullFilenameWithExtension))));
        }
        if (fileSize > MAX_FILE_SIZE) {
            throw new FileSizeException(StorageI18nMessage.maxFileSizeError(DataSize.ofBytes(MAX_FILE_SIZE).toMegabytes() + " MB"));
        }
        return true;
    }

    @Override
    public boolean supports(FileLimitType type) {
        return FileLimitType.IMAGE.equals(type);
    }


}
