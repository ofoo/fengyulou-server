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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 人员管理
 */
@RestController
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
    public String list(HttpServletRequest request, @RequestParam String userKey, Member member) {
        request.setAttribute("data", member);
        member.setUserId(currentUserManager.getUserId(userKey));
        request.setAttribute("pageInfo", memberService.getMemberListPage(member));
        return "/member/member-list";
    }

    /**
     * 保存数据
     *
     * @param member
     * @return
     */
    @RequestMapping("/member/save")
    public ServerResponse save(Member member, @RequestParam String userKey) {
        if (StringUtils.isBlank(member.getName())) {
            return ServerResponse.createByErrorMessage("请输入人员名称");
        }
        if (ObjectUtils.isNull(member.getMemberLabelId())) {
            return ServerResponse.createByErrorMessage("请选择人员标签");
        }
        member.setUserId(currentUserManager.getUserId(userKey));
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
    public ServerResponse ajaxDelete(@RequestParam List<Long> ids, @RequestParam String userKey) {
        if (ObjectUtils.isNull(ids)) {
            return ServerResponse.createByErrorMessage("请选择数据");
        }
        return memberService.deleteMemberByIdsAndUserId(ids, currentUserManager.getUserId(userKey));
    }

    /**
     * 下拉选列表
     *
     * @param userKey
     * @return
     */
    @RequestMapping("/member/list/content")
    public ServerResponse listContent(@RequestParam String userKey) {
        Member member = new Member();
        member.setUserId(currentUserManager.getUserId(userKey));
        return ServerResponse.createBySuccess(memberService.getMemberList(member));
    }
}
