package refactored;

import io.jooby.annotations.GET;
import io.jooby.annotations.Path;
import io.jooby.annotations.PathParam;
import io.jooby.exception.BadRequestException;

@Path("/multi/{multiplicando}/{multiplicador}")
public class Multiplicacao {
    
    @GET
    public String calcMulti(@PathParam String multiplicando, @PathParam String multiplicador){
        try{
            double resultado  = 0;
            try {
                resultado = multiplicacao(multiplicando, multiplicador);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return String.format("%.2f", resultado);
        } catch(NumberFormatException nfe){
            throw new BadRequestException("Parâmetro inválido");
        }
    }

    public double multiplicacao(String multiplicandoStr, String multiplicadorStr) throws IllegalAccessException{
        double dMultiplicando = 0.;
        try{
            dMultiplicando = Double.parseDouble(multiplicandoStr);
        } catch (NumberFormatException nfe){
            throw new IllegalArgumentException(String.format("multiplicando inválido:\"%s\"", multiplicandoStr));
        }
        double dMultiplicador = 0.;
        try {
            dMultiplicador = Double.parseDouble(multiplicadorStr);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException(String.format("Multiplicador inválido:\"%s\"", multiplicadorStr));
        }
        return (dMultiplicando * dMultiplicador);
    }
}
