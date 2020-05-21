package com.guoguo.fengyulou.dao.member;

import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.entity.member.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberDao {
    List<Member> getMemberList(Member member);

    int insertMember(Member member);

    int getMemberCountByMember(Member member);

    int updateMemberByIdAndUserId(Member member);

    int getMemberCountByMobileAndUserId(Member member);

    Member getMemberByIdAndUserId(Member member);

    int deleteMemberByIdsAndUserId(@Param("ids") List<Long> ids, @Param("userId") Long userId);
}
