package dv.kinash.hw9_dto.web.rest;

import dv.kinash.hw9_dto.models.DTO.ShopDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ShopControllerMarshaling {
    @GetMapping(value = {"", "/", "/list"})
    @ResponseStatus(HttpStatus.OK)
    public String getList(HttpServletResponse response);

    @PostMapping(value = {"/", "/add"})
    @ResponseStatus(HttpStatus.CREATED)
    public void add(HttpServletRequest request);

    @GetMapping("/get/{id}")
    public void getById(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response);

    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public void deleteById(HttpServletRequest request, HttpServletResponse response);

    @RequestMapping(value = {"/{id}", "/update/{id}"}, method = {RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.POST})
    public void updateById(HttpServletRequest request, @PathVariable("id") Integer id, HttpServletResponse response);
}
