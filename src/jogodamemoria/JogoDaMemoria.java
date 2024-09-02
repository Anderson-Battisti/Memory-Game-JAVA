package jogodamemoria;

import javax.swing.JOptionPane;
//@author ander.battisti

public class JogoDaMemoria 
{
    public static void main(String[] args) 
    {
        char playAgain = 's';
        char letters[] = {'A', 'C', 'K', 'R', 'R', 'D', 'E', 'D', 'Y', 'E', 'T', 'S', 'V', 'W', 'G', 'J', 'T', 'W', 'K', 'C', 'V', 'B', 'H', 'Y',
                          'B', 'J', 'G', 'S', 'A', 'H'};

        while (playAgain == 's') 
        {        
            boolean fullGameRevealed = false;
            char matrixOfLetters[][] = new char[5][6], hiddenLetters[][] = new char[5][6];
            int count = 0, count2 = 0, valuesAlreadyDrawn[] = new int[30];
            char lettersAlreadyRevealed[] = new char[40];
            boolean exit = false, numberAlreadyDrawn = true;

            for (int i = 0; i < matrixOfLetters.length; i++) 
            {
                for (int j = 0; j < matrixOfLetters[0].length; j++) // Fill up the matrix with the letters
                {                                            
                    numberAlreadyDrawn = true;
                    while (numberAlreadyDrawn == true) 
                    {                       
                        int randomIndex = (int) (Math.random() * (30 - 0) + 0);  // Generates a random int between 0 and 29
                        numberAlreadyDrawn = false;
                        
                        for (int k = 0; k < count; k++) 
                        {
                            if (valuesAlreadyDrawn[k] == randomIndex)
                            {
                                numberAlreadyDrawn = true;
                                break;
                            }
                        }

                        if (numberAlreadyDrawn == false) 
                        {
                            matrixOfLetters[i][j] = letters[randomIndex];
                            valuesAlreadyDrawn[count] = randomIndex;
                            count++;
                            System.out.println(randomIndex);
                        }
                    }
                }
            }

            for (int i = 0; i < matrixOfLetters.length; i++) 
            {
                for (int j = 0; j < matrixOfLetters[i].length; j++) // Shows the drawn matrix on the console to check it if it's necessary
                {               
                    System.out.print(matrixOfLetters[i][j] + " ");
                }
                System.out.println(); 
            }

            for (int i = 0; i < hiddenLetters.length; i++) 
            {
                for (int j = 0; j < hiddenLetters[0].length; j++) 
                {          // Fill up the hiddenLetters with '*'
                    hiddenLetters[i][j] = '*';
                }
            }

            while (fullGameRevealed == false && exit == false) // The game actually happens in this loop.
            {     
                int coordinate1FirstLetter = 10, coordinate2FirstLetter = 10, coordinate1SecondLetter = 10, coordinate2SecondLetter = 10;
                boolean flag1 = false, flag2 = false;
                String hiddenLettersString = "", firstLetterRevealed = "";

                for (int i = 0; i < hiddenLetters.length; i++) 
                {
                    for (int j = 0; j < hiddenLetters[0].length; j++) 
                    {
                        hiddenLettersString += (String.valueOf(hiddenLetters[i][j]) + "    ");  // Convert the hiddenLetters (matrix of chars) into a String (to show on JOptionPane)
                    }
                    hiddenLettersString += "\n";
                }

                while ((coordinate1FirstLetter < 0 || coordinate1FirstLetter > 4) && exit == false) 
                {
                    if (coordinate1FirstLetter == 9) 
                    {
                        exit = true;
                    } 
                    else 
                    {
                        coordinate1FirstLetter = Entrada.leiaInt(hiddenLettersString + "\nPasse a primeira coordenada da primeira letra que deseja virar\nEntre 9 para sair do jogo.");
                    }
                }

                while ((coordinate2FirstLetter < 0 || coordinate2FirstLetter > 5) && exit == false) 
                {
                    if (coordinate2FirstLetter == 9)
                    {
                        exit = true;
                    } 
                    else 
                    {
                        coordinate2FirstLetter = Entrada.leiaInt(hiddenLettersString + "\nPasse a segunda coordenada da primeira letra que deseja virar\nEntre 9 para sair do jogo.");
                    }
                }

                if (exit == false) 
                {
                    firstLetterRevealed = getFirstLetter(coordinate1FirstLetter, coordinate2FirstLetter, hiddenLetters, matrixOfLetters, letters);
                }

                while ((coordinate1SecondLetter < 0 || coordinate1SecondLetter > 4) && exit == false) 
                {
                    if (coordinate1SecondLetter == 9) 
                    {
                        exit = true;
                    } 
                    else 
                    {
                        coordinate1SecondLetter = Entrada.leiaInt(firstLetterRevealed + "\nPasse a primeira coordenada da segunda letra que deseja virar\nEntre 9 para sair do jogo.");
                    }
                }

                while ((coordinate2SecondLetter < 0 || coordinate2SecondLetter > 5) && exit == false) 
                {
                    if (coordinate2SecondLetter == 9) 
                    {
                        exit = true;
                    } 
                    else 
                    {
                        coordinate2SecondLetter = Entrada.leiaInt(firstLetterRevealed + "\nPasse a segunda coordenada da segunda letra que deseja virar\nEntre 9 para sair do jogo.");
                    }
                }

                if (exit == false) 
                {
                    if ((matrixOfLetters[coordinate1FirstLetter][coordinate2FirstLetter] == matrixOfLetters[coordinate1SecondLetter][coordinate2SecondLetter]) && 
                       ((coordinate1FirstLetter != coordinate1SecondLetter) || (coordinate2FirstLetter != coordinate2SecondLetter))) 
                    {
                        hiddenLetters[coordinate1FirstLetter][coordinate2FirstLetter] = matrixOfLetters[coordinate1FirstLetter][coordinate2FirstLetter];
                        hiddenLetters[coordinate1SecondLetter][coordinate2SecondLetter] = matrixOfLetters[coordinate1SecondLetter][coordinate2SecondLetter];
                        lettersAlreadyRevealed[count2] = matrixOfLetters[coordinate1FirstLetter][coordinate2FirstLetter];
                        count2++;              
                    } 
                    else 
                    {
                        String secondLetterRevealed = getSecondLetter(coordinate1SecondLetter, coordinate2SecondLetter, hiddenLetters, matrixOfLetters, letters);
                        JOptionPane.showMessageDialog(null, secondLetterRevealed);

                        for (int i = 0; i < lettersAlreadyRevealed.length; i++) 
                        {
                            if (lettersAlreadyRevealed[i] == hiddenLetters[coordinate1FirstLetter][coordinate2FirstLetter])
                            {
                                flag1 = true;
                            }
                        }
                        
                        if (flag1 == false)
                        {
                            hiddenLetters[coordinate1FirstLetter][coordinate2FirstLetter] = '*';
                        }

                        for (int i = 0; i < lettersAlreadyRevealed.length; i++) 
                        {
                            if (lettersAlreadyRevealed[i] == hiddenLetters[coordinate1SecondLetter][coordinate2SecondLetter]) 
                            {
                                flag2 = true;
                            }
                        }
                        
                        if (flag2 == false) 
                        {
                            hiddenLetters[coordinate1SecondLetter][coordinate2SecondLetter] = '*';
                        }
                    }
                    
                    fullGameRevealed = true;
                    for (int i = 0; i < hiddenLetters.length; i++) 
                    {
                        for (int j = 0; j < hiddenLetters[0].length; j++)
                        {
                            if (hiddenLetters[i][j] == '*')
                            {
                                fullGameRevealed = false;
                            }
                        }
                    }
                }
                
                if (fullGameRevealed == true) 
                {
                    hiddenLettersString = "";
                    
                    for (int i = 0; i < hiddenLetters.length; i++) 
                    {
                        for (int j = 0; j < hiddenLetters[0].length; j++) 
                        {
                            hiddenLettersString += (String.valueOf(hiddenLetters[i][j]) + "    ");  // Convert the hiddenLetters into a String (to show on JOptionPane)
                        }
                        hiddenLettersString += "\n";
                    }
                    JOptionPane.showMessageDialog(null, hiddenLettersString);
                }
            }
            
            playAgain = ' ';
            while (playAgain != 's' && playAgain != 'n') 
            {
                playAgain = Character.toLowerCase(Entrada.leiaChar("Você quer jogar de novo? [S/N]"));
            }
        }
        System.exit(0);
    }

