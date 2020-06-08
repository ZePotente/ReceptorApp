package prueba;

import modelo_r.desencriptacion.DesencriptacionCesarStrategy;
import modelo_r.desencriptacion.IDesencriptacionStrategy;

public class Class1 {
    public Class1() {
        super();
    }
    
    public static void main(String[] args) {
        IDesencriptacionStrategy desencriptador = new DesencriptacionCesarStrategy();
        String cifrado = "BCDEFGHIJKLMNOPQRSTUVWXYZA. bcdefghijklmnopqrstuvwxyza.\nasd";
        System.out.println("BCDEFGHIJKLMNOPQRSTUVWXYZA. bcdefghijklmnopqrstuvwxyza.\nasd");
        System.out.println(desencriptador.desencriptar(cifrado));
        
        System.out.println(desencriptador.desencriptar("A"));
        System.out.println(desencriptador.desencriptar("a"));
    }
}
