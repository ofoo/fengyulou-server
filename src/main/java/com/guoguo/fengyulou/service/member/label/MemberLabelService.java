package com.guoguo.fengyulou.service.member.label;

import com.github.pagehelper.PageInfo;
import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.entity.member.label.MemberLabel;

import java.util.List;

public interface MemberLabelService {
    /**
     * 按条件查询
     *
     * @param memberLabel
     * @return
     */
    List<MemberLabel> getMemberLabelList(MemberLabel memberLabel);

    /**
     * 按条件分页查询
     *
     * @param memberLabel
     * @return
     */
    PageInfo<MemberLabel> getMemberLabelListPage(MemberLabel memberLabel);

    /**
     * 保存数据
     * @param memberLabel
     * @return
     */
    ServerResponse saveMemberLabel(MemberLabel memberLabel);

    /**
     * 按id和用户id查询
     * @param memberLabel
     * @return
     */
    MemberLabel getMemberLabelByIdAndUserId(MemberLabel memberLabel);

    /**
     * 按id和用户id删除
     * @param ids
     * @param userId
     * @return
     */
    ServerResponse deleteMemberLabelByIdsAndUserId(List<Long> ids, Long userId);
}
