import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Producto {
    private String nombre;
    private Peso peso;
    private int precio;
}
