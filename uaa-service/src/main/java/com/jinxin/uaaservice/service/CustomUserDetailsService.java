package com.jinxin.uaaservice.service;

import com.jinxin.uaaservice.dao.RoleDao;
import com.jinxin.uaaservice.dao.UserDao;
import com.jinxin.uaaservice.dao.UserRoleDao;
import com.jinxin.uaaservice.entity.CustomerUserDetails;
import com.jinxin.uaaservice.entity.Role;
import com.jinxin.uaaservice.entity.User;
import com.jinxin.uaaservice.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserRoleDao userRoleDao;

    /**
     *获取用户信息和权限
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        User user = userDao.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<UserRole> userRoles = userRoleDao.findByUserId(user.getId());
        System.out.println(userRoles.toString());
        for(UserRole userRole:userRoles){
            Role role = roleDao.findById(userRole.getRoleId());
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            System.out.println("rolename:"+role.getName()+",userId:"+user.getId());
        }
        return new CustomerUserDetails(user.getUsername(),user.getPassword(),authorities);
    }
}
