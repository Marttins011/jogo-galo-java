package AVALIA2;
import java.util.Scanner;

public class AVALIA2 {	
	public static int askPlay(int player) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Jogador " + (player + 1) + ":\nEm que posição queres colocar a " + ((player == 0) ? "bola" : "cruz") + "?");
		return scan.nextInt();
	}
	
	public static void drawTable(int[] table) {
		for (int i = 0; i < table.length; i++)
			System.out.print("|" + ((table[i] == 0) ? "O" : ((table[i] == 1) ? "X" : i)) + (((i == 2 || i == 5 || i == 8) ? "|\n" : "")));
	}
	
	public static boolean checkPossibilities(int[] table, int[][] possibilities) {
		int filled = 0;
		for (int i = 0; i < table.length; i++)
			if (table[i] != 2) filled += 1;
		if (filled >= table.length) {
			drawTable(table);
			System.out.println("Jogo empatado!!");
			System.exit(filled);
		}
		for (int i = 0; i < possibilities.length; i++) {
			int p1 = 0, p2 = 0;
			for (int p = 0; p < possibilities[i].length; p++) {
				p1 = ((table[possibilities[i][p]] == 0) ? (p1 + 1) : p1);
				p2 = ((table[possibilities[i][p]] == 1) ? (p2 + 1) : p2);
			}
			if (p1 == possibilities[i].length || p2 == possibilities[i].length) return true;
		}
		return false;
	}
	
	public static void makeGame(int[] table, int[][] possibilities, int player) {
		drawTable(table);
		int play = askPlay(player);
		if (table[play] == 2)
			table[play] = player;
		else
			makeGame(table, possibilities, player);
		boolean gameFinished = checkPossibilities(table, possibilities);
		if (gameFinished) {
			drawTable(table);
			System.out.println("O Jogador " + (player + 1) + " ganhou o jogo!");
		} else 
			makeGame(table, possibilities, ((player == 0) ? 1 : 0));
	}
	
	public static void main(String[] args) {
		int[] table = { 2, 2, 2, 2, 2, 2, 2, 2, 2 };
		int[][] possibilities = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, { 0, 4, 8 }, { 2, 4, 6 } };
		makeGame(table, possibilities, 0);
	}
}