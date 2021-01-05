package com.bairui.common.enums;
/*
*  描述：业务枚举类
*
 */
public enum BussEnums  {
    LARGE_CASH_APPOINTMENT("1001","CHS1001","大额现金预约"),
    ACCOUNT_LIMIT_MODIFY("1002","CHS1002","限额修改"),
    CASH_TRANSFER("1003","CHS1003","转账汇款"),
    MOBILE_PHONE_MODIFY("1004","CHS1004","手机号修改");

    private String bussId;//业务id
    private String bussCode;//业务编码
    private String bussName;//业务名称

    BussEnums(String bussId, String bussCode, String bussName) {
        this.bussId = bussId;
        this.bussCode = bussCode;
        this.bussName = bussName;
    }

    public String getBussId() {
        return bussId;
    }

    public String getBussCode() {
        return bussCode;
    }

    public String getBussName() {
        return bussName;
    }
    /*
    根据业务编码获取业务名称
    @param  bussCode  业务编码
    @return  业务名称
    * */
    public static String getBussName(String bussCode){
         BussEnums[]  bussEnums  =  values();
        for (BussEnums bussEnum : bussEnums) {
            if(bussCode.equals(bussEnum.getBussCode())){
                return bussEnum.getBussName();
            }
        }
        return "";
    }
}
