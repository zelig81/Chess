/**
 * 
 */
package project.ilyagorban;

import project.ilyagorban.controllers.ChessController;
import project.ilyagorban.model.ChessModel;
import project.ilyagorban.view.ChessView;

/**
 * @author ilya gorban
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ChessModel cm = new ChessModel();
		ChessView cv = new ChessView();
		ChessController cc = new ChessController(cm, cv);
		cc.start();
	}

}
