package com.deepexi.dd.domain.transaction.domain.dto;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
* PickGoodStoreHouseReq
*
* @author admin
* @date Wed Aug 12 19:18:27 CST 2020
* @version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "PickGoodStoreHouseReq")
public class PickGoodStoreHouseResponseDTO extends AbstractObject implements Serializable {

    List<DepotPostResponseDTODepot> depotList;

}

