package cn.anx.enums;

public enum ShopStateEnum {
    CHECK(0,"checking"),OFFLINE(-1,"illegal"),SUCCESS(1,"successful"),PASS(2,"pass"),INNER_ERROR(-1001,"INNER ERROR")
    ,NULL_SHOPID(-1002,"ShopId is null");
    private int state;
    private String stateInfo;

    public static ShopStateEnum stateOf(int state){
        for (ShopStateEnum stateEnum:values()){
            if (stateEnum.getState()==state){
                return stateEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    private ShopStateEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }
}
