package com.guoguo.fengyulou.controller.member.label;

import com.github.pagehelper.PageInfo;
import com.guoguo.common.CurrentUserManager;
import com.guoguo.common.DataJson;
import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.entity.member.label.MemberLabel;
import com.guoguo.fengyulou.service.member.label.MemberLabelService;
import com.guoguo.util.ObjectUtils;
import com.guoguo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 人员标签管理
 */
@RestController
@RequestMapping("/fyl")
public class MemberLabelController {

    @Autowired
    private MemberLabelService memberLabelService;
    @Autowired
    private CurrentUserManager currentUserManager;

    /**
     * 列表页面
     *
     * @param userKey
     * @param memberLabel
     * @return
     */
    @RequestMapping("/memberLabel/list")
    public DataJson list(@RequestParam String userKey, MemberLabel memberLabel) {
        memberLabel.setUserId(currentUserManager.getUserId(userKey));
        PageInfo<MemberLabel> pageInfo = memberLabelService.getMemberLabelListPage(memberLabel);
        return DataJson.list(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 保存数据
     *
     * @param memberLabel
     * @return
     */
    @RequestMapping("/memberLabel/save")
    public ServerResponse save(MemberLabel memberLabel, @RequestParam String userKey) {
        if (StringUtils.isBlank(memberLabel.getName())) {
            return ServerResponse.createByErrorMessage("请输入人员标签名称");
        }
        memberLabel.setUserId(currentUserManager.getUserId(userKey));
        return memberLabelService.saveMemberLabel(memberLabel);
    }

    /**
     * 按id删除数据
     *
     * @param ids
     * @return
     */
    @RequestMapping("/memberLabel/delete")
    @ResponseBody
    public ServerResponse delete(@RequestParam List<Long> ids, @RequestParam String userKey) {
        if (ObjectUtils.isNull(ids)) {
            return ServerResponse.createByErrorMessage("请选择数据");
        }
        return memberLabelService.deleteMemberLabelByIdsAndUserId(ids, currentUserManager.getUserId(userKey));
    }

    /**
     * 下拉选列表
     *
     * @param userKey
     * @return
     */
    @RequestMapping("/memberLabel/list/content")
    public ServerResponse listContent(@RequestParam String userKey) {
        MemberLabel memberLabel = new MemberLabel();
        memberLabel.setUserId(currentUserManager.getUserId(userKey));
        return ServerResponse.createBySuccess(memberLabelService.getMemberLabelList(memberLabel));
    }
}
