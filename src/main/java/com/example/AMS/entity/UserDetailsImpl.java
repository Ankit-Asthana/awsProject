//package com.example.AMS.entity;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//public class UserDetailsImpl implements UserDetails {
//    @Autowired
//    private Users users;
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        //  return List.of(new SimpleGrantedAuthority(userRoles.getRoleName()));
//        SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(users.getUserRoles().getRoleName());
//        return List.of(simpleGrantedAuthority);
////        Set<Authority> set= new HashSet<>();
////        //to get the user roles of the users
////        set.add(new Authority(userRoles.getRoleId()));
////        return null;
//
////        List<GrantedAuthority> grantedAuthorities=new ArrayList<GrantedAuthority>();
////        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE"+this.users.getUserRoles().toString()));
////        //System.out.println("inside details impl "+grantedAuthorities.get(0).getAuthority());
////        return grantedAuthorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return this.users.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return this.users.getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
