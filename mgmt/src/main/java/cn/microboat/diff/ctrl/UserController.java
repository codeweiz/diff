package cn.microboat.diff.ctrl;

import cn.microboat.diff.entity.User;
import cn.microboat.common.rest.PageForm;
import cn.microboat.diff.form.UserSearchForm;
import cn.microboat.diff.service.UserService;
import cn.microboat.common.rest.RestPageResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户 API 接口
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 多条件分页排序查询用户列表
     */
    @PostMapping("/page")
    public RestPageResultResponse<User> pageUser(@RequestBody UserSearchForm searchForm, PageForm pageForm) {
        return RestPageResultResponse.of(userService.findAll(searchForm, pageForm.toPageable(), pageForm.toSort()));
    }

}
