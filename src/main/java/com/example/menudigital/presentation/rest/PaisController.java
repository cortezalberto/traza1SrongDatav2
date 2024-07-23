package com.example.menudigital.presentation.rest;


import com.example.menudigital.bussines.facade.Impl.PaisFacadeImp;
import com.example.menudigital.domain.dtos.domicilioDto.PaisDto;
import com.example.menudigital.domain.entities.Pais;
import com.example.menudigital.presentation.rest.Base.BaseControllerImp;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paises")
@CrossOrigin(origins="*")
public class PaisController extends BaseControllerImp<Pais, PaisDto,Long, PaisFacadeImp> {

    public PaisController(PaisFacadeImp facade) {
        super(facade);
    }
}
