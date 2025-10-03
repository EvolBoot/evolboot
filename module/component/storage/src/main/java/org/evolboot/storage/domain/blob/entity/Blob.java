package org.evolboot.storage.domain.blob.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.evolboot.core.entity.AbstractEntity;
import org.evolboot.core.entity.AggregateRoot;
import org.evolboot.core.entity.IdGenerate;
import org.evolboot.storage.domain.blob.adapter.StorageBlob;
import org.evolboot.storage.domain.blob.StorageBoldException;
import org.evolboot.storage.domain.blob.intercept.FileLimitType;
import org.springframework.util.DigestUtils;

import jakarta.persistence.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * 文件信息
 *
 * @author evol
 */
@Table(name = "evoltb_storage_blob")
@Entity
@Slf4j
@Getter
@NoArgsConstructor
@ToString
public class Blob extends AbstractEntity<Long> implements AggregateRoot<Blob>, StorageBlob {

    @Id
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @org.hibernate.annotations.CreationTimestamp
    protected Date createAt;

    /**
     * 新的文件名
     */
    private String name;

    /**
     * 上传时的文件名
     */
    private String originalName;

    /**
     * 存储的路径
     */
    private String path;

    /**
     * 访问的URL
     */
    private String url;

    /**
     * 文件扩展名
     */
    private String extension;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * md5 值
     */
    private String md5;

    /**
     * crc32 值
     */
    private String crc32;

    /**
     * 文件类型（预留）
     */
    private BlobType type;


    /**
     * 文件类型
     */
    private FileLimitType fileType;

    /**
     * 文件类型（预留）
     */
    private StorageType storageType = StorageType.LOCAL;


    private Long ownerUserId;

    /**
     * 上传时的文件
     */
    @JsonIgnore
    @Transient
    private InputStream inputStream;

    /**
     * 临时文件
     */
    @JsonIgnore
    @Transient
    private File file;

    private void generateId() {
        this.id = IdGenerate.longId();
    }

    @Builder
    public Blob(String originalName, InputStream inputStream, Long ownerUserId) {
        generateId();
        setOriginalName(originalName);
        setInputStream(inputStream);
        setFile(inputStream);
        setOwnerUserId(ownerUserId);
    }

    void setOwnerUserId(Long ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    @JsonIgnore
    @Transient
    public boolean isDirectory() {
        return BlobType.DIRECTORY == this.type;
    }

    @JsonIgnore
    @Transient
    public boolean getTempFile() {
        return BlobType.FILE == type;
    }

    private void setOriginalName(String originalName) {
        this.originalName = originalName;
        this.extension = FilenameUtils.getExtension(this.getOriginalName());
    }

    private void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setPath(String path) {
        this.path = path;
    }

    private void setUrl(String url) {
        this.url = url;
    }

    private void setFile(InputStream inputStream) {
        try {
            this.file = streamToTemporaryFile(inputStream);
            this.crc32 = crc32Hex(file);
            this.md5 = md5Hex(file);
            this.type = BlobType.FILE;
            this.size = file.length();
        } catch (IOException e) {
            throw new StorageBoldException(e.getMessage());
        }
    }

    @Override
    public void close() throws IOException {
        if (Objects.nonNull(inputStream)) {
            inputStream.close();
        }
        if (Objects.nonNull(file)) {
            FileUtils.forceDelete(file);
        }
    }


    private File streamToTemporaryFile(InputStream stream) throws IOException {
        String temporaryFileSuffix = String.format(".%s", getExtension());
        File temporaryFile =
                File.createTempFile(
                        String.format("%s_%s", UUID.randomUUID().toString(),
                                FilenameUtils.getBaseName(this.getName())), temporaryFileSuffix);
        try (stream) {
            FileUtils.copyToFile(stream, temporaryFile);
        }
        return temporaryFile;
    }

    private static String md5Hex(File file) throws IOException {
        try (var inputStream = FileUtils.openInputStream(file)) {
            return DigestUtils.md5DigestAsHex(inputStream);
        }
    }

    private static String crc32Hex(File file) throws IOException {
        return Long.toHexString(FileUtils.checksumCRC32(file));
    }

    @Override
    public File getFile() throws StorageBoldException {
        return file;
    }

    @Override
    public String getExtension() {
        return extension;
    }

    public void setFileType(FileLimitType fileType) {
        this.fileType = fileType;
    }

    public void update(String url, String path, String name, StorageType storageType, FileLimitType fileType) {
        setUrl(url);
        setPath(path);
        setName(name);
        setFileType(fileType);
        this.storageType = storageType;
    }

    @Override
    public Long id() {
        return id;
    }


    @Override
    public Blob root() {
        return this;
    }
}
