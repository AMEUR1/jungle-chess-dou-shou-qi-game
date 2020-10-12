package com.game.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.game.tools.MoveException;
import com.game.tools.Point;

public abstract class Piece {

	// constantes qui définit les déplacements
	public static final int ADVANCE = 1;
	public static final int BACK = 2;
	public static final int RIGHT = 3;
	public static final int LEFT = 4;

	// ajout de classe de represnter une classe
	// Directions possibles
	public static final int[] DIRECTIONS = { ADVANCE, BACK, RIGHT, LEFT };

	private Echiquier echiquier;

	/**
	 * default power is the power of piece meme si ile st dans la piege power: est le
	 * power actuuelle peuut etre default or zero
	 */
	private int defaultPower;
	private int power;
	private int color;
	private Point position;
	private int specialMove;

	public Piece() {

	}

	public Piece(int power, int color, Point position, int specialMove, Echiquier ech) {
		this.power = power;
		this.color = color;
		this.position = position;
		this.specialMove = specialMove;
		this.defaultPower = power;
		this.echiquier = ech;
	}

	public Echiquier getEchiquier() {
		return echiquier;
	}

	public void setEchiquier(Echiquier echiquier) {
		this.echiquier = echiquier;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public int getDefaultPower() {
		return defaultPower;
	}

	public void setDefaultPower(int defaultPower) {
		this.defaultPower = defaultPower;
	}

	/*
	 * on teste si il respecte les moves
	 */
	public int getSpecialMove() {
		return this.specialMove;
	}

	/**
	 * 
	 * pour lion peut soter et soouris peut nager
	 */
	public void setSpecialMove(int specialMove) {
		this.specialMove = specialMove;
	}

	public String toString() {
		return "Piece : " + this.pieceIcon() + " [ defaultPower=" + defaultPower + ", power=" + power + ", color="
				+ color + ", position=" + position + ", specialMove=" + specialMove + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + color;
		result = prime * result + defaultPower;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + specialMove;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piece other = (Piece) obj;
		if (color != other.color)
			return false;
		if (defaultPower != other.defaultPower)
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (specialMove != other.specialMove)
			return false;
		return true;
	}

	public Point whereAmI() {
		return this.position;
	}

	public boolean amAlive() {
		/**
		 * il faut le tsete du list du echiquier dans classe echiquier use this methide
		 * to choose piece
		 */
		if (this.echiquier.getTurn() == -1) {
			return this.echiquier.getPlayer1().contains(this);
		} else {
			return this.echiquier.getPlayer2().contains(this);
		}

	}

	public List<Point> getPossilesMoves() {
		/**
		 * tester pour tous les direction using Methode getPosiionifCanMove
		 */

		List<Point> moves = new ArrayList<Point>();

		for (int it : DIRECTIONS) {

			try {
				moves.add(getPositionIfCanMove(it, 1));
			} catch (MoveException e) {

			}

		}

		/**
		 * pour ne pas etre dans le trone d'un animal
		 */
		if (this.echiquier.getTurn() == -1) {
			moves.remove(new Point(0, 3));
		} else {
			moves.remove(new Point(8, 3));
		}

		return moves;

	}

	public void amInPiege() {
		/**
		 * cette fonction doit etre utilser apres chaque move
		 * 
		 * @param point using postion and list of piege pour l'adversaire et return un
		 *              Power 0 zero on teste sur les positoion du piege pour switch
		 *              power entre zero and default power
		 * 
		 *              ici on va change power si il est dans piege
		 */

		if (echiquier.getTurn() == -1) {
			/**
			 * on teste si la postion apres deplacemetn est dans piege pour mettre le zero power
			 */
			if (echiquier.getListPiegePlayer2().contains(this.position)) {
				this.power = 0;
			} else {
				this.power = this.defaultPower;
			}
		} else {
			if (echiquier.getListPiegePlayer1().contains(this.position)) {
				this.power = 0;
			} else {
				this.power = this.defaultPower;
			}

		}

	}

	public Point getPositionIfCanMove(int direction, int nbrCase) throws MoveException {
		/**
		 * @param la direction et nbre de case et special move; apres on teste si il ya
		 *           un advesaire ou alie dans la direction et il est dans l'echiquier
		 *           apres on la direction de special move
		 */
	
		Point newPosition = null;

		if (direction == ADVANCE) {
			if (this.color == Color.NOIR) {
				newPosition = new Point(this.position.getX() - 1, this.position.getY());
			} else {
				newPosition = new Point(this.position.getX() + 1, this.position.getY());
			}
		} else if (direction == BACK) {
			if (this.color == Color.NOIR) {
				newPosition = new Point(this.position.getX() + 1, this.position.getY());
			} else {
				newPosition = new Point(this.position.getX() - 1, this.position.getY());
			}
		}

		else if (direction == LEFT) {
			if (this.color == Color.NOIR) {
				newPosition = new Point(this.position.getX(), this.position.getY() + 1);
			} else {
				newPosition = new Point(this.position.getX(), this.position.getY() - 1);
			}
		}

		else if (direction == RIGHT) {
			if (this.color == Color.NOIR) {
				newPosition = new Point(this.position.getX(), this.position.getY() - 1);
			} else {
				newPosition = new Point(this.position.getX(), this.position.getY() + 1);
			}
		}

		/**
		 * apres on va teste si cette position: * dans l'echiquier * si est le water *
		 * si ila ya une autre piece si oui teste color et power * teste si est un piege
		 * si il une autre piece
		 */

		if (echiquier.isPointWater(newPosition)) {
			/**
			 * on va tester spicial move si il est une soris on le passe la move( si il est
			 * lion on passe nouveau place on va donne une nouvelle poostion apres sauter
			 * sinon decla,cher une erreur
			 * 
			 * Rat:specialMove=1=> on laisse le passe Lion ou Tigre= special move 2 =>il
			 * doit faire sauter=>incremente newPosition pour represnter sauter
			 */
			// si un Rat
			if (this.getSpecialMove() == 1) {
				// laisser passé
				System.out.println("je suis Une " + this.pieceIcon() + " je veux traverser l'eau at " + newPosition);
			} else if (this.getSpecialMove() == 2) {
				
				/**
				 * astuce pour sauter
				 */
				int nbrX = newPosition.getX() - this.position.getX();
				int nbrY = newPosition.getY() - this.position.getY();
				System.out.println("From " + this.position + "To " + newPosition);
				System.out.println("Nbr X " + nbrX + " nbr Y " + nbrY);

				newPosition.setX(newPosition.getX() + nbrX * 3);
				newPosition.setY(newPosition.getY() + nbrY * 2);
				System.out.println("je suis Une " + this.pieceIcon() + "  je veux traverser l'eau at " + newPosition);
			}

			else {
				System.out.println("vous n'avez pas le droit de traverser la revierre");
				throw new MoveException();
			}

		}

		if (!echiquier.isPointInEchiquier(newPosition)) {
			/**
			 * sinon iv declancher une exeption
			 */
			System.out.println("position hors echiquier");
			throw new MoveException();
		}

		if (!echiquier.isThereMyPiece(newPosition)) {
			/**
			 * teste si la place est vide
			 */
			throw new MoveException();
		}



		/*
		 * param une postion
		 */
		Piece pieceAt = echiquier.getPieceAt(newPosition);

		/**
		 * la derniere conditon, pour un elephant ne peut pas manger une rat
		 */
		if ((pieceAt != null && ((pieceAt.getColor() == this.color) || (pieceAt.getColor() != this.color
				&& ( ((pieceAt.getPower() > this.getDefaultPower()))
				|| (this.getDefaultPower() == 8 && pieceAt.getPower() == 1) )  )  )      ) ) {
			/**
			 * declancher une erreur les piege sont traiter apres le deplacement
			 */
			if((pieceAt.getPower() == 8 && this.getDefaultPower() == 1)) {
			System.out.println("piece at " + pieceAt.getPower() + "  my piece " + this.getDefaultPower());
			}
			else {
				System.out.println("vous n'avez pas assez de pouvoir ,or votre piece empeche ");
			throw new MoveException();
			}
		}

		return newPosition;
	}

	public Point getBestMove() {
		/**
		 * parmi les possibles moves on va bassser sur un Dataset pour determiner Best
		 * Move
		 */
		return null;
	}

	public void movePiece(int direction, int nbrCase) throws MoveException {
		/**
		 * apres le choix de piece on va le deplacer & synchro with echiquier pour la
		 * mise ajour
		 */


		Point newPostion = getPositionIfCanMove(direction, nbrCase);

		Piece pi = echiquier.getPieceAt(newPostion);

		if (pi != null) {
			echiquier.removePiece(pi);
		}

		this.position = newPostion;

		/***
		 * @param point on teste si le nouveau deplacement si oui set power to zero
		 *              sinon default power;
		 */
		System.out.println("Player" + echiquier.getTurn() + " Piece " + this.pieceIcon() + " moved From"
				+ this.getPosition() + " to " + newPostion);
		
		this.amInPiege();

		echiquier.switchPlayer();
	}

	/**
	 * pour respecter le mouve & special move pour chaque animaux && respecte la
	 * place du trone
	 */
	public abstract void isPossibleMove();

	public void randomMove() {

		List<Point> possibleMoves = this.getPossilesMoves();
		if (this.getPossilesMoves().size() != 0) {
			Random rand = new Random();
			int randomPiece = rand.nextInt(possibleMoves.size());

			/*
			 * point comme parametre
			 */

			Point newPosition = possibleMoves.get(randomPiece);
			Piece pi = echiquier.getPieceAt(newPosition);

			if (pi != null) {
				echiquier.removePiece(pi);
			}

			System.out.println("Player" + echiquier.getTurn() + " Piece " + this.pieceIcon() + " moved From"
					+ this.getPosition() + " to " + newPosition);
			this.position = newPosition;

			this.amInPiege();

			/**
			 * teste si trone is good et ya des pieces
			 */
			// echiquier.isTroneGood();
			echiquier.switchPlayer();
		}
	}

	public String pieceIcon() {
		String namClass = this.getClass().getSimpleName().substring(0, 3);
		if (this.color == Color.BLANC) {
			return namClass + " B";
		} else {
			return namClass + " N";
		}
	}

}
