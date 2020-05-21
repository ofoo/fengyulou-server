package com.guoguo.fengyulou.service.member;

import com.github.pagehelper.PageInfo;
import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.entity.member.Member;

import java.util.List;

public interface MemberService {
    /**
     * 按条件查询
     *
     * @param member
     * @return
     */
    List<Member> getMemberList(Member member);

    /**
     * 按条件分页查询
     *
     * @param member
     * @return
     */
    PageInfo<Member> getMemberListPage(Member member);

    /**
     * 保存数据
     *
     * @param member
     * @return
     */
    ServerResponse saveMember(Member member);

    /**
     * 按id和用户id查询
     *
     * @param member
     * @return
     */
    Member getMemberByIdAndUserId(Member member);

    /**
     * 按id和用户id删除
     *
     * @param ids
     * @param userId
     * @return
     */
    ServerResponse deleteMemberByIdsAndUserId(List<Long> ids, Long userId);
}
