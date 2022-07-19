package refactored;

import io.jooby.annotations.GET;
import io.jooby.annotations.Path;
import io.jooby.annotations.PathParam;
import io.jooby.exception.BadRequestException;

@Path("/soma/{parcela1}/{parcela2}")
public class Soma {
    
    @GET
    public String calcSoma(@PathParam String parcela1, @PathParam String parcela2){
        try{
            double resultado  = 0;
            try {
                resultado = soma(parcela1, parcela2);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return String.format("%.2f", resultado);
        } catch(NumberFormatException nfe){
            throw new BadRequestException("Parâmetro inválido");
        }
    }

    public double soma(String parcela1Str, String parcela2Str) throws IllegalAccessException{
        double dParcela1 = 0.;
        try{
            dParcela1 = Double.parseDouble(parcela1Str);
        } catch (NumberFormatException nfe){
            throw new IllegalArgumentException(String.format("Parcela 1 inválido:\"%s\"", parcela1Str));
        }
        double dParcela2 = 0.;
        try {
            dParcela2 = Double.parseDouble(parcela2Str);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException(String.format("Parcela 2 inválido:\"%s\"", parcela2Str));
        }
        return (dParcela1 + dParcela2);
    }
}
