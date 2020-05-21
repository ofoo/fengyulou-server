package com.guoguo.fengyulou.dao.member.label;

import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.entity.member.label.MemberLabel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberLabelDao {
    List<MemberLabel> getMemberLabelList(MemberLabel memberLabel);

    int getMemberLabelCountByMemberLabel(MemberLabel memberLabel);

    int insertMemberLabel(MemberLabel memberLabel);

    MemberLabel getMemberLabelByIdAndUserId(MemberLabel memberLabel);

    int getMemberLabelCountByNameByUserId(MemberLabel memberLabel);

    int updateMemberLabelByIdAndUserId(MemberLabel memberLabel);

    int deleteMemberLabelByIdsAndUserId(@Param("ids") List<Long> ids, @Param("userId") Long userId);
}
