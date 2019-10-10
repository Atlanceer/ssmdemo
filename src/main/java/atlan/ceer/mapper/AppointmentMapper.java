package atlan.ceer.mapper;


import atlan.ceer.model.Appointment;
import atlan.ceer.model.AppointmentExample;
import atlan.ceer.model.AppointmentKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppointmentMapper {
    long countByExample(AppointmentExample example);

    int deleteByExample(AppointmentExample example);

    int deleteByPrimaryKey(AppointmentKey key);

    int insert(Appointment record);

    int insertSelective(Appointment record);

    List<Appointment> selectByExample(AppointmentExample example);

    Appointment selectByPrimaryKey(AppointmentKey key);

    int updateByExampleSelective(@Param("record") Appointment record, @Param("example") AppointmentExample example);

    int updateByExample(@Param("record") Appointment record, @Param("example") AppointmentExample example);

    int updateByPrimaryKeySelective(Appointment record);

    int updateByPrimaryKey(Appointment record);
}