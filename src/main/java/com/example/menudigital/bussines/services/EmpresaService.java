package com.example.menudigital.bussines.services;

import com.example.menudigital.bussines.services.base.BaseService;
import com.example.menudigital.domain.entities.Empresa;

public interface EmpresaService extends BaseService<Empresa, Long> {
    public Empresa addSucursal(Long idEmpresa, Long idSucursal);
    public Empresa findWithSucursalesById(Long id);
}
