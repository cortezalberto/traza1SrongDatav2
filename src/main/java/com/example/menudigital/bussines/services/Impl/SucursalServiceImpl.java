package com.example.menudigital.bussines.services.Impl;

import com.example.menudigital.bussines.services.SucursalService;
import com.example.menudigital.bussines.services.base.BaseServiceImp;
import com.example.menudigital.domain.entities.Categoria;
import com.example.menudigital.domain.entities.Sucursal;
import com.example.menudigital.repositories.DomicilioRepository;
import com.example.menudigital.repositories.EmpresaRepository;
import com.example.menudigital.repositories.SucursalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SucursalServiceImpl extends BaseServiceImp<Sucursal, Long> implements SucursalService {
    @Autowired
    private SucursalRepository sucursalRepository;
    @Autowired
    private DomicilioRepository domicilioRepository;
    @Autowired
    private EmpresaRepository empresaRepository;


    @Override
    @Transactional
    public Sucursal create(Sucursal sucursal) {
        var empresa = empresaRepository.getById(sucursal.getEmpresa().getId());
       var domicilio=domicilioRepository.save(sucursal.getDomicilio());
        domicilioRepository.save(domicilio);
        sucursal.setDomicilio(domicilio);

        var sucursalPersisted = sucursalRepository.save(sucursal);
        empresa.getSucursales().add(sucursalPersisted);
        empresaRepository.save(empresa);
        return sucursalPersisted;

    }

    @Override
    @Transactional
    public Sucursal update(Sucursal sucursal, Long id) {
        var sucursalActualizar = sucursalRepository.getById(sucursal.getId());
        var domicilio = domicilioRepository.getById(sucursal.getDomicilio().getId());
        domicilioRepository.save(sucursal.getDomicilio());
        var empresa = empresaRepository.getById(sucursal.getEmpresa().getId());
        sucursal.setDomicilio(domicilio);
        sucursal.setEmpresa(empresa);
        sucursal.setCategorias(sucursalActualizar.getCategorias());
        return sucursalRepository.save(sucursal);
    }

    @Override
    public List<Categoria> findCategoriasBySucursalId(Long sucursalId) {
        var sucursalExiste = sucursalRepository.getById(sucursalId);

        List<Categoria> categorias = sucursalRepository.findCategoriasBySucursalId(sucursalId);

        return categorias;
    }


    @Override
    public boolean existsSucursalByEsCasaMatriz(Long id) {
        return sucursalRepository.existsSucursalByEsCasaMatriz(id);
    }

    @Override
    public List<Sucursal> findAllByEmpresaId(Long id) {
        return sucursalRepository.findAllByEmpresaId(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        var sucursal = sucursalRepository.getById(id);
        if(sucursal.getCategorias().size() > 0) {
            throw new RuntimeException("No se puede eliminar la sucursal porque tiene categorias asociadas");
        }
        sucursalRepository.delete(sucursal);
    }
}
