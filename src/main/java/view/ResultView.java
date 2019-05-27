package view;

import config.LuceneConfig;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;


public class ResultView {

    public static void printResult(TopDocs topDocs) {
        long totalHits = topDocs.totalHits.value;
        System.out.println("총 " + totalHits + "개가 일치합니다");

        String result="";

        try(IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(LuceneConfig.INDEX_PATH)))) {
            IndexSearcher searcher = new IndexSearcher(reader);
             result = Arrays.stream(topDocs.scoreDocs)
                    .map(i -> i.doc)
                    .map(docId -> getDocument(docId, searcher).toString())
                    .collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(result);
    }

    private static Document getDocument(int docId, IndexSearcher searcher) {
        Document resultDocument = null;
        try {
            resultDocument = searcher.doc(docId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  resultDocument;
    }
}
