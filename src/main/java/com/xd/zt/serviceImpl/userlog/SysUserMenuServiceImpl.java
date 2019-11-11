package com.xd.zt.serviceImpl.userlog;

import com.xd.zt.domain.userlog.SysMenu;
import com.xd.zt.domain.userlog.SysUser;
import com.xd.zt.mapper.userinfo.SysUserMenuMapper;
import com.xd.zt.service.userlog.SysUserMenuService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Author:ykr
 *
 * Date:2019/11/11
 *
 * 授权方法
 *
 * @param
 */
@Service
public class SysUserMenuServiceImpl implements SysUserMenuService {
    @Autowired
    private SysUserMenuMapper sysUserMenuMapper;


    @Override
    public List<SysMenu> selectMenuByUserId(@Param("user_id") Integer user_id) {
       List<SysMenu> sysMenuList= sysUserMenuMapper.selectMenuByUserId(user_id);
        return sysMenuList;
    }

    @Override
    public SysUser getSysUserByIdNumber(@Param("idnumber") String idnumber) {
        SysUser sysUser=  sysUserMenuMapper.getSysUserByIdNumber(idnumber);
        return sysUser;
    }

    @Override
    public SysUser getSysUserByName(@Param("username") String username) {
     SysUser sysUser= sysUserMenuMapper.getSysUserByName(username);
        return sysUser;
    }
}
