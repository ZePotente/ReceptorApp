package modelo_r;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Solamente escucha por conexiones y recibe Strings, de estos se encarga el Sistema.
 */
public class InternetManager {
    public InternetManager() {
        super();
    }
    
    public void escuchar(int nroPuerto) {
        new Thread() {
            public void run() {
                try {
                    ServerSocket s = new ServerSocket(nroPuerto);
                    conexionExitosa(); // metodo de IMR
                    while (true) { // una vez que escucha ese puerto se queda escuchandolo aunque ingresen otro puerto
                        Socket soc = s.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
                        
                        // se podran enviar en XML o alguna otra forma?
                        String msg, aux;
                        msg = "";
                        aux = in.readLine(); //Lee todo el principio y el primer parrafo del mensaje
                        // System.out.println("Primera linea: \n" + aux);
                        while (aux != null) {
                            msg += aux + "\n"; // cada "\n" separa parrafos del mensaje
                            // cada readLine() lee un renglon del mensaje,
                            // y el ultimo lee el ultimo parrafo y todo el resto
                            aux = in.readLine();
                            // System.out.println(msg);
                        }
                        mensajeRecibido(msg); // metodo de IMR
                        soc.close(); // deberian?
                    }
                } catch (Exception e) {
                    errorConexion(e.getMessage()); // metodo de IMR
                }
                finally {
                    finConexion(); // metodo de IMR
                }
            }
        }.start();
    }
    
    public void mensajeRecibido(String msg) {
        Sistema.getInstancia().mensajeRecibido(msg);
    }
    
    public void conectarConDirectorio(String nombre, String nroIP, String nroIPDirectorio,
                                      int nroPuertoDirectorio) throws Exception {
        
        Socket socket = new Socket(nroIPDirectorio.trim(), nroPuertoDirectorio);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String notificacion = "AvisoConexion\n"+nombre+"\n"+nroIP;
        out.println(notificacion);
        System.out.println("Conexion exitosa.");
        controlarConexion(in);
        socket.close();
    }
    
    private void controlarConexion(BufferedReader in) throws IOException {
        while(true) {
            System.out.println("Esperando ping.");
            String aux = in.readLine();
            System.out.println("Se recibio un ping por parte del directorio.");
        }
    }

    public void errorConexion(String error) {
        Sistema.getInstancia().errorConexion(error);
    }

    public void finConexion() {
        Sistema.getInstancia().finConexion();
    }
    
    public void conexionExitosa() {
        Sistema.getInstancia().conexionExitosa();
    }
}
