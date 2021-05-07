package com.zonions.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.zonions.entity.Menu;
import com.zonions.service.MenuService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/zonions")
public class MenuController {

  @Autowired
  private MenuService menuService;

  // To upload menu
  @PostMapping(value = "/restaurant/restaurantImage/{id}/{menuType}",
      consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public Optional<Menu> UploadMenu(@RequestParam MultipartFile file, @PathVariable int id,
      @PathVariable String menuType) {
    System.out.println("file : " + file);
    System.out.println("Id :" + id);
    System.out.println("Type: " + menuType);
    return menuService.uploadMenu(file, id, menuType);
  }

  // To update menu
  @PutMapping(value = "/restaurant/file/{id}/{type}",
      consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public Menu editMenu(@RequestParam("file") MultipartFile file, @PathVariable int id,
      @PathVariable String type) {
    return menuService.editMenu(file, id, type);
  }
}
