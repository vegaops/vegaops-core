package org.prophetech.hyperone.vegaops.ctyun.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class CustomInfo implements Serializable {
    /**
     * 客户名称
     */
    private String name;
    /**
     * 客户邮箱
     */
    private String email;
    /**
     * 客户手机
     */
    private String phone;
    /**
     * 客户类型，type为空或0时，使用accessKey匹配的用户做为订单的客户标识；
     * 为1时，使用identity中的crmBizId匹配的用户做为订单的客户标识；
     * 为2时，使用identity中的accountId作为订单用户的标识
     */
    private Integer type;
    /**
     * 身份信息
     */
    private Identity identity;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Identity{
        /**
         * 省公司crm用户bizID，type传1则必须传此值，该值主要用于省公司crm用户
         */
        private String crmBizId="";
        /**
         * 客户对应云公司账户的accountId，type传2则必须传此值，该值主要用于第三方合作客户
         */
        private String accountId="";
    }
}
