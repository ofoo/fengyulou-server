package com.guoguo.fengyulou.service.computer;

import com.github.pagehelper.PageInfo;
import com.guoguo.common.ServerResponse;
import com.guoguo.fengyulou.entity.computer.Computer;

import java.util.List;

/**
 * 云服务器
 */
public interface ComputerService {
    /**
     * 按条件查询查询
     * @param computer
     * @return
     */
    List<Computer> getComputerList(Computer computer);

    /**
     * 按条件分页查询
     * @param computer
     * @return
     */
    PageInfo<Computer> getComputerListPage(Computer computer);

    /**
     * 保存数据
     * @param computer
     * @return
     */
    ServerResponse saveComputer(Computer computer);

    /**
     * 按id和用户id查询
     * @param computer
     * @return
     */
    Computer getComputerByIdAndUserId(Computer computer);

    /**
     * 按id和用户删除
     * @param ids
     * @param userId
     * @return
     */
    ServerResponse deleteComputerByIdsAndUserId(List<Long> ids, Long userId);

}
