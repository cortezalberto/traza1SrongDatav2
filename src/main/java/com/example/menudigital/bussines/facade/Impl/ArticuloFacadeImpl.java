package com.example.menudigital.bussines.facade.Impl;

import com.example.menudigital.bussines.facade.ArticuloFacade;
import com.example.menudigital.bussines.facade.Base.BaseFacadeImp;
import com.example.menudigital.bussines.mapper.ArticuloMapper;
import com.example.menudigital.bussines.mapper.BaseMapper;
import com.example.menudigital.bussines.services.ArticuloService;
import com.example.menudigital.bussines.services.base.BaseService;
import com.example.menudigital.domain.dtos.articuloDto.ArticuloCreateDto;
import com.example.menudigital.domain.dtos.articuloDto.ArticuloDto;
import com.example.menudigital.domain.entities.Articulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticuloFacadeImpl extends BaseFacadeImp<Articulo, ArticuloDto,Long> implements ArticuloFacade {
    public ArticuloFacadeImpl(BaseService<Articulo, Long> baseService, BaseMapper<Articulo, ArticuloDto> baseMapper) {
        super(baseService, baseMapper);
    }

    @Autowired
    private ArticuloMapper articuloMapper;

    @Autowired
    private ArticuloService articuloService;

    @Override
    public List<ArticuloDto> findAllBySucursalId(Long idSucusal){
        return articuloMapper.toDTOsList(articuloService.findAllBySucursalId(idSucusal));
    }

    @Override
    public ArticuloDto createArticulo(ArticuloCreateDto dto){
        return articuloMapper.toDTO(articuloService.create(articuloMapper.toEntityCreate(dto)));
    }

    @Override
    public ArticuloDto updateArticulo(ArticuloCreateDto dto,Long id){
        // Convierte a entidad
        var entityToUpdate = articuloMapper.toEntityCreate(dto);
        // Graba una entidad
        var entityUpdated = baseService.update(entityToUpdate,id);
        // convierte a Dto para devolver
        return baseMapper.toDTO(entityUpdated);
    }
}
