package refactored;

import io.jooby.annotations.GET;
import io.jooby.annotations.Path;
import io.jooby.annotations.PathParam;
import io.jooby.exception.BadRequestException;

@Path("/div/{dividendo}/{divisor}")
public class Divisao {
    
    @GET
    public String calcDiv(@PathParam String dividendo, @PathParam String divisor){
        try{
            double resultado  = 0;
            try {
                resultado = divisao(dividendo, divisor);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return String.format("%.2f", resultado);
        } catch(NumberFormatException nfe){
            throw new BadRequestException("Parâmetro inválido");
        }
    }

    public double divisao(String dividendoStr, String divisorStr) throws IllegalAccessException{
        double dDividendo = 0.;
        try{
            dDividendo = Double.parseDouble(dividendoStr);
        } catch (NumberFormatException nfe){
            throw new IllegalArgumentException(String.format("Dividendo inválido:\"%s\"", dividendoStr));
        }
        double dDivisor = 0.;
        try {
            dDivisor = Double.parseDouble(divisorStr);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException(String.format("Divisor inválido:\"%s\"", divisorStr));
        }
        return (dDividendo/dDivisor);
    }

}
