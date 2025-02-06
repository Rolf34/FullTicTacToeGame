import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner arbuz = new Scanner(System.in);
        int razmerDoski = 3;
        boolean codeisrunning = true;

        while (codeisrunning) {
            System.out.println("""
                    ===Welcome to Main Menu===
                     ==Vas vitae Pes Patron==
                    1. Start game
                    2. Settings
                    3.Exit""");


            if (!arbuz.hasNextLine()) {
                System.out.println("Nepravilniy vvid.");
                continue;
            }

            char inputswitch = arbuz.nextLine().charAt(0);
            switch (inputswitch) {
                case '1':
                    boolean inGameMenu = true;
                    while (inGameMenu) {
                        System.out.println(": Board size: " + razmerDoski + "x" + razmerDoski);
                        System.out.println("Are you ready?(1) Yes! (2)Go back to main menu");
                        
                        char InputMainMenu1 = arbuz.nextLine().charAt(0);
                        if (InputMainMenu1 == '2') {
                            inGameMenu = false;
                        } else if (InputMainMenu1 == '1') {
                            boolean isGameOver = false;
                            boolean validMove;
                            int row = -1, col = -1;

                            int rows = razmerDoski * 2 + 1;
                            int cols = razmerDoski * 4 - 1;
                            char[][] displayBoard = new char[rows][cols];

                            for (int i = 0; i < rows; i++) {
                                for (int j = 0; j < cols; j++) {
                                    displayBoard[i][j] = ' ';
                                }
                            }

                            for (int i = 0; i < razmerDoski; i++) {
                                displayBoard[0][i * 4 + 2] = (char) ('1' + i);
                            }

                            for (int i = 0; i < razmerDoski; i++) {
                                displayBoard[i * 2 + 2][0] = (char) ('1' + i);
                            }

                            for (int i = 1; i < rows; i += 2) {
                                for (int j = 1; j < cols; j++) {
                                    displayBoard[i][j] = '-';
                                }
                            }

                            for (int i = 0; i < rows; i++) {
                                for (int j = 4; j < cols; j += 4) {
                                    displayBoard[i][j - 1] = '|';
                                }
                            }

                            char igrok = 'X';
                            while (!isGameOver) {
                                System.out.println("\nZaraz shturmuye: " + igrok);
                                for (char[] rowArray : displayBoard) {
                                    System.out.println(rowArray);
                                }

                                validMove = false;
                                while (!validMove) {
                                    System.out.println("Vvedit ryad (1-" + razmerDoski + ", or 0 to exit):");
                                    String input = arbuz.nextLine();
                                    if (input.length() == 0) continue;
                                    int ryad = input.charAt(0) - '0';
                                    
                                    if (ryad == 0) {
                                        System.out.println("Povernenya v golovne menu...");
                                        isGameOver = true;
                                        break;
                                    }

                                    System.out.println("Vvedit colonku (1-" + razmerDoski + "):");
                                    input = arbuz.nextLine();
                                    if (input.length() == 0) continue;
                                    int colonka = input.charAt(0) - '0';

                                    if (ryad >= 1 && ryad <= razmerDoski && colonka >= 1 && colonka <= razmerDoski) {
                                        row = (ryad - 1) * 2 + 2;
                                        col = (colonka - 1) * 4 + 2;

                                        if (displayBoard[row][col] != ' ') {
                                            System.out.println("Kabinka zaynyata! Sprobuyte she raz.");
                                        } else {
                                            validMove = true;
                                        }
                                    } else {
                                        System.out.println("Ne pravilniy vvid. Sprobuyte she raz.");
                                    }
                                }

                                if (isGameOver) break;

                                displayBoard[row][col] = igrok;
                                boolean peremogaBude = false;

                                for (int i = 2; i < rows; i += 2) {
                                    int count = 0;
                                    for (int j = 2; j < cols; j += 4) {
                                        if (displayBoard[i][j] == igrok) {
                                            count++;
                                            if (count == 3) peremogaBude = true;
                                        } else {
                                            count = 0;
                                        }
                                    }
                                }

                                for (int j = 2; j < cols; j += 4) {
                                    int count = 0;
                                    for (int i = 2; i < rows; i += 2) {
                                        if (displayBoard[i][j] == igrok) {
                                            count++;
                                            if (count == 3) peremogaBude = true;
                                        } else {
                                            count = 0;
                                        }
                                    }
                                }

                                for (int startI = 2; startI <= rows - 4; startI += 2) {
                                    for (int startJ = 2; startJ <= cols - 8; startJ += 4) {
                                        int count = 0;
                                        for (int k = 0; k < 3 && startI + k*2 < rows && startJ + k*4 < cols; k++) {
                                            if (displayBoard[startI + k*2][startJ + k*4] == igrok) {
                                                count++;
                                            }
                                        }
                                        if (count == 3) peremogaBude = true;
                                    }
                                }

                                for (int startI = 2; startI <= rows - 4; startI += 2) {
                                    for (int startJ = cols - 2; startJ >= 10; startJ -= 4) {
                                        int count = 0;
                                        for (int k = 0; k < 3 && startI + k*2 < rows && startJ - k*4 >= 2; k++) {
                                            if (displayBoard[startI + k*2][startJ - k*4] == igrok) {
                                                count++;
                                            }
                                        }
                                        if (count == 3) peremogaBude = true;
                                    }
                                }

                                if (peremogaBude) {
                                    System.out.println("POTUZHNA PEREMOGA " + igrok + "!!!");
                                    isGameOver = true;
                                }

                                boolean full = true;
                                for (int i = 2; i < rows; i += 2) {
                                    for (int j = 2; j < cols; j += 4) {
                                        if (displayBoard[i][j] == ' ') {
                                            full = false;
                                        }
                                    }
                                }
                                if (full) {
                                    System.out.println("Nichya nachalnika!");
                                    isGameOver = true;
                                }

                                igrok = (igrok == 'X') ? 'O' : 'X';
                            }

                            System.out.println("\nItogove pole:");
                            for (char[] rowArray : displayBoard) {
                                System.out.println(rowArray);
                            }
                            inGameMenu = false;
                        } else {
                            System.out.println("pomilka v zapiti, spobuyte she raz.");
                        }
                    }
                    break;

                case '2':
                    boolean inSettingsMenu = true;
                    while (inSettingsMenu) {
                        System.out.println("""
                            Vibir rozmiru doshki:
                            1. 3x3
                            2. 5x5
                            3. 7x7
                            4. 9x9
                            0. Povernutisya nazad""");
                        
                        char InputMainMenu2 = arbuz.nextLine().charAt(0);
                        switch (InputMainMenu2) {
                            case '1':
                                razmerDoski = 3;
                                System.out.println("Vstanovleno rozmir 3x3");
                                inSettingsMenu = false;
                                break;
                            case '2':
                                razmerDoski = 5;
                                System.out.println("Vstanovleno rozmir 5x5");
                                inSettingsMenu = false;
                                break;
                            case '3':
                                razmerDoski = 7;
                                System.out.println("Vstanovleno rozmir 7x7");
                                inSettingsMenu = false;
                                break;
                            case '4':
                                razmerDoski = 9;
                                System.out.println("Vstanovleno rozmir 9x9");
                                inSettingsMenu = false;
                                break;
                            case '0':
                                inSettingsMenu = false;
                                break;
                            default:
                                System.out.println("pomilka v zapiti, spobuyte she raz.");
                        }
                    }
                    break;

                case '3':
                    System.out.println("бажаєте вийти? ( введіть 1(так) або 2(ні)");
                    char InputMainMenu3 = arbuz.nextLine().charAt(0);
                    if (InputMainMenu3 == '1') {
                        codeisrunning = false;
                    } else if (InputMainMenu3 == '2') {
                        System.out.println("Deltuyemo v golovne menu.");
                    } else {
                        System.out.println("Pomidka v zapiti, spobuyte she raz.");
                    }
                    break;

                default:
                    System.out.println("Pomilka v zapiti, spobuyte she raz.");
                    break;
            }
        }
        System.out.println("Vihid z programi...");
        arbuz.close();
    }
}