package com.example.AMS.configuration;

import com.example.AMS.entity.AssetTypes;
import com.example.AMS.entity.RolePermissions;
import com.example.AMS.entity.UserRoles;
import com.example.AMS.entity.Users;
import com.example.AMS.repository.AssetTypeDao;
import com.example.AMS.repository.RolePermissionDao;
import com.example.AMS.repository.UserDao;
import com.example.AMS.repository.UserRoleDao;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class DatabaseInitializer {

    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Autowired
    private UserDao userDao;
    @Autowired
    private AssetTypeDao assetTypeDao;
    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
    BCryptPasswordEncoder passwordEncoder =new BCryptPasswordEncoder();

    @PostConstruct
    @Transactional
    public void init() throws ParseException{
        UserRoles role1 = new UserRoles();
        role1.setRoleName("SuperAdmin");
        role1.setRoleDescription("SuperAdmin role for the project who have each and every access");

        UserRoles role2 = new UserRoles();
        role2.setRoleName("Admin");
        role2.setRoleDescription("Admin role for the project who have most of the access");

        UserRoles role3 = new UserRoles();
        role3.setRoleName("Employee");
        role3.setRoleDescription("Employee role for the project who can do task only which is request or complain");

        RolePermissions readPermission = new RolePermissions();
        readPermission.setPermissionType(RolePermissions.permissionType.Read);
        rolePermissionDao.save(readPermission);

        RolePermissions writePermission = new RolePermissions();
        writePermission.setPermissionType(RolePermissions.permissionType.Write);
        rolePermissionDao.save(writePermission);

        RolePermissions updatePermission = new RolePermissions();
        updatePermission.setPermissionType(RolePermissions.permissionType.Update);
        rolePermissionDao.save(updatePermission);

        RolePermissions deletePermission = new RolePermissions();
        deletePermission.setPermissionType(RolePermissions.permissionType.Delete);
        rolePermissionDao.save(deletePermission);

        List<RolePermissions> superAdminPermissions = new ArrayList<>();
        List<RolePermissions> adminPermissions = new ArrayList<>();
        List<RolePermissions> employeePermissions = new ArrayList<>();
        //adding permissions in these above arraylists
        superAdminPermissions.addAll(Arrays.asList(readPermission,writePermission,updatePermission,deletePermission));
        adminPermissions.addAll(Arrays.asList(readPermission,writePermission,updatePermission,deletePermission));
        employeePermissions.addAll(Collections.singleton(readPermission));

        //linking user roles and role permissions
        role1.setRolePermissions(superAdminPermissions);
        userRoleDao.save(role1);
        role2.setRolePermissions(adminPermissions);
        userRoleDao.save(role2);
        role3.setRolePermissions(employeePermissions);
        userRoleDao.save(role3);

        UserRoles superAdminRole = userRoleDao.findByRoleName("SuperAdmin");
        UserRoles adminRole = userRoleDao.findByRoleName("Admin");
        UserRoles employeeRole= userRoleDao.findByRoleName("Employee");

        //preseeding users data
        Users user1=new Users();
        user1.setUsername("SU");
        user1.setPassword(passwordEncoder.encode("su@123"));
        user1.setEmail("su@gmail.com");
        user1.setStatus(true);
        Date d1=df.parse("2023-01-01");
        user1.setCreatedDate(d1);
        user1.setFullName("Super Admin 1");
        user1.setDeletedDate(null);
        user1.setUpdatedDate(null);
        user1.setUpdatedBy("root");
        user1.setDeletedDate(null);
        user1.setDeletedBy(null);
        user1.setUserRoles(Collections.singleton(superAdminRole).stream().findFirst().orElse(null));
        userDao.save(user1);

        Users user2=new Users();
        user2.setUsername("Admin1");
        user2.setEmail("admin@gmail.com");
        user2.setPassword(passwordEncoder.encode("admin@123"));
        user2.setStatus(true);
        Date d2=df.parse("2023-02-23");
        user2.setCreatedDate(d2);
        user2.setFullName("Admin 1");
        user2.setUpdatedDate(null);
        user2.setUpdatedBy("SU");
        user2.setDeletedDate(null);
        user2.setDeletedBy(null);
        user2.setUserRoles(Collections.singleton(adminRole).stream().findFirst().orElse(null));
        userDao.save(user2);

        Users user3=new Users();
        user3.setUsername("sudh");
        user3.setEmail("sudh@gmail.com");
        user3.setPassword(passwordEncoder.encode("sudh@123"));
        user3.setStatus(true);
        Date d3=df.parse("2023-03-23");
        user3.setCreatedDate(d3);
        user3.setFullName("Sudhanshu");
        Date d4=df.parse("2023-03-24");
        user3.setUpdatedDate(d4);
        user3.setUpdatedBy("Admin1");
        user3.setDeletedDate(null);
        user3.setDeletedBy(null);
        user3.setUserRoles(Collections.singleton(employeeRole).stream().findFirst().orElse(null));
        userDao.save(user3);

        Users user4=new Users();
        user4.setUsername("abc");
        user4.setEmail("abc@gmail.com");
        user4.setPassword(passwordEncoder.encode("abc@123"));
        user4.setStatus(false);
        Date d5=df.parse("2023-03-23");
        user4.setCreatedDate(d5);
        user4.setFullName("abc boy");
        Date d6=df.parse("2023-03-24");
        user4.setUpdatedDate(d6);
        user4.setUpdatedBy("Admin1");
        Date d7=df.parse("2023-03-26");
        user4.setDeletedDate(d7);
        user4.setDeletedBy("SU");
        user4.setUserRoles(Collections.singleton(employeeRole).stream().findFirst().orElse(null));
        userDao.save(user4);

        AssetTypes assetType1 = new AssetTypes();
        assetType1.setAssetTypeName("Laptop");
        assetType1.setCategoryType(AssetTypes.categoryType.NonConsumable);

        AssetTypes assetType2 =  new AssetTypes();
        assetType2.setAssetTypeName("Phone");
        assetType2.setCategoryType(AssetTypes.categoryType.NonConsumable);

        AssetTypes assetType3  =  new AssetTypes();
        assetType3.setAssetTypeName("Pen");
        assetType3.setCategoryType(AssetTypes.categoryType.Consumable);

        AssetTypes assetType4 =  new AssetTypes();
        assetType4.setAssetTypeName("Marker");
        assetType4.setCategoryType(AssetTypes.categoryType.Consumable);

        AssetTypes assetType5  =  new AssetTypes();
        assetType5.setAssetTypeName("Diary");
        assetType5.setCategoryType(AssetTypes.categoryType.Consumable);

        List<AssetTypes> assetTypesList = Arrays.asList(assetType1,assetType2,assetType3,assetType4,assetType5);
        assetTypeDao.saveAll(assetTypesList);
    }
}
