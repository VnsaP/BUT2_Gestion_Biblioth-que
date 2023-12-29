package utile;

public class RestrictionException extends Exception {
    public RestrictionException(String message) {
        super(message);
    }
    
/* Cette exception sera lev�e si l'abonn� n'est pas autoris� � 
 * r�server ou emprunter (par exemple, pour un dvd adulte, il 
 * n'a pas l'�ge requis). La pr�condition reste la m�me 
 * (ni r�serv� ni emprunt�)
 * 
 * La tentative de r�servation ou d�emprunt, si elle �choue, 
 * doit donner lieu � un message de refus pr�cis 
 * � ce DVD est d�j� emprunt� �, 
 * � vous n�avez pas l��ge pour emprunter ce DVD �, 
 * � ce livre est r�serv� jusqu�� 12h25 �, etc).
*/
    
}
