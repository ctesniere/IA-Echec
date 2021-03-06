package fr.esgi.ia;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import fr.esgi.export.Export;

/**
 * @author Cédric TESNIERE
 */
public class IA {

	private boolean myColor;

	private Chessboard globalChessboard;

	/**
	 * Appel un algorithme est lui donne la profondeur (difficulté de l'ia) et
	 * l'execute. Déplace le meilleur pion sur le chessboard
	 * 
	 * @param myColor Ma couleur (Voir la classe Algorithm pour avoir les
	 *            valeurs)
	 * @param depth Profondeur de l'algorithme
	 * @param chessboard
	 * @return Le meilleur mouvement
	 */
	public String play(boolean myColor, int depth, Chessboard chessboard) {

		String output = "";
		final Algorithm anAlgorithm = new AlphaBeta(depth);

		setGlobalChessboard(chessboard);
		setMyColor(myColor);

		// Boucle principale
		while (true) {
			final Move myMove = anAlgorithm.chooseMove(getGlobalChessboard(), isMyColor());

			if (myMove == null) {
				output += "ERROR: Pas de mouvement possible.";
				break; // Sort de la boucle
			} else {
				getGlobalChessboard().doMove(myMove);
				final Export export = new Export(getGlobalChessboard(), myMove);

				try {
					final ObjectMapper mapper = new ObjectMapper();
					output = mapper.writeValueAsString(export);
				} catch (JsonGenerationException e) {
					output = e.getMessage();
				} catch (JsonMappingException e) {
					output = e.getMessage();
				} catch (IOException e) {
					output = e.getMessage();
				}
				System.out.println(output);
				break;
			}
		}
		return output;
	}

	public boolean isMyColor() {
		return myColor;
	}

	public void setMyColor(boolean myColor) {
		this.myColor = myColor;
	}

	public Chessboard getGlobalChessboard() {
		return globalChessboard;
	}

	public void setGlobalChessboard(Chessboard globalChessboard) {
		this.globalChessboard = globalChessboard;
	}
}
