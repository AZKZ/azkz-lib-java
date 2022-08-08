package com.azkz.lib.csv.replaceable;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ReplaceableCsvFileTest {

    @Test
    void outputCsvFile() {
        ReplaceableCsvFile replaceableCsvFile = new ReplaceableCsvFile(new File("samples/input.csv"));
        replaceableCsvFile.replace(2);
        replaceableCsvFile.outputCsvFile(new File("samples/output.csv"));
    }
}