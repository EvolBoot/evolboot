package org.evolboot.storage.domain.blob.intercept;

/**
 * @author evol
 * 
 */
public interface FileLimitIntercept {

    /**
     * @param fullFilenameWithExtension 带扩展名的完整文件名
     * @param fileSize                  文件大小
     * @return
     * @throws FileContentTypeException 文件类型不允许异常
     * @throws FileSizeException        文件大小超出异常
     */
    boolean allow(String fullFilenameWithExtension, Long fileSize) throws FileContentTypeException, FileSizeException;

    boolean supports(FileLimitType type);
}
