package configuracion;

public class Configuracion {
    private String nroIPDir1 = "";
    private int puertoDir1;
    private String nroIPDir2 = "";
    private int puertoDir2;
    
    public Configuracion(String nroIPDirectorio) {
        this.nroIPDir1 = nroIPDirectorio;
    }
    
    public Configuracion(String nroIPDir1, String puertoDir1, String nroIPDir2, String puertoDir2) throws NumberFormatException {
        this.nroIPDir1 = nroIPDir1;
        this.puertoDir1 = Integer.parseInt(puertoDir1);
        this.nroIPDir2 = nroIPDir2;
        this.puertoDir2 = Integer.parseInt(puertoDir2);
    }
    
    public void prueba() {
        System.out.println(
        "IP Dir1: " + "\n" +
        this.getNroIPDir1() + "\n" +
        "Puerto Dir1: " + "\n" +
        this.getPuertoDir1() + "\n" +
        "IP Dir2: " + "\n" +
        this.getNroIPDir2() + "\n" +
        "Puerto Dir2: " + "\n" +
        this.getPuertoDir2());
    }

    public String getNroIPDir1() {
        return nroIPDir1;
    }

    public int getPuertoDir1() {
        return puertoDir1;
    }

    public String getNroIPDir2() {
        return nroIPDir2;
    }

    public int getPuertoDir2() {
        return puertoDir2;
    }
}
