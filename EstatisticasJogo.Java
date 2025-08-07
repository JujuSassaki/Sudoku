
class EstatisticasJogo {
    private int jogosJogados;
    private int jogosVencidos;
    private long tempoTotalJogo;
    private long tempoInicioJogo;
    
    public EstatisticasJogo() {
        this.jogosJogados = 0;
        this.jogosVencidos = 0;
        this.tempoTotalJogo = 0;
    }
    
    public void iniciarCronometro() {
        tempoInicioJogo = System.currentTimeMillis();
    }
    
    public void registrarJogoIniciado() {
        jogosJogados++;
    }
    
    public void registrarVitoria() {
        jogosVencidos++;
        long tempoJogo = System.currentTimeMillis() - tempoInicioJogo;
        tempoTotalJogo += tempoJogo;
    }
    
    public void exibirEstatisticas() {
        System.out.println("\n=== ESTATÍSTICAS ===");
        System.out.println("Jogos jogados: " + jogosJogados);
        System.out.println("Jogos vencidos: " + jogosVencidos);
        
        if (jogosJogados > 0) {
            double percentualVitoria = ((double) jogosVencidos / jogosJogados) * 100;
            System.out.printf("Taxa de vitória: %.1f%%\n", percentualVitoria);
        }
        
        if (jogosVencidos > 0) {
            long tempoMedio = tempoTotalJogo / jogosVencidos;
            System.out.printf("Tempo médio por vitória: %d min %d seg\n", 
                tempoMedio / 60000, (tempoMedio % 60000) / 1000);
        }
    }
    
    // Getters
    public int getJogosJogados() { return jogosJogados; }
    public int getJogosVencidos() { return jogosVencidos; }
    public long getTempoTotalJogo() { return tempoTotalJogo; }