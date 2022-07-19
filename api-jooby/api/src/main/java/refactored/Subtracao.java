package refactored;

import io.jooby.annotations.GET;
import io.jooby.annotations.Path;
import io.jooby.annotations.PathParam;
import io.jooby.exception.BadRequestException;

@Path("/sub/{numero1}/{numero2}")
public class Subtracao {
    
    @GET
    public String calcSoma(@PathParam String numero1, @PathParam String numero2){
        try{
            double resultado  = 0;
            try {
                resultado = subtracao(numero1, numero2);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return String.format("%.2f", resultado);
        } catch(NumberFormatException nfe){
            throw new BadRequestException("Parâmetro inválido");
        }
    }

    public double subtracao(String numero1Str, String numero2Str) throws IllegalAccessException{
        double dNumero1 = 0.;
        try{
            dNumero1 = Double.parseDouble(numero1Str);
        } catch (NumberFormatException nfe){
            throw new IllegalArgumentException(String.format("Parcela 1 inválido:\"%s\"", numero1Str));
        }
        double dNumero2 = 0.;
        try {
            dNumero2 = Double.parseDouble(numero2Str);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException(String.format("Parcela 2 inválido:\"%s\"", numero2Str));
        }
        return (dNumero1 - dNumero2);
    }
}
