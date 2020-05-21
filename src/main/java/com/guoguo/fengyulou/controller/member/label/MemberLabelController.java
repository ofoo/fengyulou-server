package com.guoguo.fengyulou.controller.member.label;

import com.guoguo.common.CurrentUserManager;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 人员标签管理
 */
@Controller
@RequestMapping("/fyl")
public class MemberLabelController {

    @Autowired
    private MemberLabelService memberLabelService;
    @Autowired
    private CurrentUserManager currentUserManager;

    /**
     * 列表页面
     *
     * @param request
     * @param memberLabel
     * @return
     */
    @RequestMapping("/memberLabel/list/page")
    public String list(HttpServletRequest request, @RequestParam String userKey, MemberLabel memberLabel) {
        request.setAttribute("data", memberLabel);
        memberLabel.setUserId(currentUserManager.getUserId(userKey));
        request.setAttribute("pageInfo", memberLabelService.getMemberLabelListPage(memberLabel));
        return "/member/label/member-label-list";
    }

    /**
     * 添加页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/memberLabel/insert")
    public String insert(HttpServletRequest request, HttpSession session) {
        request.setAttribute("pageTitle", "添加人员标签");
        return "member/label/member-label-save";
    }

    /**
     * 修改页面
     *
     * @param request
     * @param session
     * @param memberLabel
     * @return
     */
    @RequestMapping("/memberLabel/update")
    public String update(HttpServletRequest request, @RequestParam String userKey, MemberLabel memberLabel) {
        request.setAttribute("pageTitle", "修改人员标签");
        // 查询人员标签
        memberLabel.setUserId(currentUserManager.getUserId(userKey));
        request.setAttribute("data", memberLabelService.getMemberLabelByIdAndUserId(memberLabel));
        return "member/label/member-label-save";
    }

    /**
     * 保存数据
     *
     * @param memberLabel
     * @return
     */
    @RequestMapping("/memberLabel/ajax/save")
    @ResponseBody
    public ServerResponse ajaxSave(MemberLabel memberLabel, @RequestParam String userKey) {
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
    @RequestMapping("/memberLabel/ajax/delete")
    @ResponseBody
    public ServerResponse ajaxDelete(@RequestParam List<Long> ids, @RequestParam String userKey) {
        if (ObjectUtils.isNull(ids)) {
            return ServerResponse.createByErrorMessage("请选择数据");
        }
        return memberLabelService.deleteMemberLabelByIdsAndUserId(ids, currentUserManager.getUserId(userKey));
    }

    /**
     * 下拉选列表
     *
     * @param request
     * @return
     */
    @RequestMapping("/memberLabel/ajax/list")
    public String ajaxList(HttpServletRequest request, @RequestParam String userKey) {
        MemberLabel memberLabel = new MemberLabel();
        memberLabel.setUserId(currentUserManager.getUserId(userKey));
        request.setAttribute("list", memberLabelService.getMemberLabelList(memberLabel));
        return "common/select-item";
    }
}
