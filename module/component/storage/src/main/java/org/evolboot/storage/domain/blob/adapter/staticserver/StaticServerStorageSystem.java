package org.evolboot.storage.domain.blob.adapter.staticserver;

import org.evolboot.core.util.PathUtil;
import org.evolboot.storage.domain.blob.adapter.AbstractStorageSystem;
import org.evolboot.storage.domain.blob.adapter.StorageBlob;
import org.evolboot.storage.domain.blob.entity.StorageType;

import java.io.File;
import java.io.IOException;

import static org.apache.commons.io.FileUtils.copyFile;
import static org.apache.commons.io.FileUtils.touch;

/**
 * @author evol
 */
public class StaticServerStorageSystem extends AbstractStorageSystem {

    private final String directory;

    public StaticServerStorageSystem(String directory, String baseUrl) {
        super(baseUrl);
        this.directory = directory;
    }

    @Override
    protected String storeBlobToUrl(StorageBlob blob, String path) throws IOException {
        File storeFile = new File(PathUtil.concat(this.directory, path));
        touch(storeFile);
        copyFile(blob.getFile(), storeFile);
        return path;
    }

    @Override
    protected StorageType getStorageType() {
        return StorageType.STATIC_SERVER;
    }


}
