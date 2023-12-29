package utile;

public class RestrictionException extends Exception {
    public RestrictionException(String message) {
        super(message);
    }
    
/* Cette exception sera levée si l'abonné n'est pas autorisé à 
 * réserver ou emprunter (par exemple, pour un dvd adulte, il 
 * n'a pas l'âge requis). La précondition reste la même 
 * (ni réservé ni emprunté)
 * 
 * La tentative de réservation ou d’emprunt, si elle échoue, 
 * doit donner lieu à un message de refus précis 
 * « ce DVD est déjà emprunté », 
 * « vous n’avez pas l’âge pour emprunter ce DVD », 
 * « ce livre est réservé jusqu’à 12h25 », etc).
*/
    
}
