package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.admin.annotation.LoginAdmin;
import org.linlinjava.litemall.admin.util.RoleVo;
import org.linlinjava.litemall.core.util.RegexUtil;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.linlinjava.litemall.db.domain.LitemallAdmin;
import org.linlinjava.litemall.db.domain.LitemallResource;
import org.linlinjava.litemall.db.domain.LitemallResourceNode;
import org.linlinjava.litemall.db.domain.LitemallRole;
import org.linlinjava.litemall.db.service.LitemallResourceService;
import org.linlinjava.litemall.db.service.LitemallRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.linlinjava.litemall.admin.util.AdminResponseCode.ADMIN_INVALID_NAME;
import static org.linlinjava.litemall.admin.util.AdminResponseCode.ADMIN_INVALID_PASSWORD;
import static org.linlinjava.litemall.admin.util.AdminResponseCode.ADMIN_NAME_EXIST;

/**
 * @author ulongx
 */
@RestController
@RequestMapping("/admin/role")
@Validated
public class AdminRoleController {
    private final Log logger = LogFactory.getLog(AdminRoleController.class);

    @Autowired
    private LitemallRoleService roleService;

    @Autowired
    private LitemallResourceService resourceService;

    @GetMapping("/read")
    public Object read(@LoginAdmin Integer adminId, @NotNull Integer id) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }

        LitemallRole brand = roleService.findById(id);

        return ResponseUtil.ok(brand);
    }

    @GetMapping("/list")
    public Object list(@LoginAdmin Integer adminId,
                       String rolename,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "role_name") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }

        List<LitemallRole> roleList = roleService.querySelective(rolename, page, limit, sort, order);
        int total = roleService.countSelective(rolename, page, limit, sort, order);
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("items", roleList);

        return ResponseUtil.ok(data);
    }

    private Object validate(LitemallRole role) {
        String name = role.getRoleName();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }
        if (!RegexUtil.isUsername(name)) {
            return ResponseUtil.fail(ADMIN_INVALID_NAME, "角色名称不符合规定");
        }
        return null;
    }

    /**
     * 角色增加
     * @param adminId
     * @param role
     * @return
     */
    @PostMapping("/create")
    public Object create(@LoginAdmin Integer adminId, @RequestBody RoleVo role) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }
        Object error = validate(role);
        if (error != null) {
            return error;
        }

        List<LitemallRole> roleList = roleService.findByRoleName(role.getRoleName());
        if (roleList.size() > 0){
            return ResponseUtil.fail(ADMIN_NAME_EXIST, "该角色已经存在");
        }

        roleService.add(role, role.getResources());

        return ResponseUtil.ok(role);
    }
    @PostMapping("/update")
    public Object update(@LoginAdmin Integer adminId, @RequestBody LitemallRole role) {
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }
        Object error = validate(role);
        if (error != null) {
            return error;
        }

        return ResponseUtil.ok();
    }

    @GetMapping("/listSrc")
    public Object listResource(@LoginAdmin Integer adminId){
        if (adminId == null) {
            return ResponseUtil.unlogin();
        }

        List<LitemallResourceNode> resourceNodes = resourceService.queryResorceTree();

        return ResponseUtil.ok(resourceNodes);
    }
//
//    private Object validate(LitemallAdmin admin) {
//        String name = admin.getUsername();
//        if (StringUtils.isEmpty(name)) {
//            return ResponseUtil.badArgument();
//        }
//        if (!RegexUtil.isUsername(name)) {
//            return ResponseUtil.fail(ADMIN_INVALID_NAME, "管理员名称不符合规定");
//        }
//        String password = admin.getPassword();
//        if (StringUtils.isEmpty(password) || password.length() < 6) {
//            return ResponseUtil.fail(ADMIN_INVALID_PASSWORD, "管理员密码长度不能小于6");
//        }
//        return null;
//    }
//
//    @PostMapping("/create")
//    public Object create(@LoginAdmin Integer adminId, @RequestBody LitemallAdmin admin) {
//        if (adminId == null) {
//            return ResponseUtil.unlogin();
//        }
//        Object error = validate(admin);
//        if (error != null) {
//            return error;
//        }
//
//        String username = admin.getUsername();
//        List<LitemallAdmin> adminList = adminService.findAdmin(username);
//        if (adminList.size() > 0) {
//            return ResponseUtil.fail(ADMIN_NAME_EXIST, "管理员已经存在");
//        }
//
//        String rawPassword = admin.getPassword();
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encodedPassword = encoder.encode(rawPassword);
//        admin.setPassword(encodedPassword);
//        adminService.add(admin);
//        return ResponseUtil.ok(admin);
//    }
//
//    @GetMapping("/read")
//    public Object read(@LoginAdmin Integer adminId, @NotNull Integer id) {
//        if (adminId == null) {
//            return ResponseUtil.unlogin();
//        }
//
//        LitemallAdmin admin = adminService.findById(id);
//        return ResponseUtil.ok(admin);
//    }
//
//    @PostMapping("/update")
//    public Object update(@LoginAdmin Integer adminId, @RequestBody LitemallAdmin admin) {
//        if (adminId == null) {
//            return ResponseUtil.unlogin();
//        }
//        Object error = validate(admin);
//        if (error != null) {
//            return error;
//        }
//
//        Integer anotherAdminId = admin.getId();
//        if (anotherAdminId == null) {
//            return ResponseUtil.badArgument();
//        }
//
//        String rawPassword = admin.getPassword();
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encodedPassword = encoder.encode(rawPassword);
//        admin.setPassword(encodedPassword);
//
//        if (adminService.updateById(admin) == 0) {
//            return ResponseUtil.updatedDataFailed();
//        }
//
//        return ResponseUtil.ok(admin);
//    }
//
//    @PostMapping("/delete")
//    public Object delete(@LoginAdmin Integer adminId, @RequestBody LitemallAdmin admin) {
//        if (adminId == null) {
//            return ResponseUtil.unlogin();
//        }
//
//        Integer anotherAdminId = admin.getId();
//        if (anotherAdminId == null) {
//            return ResponseUtil.badArgument();
//        }
//
//        adminService.deleteById(anotherAdminId);
//        return ResponseUtil.ok();
//    }
}
