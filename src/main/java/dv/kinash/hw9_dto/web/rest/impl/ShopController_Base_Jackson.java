package dv.kinash.hw9_dto.web.rest.impl;

import dv.kinash.hw9_dto.models.DTO.ShopDTO;
import dv.kinash.hw9_dto.service.ShopService;
import dv.kinash.hw9_dto.web.rest.ShopControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shops")
public class ShopController_Base_Jackson implements ShopControllerBase {
    @Autowired
    private ShopService service;

    @Override
    public List<ShopDTO> getList(){
        return service.getList();
    }

    @Override
    public void add(@RequestBody ShopDTO newShop){
        service.add(newShop);
    }

    @Override
    public ResponseEntity<ShopDTO> getById(@PathVariable("id") Integer id){
        ShopDTO foundShop = service.getById(id);
        if (foundShop == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(foundShop, HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteById(@PathVariable("id") Integer id){
        if (! service.deleteById(id))
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ShopDTO> updateById(@PathVariable("id") Integer id, @RequestBody ShopDTO shop){
        ShopDTO updatedShop = service.updateById(id, shop);
        if (updatedShop == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedShop, HttpStatus.OK);
    }

}
