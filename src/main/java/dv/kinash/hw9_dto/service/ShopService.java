package dv.kinash.hw9_dto.service;

import dv.kinash.hw9_dto.models.DTO.ShopDTO;
import dv.kinash.hw9_dto.models.converter.ShopConverter;
import dv.kinash.hw9_dto.models.entity.Shop;
import dv.kinash.hw9_dto.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShopService {
    @Autowired
    private ShopRepository repository;
    public ShopService() {
    }

    public ShopDTO getById(Integer id){
        Shop shop = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found by id="+id));
        return ShopConverter.GetDTOFromData(shop);
    }
    public List<ShopDTO> getList(){
        List<ShopDTO> shopList = new ArrayList<>();
        repository.findAll().forEach(shop -> shopList.add(ShopConverter.GetDTOFromData(shop)));
        return shopList;
    }
    public void add(ShopDTO shop){
        shop.setId(null);
        repository.save(ShopConverter.GetDataFromDTO(shop));
    }
    public ShopDTO updateById(Integer id, ShopDTO shop){
        if (repository.findById(id).isEmpty())
            return null;
        shop.setId(id);
        repository.save(ShopConverter.GetDataFromDTO(shop));
        return shop;
    }
    public Boolean deleteById(Integer id){
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
