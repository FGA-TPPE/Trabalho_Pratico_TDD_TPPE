package com.app;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class WriterTest {
    @Test
    public void testWritableOutputPath(){
        // Arrange
        Writer writer = new Writer();
        boolean permicao = false;
        String messageError = null;
        String currentPath = Path.of("").toAbsolutePath().toString();

        // Act
        try {
            writer.setOutputPath(currentPath);
            permicao = writer.pathAllowWrite(writer.getOutputPath());
        } catch (Exception ex){
            permicao = false;
            messageError = ex.getMessage();
        }

        // Assert
        assertEquals(true, permicao);
        assertNull(messageError);
    }

    @Test
    public void testNotWritableOutputPath(){
        // Arrange
        Writer writer = new Writer();
        boolean permicao = false;
        String messageError = null;
        String currentPath = Path.of("").toAbsolutePath().toString() + Path.of("/output/random");

        // Act
        try {
            writer.setOutputPath(currentPath);
            permicao = writer.pathAllowWrite(writer.getOutputPath());
        } catch (Exception ex){
            permicao = false;
            messageError = ex.getMessage();
        }

        // Assert
        assertEquals(false, permicao);
        assertEquals("Não há permição de escrita no caminho de destino!", messageError);
    }
}