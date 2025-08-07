
class ValidadorSudoku {
    private static final int TAMANHO = 9;

    public boolean jogadaValida(int[][] tabuleiro, int linha, int coluna, int numero) {
        return validarLinha(tabuleiro, linha, numero) &&
               validarColuna(tabuleiro, coluna, numero) &&
               validarSetor3x3(tabuleiro, linha, coluna, numero);
    }

    private boolean validarLinha(int[][] tabuleiro, int linha, int numero) {
        for (int coluna = 0; coluna < TAMANHO; coluna++) {
            if (tabuleiro[linha][coluna] == numero) {
                return false;
            }
        }
        return true;
    }

    private boolean validarColuna(int[][] tabuleiro, int coluna, int numero) {
        for (int linha = 0; linha < TAMANHO; linha++) {
            if (tabuleiro[linha][coluna] == numero) {
                return false;
            }
        }
        return true;
    }

    private boolean validarSetor3x3(int[][] tabuleiro, int linha, int coluna, int numero) {
        int inicioLinha = (linha / 3) * 3;
        int inicioColuna = (coluna / 3) * 3;

        for (int i = inicioLinha; i < inicioLinha + 3; i++) {
            for (int j = inicioColuna; j < inicioColuna + 3; j++) {
                if (tabuleiro[i][j] == numero) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean sudokuCompleto(int[][] tabuleiro) {
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                if (tabuleiro[i][j] == 0) {
                    return false;
                }
            }
        }
        return sudokuValido(tabuleiro);
    }

    public boolean sudokuValido(int[][] tabuleiro) {
        // Verificar todas as linhas
        for (int linha = 0; linha < TAMANHO; linha++) {
            if (!conjuntoValido(obterLinha(tabuleiro, linha))) {
                return false;
            }
        }

        // Verificar todas as colunas
        for (int coluna = 0; coluna < TAMANHO; coluna++) {
            if (!conjuntoValido(obterColuna(tabuleiro, coluna))) {
                return false;
            }
        }

        // Verificar todos os setores 3x3
        for (int linha = 0; linha < TAMANHO; linha += 3) {
            for (int coluna = 0; coluna < TAMANHO; coluna += 3) {
                if (!conjuntoValido(obterSetor3x3(tabuleiro, linha, coluna))) {
                    return false;
                }
            }
        }

        return true;
    }

    private int[] obterLinha(int[][] tabuleiro, int linha) {
        return tabuleiro[linha].clone();
    }

    private int[] obterColuna(int[][] tabuleiro, int coluna) {
        int[] colunaArray = new int[TAMANHO];
        for (int i = 0; i < TAMANHO; i++) {
            colunaArray[i] = tabuleiro[i][coluna];
        }
        return colunaArray;
    }

    private int[] obterSetor3x3(int[][] tabuleiro, int inicioLinha, int inicioColuna) {
        int[] setor = new int[TAMANHO];
        int index = 0;
        
        for (int i = inicioLinha; i < inicioLinha + 3; i++) {
            for (int j = inicioColuna; j < inicioColuna + 3; j++) {
                setor[index++] = tabuleiro[i][j];
            }
        }
        
        return setor;
    }

    private boolean conjuntoValido(int[] conjunto) {
        boolean[] usado = new boolean[10]; // índices 1-9
        
        for (int numero : conjunto) {
            if (numero != 0) { // Ignorar células vazias
                if (numero < 1 || numero > 9 || usado[numero]) {
                    return false;
                }
                usado[numero] = true;
            }
        }
        
        return true;
    }
}
