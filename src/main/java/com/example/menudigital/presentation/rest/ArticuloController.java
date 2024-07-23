package com.example.menudigital.presentation.rest;

import com.example.menudigital.bussines.facade.Impl.ArticuloFacadeImpl;
import com.example.menudigital.domain.dtos.articuloDto.ArticuloCreateDto;
import com.example.menudigital.domain.dtos.articuloDto.ArticuloDto;
import com.example.menudigital.domain.entities.Articulo;
import com.example.menudigital.presentation.rest.Base.BaseControllerImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articulos")
@CrossOrigin(origins="*")
public class ArticuloController extends BaseControllerImp<Articulo, ArticuloDto,Long, ArticuloFacadeImpl> {
    public ArticuloController(ArticuloFacadeImpl facade) {
        super(facade);
    }

    @GetMapping("/porSucursal/{idSucursal}")
    public ResponseEntity<List<ArticuloDto>> getArticulosPorSucursal(@PathVariable Long idSucursal) {
        return ResponseEntity.ok().body(facade.findAllBySucursalId(idSucursal));
    }

    @PostMapping("/create")
    public ResponseEntity<ArticuloDto> create(@RequestBody ArticuloCreateDto articuloDto) {
        return ResponseEntity.ok().body(facade.createArticulo(articuloDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ArticuloDto> update(@PathVariable Long id, @RequestBody ArticuloCreateDto articuloDto) {
        return ResponseEntity.ok().body(facade.updateArticulo(articuloDto, id));
    }
}
