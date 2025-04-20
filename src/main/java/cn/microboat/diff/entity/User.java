package cn.microboat.diff.entity;

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
@Comment("用户表")
@Entity(name = "t_user")
public class User extends AuditBean {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Comment("用户编码")
    @Column(name = "code", length = 64, unique = true)
    private String code;

    @Comment("用户名称")
    @Column(name = "name", length = 128)
    private String name;

}
