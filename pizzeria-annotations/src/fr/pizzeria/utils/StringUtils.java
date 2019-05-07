package fr.pizzeria.utils;

import java.lang.reflect.Field;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Classe Utilitaire pour les cha�nes de caract�res
 * @author Formation
 *
 */
public class StringUtils {

	/**
	 * Renvoie une chaine de caract�re r�sumant les attributs d'une pizza pourvus d'une annotation ToString
	 * @param categoriePizza enum�ration correspondant � la cat�gorie de la pizza
	 * @param p la pizza
	 * @return la chaine de caract�re souhait�e
	 */
	public static String getStringWithToString(CategoriePizza categoriePizza,Pizza p) {
		String str = "";
		Object value = null;
		// Pour tous les attributs de la Classe Pizza, on recherche ceux qui ont une une annotation @ToString
		for(Field field : Pizza.class.getDeclaredFields()) {
			field.setAccessible(true);
			if(field.isAnnotationPresent(ToString.class)) {
				// On r�cup�re l'annotation et ses diff�rentes caract�ristiques
				ToString ts = field.getAnnotation(ToString.class);
				// On regarde si l'annotation @ToString poss�de l'attribut uppercase=true
				if(ts.uppercase()) {
					try {
						value = field.get(p);
						str +=value.toString().toUpperCase()+" -> ";
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
				// On regarde si l'annotation @ToString poss�de l'attribut price=true
				else if(ts.price()) {
					try {
						value = field.get(p);
						str +="("+value.toString().toUpperCase()+"�) -- Cat�gorie "+categoriePizza.getCategorie();
					}
					catch(Exception e) {
						e.printStackTrace();	
					}
				}
				// Les autres cas
				else {
					try {
						value = field.get(p);
						str +=value.toString()+" ";
					}
					catch(Exception e) {
						e.printStackTrace();	
					}
				}
				
			}
		}
		return str;
	}
}
