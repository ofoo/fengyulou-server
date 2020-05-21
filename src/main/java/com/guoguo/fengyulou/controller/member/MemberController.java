package com.guoguo.fengyulou.controller.member;

import com.guoguo.common.CurrentUserManager;
import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.entity.member.Member;
import com.guoguo.fengyulou.entity.member.label.MemberLabel;
import com.guoguo.fengyulou.service.member.MemberService;
import com.guoguo.fengyulou.service.member.label.MemberLabelService;
import com.guoguo.util.ObjectUtils;
import com.guoguo.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 人员管理
 */
@Controller
@RequestMapping("/fyl")
public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberLabelService memberLabelService;
    @Autowired
    private CurrentUserManager currentUserManager;

    /**
     * 列表页面
     *
     * @return
     */
    @RequestMapping("/member/list/page")
    public String list(HttpServletRequest request, HttpSession session, Member member) {
        request.setAttribute("data", member);
        member.setUserId(currentUserManager.getUserId());
        request.setAttribute("pageInfo", memberService.getMemberListPage(member));
        return "/member/member-list";
    }

    /**
     * 添加页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/member/insert")
    public String insert(HttpServletRequest request, HttpSession session) {
        request.setAttribute("pageTitle", "添加人员");
        // 查询人员标签列表
        MemberLabel memberLabel = new MemberLabel();
        memberLabel.setUserId(currentUserManager.getUserId());
        request.setAttribute("memberLabelList", memberLabelService.getMemberLabelList(memberLabel));
        // task=任务页面打开
        String str = request.getParameter("str");
        logger.info("str={}", str);
        request.setAttribute("str", str);
        return "member/member-save";
    }

    /**
     * 修改页面
     *
     * @param request
     * @param session
     * @param member
     * @return
     */
    @RequestMapping("/member/update")
    public String update(HttpServletRequest request, HttpSession session, Member member) {
        request.setAttribute("pageTitle", "修改人员");
        //获取用户id
        Long userId = currentUserManager.getUserId();
        // 查询人员
        member.setUserId(userId);
        request.setAttribute("data", memberService.getMemberByIdAndUserId(member));
        // 查询人员标签列表
        MemberLabel memberLabel = new MemberLabel();
        memberLabel.setUserId(userId);
        request.setAttribute("memberLabelList", memberLabelService.getMemberLabelList(memberLabel));
        return "member/member-save";
    }

    /**
     * 保存数据
     *
     * @param member
     * @return
     */
    @RequestMapping("/member/ajax/save")
    @ResponseBody
    public ServerResponse ajaxSave(Member member, HttpSession session) {
        if (StringUtils.isBlank(member.getName())) {
            return ServerResponse.createByErrorMessage("请输入人员名称");
        }
        if (ObjectUtils.isNull(member.getMemberLabelId())) {
            return ServerResponse.createByErrorMessage("请选择人员标签");
        }
        member.setUserId(currentUserManager.getUserId());
        return memberService.saveMember(member);
    }

    /**
     * 删除数据
     *
     * @param ids
     * @return
     */
    @RequestMapping("/member/ajax/delete")
    @ResponseBody
    public ServerResponse ajaxDelete(@RequestParam List<Long> ids, HttpSession session) {
        if (ObjectUtils.isNull(ids)) {
            return ServerResponse.createByErrorMessage("请选择数据");
        }
        return memberService.deleteMemberByIdsAndUserId(ids, currentUserManager.getUserId());
    }

    /**
     * 下拉选列表
     *
     * @param request
     * @return
     */
    @RequestMapping("/member/ajax/list")
    public String ajaxList(HttpServletRequest request, HttpSession session) {
        Member member = new Member();
        member.setUserId(currentUserManager.getUserId());
        request.setAttribute("list", memberService.getMemberList(member));
        return "common/select-item";
    }
}
