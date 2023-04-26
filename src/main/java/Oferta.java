import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
public class Oferta {
    private Producto producto;
    private Descuento descuento;
    private String fechaDesde;
    private String fechaHasta;
}
