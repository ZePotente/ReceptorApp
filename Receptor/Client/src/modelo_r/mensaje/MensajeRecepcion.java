package modelo_r.mensaje;

import excepciones.MensajeMalFormadoException;

import modelo_r.ArmableDesarmable;
import modelo_r.Usuario;

public class MensajeRecepcion extends Mensaje {
    public MensajeRecepcion(String asunto, String descripcion, Usuario destinatario, Usuario emisor) {
        super(asunto,descripcion,destinatario,emisor);
    }
    
    @Override
    public String desarmar() {
        return Mensaje.MENSAJE_RECEPCION + ArmableDesarmable.SEPARADOR +
               super.toString();
    }
    
    public static MensajeRecepcion armar(String msg) throws MensajeMalFormadoException {  
        String[] aux = msg.split(SEPARADOR);
        if (aux.length != LENGTH)
            throw new MensajeMalFormadoException(aux.length, 6);
        return new MensajeRecepcion(aux[0], aux[1], new Usuario(aux[2], aux[3]), new Usuario(aux[4], aux[5]));
    }
}
