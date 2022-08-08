package com.azkz.lib.csv.replaceable;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class ReplaceableCsvFile implements Iterable<ReplaceableCsvRecord> {

    /**
     * レコード
     */
    private List<ReplaceableCsvRecord> records = new ArrayList<>();

    /**
     * ファイル
     */
    private File csvFile;

    /**
     * ヘッダー
     */
    private String header;

    public ReplaceableCsvFile(File csvFile) {
        this.csvFile = csvFile;
        try (
                CSVReader csvReader = new CSVReader(new FileReader(this.csvFile));
                BufferedReader bufferedReader = new BufferedReader(new FileReader(this.csvFile))
        ) {
            // ヘッダーをスキップ
            csvReader.skip(1);
            this.header = bufferedReader.readLine();

            String textLine;
            List<String> separatedLine;
            while ((textLine = bufferedReader.readLine()) != null) {
                separatedLine = Arrays.asList(csvReader.readNext());
                this.records.add(new ReplaceableCsvRecord(textLine, separatedLine));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
    }

    public void replace(int targetIndex) {
        for (ReplaceableCsvRecord record : this) {
            record.replace(targetIndex);
        }
    }

    public void outputCsvFile(File outputFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(this.header);
            for(ReplaceableCsvRecord record:this) {
                writer.newLine();
                writer.write(record.getTextLineReplaced());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<ReplaceableCsvRecord> iterator() {
        return this.records.iterator();
    }

}
