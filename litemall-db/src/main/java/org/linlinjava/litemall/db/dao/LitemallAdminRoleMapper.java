package org.linlinjava.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.LitemallAdminRole;
import org.linlinjava.litemall.db.domain.LitemallAdminRoleExample;

public interface LitemallAdminRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin_role
     *
     * @mbg.generated
     */
    long countByExample(LitemallAdminRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin_role
     *
     * @mbg.generated
     */
    int deleteByExample(LitemallAdminRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin_role
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin_role
     *
     * @mbg.generated
     */
    int insert(LitemallAdminRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin_role
     *
     * @mbg.generated
     */
    int insertSelective(LitemallAdminRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin_role
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallAdminRole selectOneByExample(LitemallAdminRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin_role
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallAdminRole selectOneByExampleSelective(@Param("example") LitemallAdminRoleExample example, @Param("selective") LitemallAdminRole.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin_role
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    List<LitemallAdminRole> selectByExampleSelective(@Param("example") LitemallAdminRoleExample example, @Param("selective") LitemallAdminRole.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin_role
     *
     * @mbg.generated
     */
    List<LitemallAdminRole> selectByExample(LitemallAdminRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin_role
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallAdminRole selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") LitemallAdminRole.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin_role
     *
     * @mbg.generated
     */
    LitemallAdminRole selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin_role
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LitemallAdminRole record, @Param("example") LitemallAdminRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin_role
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LitemallAdminRole record, @Param("example") LitemallAdminRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin_role
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LitemallAdminRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin_role
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LitemallAdminRole record);
}