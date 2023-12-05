package com.noticias.noticias.controladores;

import com.noticias.noticias.entidades.Noticia;
import com.noticias.noticias.servicios.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class InicioControlador {

    @Autowired
    NoticiaServicio noticiaServicio;
    @GetMapping("/")
    public String inicio(ModelMap model){
        List<Noticia> noticias = noticiaServicio.mostrarNoticias();
        model.addAttribute("noticias", noticias);
        return "index.html";
    }




}
