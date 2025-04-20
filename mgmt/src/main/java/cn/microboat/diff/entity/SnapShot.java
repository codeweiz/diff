package cn.microboat.diff.entity;

import cn.microboat.common.jpa.AuditBean;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Comment("快照表")
@Entity(name = "t_snapshot")
public class SnapShot extends AuditBean {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Comment("编码")
    @Column(name = "code", length = 64, unique = true)
    private String code;

    @Comment("用户编码")
    @Column(name = "user_code", length = 64, nullable = false)
    private Long userCode;

    @Comment("用户名称")
    @Column(name = "user_name", length = 128)
    private String userName;

    @Comment("内容")
    @Column(name = "content", columnDefinition = "json")
    private String content;

    @Comment("版本号")
    @Column(name = "version", length = 20, nullable = false)
    private Long version;
}
