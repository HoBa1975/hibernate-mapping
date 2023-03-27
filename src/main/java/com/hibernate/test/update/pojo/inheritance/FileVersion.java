package com.hibernate.test.update.pojo.inheritance;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DiscriminatorValue("F")
@DynamicUpdate()
public class FileVersion extends AbstractVersion{
    @Column(name = "FILESIZE")
    private Long fileSize;

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
}
