package com.xd.zt.mapper.userinfo;

import com.xd.zt.domain.userlog.SysMenu;
import com.xd.zt.domain.userlog.SysRoleUser;
import com.xd.zt.domain.userlog.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserMenuMapper {
    public List<SysMenu> selectMenuByUserId(@Param("user_id") Integer user_id);
    public SysUser getSysUserByIdNumber(@Param("idnumber") String idnumber);
    public SysUser getSysUserByName(@Param("username") String username);

    List<SysRoleUser> selectRoleUserByUserId(@Param("userid") Integer userid);
}