    public static String getFirstLetter(int coordinate1FirstLetter, int coordinate2FirstLetter, char hiddenLetters[][], char matrixOfLetters[][], char letters[]) 
    {
        String hiddenLettersString = "";
        hiddenLetters[coordinate1FirstLetter][coordinate2FirstLetter] = matrixOfLetters[coordinate1FirstLetter][coordinate2FirstLetter];
        
        for (int i = 0; i < hiddenLetters.length; i++) 
        {
            for (int j = 0; j < hiddenLetters[0].length; j++) 
            {
                hiddenLettersString += (String.valueOf(hiddenLetters[i][j]) + "    ");  // Convert the hiddenLetters into a String (to return a String))
            }
            hiddenLettersString += "\n";
        }  
        return hiddenLettersString;
    }

    public static String getSecondLetter(int coordinate1SecondLetter, int coordinate2SecondLetter, char hiddenLetters[][], char matrixOfLetters[][], char letters[]) 
    {
        String hiddenLettersString = "";
        hiddenLetters[coordinate1SecondLetter][coordinate2SecondLetter] = matrixOfLetters[coordinate1SecondLetter][coordinate2SecondLetter];
        
        for (int i = 0; i < hiddenLetters.length; i++) 
        {
            for (int j = 0; j < hiddenLetters[0].length; j++)
            {
                hiddenLettersString += hiddenLetters[i][j] + "    ";
            }
            hiddenLettersString += "\n";
        }
        return hiddenLettersString;
    }
}
