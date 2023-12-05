package com.noticias.noticias.servicios;

import com.noticias.noticias.entidades.Noticia;
import com.noticias.noticias.excepciones.MiExcepcion;
import com.noticias.noticias.repositorios.NoticiaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoticiaServicio {

    @Autowired
    NoticiaRepositorio noticiaRepositorio;
    @Transactional
    public void crearNoticia(String titulo, String cuerpo) throws MiExcepcion {
        Noticia noticia = new Noticia();
        validarNoticia(titulo,cuerpo);
        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        noticiaRepositorio.save(noticia);
    }

    @Transactional
    public void modificarNoticia(Long id,String titulo, String cuerpo) throws MiExcepcion {
        if(id == null){
            throw new MiExcepcion("El id no puede ser nulo");
        }
        Optional<Noticia> noticia = noticiaRepositorio.findById(String.valueOf(id));
        validarNoticia(titulo,cuerpo);
        if(noticia.isPresent()){
           Noticia n = noticia.get();
           n.setTitulo(titulo);
           n.setCuerpo(cuerpo);
           noticiaRepositorio.save(n);
        }
    }

    @Transactional
    public void eliminarNoticia(Long id) throws MiExcepcion {
        if (id == null ){
            throw new MiExcepcion("El id no puede ser nulo");
        }
        Optional<Noticia> respuesta = noticiaRepositorio.findById(String.valueOf(id));
        if (respuesta.isPresent()){
            Noticia noticia = respuesta.get();
            noticiaRepositorio.delete(noticia);
        }
    }

    public List<Noticia> mostrarNoticias(){
        List<Noticia> listaNoticias = new ArrayList();
        listaNoticias = noticiaRepositorio.findAll();
        return listaNoticias;
    }

    public Noticia getOne(Long id){
        return noticiaRepositorio.getOne(String.valueOf(id));
    }
    public void validarNoticia(String titulo, String cuerpo) throws MiExcepcion{
        if(titulo.isEmpty() || titulo == null ){
            throw new MiExcepcion("El titulo no puede ser nulo o vacio");
        }
        if(cuerpo.isEmpty() || cuerpo == null ){
            throw new MiExcepcion("El titulo no puede ser nulo");
        }
    }
}
