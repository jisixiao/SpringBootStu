package com.jsz.back.common.shiro;

import com.jsz.back.entity.SysPermission;
import com.jsz.back.entity.SysRole;
import com.jsz.back.entity.UserInfo;
import com.jsz.back.serice.UserInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class MyRealm extends AuthorizingRealm {

    private static final Logger logger = LogManager.getLogger(MyRealm.class);
    @Autowired
    private UserInfoService userService;





    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
       logger.info("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        //SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //UserInfo userInfo  = (UserInfo)principals.getPrimaryPrincipal();
        //for(SysRole role:userInfo.getRoleList()){
        //    authorizationInfo.addRole(role.getRole());
        //    for(SysPermission p:role.getPermissions()){
        //        authorizationInfo.addStringPermission(p.getPermission());
        //    }
        //}
        return null;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("MyShiroRealm.doGetAuthenticationInfo()");
        UsernamePasswordToken tokens = (UsernamePasswordToken) token;
        String username = tokens.getUsername();
        String password = String.valueOf(tokens.getPassword());

        logger.info(token.getCredentials());
        UserInfo userInfo = userService.getUserInfoByUserName(username);
        logger.info(userInfo);
        if (userInfo==null){
            throw   new UnknownAccountException();/*没找到帐号*/
        }
        //设置盐值
        ByteSource salt=ByteSource.Util.bytes(userInfo.getSalt());

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户名
                password, //密码
                salt,//salt=username+salt  明文访问不需要此句
                getName()  //realm name
        );
        return authenticationInfo;
    }

    public static void main(String args[]){
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        ByteSource salt = ByteSource.Util.bytes("e6c9c65e-38c4-4ae5-9ef6-279714082861");
        System.out.println(salt);
        SimpleHash sh=new SimpleHash("MD5", "admin", salt, 2);
        System.out.println(sh);
        //admin ——> 9eb81aa26096894a8e1f383d87a03b3b
    }
}
