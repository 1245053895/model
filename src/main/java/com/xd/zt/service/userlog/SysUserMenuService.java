package com.xd.zt.service.userlog;

import com.xd.zt.domain.userlog.SysMenu;
import com.xd.zt.domain.userlog.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SysUserMenuService {
    public List<SysMenu> selectMenuByUserId(@Param("user_id") Integer user_id);
    public SysUser getSysUserByIdNumber(@Param("idnumber") String idnumber);
    public SysUser getSysUserByName(@Param("username") String username);
}
