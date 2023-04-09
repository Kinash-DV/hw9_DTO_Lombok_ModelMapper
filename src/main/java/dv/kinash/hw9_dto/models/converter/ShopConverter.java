package dv.kinash.hw9_dto.models.converter;

import dv.kinash.hw9_dto.models.DTO.ShopDTO;
import dv.kinash.hw9_dto.models.entity.Shop;

public abstract class ShopConverter {
    public static Shop GetDataFromDTO(ShopDTO dto){
        Shop shop = new Shop();
        shop.setId(dto.getId());
        shop.setName(dto.getName());
        shop.setCity(dto.getCity());
        shop.setStreet(dto.getStreet());
        shop.setHasWebsite(dto.getHasWebsite());
        shop.setHeadcount(0);
        return shop;
    }
    public static ShopDTO GetDTOFromData(Shop shop){
        ShopDTO dto = new ShopDTO();
        dto.setId(shop.getId());
        dto.setName(shop.getName());
        dto.setCity(shop.getCity());
        dto.setStreet(shop.getStreet());
        dto.setHasWebsite(shop.getHasWebsite());
        return dto;
    }
}
