package dv.kinash.hw9_dto.web.rest.impl;

import com.google.gson.Gson;
import dv.kinash.hw9_dto.models.DTO.ShopDTO;
import dv.kinash.hw9_dto.service.ShopService;
import dv.kinash.hw9_dto.web.rest.ShopControllerMarshaling;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gson")
public class ShopController_Marshaling_Gson implements ShopControllerMarshaling {
    @Autowired
    private ShopService service;
    @Autowired
    private Gson mapper;

    @Override
    public String getList(HttpServletResponse response) {
        List<ShopDTO> list = service.getList();
        return mapper.toJson(list);
    }
    @SneakyThrows
    @Override
    public void add(HttpServletRequest request) {
        BufferedReader reader = request.getReader();
        String bodyString = reader.lines().collect(Collectors.joining());
        ShopDTO newShop = mapper.fromJson(bodyString, ShopDTO.class);
        service.add(newShop);
    }

    @Override
    public void getById(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response) {
        ShopDTO foundShop = service.getById(id);
        if (foundShop == null)
            response.setStatus(HttpStatus.NOT_FOUND.value());
        else {
            try {
                response.setStatus(HttpStatus.OK.value());
                response.getWriter().println(mapper.toJson(foundShop));
            } catch (IOException e) {
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        }
    }

    @Override
    public void deleteById(HttpServletRequest request, HttpServletResponse response) {
        String stringId = (String) request.getParameter("id");
        if (stringId == null) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return;
        }
        Integer requestId = Integer.valueOf(stringId);
        if (service.deleteById(requestId))
            response.setStatus(HttpStatus.OK.value());
        else
            response.setStatus(HttpStatus.NOT_FOUND.value());
    }

    @Override
    public void updateById(HttpServletRequest request, @PathVariable("id") Integer id, HttpServletResponse response) {
        ShopDTO shop;
        try {
            ServletInputStream stream = request.getInputStream();
            String bodyString = new String(stream.readAllBytes());
            shop = mapper.fromJson(bodyString, ShopDTO.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ShopDTO updatedShop = service.updateById(id, shop);
        if (updatedShop == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return;
        }
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            String bodyString = mapper.toJson(updatedShop);
            outputStream.write(bodyString.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
