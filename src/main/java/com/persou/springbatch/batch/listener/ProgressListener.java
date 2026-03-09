package com.persou.springbatch.batch.listener;

import com.persou.springbatch.dto.Employee;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

/**
 * Listener que monitora o progresso do processamento batch.
 * <p>
 * Combina dois listeners:
 * - StepExecutionListener: captura início/fim do step (para saber totais)
 * - ItemWriteListener: captura cada write (para contar progresso)
 */
public class ProgressListener implements ItemWriteListener<Employee>, StepExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(ProgressListener.class);

    private int totalProcessed = 0;
    private long startTime;

    // ========== StepExecutionListener ==========

    /**
     * Chamado ANTES do step começar.
     * Aqui inicializamos contadores e capturamos o tempo de início.
     */
    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.totalProcessed = 0;
        this.startTime = System.currentTimeMillis();
        log.info("🚀 Iniciando processamento de funcionários...");
    }

    /**
     * Chamado DEPOIS do step terminar (sucesso ou falha).
     * Aqui você pode enviar notificação final ao cliente.
     *
     * @return
     */
    public ExitStatus afterStep(StepExecution stepExecution) {
        long duration = System.currentTimeMillis() - startTime;

        long readCount = stepExecution.getReadCount();
        long writeCount = stepExecution.getWriteCount();
        long skipCount = stepExecution.getSkipCount();

        log.info("✅ Processamento finalizado!");
        log.info("   📊 Lidos: {}", readCount);
        log.info("   💾 Escritos: {}", writeCount);
        log.info("   ⚠️  Ignorados: {}", skipCount);
        log.info("   ⏱️  Tempo: {} ms", duration);
        log.info("   📈 Status: {}", stepExecution.getExitStatus());

        // 🔔 AQUI você enviaria email/notificação para o cliente
        // notificationService.sendCompletionEmail(writeCount, duration);

        return stepExecution.getExitStatus();
    }

    // ========== ItemWriteListener ==========

    /**
     * Chamado DEPOIS de cada chunk ser escrito e commitado.
     * Aqui você atualiza o progresso em tempo real.
     */
    public void afterWrite(List<? extends Employee> items) {
        totalProcessed += items.size();

        // Log simples
        log.info("📝 Chunk processado: {} funcionários | Total acumulado: {}",
            items.size(), totalProcessed);

        // 🎯 AQUI você poderia:
        // 1. Atualizar barra de progresso em tempo real
        // progressService.updateProgress(totalProcessed);

        // 2. Enviar notificação a cada X registros
        // if (totalProcessed % 1000 == 0) {
        //     notificationService.sendProgressUpdate(totalProcessed);
        // }

        // 3. Salvar checkpoint no banco
        // checkpointRepository.save(new Checkpoint(jobId, totalProcessed));
    }

    /**
     * Chamado ANTES de escrever um chunk.
     * Útil se você quiser fazer validações antes de commitar.
     */
    public void beforeWrite(List<? extends Employee> items) {
        // Você pode fazer validações aqui antes do write
        // Ex: verificar se tem duplicatas, validar regras de negócio, etc.
    }

    /**
     * Chamado quando acontece erro durante o write.
     * Aqui você pode logar erros específicos ou tentar recovery.
     */
    public void onWriteError(Exception exception, List<? extends Employee> items) {
        log.error("❌ Erro ao escrever chunk de {} itens: {}",
            items.size(), exception.getMessage());

        // 🔔 AQUI você notificaria o cliente sobre a falha
        // notificationService.sendErrorAlert(exception, totalProcessed);
    }
}