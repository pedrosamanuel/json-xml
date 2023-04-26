import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Descuento {
    private char tipo;
    private float cantidad;
    private int tope;
}
