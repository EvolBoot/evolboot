package org.evolboot.storage.domain.blob.impl.local;

import org.evolboot.core.util.PathUtil;
import org.evolboot.storage.domain.blob.impl.AbstractStorageSystem;
import org.evolboot.storage.domain.blob.impl.StorageBlob;
import org.evolboot.storage.domain.blob.entity.StorageType;

import java.io.File;
import java.io.IOException;

import static org.apache.commons.io.FileUtils.copyFile;
import static org.apache.commons.io.FileUtils.touch;

/**
 * @author evol
 */
public class LocalStorageSystem extends AbstractStorageSystem {

    private final String directory;


    private final String prefix;

    public LocalStorageSystem(String directory, String prefix, String baseUrl) {
        super(baseUrl);
        this.directory = directory;
        this.prefix = prefix;
    }

    @Override
    protected String storeBlobToUrl(StorageBlob blob, String path) throws IOException {
        File storeFile = new File(PathUtil.concat(this.directory, path));
        touch(storeFile);
        copyFile(blob.getFile(), storeFile);
        return PathUtil.concat(prefix, path);
    }

    @Override
    protected StorageType getStorageType() {
        return StorageType.LOCAL;
    }


}
