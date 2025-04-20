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
@Comment("文章表")
@Entity(name = "t_article")
public class Article extends AuditBean {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Comment("文章编码")
    @Column(name = "code", length = 64, unique = true)
    private String code;

    @Comment("文章名称")
    @Column(name = "name", length = 128)
    private String name;

    @Version
    @Comment("文章版本号")
    @Column(name = "version")
    private Integer version;

}
