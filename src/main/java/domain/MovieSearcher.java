package domain;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

public class MovieSearcher {

    public void search(String queryTerm) throws IOException {
        IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get("./index")));
        IndexSearcher searcher = new IndexSearcher(reader);

        TermQuery termQuery = new TermQuery(new Term("title", queryTerm));
        TermQuery termQuery2 = new TermQuery(new Term("titleEn", queryTerm));

        BooleanQuery query = new BooleanQuery
                .Builder()
                .add(new BooleanClause(termQuery, BooleanClause.Occur.SHOULD))
                .add(new BooleanClause(termQuery2, BooleanClause.Occur.SHOULD))
                .build();

        int hitsPerPage = 10;

        TopDocs docs = searcher.search(query, hitsPerPage);
        ScoreDoc[] hits = docs.scoreDocs;

        System.out.println("총 " + hits.length + "개가 일치합니다");
        for(int i=0;i<hits.length;++i) {
            int docId = hits[i].doc;
            Document d = searcher.doc(docId);
            System.out.println((i + 1) + ". " +"key:"+ d.get("key")
                    + "\ttitle:" + d.get("title")  + "\ttitleEn:" + d.get("titleEn") + "\treleaseYear:" + d.get("releaseYear")
                    + "\tcountry:" + d.get("country")+ "\truntime:" + d.get("runtime") + "\tgenre:" + d.get("genre")
                    + "\trelease:" + d.get("release")+ "\tdirector:" + d.get("director")+ "\tproduction:" + d.get("production"));
        }

        reader.close();

    }
}
