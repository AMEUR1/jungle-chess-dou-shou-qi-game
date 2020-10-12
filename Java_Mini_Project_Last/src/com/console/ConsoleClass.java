package com.console;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.game.classes.Echiquier;
import com.game.classes.Lion;
import com.game.classes.Loup;
import com.game.classes.Piece;
import com.game.tools.MoveException;
import com.game.tools.Point;

public class ConsoleClass {

	private static int direction;
	
	public static void MachineContreMachine(Echiquier echiquier) {
		while (true) {
		echiquier.randomTurn();
		echiquier.showEchiquier();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
	
	/***
	 * 
	 * il faut annuler les espaces dans string et mettre Tig_B
	 */
	public static void HumanContreHuman( Echiquier echiquier) {
		while (true) {
			System.out.println("saisir votre Commande player"+echiquier.getTurn());
			Scanner scanner = new Scanner(System.in);
			String command = scanner.nextLine();

			String[] trait = command.split(",");

			// echiquier.switchPlayer();
			Piece choosenPiece = echiquier.getPieceByLabel(trait[0]);
			if (choosenPiece != null && trait.length == 2) {
				System.out.println("you choose : " + choosenPiece);
				System.out.println("to make this move " + trait[1]);

				if ("a".equals(trait[1])) {
					direction = 1;
				} else if ("b".equals(trait[1])) {
					direction = 2;
				} else if ("r".equals(trait[1])) {
					direction = 3;
				} else if ("l".equals(trait[1])) {
					direction = 4;
				}
				

				
				try {

					choosenPiece.movePiece(direction, 0);
				} catch (MoveException | NullPointerException e) {
					System.out.println("Cant Move");
				}

			} else {
				System.out.println("Cant Find Piece Or command not correct");
			}

			echiquier.showEchiquier();
		}
	}

	public static void HumanContreMachine(Echiquier echiquier) {
		
		while (true) {
			if(echiquier.getTurn()==-1) {
			System.out.println("saisir votre Commande player"+echiquier.getTurn());
			Scanner scanner = new Scanner(System.in);
			String command = scanner.nextLine();

			String[] trait = command.split(",");

			// echiquier.switchPlayer();
			Piece choosenPiece = echiquier.getPieceByLabel(trait[0]);
			if (choosenPiece != null && trait.length == 2) {
				System.out.println("you choose : " + choosenPiece);
				System.out.println("to make this move " + trait[1]);

				if ("a".equals(trait[1])) {
					direction = 1;
				} else if ("b".equals(trait[1])) {
					direction = 2;
				} else if ("r".equals(trait[1])) {
					direction = 3;
				} else if ("l".equals(trait[1])) {
					direction = 4;
				}
				

				
				try {

					choosenPiece.movePiece(direction, 0);
				} catch (MoveException | NullPointerException e) {
					System.out.println("Cant Move");
				}

			} else {
				System.out.println("Cant Find Piece Or command not correct");
			}

			
			}
			else {
				echiquier.randomTurn();
				
			}
			echiquier.showEchiquier();
		}
		
	}
	
	public static void main(String[] args) {

		
		
		
		Echiquier echiquier = Echiquier.getInstance();
		
		
		System.out.println("mettre votre choix");
		System.out.println("1-Human VS Human");
		System.out.println("2-Human VS Machine");
		System.out.println("3-Machine VS Machine");
		System.out.println("4-exit");
		int choose=0;
		Scanner sc=new Scanner(System.in);
		choose=sc.nextInt();
		echiquier.showEchiquier();
		System.out.println("The game staring in 3 sec......\n");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch (choose) {
		case 1:
			HumanContreHuman(echiquier);
			break;
		case 2:
			HumanContreMachine(echiquier);
			break;
		case 3:
			MachineContreMachine(echiquier);
			break;
		case 4:
			System.exit(0);
			break;

		default:
			break;
		}
		
		//MachineContreMachine(echiquier);
		//HumanContreHuman(echiquier);
		//HumanContreMachine(echiquier);
		/*
		 * System.out.println(lion.pieceIcon());
		 * System.out.println(lion.getPossilesMoves());
		 * System.out.println("this is choose by Label : " +
		 * echiquier.getPieceByLabel("Tig B"));
		 */
		
		
//		Piece pos=echiquier.getPieceByLabel("Tig B");
//		pos.randomMove();
	
		//random game
		/*	while (true) {
			echiquier.randomTurn();
			echiquier.showEchiquier();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
*/
		
		
		//pos.randomMove();
		//echiquier.showEchiquier();
		
		// user to user
		/*
		while (true) {
			System.out.println("saisir votre Commande player"+echiquier.getTurn());
			Scanner scanner = new Scanner(System.in);
			String command = scanner.nextLine();

			String[] trait = command.split(",");

			// echiquier.switchPlayer();
			Piece choosenPiece = echiquier.getPieceByLabel(trait[0]);
			if (choosenPiece != null && trait.length == 2) {
				System.out.println("you choose : " + choosenPiece);
				System.out.println("to make this move " + trait[1]);

				if ("a".equals(trait[1])) {
					direction = 1;
				} else if ("b".equals(trait[1])) {
					direction = 2;
				} else if ("r".equals(trait[1])) {
					direction = 3;
				} else if ("l".equals(trait[1])) {
					direction = 4;
				}
				
				try {

					choosenPiece.movePiece(direction, 0);
				} catch (MoveException | NullPointerException e) {
					System.out.println("Cant Move");
				}

			} else {
				System.out.println("Cant Find Piece Or command not correct");
			}

			echiquier.showEchiquier();
		}*/

		/***
		 * try move done
		 */
		/*
		 * try { lion.movePiece(1, 0); } catch (MoveException e) { // TODO
		 * Auto-generated catch block System.out.println("Cant Move"); }
		 * 
		 * 
		 * echiquier.showEchiquier();
		 */

		/*
		 * Scanner s = new Scanner(System.in);
		 * 
		 * echequier.showEchequier();
		 * 
		 * while (true) {
		 * 
		 * // Lire la commande au clavier String commande = s.nextLine();
		 * 
		 * // Véifier que la commande a été correctement saisie Matcher m =
		 * Pattern.compile("^([A,B,C]) (a|b|t|bt|dta|dtb|dba|dbb)$").matcher(commande);
		 * 
		 * if (m.matches()) {
		 * 
		 * String pieceString = m.group(1); String directionString = m.group(2); int
		 * direction = 0;
		 * 
		 * if ("a".equals(directionString)) { direction = Piece.ADVANCE; } else if
		 * ("b".equals(directionString)) { direction = Piece.BACK; } else if
		 * ("t".equals(directionString)) { direction = Piece.TOP; } else if
		 * ("bt".equals(directionString)) { direction = Piece.BOTTOM; } else if
		 * ("dta".equals(directionString)) { direction = Piece.DIAG_TOP_ADVANCE; } else
		 * if ("dtb".equals(directionString)) { direction = Piece.DIAG_TOP_BACK; } else
		 * if ("dba".equals(directionString)) { direction = Piece.DIAG_BOTTOM_BACK; }
		 * else if ("dbb".equals(directionString)) { direction = Piece.DIAG_BOTTOM_BACK;
		 * }
		 * 
		 * try {
		 * 
		 * Piece piece = echequier.getPieceWithColor(echequier.getTour(), pieceString);
		 * if (piece != null) { piece.seDeplacer(direction, 1);
		 * 
		 * } else { System.out.println("La piece n'existe plus");
		 * 
		 * } } catch (ImpossibleDeplacementException e) {
		 * System.out.println("Déplacement impossible"); }
		 * 
		 * } else if ("exit".equals(commande)) { System.exit(0); }
		 * 
		 * else { System.out.println("commande incorrecte"); }
		 * 
		 * echequier.showEchequier(); }
		 * 
		 */

		// Echiquier echiquier=new Echiquier();

		// echiquier.showEchiquier();
		// System.out.println(echiquier.getTurn());
		// echiquier.switchPlayer();
//		System.out.println(echiquier.isLose());

//		Piece piece=new Loup();
//		System.out.println(piece.pieceIcon());
		/*
		 * System.out.println("*****************************"); Piece lionN = new
		 * Lion(7, -1, new Point(0, 6), 2, echiquier); Piece
		 * lin=echiquier.getPieceAt(new Point(0,6));
		 * 
		 * echiquier.removePiece(lionN);
		 * 
		 * echiquier.showEchiquier();
		 * 
		 */
	}
}
