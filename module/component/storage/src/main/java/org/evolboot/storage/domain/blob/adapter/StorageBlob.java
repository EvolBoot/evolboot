package org.evolboot.storage.domain.blob.adapter;

import java.io.Closeable;
import java.io.File;

/**
 * 用来存储到文件系统
 *
 * @author evol
 */
public interface StorageBlob extends Closeable {

    File getFile();

    String getExtension();

}

