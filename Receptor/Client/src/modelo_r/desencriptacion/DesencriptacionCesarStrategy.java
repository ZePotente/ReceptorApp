package modelo_r.desencriptacion;

public class DesencriptacionCesarStrategy implements IDesencriptacionStrategy{
    private final int DESPLAZAMIENTO = 1;
    public DesencriptacionCesarStrategy() {
        super();
    }

    @Override
    public String desencriptar(String cifrado) {
        String mensaje = "";
        char car;
        for (int i = 0; i < cifrado.length(); i++) {
            car = cifrado.charAt(i);
            if (isMinuscula(car))
                car = this.desplazar('a', car);
            else
                if (isMayuscula(car)) 
                    car = this.desplazar('A', car);
            mensaje += car;
        }
        return mensaje;
    }
    
    private boolean isMinuscula(char car) {
        return car >= 'a' && car <= 'z';
    }
    
    private boolean isMayuscula(char car) {
        return car >= 'A' && car <= 'Z';
    }
    
    private char desplazar(char base, char car) {
        char resultado = (char) (car - this.DESPLAZAMIENTO);
        if (resultado < base)
            resultado += 26;
        return resultado;
    }
    
}
