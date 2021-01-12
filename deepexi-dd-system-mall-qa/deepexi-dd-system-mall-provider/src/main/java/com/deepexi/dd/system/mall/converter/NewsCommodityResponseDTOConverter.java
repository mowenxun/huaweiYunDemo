package com.deepexi.dd.system.mall.converter;

import com.deepexi.dd.system.mall.domain.dto.commodity.NewsCommodityRecommendResponseDTO;
import com.deepexi.dd.system.mall.domain.dto.commodity.NewsCommodityResponseDTO;
import com.deepexi.sdk.commodity.model.PageShelvesItemSkuGroupSkuResponseDTO;
import com.deepexi.sdk.commodity.model.PageShelvesItemSkuResponseDTO;
import com.deepexi.util.CollectionUtil;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class NewsCommodityResponseDTOConverter {

    public static NewsCommodityResponseDTO convert(PageShelvesItemSkuResponseDTO dto){
        NewsCommodityResponseDTO result = new NewsCommodityResponseDTO();
        result.setChannelId(dto.getChannelId());
        result.setId(dto.getItemWhole().getId());
        result.setItemReleaseShop(dto.getItemReleaseShop());
        result.setMajorPicture(dto.getItemWhole().getMajorPicture());
        result.setName(dto.getItemWhole().getName());
        result.setSubName(dto.getItemWhole().getSubName());
        result.setRemark(dto.getItemWhole().getRemark());
        result.setShopId(dto.getShopId());
        result.setPrice(new BigDecimal(0));
        result.setSkuIds(dto.getSkuList().stream().map(PageShelvesItemSkuGroupSkuResponseDTO::getId).collect(Collectors.toList()));
        if (Objects.nonNull(dto.getItemWhole())) {
            result.setItemTagList(dto.getItemWhole().getItemTagList());
        }
        return result;
    }

    public static NewsCommodityRecommendResponseDTO convert2(PageShelvesItemSkuResponseDTO dto){
        NewsCommodityRecommendResponseDTO result = new NewsCommodityRecommendResponseDTO();
        result.setChannelId(dto.getChannelId());
        result.setId(dto.getItemWhole().getId());
        result.setItemReleaseShop(dto.getItemReleaseShop());
        result.setMajorPicture(dto.getItemWhole().getMajorPicture());
        result.setName(dto.getItemWhole().getName());
        result.setSubName(dto.getItemWhole().getSubName());
        result.setRemark(dto.getItemWhole().getRemark());
        result.setShopId(dto.getShopId());
        List<PageShelvesItemSkuGroupSkuResponseDTO> pageShelvesItemSkuGroupSkuResponseDTOList = dto.getSkuList();
        if (CollectionUtil.isEmpty(pageShelvesItemSkuGroupSkuResponseDTOList)) {
            result.setSuggestPrice(null);
        } else {
            // 根据建议零售价正序（从小到大）排列
            List<PageShelvesItemSkuGroupSkuResponseDTO> newList = pageShelvesItemSkuGroupSkuResponseDTOList.stream().sorted(Comparator.comparing(PageShelvesItemSkuGroupSkuResponseDTO::getPrice))
                    .collect(Collectors.toList());
            result.setSuggestPrice(newList.get(0).getPrice());
            result.setSkuCode(newList.get(0).getCode());
        }
        result.setSkuIds(dto.getSkuList().stream().map(PageShelvesItemSkuGroupSkuResponseDTO::getId).collect(Collectors.toList()));
        if (Objects.nonNull(dto.getItemWhole())) {
            result.setItemTagList(dto.getItemWhole().getItemTagList());
        }
        return result;
    }
}
