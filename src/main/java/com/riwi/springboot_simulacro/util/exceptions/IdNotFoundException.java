package com.riwi.springboot_simulacro.util.exceptions;

public class IdNotFoundException extends RuntimeException {

    //%s es para insertar variable
    private static final String ERROR_MESSAGE = "No hay registro en la entidad %s con el id suministrado";

    public IdNotFoundException(String nameEntity){
        /*
        * Se utiliza el constructor de RuntimeException y enviamos el mensaje
        * Inyectamos el nombre de la entidad
        * */

        super(String.format(ERROR_MESSAGE, nameEntity));

    }
}
