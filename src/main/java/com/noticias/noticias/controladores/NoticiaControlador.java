package com.noticias.noticias.controladores;

import com.noticias.noticias.excepciones.MiExcepcion;
import com.noticias.noticias.servicios.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping("/noticia")
public class NoticiaControlador {
    @Autowired
    NoticiaServicio noticiaServicio;
    @GetMapping("/mostrar/{id}")
    public String mostrar(@PathVariable Long id, ModelMap modelo){
        modelo.put("noticia", noticiaServicio.getOne(id));
        return "mostrar_noticia";
    }

    @GetMapping("/registrar")
    public String registar(){
        return "crear_noticia";
    }
    @PostMapping("/registro")
    public String registrar(@RequestParam String titulo, @RequestParam String cuerpo) throws MiExcepcion {
        try{
            noticiaServicio.crearNoticia(titulo,cuerpo);
        }catch(MiExcepcion e){
            Logger.getLogger(NoticiaControlador.class.getName());
            return "crear_noticia";
        }
        return "index";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, ModelMap modelo){
        modelo.put("noticia", noticiaServicio.getOne(id));
        return "modificar_noticia";
    }
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, String titulo, String cuerpo) throws MiExcepcion {
        try{
            noticiaServicio.modificarNoticia(id,titulo, cuerpo);
        }catch(MiExcepcion e){
            Logger.getLogger(NoticiaControlador.class.getName());
            return "modificar_noticia";
        }
        return "index";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, ModelMap modelo){
        modelo.put("noticia", noticiaServicio.getOne(id));
        return "eliminar_noticia";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) throws MiExcepcion {
        try{
            noticiaServicio.eliminarNoticia(id);
        } catch (MiExcepcion e) {
            throw new MiExcepcion("El id es nulo, o no se encuentra la noticia");
        }
        return "index";
    }

}
