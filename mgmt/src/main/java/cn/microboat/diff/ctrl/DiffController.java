package cn.microboat.diff.ctrl;

import cn.microboat.common.rest.PageForm;
import cn.microboat.common.rest.RestPageResultResponse;
import cn.microboat.diff.entity.Diff;
import cn.microboat.diff.form.DiffSearchForm;
import cn.microboat.diff.service.DiffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 差异 API 接口
 */
@RestController
@RequestMapping("/diff")
public class DiffController {

    @Autowired
    private DiffService diffService;

    @PostMapping("/page")
    public RestPageResultResponse<Diff> pageDiff(@RequestBody DiffSearchForm searchForm, PageForm pageForm) {
        return RestPageResultResponse.of(diffService.findAll(searchForm, pageForm.toPageable(), pageForm.toSort()));
    }

}
