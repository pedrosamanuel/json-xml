import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String ruta = args[0];
        String salida = args[1];
        List<Oferta> ofertas = new ArrayList<>();

        leerOfertas(ruta,ofertas);
        if(salida.equalsIgnoreCase("JSON")){
            String JSON = ofertasToJSON(ofertas);
            System.out.println(JSON);
        }else if(salida.equalsIgnoreCase("XML")){
            String JSON = ofertasToJSON(ofertas);
            String XML = jsonToXML(JSON,ofertas);
            System.out.println(XML);

        }



    }
    public static void leerOfertas(String ruta, List<Oferta> ofertas) throws IOException {
        String[] atributos;

        for (String linea : Files.readAllLines(Paths.get(ruta))){
            atributos = linea.split(",");

            Producto producto = new Producto();
            Oferta oferta = new Oferta();
            Descuento descuento = new Descuento();
            Peso peso = new Peso();

            producto.setNombre(atributos[0]);
            peso.setPeso(Integer.parseInt(atributos[1]));
            peso.setUnidad(atributos[2]);
            producto.setPeso(peso);
            producto.setPrecio(Integer.parseInt(atributos[3]));

            descuento.setTipo(atributos[4].charAt(0));
            descuento.setCantidad(Float.parseFloat(atributos[5]));
            descuento.setTope(Integer.parseInt(atributos[6]));

            oferta.setProducto(producto);
            oferta.setDescuento(descuento);
            oferta.setFechaDesde(atributos[7]);
            oferta.setFechaHasta(atributos[8]);

            ofertas.add(oferta);
        }


    }
    public static String ofertasToJSON(List<Oferta> ofertas) throws JsonProcessingException {
        String jsonText = "";
        for (Oferta oferta : ofertas){
            ObjectMapper objectMapper = new ObjectMapper();
            jsonText = jsonText + objectMapper.writeValueAsString(oferta);
        }
        return jsonText;

    }
    public static String jsonToXML(String JSON, List<Oferta> ofertas) throws JsonProcessingException {
        String xml = "";
        for (Oferta oferta : ofertas){
        JsonMapper jsonMapper = new JsonMapper();
        oferta = jsonMapper.readValue(JSON, Oferta.class);
        XmlMapper xmlMapper = new XmlMapper();
        xml = xml + xmlMapper.writeValueAsString(oferta);
        }

        return xml;
    }
}
