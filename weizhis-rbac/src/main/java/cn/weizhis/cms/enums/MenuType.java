package cn.weizhis.cms.enums;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 15:33
 * @Description:
 */
public enum MenuType {
    CATALOG(0,"目录"),
    MENU(1, "菜单"),
    BUTTON(2, "按钮");

    MenuType(int vaule, String name){
        this.value = vaule;
        this.name = name;
    }
    private String name;
    private int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
