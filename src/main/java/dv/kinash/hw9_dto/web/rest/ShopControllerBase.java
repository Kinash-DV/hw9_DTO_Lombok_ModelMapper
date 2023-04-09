package dv.kinash.hw9_dto.web.rest;

import dv.kinash.hw9_dto.models.DTO.ShopDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ShopControllerBase {
    @GetMapping(value = {"", "/", "/list"})
    @ResponseStatus(HttpStatus.OK)
    public List<ShopDTO> getList();

    @PostMapping(value = {"/", "/add"})
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody ShopDTO newShop);

    @GetMapping("/get/{id}")
    public ResponseEntity<ShopDTO> getById(@PathVariable("id") Integer id);

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public ResponseEntity deleteById(@PathVariable("id") Integer id);

    @RequestMapping(value = {"/{id}", "/update/{id}"}, method = {RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.POST})
    public ResponseEntity<ShopDTO> updateById(@PathVariable("id") Integer id, @RequestBody ShopDTO shop);
}
