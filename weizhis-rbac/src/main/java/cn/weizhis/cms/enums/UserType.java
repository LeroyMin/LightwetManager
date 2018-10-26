package cn.weizhis.cms.enums;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 17:42
 * @Description:
 */
public enum UserType {

    SAAS_ADMIN("0", "saas管理员"),

    SAAS_MER("1", "saas平台下商户"),

    PLAT_MERT("2", "平台商户"),

    PLAT_ADMIN("3", "平台商户管理员"),

    PLAT_SUB_MERT("4","平台子商户");


    UserType(String code, String name){
        this.code = code;
        this.name = name;
    }
    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
