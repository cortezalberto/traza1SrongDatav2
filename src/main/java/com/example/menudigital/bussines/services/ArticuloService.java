package com.example.menudigital.bussines.services;

import com.example.menudigital.bussines.services.base.BaseService;
import com.example.menudigital.domain.entities.Articulo;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticuloService extends BaseService<Articulo, Long> {
    boolean existsArticuloByAlergeno(Long idAlergeno);
    List<Articulo> findAllBySucursalId( Long idSucusal);

}
