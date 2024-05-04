package desplieguetomcat2bd;

import java.util.*;

public class ListaUsuarios {
    private Vector<Usuario> vectorUsuarios = new Vector<>();

    public void setUsuario(Usuario usuario) {
        if (usuario != null) {
            vectorUsuarios.add(usuario);
        } else {
            System.out.println("Error: Usuario nulo");
        }
    }

    // Recupera la lista de usuarios
    public Vector<Usuario> getLista() {
        return vectorUsuarios;
    }

    // Recupera el numero total de usuarios en la lista
    public int getNumTotalUsuarios() {
        return vectorUsuarios.size();
    }
}
