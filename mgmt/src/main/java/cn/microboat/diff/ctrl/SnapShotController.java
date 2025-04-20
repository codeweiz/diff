package cn.microboat.diff.ctrl;

import cn.microboat.common.rest.PageForm;
import cn.microboat.common.rest.RestPageResultResponse;
import cn.microboat.diff.entity.SnapShot;
import cn.microboat.diff.form.SnapShotSearchForm;
import cn.microboat.diff.service.SnapShotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 快照 API 接口
 */
@RestController
@RequestMapping("/snapshot")
public class SnapShotController {

    @Autowired
    private SnapShotService snapShotService;

    @PostMapping("/page")
    public RestPageResultResponse<SnapShot> pageSnapShot(@RequestBody SnapShotSearchForm searchForm, PageForm pageForm) {
        return RestPageResultResponse.of(snapShotService.findAll(searchForm, pageForm.toPageable(), pageForm.toSort()));
    }

}
