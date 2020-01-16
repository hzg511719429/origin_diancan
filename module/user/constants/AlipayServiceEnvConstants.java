

/**

 * Alipay.com Inc.

 * Copyright (c) 2004-2014 All Rights Reserved.

 */

package com.zknu.shop.module.user.constants;


/**
 * 支付宝服务窗环境常量（demo中常量只是参考，需要修改成自己的常量值）
 * 
 * @author taixu.zqq
 * @version $Id: AlipayServiceConstants.java, v 0.1 2014年7月24日 下午4:33:49 taixu.zqq Exp $
 */
public class AlipayServiceEnvConstants {

    /**支付宝公钥-从支付宝生活号详情页面获取*/
	public static final String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB";
    
    /**签名编码-视支付宝服务窗要求*/
    public static final String SIGN_CHARSET      = "GBK";

    /**字符编码-传递给支付宝的数据编码*/
    public static final String CHARSET           = "GBK";

    /**签名类型-视支付宝服务窗要求*/
    public static final String SIGN_TYPE         = "RSA2";
    
    /**开发者账号PID*/
    public static final String PARTNER           = "";

    /** 服务窗appId  */
    //TODO !!!! 注：该appId必须设为开发者自己的生活号id  
    public static final String APP_ID            = "2016080500176754";

    //TODO !!!! 注：该私钥为测试账号私钥  开发者必须设置自己的私钥 , 否则会存在安全隐患 
    public static final String PRIVATE_KEY       = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJioxfrWSe0zX5IY39ShTenK2Rr+Jsv4Vo7bkFCVjayiztfhWdjAOuA12waTet5sQTP84gjXtCtWwDL7A0h0tqyiStqhzt5IkaziC+Qzm+cgJAFhX0+QTIvHJG6aJ9Nl7qjMwX1zRYjERWJffN7dnGX2GXIwCFpwfvbhG8EmykBFAgMBAAECgYAd5R8wKfq8Ks1SbPCYUSZS3Fh7wr2SQAWjYDf4r/DYl+FtwUG5ApwFAEbuGL99AzIZnjIdjTBJNGnbEfDopYKIKcZAbJL+jTfTj471POKM8D8nvEKoF4ntZEOX4qx0IMJub2NVkQ8VnAPAvi6481d0kHyKLnlCAgamX9JjrYh3mQJBAOYQHwGaNGvrLYSNDWa0dsO7PD2lkxLFmPFjY1xySSqLljBiMtM0hm6RvAOisol5Kx5lR/lBbvxha+meRgt4AJsCQQCp3rGEIcVsqQkDC5v1uEml2jBtH2tNVbNmT3ZuVUyw8Yzjv0wiJdxBAmzppXNWuP5xtmzvvKhJS8WkN1E4M6CfAkEA4RwZGrpGXOprSW2zFTGzm+WTFkby18Vc16UGTijNisG2nXUYgiy89WfH7k6+H2HeXQmBGVV7QZwZuSfTJdmvzQJARKVNr0vGcGbO0YA7gkz61h8aOr7bqeDcIpgyD9xApbC3csG3joUmQM8uHC1UMtOHX4SM+USP7S7Fq9r+42tZmQJAY9s9FV6oOPcLuzzIOiCQuicjrYyLtW7kcJJYXohAa1UdkHioRaZsvFTYbsiVqwdYsMnDZZOYaA0JfaDlWjSFwA==";
    
    //TODO !!!! 注：该公钥为测试账号公钥  开发者必须设置自己的公钥 ,否则会存在安全隐患
    public static final String PUBLIC_KEY        = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB";
    /**支付宝网关*/
    public static final String ALIPAY_GATEWAY    = "https://openapi.alipaydev.com/gateway.do";

    /**授权访问令牌的授权类型*/
    public static final String GRANT_TYPE        = "authorization_code";
}