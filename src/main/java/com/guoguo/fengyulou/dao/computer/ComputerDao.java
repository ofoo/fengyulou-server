package com.guoguo.fengyulou.dao.computer;

import com.guoguo.fengyulou.entity.computer.Computer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ComputerDao {
    List<Computer> getComputerList(Computer computer);

    Computer getComputerById(@Param("id") Long id);

    int insertComputer(Computer computer);

    Computer getComputerByIdAndUserId(Computer computer);

    int updateComputerByIdAndUserId(Computer computer);

    int deleteComputerByIdsAndUserId(@Param("ids") List<Long> ids, @Param("userId") Long userId);

    int getComputerCountByNameAndUserId(Computer computer);
}
