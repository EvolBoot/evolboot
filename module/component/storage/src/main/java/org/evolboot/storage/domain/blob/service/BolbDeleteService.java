package org.evolboot.storage.domain.blob.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.evolboot.storage.domain.blob.adapter.StorageSystem;
import org.evolboot.storage.domain.blob.entity.Blob;
import org.evolboot.storage.domain.blob.repository.BlobRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author linjie
 */
@Service
@Slf4j
public class BolbDeleteService {

    private final BlobRepository repository;

    private final StorageSystem storageSystem;

    public BolbDeleteService(BlobRepository repository, StorageSystem storageSystem) {
        this.repository = repository;
        this.storageSystem = storageSystem;
    }


    public void execute(Long id) {
        Optional<Blob> optionalBlob = repository.findById(id);
        if (optionalBlob.isEmpty()) {
            log.info("Bolb:删除:找不到对应名称的文件:{}", id);
            return;
        }
        Blob bolb = optionalBlob.get();
        storageSystem.delete(bolb);
        repository.deleteById(bolb.id());
    }


    public void deleteById(Long id) {
        Optional<Blob> optionalBlob = repository.findById(id);
        if (optionalBlob.isEmpty()) {
            log.info("Bolb:删除:找不到对应名称的ID:{}", id);
            return;
        }
        Blob bolb = optionalBlob.get();
        storageSystem.delete(bolb);
        repository.deleteById(bolb.id());
    }

    public static void main(String[] args) {

        String name = FilenameUtils.getName("https://nezha-saas.oss-cn-hongkong.aliyuncs.com/tiaojia/ecommercec/20250618/6e4275a6-77e2-4796-8b79-cb2c9171ceb0.zip");
        System.out.println(name);
    }
}
