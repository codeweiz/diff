package cn.microboat.common.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditBean {

    @Comment("创建时间")
    @CreatedDate
    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Comment("修改时间")
    @LastModifiedDate
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @Comment("创建人")
    @CreatedBy
    @Column(name = "create_by", length = 64)
    private String createBy;

    @Comment("修改人")
    @LastModifiedBy
    @Column(name = "update_by", length = 64)
    private String updateBy;

}
