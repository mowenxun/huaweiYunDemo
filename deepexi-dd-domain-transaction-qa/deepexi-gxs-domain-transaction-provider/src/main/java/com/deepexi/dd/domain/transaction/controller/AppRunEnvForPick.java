package com.deepexi.dd.domain.transaction.controller;

import com.deepexi.clientiam.model.GroupResultVO;
import lombok.Data;

@Data
public class AppRunEnvForPick {
    GroupResultVO topOrganization ;
    GroupResultVO userOrganization;
    String tenantId ;
    Long userId ;
    String username;
    Long appId ;
}
