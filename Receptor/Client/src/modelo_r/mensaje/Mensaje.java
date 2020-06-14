package modelo_r.mensaje;

import modelo_r.ArmableDesarmable;
import modelo_r.Usuario;
import excepciones.MalTipoDeMensajeException;
import excepciones.MensajeMalFormadoException;

public abstract class Mensaje implements ArmableDesarmable {
    // clase
    protected static final String MENSAJE_SIMPLE = "1", MENSAJE_ALARMA = "2", MENSAJE_RECEPCION = "3";
    // instancia
    protected String asunto;
    protected String descripcion;
    protected Usuario emisor;
    protected Usuario destinatario; // lo dejo aparte porque capaz sea otra clase mas especializada
    
    public Mensaje(String asunto, String descripcion, Usuario destinatario, Usuario emisor) {
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.destinatario = destinatario;
        this.emisor = emisor;
    }
    
    @Override
    public String toString() {
        return asunto + ArmableDesarmable.SEPARADOR +
               descripcion + ArmableDesarmable.SEPARADOR +
               destinatario.toString() + ArmableDesarmable.SEPARADOR +
               emisor.toString();
    }
    
    public String desarmar() {
        return this.toString();
    }
    
    public Usuario getDestinatario() {
        return this.destinatario;
    }
    
    public static Mensaje armar(String tipoYMsg) throws MalTipoDeMensajeException, MensajeMalFormadoException {
        Mensaje mensaje;
        String tipo, msg;
        String[] aux = tipoYMsg.split(ArmableDesarmable.SEPARADOR);
        tipo = aux[0];
        
        msg = tipoYMsg.substring(tipo.length() + SEPARADOR.length()); // sin tomar el tipo de mensaje y el separador
        switch (tipo) {
        case MENSAJE_SIMPLE:
            mensaje = MensajeSimple.armar(msg);
            break;
        case MENSAJE_ALARMA:    
            mensaje = MensajeAlarma.armar(msg);
            break;
        case MENSAJE_RECEPCION: 
            mensaje = MensajeRecepcion.armar(msg);
            break;
        default:
            throw new MalTipoDeMensajeException(tipo);
        }
        return mensaje;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Usuario getEmisor() {
        return emisor;
    }

    public void ejecutar(){};
}
