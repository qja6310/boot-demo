package cn.com.newloading.dao;

import cn.com.newloading.bean.WebLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper {

    @Insert("INSERT INTO `t_logs`(`url`, `inParam`, `outParam`, `inTime`, `outTime`, `redate`) " +
            "VALUES (#{url}, #{inParam}, #{outParam}, #{inTime}, #{outTime}, #{redate}) ")
    Integer addLog(WebLog log);

}
