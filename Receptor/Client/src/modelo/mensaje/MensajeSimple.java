package modelo.mensaje;

import excepciones.MensajeMalFormadoException;

import modelo.ArmableDesarmable;
import modelo.Usuario;

public class MensajeSimple extends Mensaje {
    public MensajeSimple(String asunto, String descripcion, Usuario destinatario, Usuario emisor) {
        super(asunto,descripcion,destinatario,emisor);
    }

    @Override
    public String desarmar() {
        return Mensaje.MENSAJE_SIMPLE + ArmableDesarmable.SEPARADOR +
               super.toString();
    }
    
    public static MensajeSimple armar(String msg) throws MensajeMalFormadoException {  
        String[] aux = msg.split(SEPARADOR); // se usa split aca y en el super() lo que es ineficiente
        if (aux.length != 6) //se deberia cambiar el 6 por algo mas dinamico
            throw new MensajeMalFormadoException(aux.length, 6);
        // Usuario deberia implementar armar y desarmar (muy acoplado)
        // pero por ahora sus dos atributos son strings y para probar basta.
        return new MensajeSimple(aux[0], aux[1], new Usuario(aux[2], aux[3]), new Usuario(aux[4], aux[5]));
    }
}
