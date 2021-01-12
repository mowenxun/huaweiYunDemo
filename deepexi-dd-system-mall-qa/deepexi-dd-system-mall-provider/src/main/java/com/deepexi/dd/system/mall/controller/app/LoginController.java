package com.deepexi.dd.system.mall.controller.app;

import com.deepexi.dd.system.mall.domain.dto.User.LoginAdminRequestDTO;
import com.deepexi.dd.system.mall.domain.dto.User.LoginResponseDTO;
import com.deepexi.dd.system.mall.service.app.LoginService;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import javax.validation.Valid;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;

@RestController
@RequestMapping("/admin-api/v1/app/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping
    @ApiOperation("登录接口")
    public Payload<LoginResponseDTO> login(@Valid LoginAdminRequestDTO dto)throws Exception{
        return new Payload<>(loginService.login(dto));
    }

    @PostMapping("/loging")
    @ApiOperation("登录接口-加密")
    public Payload<LoginResponseDTO> loging(@Valid @RequestBody LoginAdminRequestDTO dto)throws Exception{

        String abc =rsaEncrypt("haha","MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCWqmlnUmee2iILSz0HR7Z3dfvk1YqLR+sOb7+ZKsd4fXmh+ruwSFQjGLmySv2ZEs8gLNDqml84+AkH9ZLlAEoMCWT/MPiOwoQxr9uVDYcQUkxh1AsC1fEVD89EZpsZHGmEhw3/sRCeA3ZDhxVElpqi1RzWAKMT066BH/F0/9qZrQIDAQAB");

        return new Payload<>(loginService.logining(dto));
    }

    /**
     * RSA加密（公钥）
     *
     * @param value 需要加密的值
     * @param publicKey 公钥
     * @return 已加密的值
     */
    public static String rsaEncrypt(String value, String publicKey)
            throws Exception {
        // 对公钥解密
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key key = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptData = cipher.doFinal(value.getBytes());

        return Base64.encodeBase64String(encryptData);
    }
}
