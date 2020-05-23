package com.guoguo.fengyulou.controller.member;

import com.github.pagehelper.PageInfo;
import com.guoguo.common.CurrentUserManager;
import com.guoguo.common.DataJson;
import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.entity.member.Member;
import com.guoguo.fengyulou.service.member.MemberService;
import com.guoguo.util.ObjectUtils;
import com.guoguo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 人员管理
 */
@RestController
@RequestMapping("/fyl")
public class MemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private CurrentUserManager currentUserManager;

    /**
     * 列表页面
     *
     * @return
     */
    @RequestMapping("/member/list")
    public DataJson list(@RequestParam String userKey, Member member) {
        member.setUserId(currentUserManager.getUserId(userKey));
        PageInfo<Member> pageInfo = memberService.getMemberListPage(member);
        return DataJson.list(pageInfo.getTotal(), pageInfo.getList());
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
    @RequestMapping("/member/delete")
    public ServerResponse delete(@RequestParam List<Long> ids, @RequestParam String userKey) {
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
